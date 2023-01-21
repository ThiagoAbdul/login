package util;

import java.io.IOException;

public interface Hash {
    
    public abstract String gerarHash(String senhaPlana) throws IOException;

}
