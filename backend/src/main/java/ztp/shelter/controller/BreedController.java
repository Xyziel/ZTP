package ztp.shelter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ztp.shelter.model.repository.BreedRepo;
import ztp.shelter.service.BreedService;

@RestController
@RequestMapping("/breed")
public class BreedController
{
    @Autowired
    BreedService breedService;

    @GetMapping
    public ResponseEntity<?> getAllBreeds()
    {
        return ResponseEntity.ok(breedService.getAllBreeds());
    }
}
