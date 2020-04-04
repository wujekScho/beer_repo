INSERT INTO users (id, login, password, activated)
VALUES (1, 'test@test.org', '$2a$10$4Xg9D6/EsIq0tDYbeeAM0eLXGXRAPy.NzVIlBvIPxG7JNH6Y2M6sK', true);

INSERT INTO user_roles (id, role)
VALUES (1, 'REGULAR_USER');

INSERT INTO yeasts (id, addedBy, name, pending)
VALUES (1, 'test@test.org', 'Fermentis WB-06', false),
       (2, 'test@test.org', 'SafAle BE-256', false),
       (3, 'SafAle US-05', 'Fermentis WB-06', false),
       (4, 'SafAle S-33', 'Fermentis WB-06', false);

INSERT INTO brewings (gravity, name, style, volume, timestamp, yeast_id)
VALUES (10.0, 'Russian Tzar', 'Russian Imperial Stout', 20.0, '2020-04-04 16:16:38.000000', 1),
       (15.0, 'Pani z Ameryki', 'American IPA', 20.0, '2020-04-04 16:16:38.000000', 2);

