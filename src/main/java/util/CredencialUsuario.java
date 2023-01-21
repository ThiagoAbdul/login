package util;

import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode @AllArgsConstructor @Builder
public class CredencialUsuario {

    private String email, senha;
    private Hash hash;

    public boolean confereHashDeSenha(String hashSenhaInformado) throws IOException{
        return gerarHashDeSenha().equals(hashSenhaInformado);
    }

    public String gerarHashDeSenha() throws IOException{
        return this.hash.gerarHash(this.senha.concat(this.email));
    }

}
