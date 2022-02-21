package exception;

/**
 * Classe responsável por encapsular as exceções técnicas da Aplicação.
 */
public class TechnicalException extends Exception {

    private static final long serialVersionUID = -5255597096714502776L ;

    /**
     * @param message
     */
    public TechnicalException( String message )
    {
        super( message ) ;
    }

    /**
     * @param cause
     */
    public TechnicalException( Throwable cause )
    {
        super( cause ) ;
    }

    /**
     * @param message
     * @param cause
     */
    public TechnicalException( String message, Throwable cause )
    {
        super( message, cause ) ;
    }
}
