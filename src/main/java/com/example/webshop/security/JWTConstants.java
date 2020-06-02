package com.example.webshop.security;

public class JWTConstants {

    public static final String SECRET = "NOYOUDIDNTSEETHIS";
    // 1000 milliseconds * 60 seconds * 60 minutes * 24 hours = 1 day;
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CLAIMS = "authorities";

}
