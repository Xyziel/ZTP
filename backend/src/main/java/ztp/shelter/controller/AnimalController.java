package ztp.shelter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ztp.shelter.model.entity.Animal;
import ztp.shelter.service.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalController
{
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<?> getAllAnimals()
    {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @GetMapping("/getLastSix")
    public ResponseEntity<?> getLastSixAddedAnimals()
    {
        return ResponseEntity.ok(animalService.getLastSix());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnimalById(@PathVariable("id") Integer idAnimal)
    {
        return ResponseEntity.ok(animalService.getAnimalById(idAnimal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAnimalById(@PathVariable("id") Integer idAnimal)
    {
        animalService.removeAnimalById(idAnimal);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> addAnimal(@RequestBody Animal animal)
    {
        animalService.createAnimal(animal);
        return ResponseEntity.status(201).build();
    }




}
