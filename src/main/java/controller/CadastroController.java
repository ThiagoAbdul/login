package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.UsuarioDAO;
import model.beans.Usuario;
import util.Criptografia;

@WebServlet(urlPatterns = {"/cadastro"})
public class CadastroController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        try (PrintWriter out = response.getWriter()) {
            Usuario usuario = criarUsuario(request);
            UsuarioDAO dao = new UsuarioDAO();
            dao.salvar(usuario);
            dao.liberarRecurso();
            response.sendRedirect("login.html");

        } catch (IOException e) {
            e.printStackTrace();

        }
        
    }

    private Usuario criarUsuario(HttpServletRequest request){
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String cep = request.getParameter("cep");
        short numero = Short.parseShort(request.getParameter("numero"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        try {
            String hashSenha = Criptografia.gerarHash(senha, email);

            return Usuario.builder()
            .nome(nome)
            .telefone(telefone)
            .cep(cep)
            .numeroEndereco(numero)
            .email(email)
            .hashSenha(hashSenha)
            .build();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException("Deu ruim");
        }
        
        
    }


}