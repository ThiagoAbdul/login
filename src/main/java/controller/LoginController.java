package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.EmailNaoCadastradoException;
import model.DAO.UsuarioDAO;
import model.beans.Usuario;

import util.CredencialUsuario;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends ServletController{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            usuarioDAO = new UsuarioDAO();
            logar(request, response);
        } catch (EmailNaoCadastradoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            fecharConexaoComBanco();
        }
    }

    private void logar(HttpServletRequest request, HttpServletResponse response) 
                                            throws EmailNaoCadastradoException, 
                                            IOException, ServletException{
        
        CredencialUsuario credencial = credencialDaRequest(request);
        Usuario usuarioBuscado = usuarioDAO.buscarUsuarioPeloEmail(credencial.getEmail());
        if(credencial.confereHashDeSenha(usuarioBuscado.getSenha())){
            request.setAttribute("usuario", usuarioBuscado);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
       
    }

     private CredencialUsuario credencialDaRequest(HttpServletRequest request) throws IOException{
        return CredencialUsuario.builder()
                                .email(request.getParameter("email")) 
                                .senha(request.getParameter("senha"))
                                .hash(this.hash)
                                .build();
     }

}
