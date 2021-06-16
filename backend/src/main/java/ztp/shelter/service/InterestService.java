package ztp.shelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ztp.shelter.model.entity.Interest;
import ztp.shelter.model.repository.InterestRepo;
import ztp.shelter.service.helpers.ExceptionThrower;

import java.util.List;

@Service
public class InterestService
{
    @Autowired
    InterestRepo interestRepo;

    @Autowired
    SessionService sessionService;

    @Autowired
    ShelterUserService shelterUserService;

    public Integer getNumberOfLikesByAnimalId(Integer animalId)
    {
        ExceptionThrower.throwIfDataIsNull(animalId);
        Integer likes = interestRepo.getAnimalLikesById(animalId);
        ExceptionThrower.throwIfDataDoesNotExist(likes);
        return likes;
    }

    public List getAnimalsIdThatUserLikes(String email)
    {
        ExceptionThrower.throwIfDataIsNull(email);
        List list =  interestRepo.getAnimalsIdThatUserLikes(email);
        ExceptionThrower.throwIfDataDoesNotExist(list);
        return list;
    }

    public void addInterest(Integer idUser, Integer idAnimal)
    {
        ExceptionThrower.throwIfDataIsNull(idUser,idAnimal);
        ExceptionThrower.throwIfDataAlreadyExists(getInterestByIdUserAndIdAnimal(idUser,idAnimal));
        interestRepo.addInterest(idUser,idAnimal);
    }

    public Interest getInterestByIdUserAndIdAnimal(Integer idUser, Integer idAnimal)
    {
        ExceptionThrower.throwIfDataIsNull(idUser,idAnimal);
        Interest interest = interestRepo.getInterestByIdUserAndIdAnimal(idUser,idAnimal);
        return interest;
    }

    public void removeInterest(Integer idUser, Integer idAnimal)
    {
        ExceptionThrower.throwIfDataIsNull(idUser,idAnimal);
        ExceptionThrower.throwIfDataDoesNotExist(getInterestByIdUserAndIdAnimal(idUser,idAnimal));
        interestRepo.removeInterest(idUser,idAnimal);
    }

}
