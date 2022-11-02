create table test.test
(
    id       bigint auto_increment
        primary key,
    column_1 int          not null,
    column_2 varchar(100) not null,
    column_3 datetime     not null,
    column_4 tinyint      not null
);

create index test_column_4_column_3_index
    on test.test (column_4, column_3);

create table test.test2
(
    id       bigint auto_increment primary key,
    column_1 int          not null,
    column_2 varchar(100) not null,
    column_3 datetime     not null,
    column_4 tinyint      not null
);

create index test2_column_4_index
    on test.test2 (column_4);

create table test.test3
(
    id       bigint auto_increment primary key,
    column_1 int          not null,
    column_2 varchar(100) not null,
    column_3 datetime     not null,
    column_4 tinyint      not null
);