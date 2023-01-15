package model.DAO;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import exceptions.EmailNaoCadastradoException;
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

    @SuppressWarnings("unchecked")
    public Usuario buscarUsuarioPeloEmail(String email) throws EmailNaoCadastradoException{
        
        TypedQuery<Usuario> query = (TypedQuery<Usuario>) em
                                        .createNamedQuery("usuario.buscarUsuarioPeloEmail")
                                        .setParameter("email", email);
        try{
            return query.getSingleResult();
        }
        catch(NoResultException e){
            throw new EmailNaoCadastradoException();
        }
        
    }


}
