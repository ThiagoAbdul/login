package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Criptografia {

    private static final int HASH_SIZE = 40;

    private static final float MOD = 39;

    public static String gerarHash(String senhaPlana, String email) throws IOException{
        double semente = email.hashCode();
        char [] hashArray = new char[HASH_SIZE];
        long num;
        int numeroSenha = getNumero(senhaPlana.concat(getSalt()));
        for(int i = 0; i < HASH_SIZE; i++){
            num = (long)(semente % MOD);
            hashArray[i] = (char)(79 + num);
            semente = ((semente * num - 13.4) % (numeroSenha));
        }
        return new String(hashArray);
    }

    private static String getSalt() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("/home/abdul/Documentos/estudos/java/projects-workspace/login/src/main/resources/arquivos/.salt.txt"))){
            return br.readLine();
        }
    }

    private static int getNumero(String senhaPlana){
        int total = 0;
        for(char letra : senhaPlana.toCharArray()) total += letra;
        return total;
    }

    public boolean confirmarSenha(String senha, String email, String hash) throws IOException{
        return gerarHash(senha, email).equals(hash);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(gerarHash("Helo", "Thiago"));
    }

}
