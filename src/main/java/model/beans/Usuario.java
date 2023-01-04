package model.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="usuario")
@Getter @EqualsAndHashCode @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome, telefone, cep; 
    @Column(unique = true)
    private String email;
    @Column(name = "hash_senha")
    private String hashSenha;

    public Usuario(String nome, String telefone, String cep, String email, String hashSenha) {
        this.nome = nome;
        this.telefone = telefone;
        this.cep = cep;
        this.email = email;
        this.hashSenha = hashSenha;
    }

}
