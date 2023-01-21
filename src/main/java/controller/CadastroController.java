package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.EmailJaCadastradoException;
import model.DAO.UsuarioDAO;
import model.beans.Usuario;
import util.CredencialUsuario;

@WebServlet(urlPatterns = {"/cadastro"})
public class CadastroController extends ServletController {

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
        finally{
            fecharConexaoComBanco();
        } 
    }

    private void cadastrar(HttpServletRequest request) throws EmailJaCadastradoException, IOException, SQLException{
            Usuario usuario = criarUsuario(request);
            if(emailJaCadastrado(usuario.getEmail())){
                throw new EmailJaCadastradoException();
            }  
            dao.salvar(usuario);
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
        CredencialUsuario credencial = CredencialUsuario.builder()
                                .email(usuario.getEmail())
                                .senha(usuario.getSenha())
                                .hash(this.hash)
                                .build();
        usuario.setSenha(credencial.gerarHashDeSenha());
    }

    private boolean emailJaCadastrado(String email){
        return this.dao.encontrouEmail(email);
    }
}