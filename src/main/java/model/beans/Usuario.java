package model.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedQueries({
    @NamedQuery(name = "usuario.buscarUsuarioPeloEmail",
                query = "select u from usuario u where u.email = :email"    
    )
})
@Entity(name="usuario")
@Table(name = "usuario")
@Getter @EqualsAndHashCode @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome, telefone, cep; 
    @Column(name = "numero_endereco")
    private short numeroEndereco;
    @Column(unique = true)
    private String email;
    @Column(name = "hash_senha")
    @Setter
    private String senha;
    @Column(name = "tem_foto")
    @Builder.Default
    private boolean temFoto = false;

    public Usuario(String nome, String telefone, String cep, short numeroEndereco, String email, String senha) {
        this.nome = nome;
        this.telefone = telefone;
        this.cep = cep;
        this.numeroEndereco = numeroEndereco;
        this.email = email;
        this.senha = senha;
    }


}
