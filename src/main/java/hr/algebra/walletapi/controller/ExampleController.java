package hr.algebra.walletapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {


    @GetMapping()
    public ResponseEntity<String> example() {
        return ResponseEntity.ok("Hello world");
    }
}
