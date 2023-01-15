package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.EmailNaoCadastradoException;
import model.DAO.UsuarioDAO;


@WebServlet(urlPatterns = {"/login"})
public class LoginController extends ServletController{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String senhaInformada = request.getParameter("senha");
        dao = new UsuarioDAO();
        try {
            String senhaCadastrada = dao.buscarSenhaPeloEmail(email);
            PrintWriter out = response.getWriter();
            if(cripto.confirmarSenha(senhaInformada, email, senhaCadastrada)){
                out.println("Correto");
            }
            else{
                out.println("Incorreto");
            }
        } catch (EmailNaoCadastradoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
