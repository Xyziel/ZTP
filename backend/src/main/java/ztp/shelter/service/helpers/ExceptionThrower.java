package ztp.shelter.service.helpers;

import ztp.shelter.exceptions.CustomException;
import ztp.shelter.exceptions.DataAlreadyExistsException;
import ztp.shelter.exceptions.DataDoesNotExistException;
import ztp.shelter.exceptions.SentDataIsNullException;

public class ExceptionThrower
{
    public static void throwIfDataIsNull(Object object)
    {
        if(object == null)
        {
            //Maybe differnt exception
            throw new SentDataIsNullException();
        }
    }

    public static void throwIfDataIsNull(Object object1, Object object2)
    {
        if(object1 == null || object2 == null)
        {
            //Maybe differnt exception
            throw new SentDataIsNullException();
        }
    }

    public static void throwIfDataIsNull(Object object1, Object object2, Object object3)
    {
        if(object1 == null || object2 == null || object3 == null)
        {
            //Maybe differnt exception
            throw new SentDataIsNullException();
        }
    }

    public static void throwIfDataAlreadyExists(Object object)
    {
        if(object!=null)
        {
            throw new DataAlreadyExistsException("Such data already exists", 409);
        }
    }

    public static void throwIfDataDoesNotExist(Object object)
    {

        if(object == null)
        {
            throw new DataDoesNotExistException("Such data does not exist", 404);
        }
    }
}
