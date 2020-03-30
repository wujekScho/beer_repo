INSERT INTO brewings (gravity, name, style, volume)
VALUES (10.0, 'Russian Tzar', 'Russian Imperial Stout', 20.0),
       (15.0, 'Pani z Ameryki', 'American IPA', 20.0);

INSERT INTO users (id, login, password, activated)
VALUES (1, 'test@test.org', '$2a$10$4Xg9D6/EsIq0tDYbeeAM0eLXGXRAPy.NzVIlBvIPxG7JNH6Y2M6sK', true);

INSERT INTO user_roles (id, role)
VALUES (1, 'REGULAR_USER');