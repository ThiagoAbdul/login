package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        UsuarioDAO dao = null;
        try (PrintWriter out = response.getWriter()) {
            Usuario usuario = criarUsuario(request);
            dao = new UsuarioDAO();
            if(emailJaCadastrado(usuario.getEmail(), dao)){
                throw new EmailJaCadastradoException();
            }  
            dao.salvar(usuario);
            dao.liberarRecurso();
            response.sendRedirect("login.html");

        } catch (IOException | SQLException | EmailJaCadastradoException e) {
            if(dao != null) dao.liberarRecurso();
            try {
                response.sendRedirect("cadastrar.html");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        
    }

    private Usuario criarUsuario(HttpServletRequest request) throws IOException{
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String cep = request.getParameter("cep");
        short numero = Short.parseShort(request.getParameter("numero"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
    
        Usuario usuario = Usuario.builder()
            .nome(nome)
            .telefone(telefone)
            .cep(cep)
            .numeroEndereco(numero)
            .email(email)
            .senha(senha)
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