package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.FotoPerfilDAO;

@WebServlet(urlPatterns = {"/pegarFoto"})
public class FotoPerfilController extends ServletController{

    private FotoPerfilDAO fotoPerfilDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        
    }

    private void enviarFotoPerfil(HttpServletRequest request, HttpServletRequest response){
        
    }

}
