package controller;

import javax.servlet.http.HttpServlet;

import model.DAO.UsuarioDAO;
import util.Criptografia;

public abstract class ServletController extends HttpServlet{

    protected Criptografia cripto;
    protected UsuarioDAO dao;

    public ServletController(){
        cripto = new Criptografia();
        dao = new UsuarioDAO();
    }
    
}
