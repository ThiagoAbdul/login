package controller;

import javax.servlet.http.HttpServlet;

import model.DAO.UsuarioDAO;
import util.DigestorDeSenha;
import util.Hash;


public abstract class ServletController extends HttpServlet{

    protected Hash hash;

    public ServletController(){
        hash = new DigestorDeSenha();
    }

    protected void fecharConexaoComBanco(UsuarioDAO dao){
        if(dao != null){
            dao.liberarRecurso();
        }
    }
    
}
