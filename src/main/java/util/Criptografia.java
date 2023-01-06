package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Criptografia {

    private final MessageDigest encoder;

    public Criptografia(){
        try {
            encoder = MessageDigest.getInstance("sha256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public String gerarHash(String senhaPlana, String email) throws IOException{
        String senhaComSalts = senhaPlana.concat(email).concat(getSalt());
        byte[] hash = encoder.digest(senhaComSalts.getBytes());
        StringBuilder sb = new StringBuilder();
        for(byte caracter : hash) sb.append(String.format("%02x", 0xFF & caracter));
        return sb.toString();
    }

    private static String getSalt() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("/home/abdul/Documentos/estudos/java/projects-workspace/login/src/main/resources/arquivos/.salt.txt"))){
            return br.readLine();
        }
    }

    public boolean confirmarSenha(String senha, String email, String hash) throws IOException{
        return gerarHash(senha, email).equals(hash);
    }

}
