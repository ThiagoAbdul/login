package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.EmailNaoCadastradoException;
import model.DAO.UsuarioDAO;
import model.beans.Usuario;


@WebServlet(urlPatterns = {"/login"})
public class LoginController extends ServletController{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String senhaInformada = request.getParameter("senha");
        dao = new UsuarioDAO();
        try {
            Usuario usuario = dao.buscarUsuarioPeloEmail(email);
            dao.liberarRecurso();
            PrintWriter out = response.getWriter();
            if(cripto.confirmarSenha(senhaInformada, email, usuario.getSenha())){
                request.setAttribute("usuario", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
                rd.forward(request, response);
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
        } catch (ServletException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
