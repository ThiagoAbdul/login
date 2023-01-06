package model.DAO;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.beans.Usuario;

public class UsuarioDAO {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public UsuarioDAO(){
        emf = Persistence.createEntityManagerFactory("dblogin");
        em = emf.createEntityManager();
    }

    public void salvar(Usuario usuario) throws SQLException{
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
    }

    public void liberarRecurso(){
        if(em != null){
            em.close();
            em = null;
        }
        if(emf != null){
            emf.close();
            emf = null;
        }
    }

}
