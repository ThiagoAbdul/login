package model.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedQueries({
    @NamedQuery(name = "fotoPerfil.buscarFotoDePerfil",
                query = "select foto from foto_perfil f where idUsuario = :id"
    )
})
@Entity(name = "foto_perfil")
@Getter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class FotoPerfil implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_usuario")
    private long idUsuario;
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @Lob
    @Setter
    private byte[] foto;
    
    public FotoPerfil(Usuario usuario, byte[] foto) {
        this.usuario = usuario;
        this.foto = foto;
    }


    
}
