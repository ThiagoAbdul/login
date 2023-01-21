package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet(urlPatterns = {"/trocarFoto"})
public class FotoPerfilController extends ServletController{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        InputStream streamPart = null;
        try {
            streamPart = request.getPart("imagem").getInputStream();
            long idUsuario = Long.parseLong(request.getParameter("idUsuario"));
            dao.alterarFotoDePerfil(idUsuario, streamPart);
            streamPart.close();
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            if(streamPart != null)
                try {
                    streamPart.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
        }
        finally{
            fecharConexaoComBanco();
        }
    }
    
}
