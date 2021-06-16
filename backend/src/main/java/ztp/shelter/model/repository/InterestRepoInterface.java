package ztp.shelter.model.repository;

import ztp.shelter.model.entity.Interest;

import java.util.List;

public interface InterestRepoInterface
{
    public Integer getAnimalLikesById(Integer animalId);
    List<Integer> getAnimalsIdThatUserLikes(String email);
    void addInterest(Integer userId,Integer animalId);
    void removeInterest(Integer userId, Integer animalId);
    Interest getInterestByIdUserAndIdAnimal(Integer userId, Integer animalId);
}
