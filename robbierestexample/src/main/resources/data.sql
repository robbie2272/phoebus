insert into customer (CUST_ID,FORENAME,SURNAME,DOB) values (1,'Jack','Jones','1985-05-04');
insert into customer (CUST_ID,FORENAME,SURNAME,DOB) values (2,'Jill','Jones','1987-06-15');
insert into customer (CUST_ID,FORENAME,SURNAME,DOB) values (3,'Bobby','Moore','1961-01-21');
insert into customer (CUST_ID,FORENAME,SURNAME,DOB) values (4,'Jane','Smith','2001-07-24');
insert into account (ACC_ID,ACCOUNT_NUMBER) values (1,13657432);
insert into account (ACC_ID,ACCOUNT_NUMBER) values (2,89334625);
insert into account (ACC_ID,ACCOUNT_NUMBER) values (3,87421521);
insert into account (ACC_ID,ACCOUNT_NUMBER) values (4,32421721);
insert into CUSTOMER_ACCOUNT (CUST_ID, ACC_ID) values (1,1);
insert into CUSTOMER_ACCOUNT (CUST_ID, ACC_ID) values (2,1);
insert into CUSTOMER_ACCOUNT (CUST_ID, ACC_ID) values (3,2);
insert into CUSTOMER_ACCOUNT (CUST_ID, ACC_ID) values (4,3);
insert into CUSTOMER_ACCOUNT (CUST_ID, ACC_ID) values (4,4);