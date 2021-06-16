package ztp.shelter.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ztp.shelter.model.entity.Breed;

import java.util.List;

@Repository
public interface BreedRepo extends JpaRepository<Breed, Integer>
{
    @Query(value = "SELECT * FROM breeds",nativeQuery = true)
    public List<Breed> getAllBreeds();

}
