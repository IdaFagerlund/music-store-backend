package com.example.webshop.services;

import com.example.webshop.entities.Test;
import com.example.webshop.exceptions.ValidationException;
import com.example.webshop.models.TestResponseModel;
import com.example.webshop.repositories.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestResponseModel> findAll() {
        return testRepository.findAll()
                .stream().map(test -> new TestResponseModel(test.getWord()))
                .collect(Collectors.toList());
    }

    public Test save(TestResponseModel testResponseModel) {
        if (testResponseModel.getWord() == null) {
            throw new ValidationException("missing word");
        }
        return testRepository.save(new Test(testResponseModel.getWord()));
    }

}
