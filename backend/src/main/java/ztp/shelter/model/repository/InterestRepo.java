package ztp.shelter.model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;
import ztp.shelter.model.entity.Interest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Repository
public class InterestRepo implements InterestRepoInterface
{
    @Autowired
    private EntityManager entityManager;

    @Override
    public Integer getAnimalLikesById(Integer animalId)
    {

        // # should work as named param
        Query q = entityManager.createNativeQuery("SELECT count(user_id) from interests where animal_id=?1").
                setParameter(1, animalId);
        Integer numberOfLikes = ((BigInteger) q.getSingleResult()).intValue();
        return numberOfLikes;
    }

    @Override
    public List<Integer> getAnimalsIdThatUserLikes(String email)
    {
        Query q = entityManager.createNativeQuery("SELECT animal_id from interests\n" +
                                                          "inner join users u on interests.user_id = u.id_user\n" +
                                                          "where u.email=?1").
                setParameter(1, email);
        List animalsIdList = q.getResultList();
        return animalsIdList;
    }

    @Override
    @Modifying
    @Transactional
    public void addInterest(Integer userId, Integer animalId)
    {
        //1 = user_id 2 = animal_id
        Query q = entityManager.createNativeQuery("INSERT INTO interests values (?1,?2)").
                setParameter(1, userId).
                setParameter(2, animalId);
        q.executeUpdate(); //returns number of row affected

    }

    @Override
    @Modifying
    @Transactional
    public void removeInterest(Integer userId, Integer animalId)
    {
        Query q = entityManager.createNativeQuery("DELETE FROM interests where user_id = ?1 and animal_id = ?2").
                setParameter(1, userId).
                setParameter(2, animalId);
        q.executeUpdate(); //returns number of row affected
    }

    @Override
    public Interest getInterestByIdUserAndIdAnimal(Integer userId, Integer animalId)
    {
        Query q = entityManager
                .createNativeQuery("select * from interests where user_id = ?1 and animal_id = ?2").
                        setParameter(1, userId).
                        setParameter(2, animalId);
        List<Object[]> interests = q.getResultList();
        Interest interest = new Interest();
        for(Object[] obj : interests)
        {
            interest.setUserId((Integer) obj[0]);
            interest.setAnimalId((Integer) obj[1]);
        }

        return interests ==null || interests.isEmpty() ? null : interest;
    }
}
