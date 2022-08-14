create table CUSTOMER
(
    AGE     NUMBER(10),
    COUNTRY VARCHAR2(50 char),
    NAME    VARCHAR2(50 char),
    ID      NUMBER(10) not null
        constraint CUSTOMER_PK
        primary key
)