package ztp.shelter.exceptions;

public class DataDoesNotExistException extends CustomException
{
    public DataDoesNotExistException(String message, Integer httpStatus)
    {
        super(message, httpStatus);
    }
}
