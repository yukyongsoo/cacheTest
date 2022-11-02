-- truncate table test;

# DELIMITER $$
# CREATE PROCEDURE insertLoop()
# BEGIN
#     DECLARE i INT DEFAULT 1;
#     WHILE (i <= 100000000) DO -- (i가 만료 될 때까지 반복)
#     INSERT INTO test(column_1, column_2, column_3, column_4)
#         VALUE (RAND() * 1000,'testdata', NOW(), RAND() * 100);
#     SET i = i + 1;
#         END WHILE;
# END$$
# DELIMITER ;

-- CALL insertLoop();

-- truncate table test2;
#
# DELIMITER $$
# CREATE PROCEDURE insertLoop2()
# BEGIN
#     DECLARE i INT DEFAULT 1;
#     DECLARE j INT DEFAULT 1;
#     WHILE (i <= 10) DO -- (i가 만료 될 때까지 반복)
#         WHILE (j <= 10) DO
#             INSERT INTO test2(column_1, column_2, column_3, column_4)
#             VALUE (RAND() * 1000,'testdata', NOW(), i);
#             SET j = j + 1;
#         END WHILE;
#     SET i = i + 1;
#     SET j = 1;
#         END WHILE;
# END$$
# DELIMITER ;

-- CALL insertLoop2();

-- truncate table test3;

-- drop procedure insertLoop3;

# DELIMITER $$
# CREATE PROCEDURE insertLoop3()
# BEGIN
#     DECLARE i INT DEFAULT 1;
#     WHILE (i <= 20) DO -- (i가 만료 될 때까지 반복)
#     INSERT INTO test3(column_1, column_2, column_3, column_4)
#         VALUE (RAND() * 1000,'testdata', NOW(), RAND() * 100);
#     SET i = i + 1;
#         END WHILE;
# END$$
# DELIMITER ;

-- CALL insertLoop3();