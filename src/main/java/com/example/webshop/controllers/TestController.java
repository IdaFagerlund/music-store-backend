package com.example.webshop.controllers;

import com.example.webshop.entities.Test;
import com.example.webshop.models.TestResponseModel;
import com.example.webshop.services.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TestResponseModel>> getTests() {
        return ResponseEntity.status(200).body(testService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Test> getTests(@RequestBody TestResponseModel testResponseModel) {
        return ResponseEntity.status(200).body(testService.save(testResponseModel));
    }

}
