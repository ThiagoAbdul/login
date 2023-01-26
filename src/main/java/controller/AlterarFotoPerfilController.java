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
        Usuario usuario = null;
        try (InputStream streamDaImagem = getStreamDaImagem(request)){
            usuario = getUsuarioPelaRequest(request);
            trocarFoto(streamDaImagem, usuario);
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } catch (IOException | ServletException e) {
            e.printStackTrace();
            response.setStatus(500);
            if(usuario != null){
                request.setAttribute("usuario", usuario);
                try {
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                } 
                catch (ServletException | IOException e1) {   
                    e1.printStackTrace();
                }
            }
            else{
                try {
                    response.sendRedirect("erro.html");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            
        }
        finally{
            fecharConexaoComBanco();
        }
    }

    private Usuario getUsuarioPelaRequest(HttpServletRequest request){
        long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscar(idUsuario);
    }

    private void trocarFoto(InputStream streamDaImagem, Usuario usuario) throws IOException{
        fotoPerfilDAO = new FotoPerfilDAO();
        fotoPerfilDAO.alterarFotoDePerfil(usuario.getId(), streamDaImagem);
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
