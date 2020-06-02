INSERT INTO user_role (id, user_role)
VALUES (1000, "ROLE_USER"), (1001, "ROLE_ADMIN"), (1002, "ROLE_DEVELOPER");

INSERT INTO app_user (id, username, password, email)
VALUES (1003, "user", "$2a$10$48A4EwdmMTgfHKGZ2v/7R.SKRMBjsPQsBoj6YygWA2yRcpTouVt4e", "user.user@gmail.com"),
(1004, "admin", "$2a$10$fO7hsUpfSAarKNTMOHwfgOjl1oHiL07ptEVJ2xJUxGbSx1RPDUfJm", "admin.admin@gmail.com"),
(1005, "developer", "$2a$10$o9vfgmkjpfkqGmzjrX57OuvJ3VeinHACWXt57eChO8eL7EzbXmUjy", "developer.developer@gmail.com");

INSERT INTO app_user_user_role (app_user_id, user_role_id)
VALUES (1003, 1000), (1004, 1001), (1005, 1002);

INSERT INTO product(id, name, description, price, stock, is_featured)
VALUES (1006, "The product name", "The description", 50.50, 10, 1);

INSERT INTO product_review(id, comment, last_time_updatedutc, stars, time_createdutc, app_user_id, product_id)
VALUES (1007, "Amazing!", "2020-01-22T00:00:00", 4, "2020-01-21T00:00:00", 1003, 1006);