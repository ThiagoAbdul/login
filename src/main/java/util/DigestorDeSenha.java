package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DigestorDeSenha implements Hash{

    private final MessageDigest encoder;

    public DigestorDeSenha(){
        try {
            encoder = MessageDigest.getInstance("sha512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public String gerarHash(String senhaPlana) throws IOException{
        String senhaComSalts = senhaPlana.concat(getSalt());
        byte[] hash = encoder.digest(senhaComSalts.getBytes());
        StringBuilder sb = new StringBuilder();
        for(byte caracter : hash) sb.append(String.format("%02x", 0xFF & caracter));
        return sb.toString();
    }

    private static String getSalt() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("../webapps/arquivos/.salt"))){
            return br.readLine();
        }
    }


}
