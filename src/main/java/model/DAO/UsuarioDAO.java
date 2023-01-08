package model.DAO;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

    public boolean encontrouEmail(String email){
        String SQL = "SELECT email FROM usuario WHERE email = :email limit 1";
        Query query = em.createNativeQuery(SQL).setParameter("email", email);
        return query.getResultList().size() > 0;
    }

}
