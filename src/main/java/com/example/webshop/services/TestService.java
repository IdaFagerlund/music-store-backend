package com.example.webshop.services;

import com.example.webshop.entities.Test;
import com.example.webshop.exceptions.ValidationException;
import com.example.webshop.models.TestModel;
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

    public List<TestModel> findAll() {
        return testRepository.findAll()
                .stream().map(test -> new TestModel(test.getWord()))
                .collect(Collectors.toList());
    }

    public Test save(TestModel testModel) {
        if (testModel.getWord() == null) {
            throw new ValidationException("missing word");
        }
        return testRepository.save(new Test(testModel.getWord()));
    }

}
