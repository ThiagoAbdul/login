package exceptions;

public class EmailNaoCadastradoException extends Exception{

    public EmailNaoCadastradoException(){
        super("E-mail ainda não cadastrado ou incorreto");
    }
    
}
