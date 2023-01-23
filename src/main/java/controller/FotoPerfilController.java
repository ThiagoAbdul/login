package controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.FotoPerfilDAO;

@WebServlet(urlPatterns = {"/pegarFoto"})
public class FotoPerfilController extends ServletController{

    private FotoPerfilDAO fotoPerfilDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        this.fotoPerfilDAO = new FotoPerfilDAO();
        try{
            enviarFotoPerfil(request, response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            fecharConexaoComBanco();
        }
    }

    private void enviarFotoPerfil(HttpServletRequest request, HttpServletResponse response) {
        try (OutputStream os = response.getOutputStream()) {
            response.setContentType("image/gif");
            long idUsuario = Long.parseLong(request.getParameter("id"));
            os.write(fotoPerfilDAO.buscarBytesDaFoto(idUsuario));
            os.flush();
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    protected void fecharConexaoComBanco() {
        super.fecharConexaoComBanco();
        if(fotoPerfilDAO != null){
            fotoPerfilDAO.liberarRecurso();
        }
    }

}
