package exceptions;

public class EmailJaCadastradoException extends Exception{
    
    public EmailJaCadastradoException(){
        super("Usuário já possui cadastro");
    }

}
