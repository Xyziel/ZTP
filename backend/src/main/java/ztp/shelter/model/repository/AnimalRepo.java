package ztp.shelter.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ztp.shelter.model.entity.Animal;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal, Integer>
{
//    @Query(value = "SELECT * from animals",nativeQuery = true)
//    public List<Animal> findAll();

    List<Animal> findAll();

    Animal findAnimalByIdAnimal(Integer idAnimal);

    @Modifying
    @Transactional
    void deleteById(Integer integer);

    @Query(value = "select * from animals order by arrival_date desc\n" +
            "fetch first 6 rows only", nativeQuery = true)
    List<Animal> findLastSix();
}
