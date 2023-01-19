package model.DAO;

import java.io.IOException;
import java.io.InputStream;
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

    public void atualizarUsuario(Usuario usuario){
        if(usuario.getId() < 1){
            throw new RuntimeException("Usuário sem identificação");
        }
        em.getTransaction();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    public void alterarFotoDePerfil(long id, byte[] bytesDaFoto){
        Usuario usuario = em.find(Usuario.class, id);
        usuario.setFotoDePerfil(bytesDaFoto);
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public void alterarFotoDePerfil(long id, InputStream streamDaFoto) throws IOException{
        alterarFotoDePerfil(id, streamDaFoto.readAllBytes());
    }

    public byte[] buscarBlobDaFotoDePerfil(long id){
        TypedQuery<byte[]> query = em
                    .createNamedQuery("usuario.buscarFotoDePerfil", byte[].class)
                    .setParameter("id", id);
        try{
            return query.getSingleResult();
        }
        catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
    }

}
