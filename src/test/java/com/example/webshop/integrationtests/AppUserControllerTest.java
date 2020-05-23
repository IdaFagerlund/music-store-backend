package com.example.webshop.integrationtests;

import com.example.webshop.entities.AppUser;
import com.example.webshop.models.AppUserRequestModel;
import com.example.webshop.repositories.AppUserRepository;
import com.example.webshop.repositories.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class AppUserControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // POST /user/register

    @Test
    public void registerSuccessReturnsExpectedResponse() {

        AppUserRequestModel requestJson = new AppUserRequestModel("username1", "password", "some.email@gmail.com");

        webTestClient.post().uri("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), AppUserRequestModel.class)
                .exchange()
                .expectStatus().isEqualTo(201);

    }

    @Test
    public void registerSuccessUpdatesDatabaseCorrectly() {

        AppUserRequestModel requestJson = new AppUserRequestModel("username2", "password", "another.email@gmail.com");

        webTestClient.post().uri("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), AppUserRequestModel.class)
                .exchange();

        Optional<AppUser> appUser = appUserRepository.findByUsername("username2");
        assertTrue(appUser.isPresent());

//        UserRole userRole = userRoleRepository.findByUserRole(UserRoleEnum.ROLE_USER);
//        System.out.println(userRole.getAppUsers().size());
//        System.out.println(appUser.get());
        //assertTrue(userRole.getAppUsers().contains());


    }

    @Test
    public void registerFailsAtUnavailableEmail() {

        AppUserRequestModel requestJson = new AppUserRequestModel("username3", "password", "superemail@gmail.com");

        webTestClient.post().uri("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), AppUserRequestModel.class)
                .exchange()
                .expectStatus().isEqualTo(201);

        webTestClient.post().uri("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), AppUserRequestModel.class)
                .exchange()
                .expectStatus().isEqualTo(409);

    }

    @Test
    public void registerFailsAtMalformedEmail() {

        List<String> invalidEmails = Arrays.asList("email", "email.com", "email@email", "@email.com", "email@");

        invalidEmails.forEach(invalidEmail -> {
            AppUserRequestModel requestJson = new AppUserRequestModel("username", "password", invalidEmail);

            webTestClient.post().uri("/users/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(requestJson), AppUserRequestModel.class)
                    .exchange()
                    .expectStatus().isEqualTo(400);
        });

    }

    // POST /user/login

    @Test
    public void loginSuccessReturnsExpectedResponse() {

    }

    // GET /user/
    @Test
    public void getAllUserDataSuccessReturnsExpectedResponse() {
        //TODO different db variations
    }
    //TODO response with different db variations

    // PATCH /user/
    //TODO response
    //TODO db updates
}