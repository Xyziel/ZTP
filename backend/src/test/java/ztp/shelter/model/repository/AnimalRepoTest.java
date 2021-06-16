package ztp.shelter.model.repository;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ztp.shelter.model.entity.Animal;
import ztp.shelter.model.entity.Breed;
import ztp.shelter.model.entity.Size;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnimalRepoTest
{

    @Autowired
    private AnimalRepo animalRepo;


    @AfterEach
    public void destroyAll()
    {
        animalRepo.deleteAll();
    }


    @Test
    public void addAnimalTest() throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Breed breed = mock(Breed.class);
        Size size = mock(Size.class);
        Animal animal = new Animal("Dżasper",
                                   formatter.parse("2020-01-01"),
                                   "slodziak",
                                    breed,
                                   size);
        Animal animal1 = animalRepo.save(animal);
        assertEquals(animal.getName(), animal1.getName());
    }

    @Test
    public void deleteByIdTest()
    {
        Animal animal = new Animal(1, "Dżasper");
        animalRepo.save(animal);
        animalRepo.deleteById(animal.getIdAnimal());
        Animal animal1 = animalRepo.findAnimalByIdAnimal(animal.getIdAnimal());
        assertNotEquals(animal,animal1);
    }
}
