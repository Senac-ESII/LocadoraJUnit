package local.exception;

public class ClienteException extends RuntimeException{

    public ClienteException(String mensagem){
        super(mensagem);
    }
}
