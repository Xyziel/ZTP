package ztp.shelter.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ztp.shelter.model.entity.Breed;
import ztp.shelter.model.entity.Size;

import java.util.List;

@Repository
public interface SizeRepo extends JpaRepository<Size, Integer>
{
    @Query(value = "SELECT * FROM sizes",nativeQuery = true)
    public List<Size> getAllSizes();

}
