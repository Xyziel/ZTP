package ztp.shelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ztp.shelter.service.SizeService;

@RestController
@RequestMapping("/size")
public class SizeController
{
    @Autowired
    SizeService sizeService;

    @GetMapping
    public ResponseEntity<?> getAllSizes()
    {
        return ResponseEntity.ok(sizeService.getAllSizes());
    }
}
