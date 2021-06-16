package ztp.shelter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ztp.shelter.exceptions.DataDoesNotExistException;
import ztp.shelter.exceptions.SentDataIsNullException;
import ztp.shelter.model.entity.Animal;
import ztp.shelter.model.entity.Breed;
import ztp.shelter.model.entity.Size;
import ztp.shelter.model.repository.AnimalRepo;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AnimalServiceTest
{
    @TestConfiguration
    static class AnimalServiceContext
    {

        @Bean
        public AnimalService animalService()
        {
            return new AnimalService();
        }
    }

    @Autowired
    private AnimalService animalService;

    @MockBean
    private AnimalRepo animalRepoMocked;


    @Test
    public void getAnimalByIdWhenDataDoesNotExistException()
    {
        when(animalRepoMocked.findAnimalByIdAnimal(999))
                .thenThrow(new DataDoesNotExistException("Such data does not exist", 404));
        Exception exception = assertThrows(DataDoesNotExistException.class, () -> animalService.getAnimalById(999));
        assertEquals("Such data does not exist", exception.getMessage());
    }




}
