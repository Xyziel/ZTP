package ztp.shelter.exceptions;

public class DataAlreadyExistsException extends CustomException
{
    public DataAlreadyExistsException(String message, Integer httpStatus)
    {
        super(message, httpStatus);
    }
}
