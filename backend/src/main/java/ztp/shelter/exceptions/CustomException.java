package ztp.shelter.exceptions;

public class CustomException extends RuntimeException
{
    private Integer httpStatus;

    public CustomException(String message, Integer httpStatus)
    {
        super(message);
        this.httpStatus = httpStatus;
    }

    public Integer getHttpStatus()
    {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus)
    {
        this.httpStatus = httpStatus;
    }
}
