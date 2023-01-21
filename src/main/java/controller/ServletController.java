package controller;

import javax.servlet.http.HttpServlet;

import model.DAO.UsuarioDAO;
import util.DigestorDeSenha;
import util.Hash;


public abstract class ServletController extends HttpServlet{

    protected Hash hash;
    protected UsuarioDAO dao;

    public ServletController(){
        try{
            hash = new DigestorDeSenha();
            dao = new UsuarioDAO();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void fecharConexaoComBanco(){
        if(dao != null){
            dao.liberarRecurso();
        }
    }
    
}
