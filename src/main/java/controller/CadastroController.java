package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/cadastro"})
public class CadastroController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        String nome = request.getParameter("nome");
        System.out.println(nome.toUpperCase());
        try (PrintWriter out = response.getWriter()) {
            out.println(nome.toLowerCase());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}