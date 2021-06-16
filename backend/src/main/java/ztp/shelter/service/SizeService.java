package ztp.shelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztp.shelter.model.entity.Size;
import ztp.shelter.model.repository.SizeRepo;
import ztp.shelter.service.helpers.ExceptionThrower;

import java.util.List;

@Service
public class SizeService
{
    @Autowired
    SizeRepo sizeRepo;

    public List<Size> getAllSizes()
    {
        List<Size> sizes = sizeRepo.getAllSizes();
        ExceptionThrower.throwIfDataDoesNotExist(sizes);
        return sizes;
    }
}
