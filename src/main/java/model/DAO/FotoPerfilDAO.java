package model.DAO;

import java.io.InputStream;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.io.IOException;

import model.beans.FotoPerfil;
import model.beans.Usuario;

public class FotoPerfilDAO extends DAO<FotoPerfil>{

    public void alterarFotoDePerfil(long id, byte[] bytesDaFoto){
        FotoPerfil fotoPerfil = em.find(FotoPerfil.class, id);
        if(fotoPerfil == null){
            Usuario usuario = em.find(Usuario.class, id);
            fotoPerfil = new FotoPerfil(usuario, bytesDaFoto);
        }
        else{
            fotoPerfil.setFoto(bytesDaFoto);
        }
        em.getTransaction().begin();
        em.persist(fotoPerfil);
        em.getTransaction().commit();
    }

    public void alterarFotoDePerfil(long id, InputStream streamDaFoto) throws IOException{
        alterarFotoDePerfil(id, streamDaFoto.readAllBytes());
    }

    public byte[] buscarBytesDaFoto(long id){
        TypedQuery<byte[]> query = em
                    .createNamedQuery("fotoPerfil.buscarFotoDePerfil", byte[].class)
                    .setParameter("id", id);
        try{
            return query.getSingleResult();
        }
        catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FotoPerfil buscar(Object primaryKey){
        return em.find(FotoPerfil.class, primaryKey);
    }
    
}
