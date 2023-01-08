package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.EmailJaCadastradoException;
import model.DAO.UsuarioDAO;
import model.beans.Usuario;
import util.Criptografia;

@WebServlet(urlPatterns = {"/cadastro"})
public class CadastroController extends HttpServlet {

    private final Criptografia cripto;

    public CadastroController(){
        super();
        cripto = new Criptografia();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            cadastrar(request);
            response.sendRedirect("login.html");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (EmailJaCadastradoException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        } 
    }

    private void cadastrar(HttpServletRequest request) throws EmailJaCadastradoException, IOException{
        UsuarioDAO dao = null;
        try {
            Usuario usuario = criarUsuario(request);
            dao = new UsuarioDAO();
            if(emailJaCadastrado(usuario.getEmail(), dao)){
                throw new EmailJaCadastradoException();
            }  
            dao.salvar(usuario);
            dao.liberarRecurso();
            
        }
        catch(SQLException e){
            if(dao != null) dao.liberarRecurso();
            e.printStackTrace();
        }
    }

    private Usuario criarUsuario(HttpServletRequest request) throws IOException{
        Usuario usuario = Usuario.builder()
            .nome(request.getParameter("nome"))
            .telefone(request.getParameter("telefone"))
            .cep(request.getParameter("cep"))
            .numeroEndereco(Short.parseShort(request.getParameter("numero")))
            .email(request.getParameter("email"))
            .senha(request.getParameter("senha"))
            .build();

        criptografarSenha(usuario);
        return usuario;
    }

    private void criptografarSenha(Usuario usuario) throws IOException{
        usuario.setSenha(cripto.gerarHash(usuario.getSenha(), usuario.getEmail()));
    }

    private boolean emailJaCadastrado(String email, UsuarioDAO dao){
        return dao.encontrouEmail(email);
    }
}