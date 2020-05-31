package com.example.webshop.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class UtilService {

    public boolean doesErrorsExists(String... args) {
        return Arrays.stream(args).anyMatch(Objects::nonNull);
    }

    public boolean isFieldNullOrEmpty(String field) {
        return field == null || field.trim().length() == 0;
    }

}
