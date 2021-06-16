package ztp.shelter.exceptions;

public class JWTTokenViolatedException extends CustomException
{
    public JWTTokenViolatedException()
    {
        super("Don't even try to be an impostor", 403);
    }
}
