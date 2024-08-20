CREATE TABLE CUSTOMER_INFO
(
    CUSTOMER_ID serial primary key,
    USERNAME    varchar(128) not null,
    FIRSTNAME   varchar(128) not null,
    SURNAME     varchar(128) not null,
    PATRONYMIC  varchar(128),
    USER_ROLE   varchar(10)  not null,
    CREATE_DATE date default CURRENT_DATE,
    IS_DELETE   boolean      not null
);

CREATE TABLE PRODUCT
(
    PRODUCT_ID       serial primary key,
    PRODUCT_TYPE     varchar(10)  not null,
    PRODUCT_NAME     varchar(128) not null,
    PRODUCT_PRICE    BIGINT       not null,
    IS_DELETE        boolean      not null,
    CUSTOMER_INFO_ID integer REFERENCES CUSTOMER_INFO (CUSTOMER_ID) on update cascade
);