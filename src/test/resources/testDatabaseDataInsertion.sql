INSERT INTO player (name, total_games, total_wins)
VALUES ('fifth', 6, 2),
        ('third', 4, 2),
        ('sixth', 8, 1),
        ('fourth (second)', 12, 6),
        ('first', 1, 1),
        ('seventh (third)', 17, 0),
        ('second (first)', 10, 10);

INSERT INTO game (player, result)
VALUES (1, 0),
        (1, 1),
        (5, 1),
        (2, 1),
        (1, 1),
        (2, 0);

INSERT INTO guess (game, guess, match_count, place_match_count, creation_time)
VALUES (3, '1234', 1, 1, '2023-10-01 12:45:52.045'),
        (3, '3456', 2, 0, '2023-10-01 12:48:15.078'),
        (1, '9876', 0, 3, '2023-10-01 12:46:17.088'),
        (3, '2345', 2, 1, '2023-10-01 12:46:55.015');
