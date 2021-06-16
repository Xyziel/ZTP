package ztp.shelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztp.shelter.model.entity.Breed;
import ztp.shelter.model.repository.BreedRepo;
import ztp.shelter.service.helpers.ExceptionThrower;

import java.util.List;

@Service
public class BreedService
{
    @Autowired
    BreedRepo breedRepo;

    public List<Breed> getAllBreeds()
    {
        List<Breed> breeds = breedRepo.getAllBreeds();
        ExceptionThrower.throwIfDataDoesNotExist(breeds);
        return breeds;
    }
}
