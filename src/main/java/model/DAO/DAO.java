package model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAO {
    protected EntityManagerFactory emf;
    protected EntityManager em;

    public DAO(){
        super();
        emf = Persistence.createEntityManagerFactory("dblogin");
        em = emf.createEntityManager();
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
