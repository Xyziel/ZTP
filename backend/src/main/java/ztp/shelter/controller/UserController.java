package ztp.shelter.controller;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ztp.shelter.exceptions.CustomException;
import ztp.shelter.model.entity.User;
import ztp.shelter.service.SessionService;
import ztp.shelter.service.ShelterUserService;

@RestController
public class UserController
{
    @Autowired
    private ShelterUserService shelterUserService;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User newUser)
    {
        shelterUserService.createUser(newUser, "USER");
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/user/addEmployee")
    public ResponseEntity<?> createUser(@RequestBody User newEmployee)
    {
        shelterUserService.createUser(newEmployee, "EMPLOYEE");
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/user/getAllUsersWithoutAdmin")
    public ResponseEntity<?> getAllUsersWithoutAdmin()
    {
        return ResponseEntity.ok(shelterUserService.getAllUsersWithoutAdmin());
    }


    @PutMapping("/user/changePassword")
    public ResponseEntity<?> changeUserPassword(@CookieValue(value = "Authorization", required = false) String token,
                                                @RequestBody JSONObject jsonObject)
    {
        String email = sessionService.getUserEmail(token);
        shelterUserService.changeUserPassword(jsonObject, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> removeUserById(@PathVariable("id") Integer idUser)
    {
        shelterUserService.removeUserById(idUser);
        return ResponseEntity.ok().build();
    }



}
