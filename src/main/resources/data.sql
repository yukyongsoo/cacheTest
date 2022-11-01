-- truncate table test;

# DELIMITER $$
# CREATE PROCEDURE insertLoop()
# BEGIN
#     DECLARE i INT DEFAULT 1;
#     WHILE (i <= 100000000) DO -- (i가 1000이 될 때까지 반복)
#     INSERT INTO test(column_1, column_2, column_3, column_4)
#         VALUE (RAND() * 1000,'testdata', NOW(), RAND() * 100);
#     SET i = i + 1;
#         END WHILE;
# END$$
# DELIMITER ;

-- CALL insertLoop();