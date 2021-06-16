package ztp.shelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztp.shelter.model.entity.Animal;
import ztp.shelter.model.repository.AnimalRepo;
import ztp.shelter.service.helpers.ExceptionThrower;

import java.util.List;

//TODO w serwisach wszystkie throwy sa do zmiany raczej


@Service
public class AnimalService
{
    @Autowired
    private AnimalRepo animalRepo;

    public List<Animal> getAllAnimals()
    {
        return animalRepo.findAll();
    }

    public List<Animal> getLastSix()
    {
        return animalRepo.findLastSix();
    }

    public Animal getAnimalById(Integer idAnimal)
    {
        ExceptionThrower.throwIfDataIsNull(idAnimal);
        Animal animal = animalRepo.findAnimalByIdAnimal(idAnimal);
        ExceptionThrower.throwIfDataDoesNotExist(animal);
        return animal;
    }

    public void removeAnimalById(Integer idAnimal)
    {
        ExceptionThrower.throwIfDataIsNull(idAnimal);
        Animal animal = animalRepo.findAnimalByIdAnimal(idAnimal);
        ExceptionThrower.throwIfDataDoesNotExist(animal);
        animalRepo.deleteById(idAnimal);
    }

    public void createAnimal(Animal animal)
    {

        ExceptionThrower.throwIfDataIsNull(animal);
        //check if fields are null?
        animalRepo.save(animal);
    }

}
