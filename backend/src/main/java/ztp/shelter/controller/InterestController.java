package ztp.shelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ztp.shelter.service.InterestService;
import ztp.shelter.service.SessionService;
import ztp.shelter.service.ShelterUserService;

@RestController
@RequestMapping("/interest")
public class InterestController
{
    @Autowired
    private InterestService interestService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ShelterUserService shelterUserService;


    @GetMapping("getAllLikesByAnimalId/{id}")
    public ResponseEntity<?> getAllLikesByAnimalId(@PathVariable("id") Integer animalId)
    {
        return ResponseEntity.ok(interestService.getNumberOfLikesByAnimalId(animalId));
    }

    @GetMapping("getAnimalsIdThatUserLikes")
    public ResponseEntity<?> getAnimalsIdThatUserLikes(@CookieValue(value = "Authorization", required = false) String token)
    {
        String email = sessionService.getUserEmail(token);
        return ResponseEntity.ok(interestService.getAnimalsIdThatUserLikes(email));
    }

    @PostMapping
    public ResponseEntity<?> addInterest(@CookieValue(value = "Authorization", required = false) String token,
                                         @RequestBody Integer idAnimal)
    {
        String email = sessionService.getUserEmail(token);
        Integer idUser = shelterUserService.getUserIdByEmail(email);
        interestService.addInterest(idUser,idAnimal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeInterest(@CookieValue(value = "Authorization", required = false) String token,
                                            @PathVariable("id") Integer idAnimal)
    {
        String email = sessionService.getUserEmail(token);
        Integer idUser = shelterUserService.getUserIdByEmail(email);
        interestService.removeInterest(idUser,idAnimal);
        return ResponseEntity.ok().build();
    }



}
