package hr.algebra.walletapi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Value("${message.hello}")
    private String value;

    @GetMapping()
    public ResponseEntity<String> example() {
        return ResponseEntity.ok(value);
    }
}
