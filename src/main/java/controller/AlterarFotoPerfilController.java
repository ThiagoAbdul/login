package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.FotoPerfilDAO;
import model.DAO.UsuarioDAO;
import model.beans.Usuario;

@MultipartConfig
@WebServlet(urlPatterns = {"/trocarFoto"})
public class AlterarFotoPerfilController extends ServletController{

    private FotoPerfilDAO fotoPerfilDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        
        try (BufferedInputStream streamPart = getStreamDaImagem(request)){
            long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
            fotoPerfilDAO = new FotoPerfilDAO();
            fotoPerfilDAO.alterarFotoDePerfil(idUsuario, streamPart);
            usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.getEm().find(Usuario.class, idUsuario);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        finally{
            fecharConexaoComBanco();
        }
    }

    private BufferedInputStream getStreamDaImagem(HttpServletRequest request) 
                                                throws IOException, ServletException{
        InputStream streamDaImagem = request.getPart("imagem").getInputStream();
        return new BufferedInputStream(streamDaImagem);
    }

    @Override
    protected void fecharConexaoComBanco() {
        super.fecharConexaoComBanco();
        if(fotoPerfilDAO != null){
            fotoPerfilDAO.liberarRecurso();
        }
    }
    
}
