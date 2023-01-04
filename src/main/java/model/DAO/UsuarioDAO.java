package model.DAO;

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

    public boolean salvar(Usuario usuario){
        try{
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Erro ao salvar o usuário");
            return false;
        }
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

    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = Usuario.builder()
                            .cep("05054030")
                            .email("thiagoabdul10@gmail.com")
                            .nome("Thiago abdul")
                            .telefone("11983368192")
                            .hashSenha("dldspdkopsw7r7we7w454w8dw8dr8wd")
                            .build();
        dao.salvar(usuario);
    }

}
