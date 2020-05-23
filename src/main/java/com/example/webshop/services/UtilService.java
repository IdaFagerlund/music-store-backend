package com.example.webshop.services;

import com.example.webshop.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilService {

    public void validateThatFieldsAreNotNullOrEmpty(String... args) {
        List<String> missingFields = Arrays.stream(args)
                .filter(field -> field == null || field.trim().length() == 0)
                .collect(Collectors.toList());
        if(!missingFields.isEmpty()) {
            throw new ValidationException(400, "Missing fields");
        }
    }

}
