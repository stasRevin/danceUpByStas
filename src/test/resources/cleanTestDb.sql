DELETE FROM USER_DANCE;
DELETE FROM USER_ROLE;
DELETE FROM USER_LESSON;
DELETE FROM DANCE;
DELETE FROM LESSON;
DELETE FROM LOCATION;
DELETE FROM ROLE;
DELETE FROM SCHEDULE;
DELETE FROM USER;
ALTER TABLE DANCE AUTO_INCREMENT = 1;
INSERT INTO DANCE VALUES(1, 'Waltz', 'elegant, classy');
INSERT INTO DANCE VALUES (2, 'Rumba', 'romantic, slow, easy');
INSERT INTO DANCE VALUES (3, 'Tango', 'romantic, staccato, dramatic');
ALTER TABLE USER AUTO_INCREMENT = 1;
INSERT INTO USER (username, password, is_deleted, first_name, last_name, address1, city, state, postalcode, pay_rate) VALUES ('mjackson', '123abc', 0, 'Michael', 'Jackson', '123 Main St', 'Madison', 'WI', '53705', 50.00);
INSERT INTO USER (username, password, is_deleted, first_name, last_name, address1, city, state, postalcode, pay_rate) VALUES ('mjessy', '123abc', 0, 'Mary', 'Jessy', '123 Main St', 'Madison', 'WI', '53704', 60.00);
ALTER TABLE ROLE AUTO_INCREMENT = 1;
INSERT INTO ROLE (name) VALUES ("instructor");
INSERT INTO ROLE (name) VALUES ("student");
INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);
ALTER TABLE LOCATION AUTO_INCREMENT = 1;
INSERT INTO LOCATION (name, address1, city, state, postalcode) values ('My Ballroom', '123 Main St', 'Madison', 'WI','53705');
INSERT INTO LOCATION (name, address1, city, state, postalcode) values ('Elite Dance', '124 Main St', 'Madison', 'WI','53705');
ALTER TABLE LESSON AUTO_INCREMENT = 1;
INSERT INTO LESSON (start_time, end_time, location_id) values('2018-09-27 17:00:00', '2018-09-28 18:00:00', 1);
INSERT INTO LESSON (start_time, end_time, location_id) values('2018-10-01 19:00:00', '2018-10-02 18:00:00', 2);
INSERT INTO USER_LESSON (user_id, lesson_id) VALUES (1, 1);
INSERT INTO USER_LESSON (user_id, lesson_id) VALUES (1, 2);
ALTER TABLE SCHEDULE AUTO_INCREMENT = 1;
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('2018-09-27 17:00:00', '2018-09-27 22:00:00', '2018-09-27', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('2018-10-27 17:00:00', '2018-10-27 22:00:00', '2018-10-27', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('2018-11-27 17:00:00', '2018-11-27 22:00:00', '2018-11-27', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('2018-12-27 17:00:00', '2018-12-27 22:00:00', '2018-12-27', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('2018-09-27 15:00:00', '2018-09-27 21:00:00', '2018-09-27', 2);