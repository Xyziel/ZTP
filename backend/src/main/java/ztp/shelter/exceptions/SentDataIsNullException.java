package ztp.shelter.exceptions;

public class SentDataIsNullException extends CustomException
{
    public SentDataIsNullException()
    {
        super("Sent data is null", 422);
    }
}
