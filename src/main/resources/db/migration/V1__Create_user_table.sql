CREATE TABLE USER
(
    ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    account_id varchar(100),
    name varchar(50),
    token varchar(36),
    gmt_create BIGINT,
    gmt_modify BIGINT
);