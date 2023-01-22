package model.DAO;

import java.sql.SQLException;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import exceptions.EmailNaoCadastradoException;
import model.beans.Usuario;

public class UsuarioDAO extends DAO{

    public void salvar(Usuario usuario) throws SQLException{
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
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

    public void atualizarUsuario(Usuario usuario){
        if(usuario.getId() < 1){
            throw new RuntimeException("Usuário sem identificação");
        }
        em.getTransaction();
        em.merge(usuario);
        em.getTransaction().commit();
    }

}
