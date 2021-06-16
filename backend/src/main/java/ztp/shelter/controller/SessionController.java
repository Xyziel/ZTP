package ztp.shelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ztp.shelter.security.jwt.helpers.JWTExtractor;
import ztp.shelter.service.SessionService;

@RestController
public class SessionController
{
    @Autowired
    private SessionService sessionService;

    @GetMapping(path = "/getUserRole")
    public ResponseEntity<?> getUserRole(@CookieValue(value = "Authorization", required = false) String token)
    {
        String role = sessionService.getUserRole(token);
        return ResponseEntity.ok(role);
    }

    @GetMapping(path = "/getUserEmail")
    public ResponseEntity<?> getUserEmail(@CookieValue(value = "Authorization", required = false) String token)
    {
        String email = sessionService.getUserEmail(token);
        return ResponseEntity.ok(email);
    }
}
