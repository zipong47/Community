alter table QUESTION alter column CREATOR BIGINT;
alter table COMMENT alter column COMMENTER BIGINT not null;
