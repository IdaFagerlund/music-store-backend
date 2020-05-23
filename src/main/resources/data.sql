INSERT INTO user_role (id, user_role)
VALUES (1000, "ROLE_USER"), (1001, "ROLE_ADMIN"), (1002, "ROLE_OWNER");

INSERT INTO app_user (id, username, password, email)
VALUES (1003, "user", "$2a$10$48A4EwdmMTgfHKGZ2v/7R.SKRMBjsPQsBoj6YygWA2yRcpTouVt4e", "a"),
(1004, "admin", "$2a$10$fO7hsUpfSAarKNTMOHwfgOjl1oHiL07ptEVJ2xJUxGbSx1RPDUfJm", "b"),
(1005, "owner", "$2a$10$mpMMyZuhD1fY6c4onPgCQ.OppZLQVKWOj2U1.4T420ur3jcFC8L0O", "c");

INSERT INTO app_user_user_role (app_user_id, user_role_id)
VALUES (1003, 1000), (1004, 1001), (1005, 1002);

INSERT INTO product (id, average_review_stars, description, is_removed, name, is_featured, stock)
VALUES (1006, 3, "the description", false, "product name", false, 10);

INSERT INTO pricing (id, price, time_at_pricingutc, product_id)
VALUES (1007, 100, "2020-01-22T00:00:00", 1006), (1008, 200, "2020-02-23T00:00:00", 1006);