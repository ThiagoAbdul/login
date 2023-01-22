package controller;

import javax.servlet.http.HttpServlet;

import model.DAO.UsuarioDAO;
import util.DigestorDeSenha;
import util.Hash;


public abstract class ServletController extends HttpServlet{

    protected Hash hash;
    protected UsuarioDAO usuarioDAO;

    public ServletController(){
        hash = new DigestorDeSenha();
    }

    protected void fecharConexaoComBanco(){
        if(usuarioDAO != null){
            usuarioDAO.liberarRecurso();
        }
    }
    
}
