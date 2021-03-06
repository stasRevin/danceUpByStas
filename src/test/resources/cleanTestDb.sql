DELETE FROM USER_DANCE;
DELETE FROM USER_ROLE;
DELETE FROM USER_LESSON;
DELETE FROM USER_LOCATION;
DELETE FROM DANCE;
DELETE FROM LESSON;
DELETE FROM LOCATION;
DELETE FROM ROLE;
DELETE FROM SCHEDULE;
DELETE FROM NOTIFICATION;
DELETE FROM USER;
ALTER TABLE DANCE AUTO_INCREMENT = 1;
INSERT INTO DANCE VALUES(1, 'Waltz', 'elegant, classy');
INSERT INTO DANCE VALUES (2, 'Rumba', 'romantic, slow, easy');
INSERT INTO DANCE VALUES (3, 'Tango', 'romantic, staccato, dramatic');
ALTER TABLE USER AUTO_INCREMENT = 1;
INSERT INTO USER (username, password, is_deleted, first_name, last_name, address1, city, state, postalcode, pay_rate, photo_name) VALUES ('mjackson', '123abc', 0, 'Michael', 'Jackson', '123 Main St', 'Madison', 'WI', '53705', 50.00, 'mjackson.jpg');
INSERT INTO USER (username, password, is_deleted, first_name, last_name, address1, city, state, postalcode, pay_rate) VALUES ('mjessy', '123abc', 0, 'Mary', 'Jessy', '123 Main St', 'Madison', 'WI', '53704', 60.00);
INSERT INTO USER_DANCE (user_id, dance_id) VALUES (1, 1);
INSERT INTO USER_DANCE (user_id, dance_id) VALUES (1, 2);
ALTER TABLE ROLE AUTO_INCREMENT = 1;
INSERT INTO ROLE (name) VALUES ("instructor");
INSERT INTO ROLE (name) VALUES ("student");
INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 2);
ALTER TABLE LOCATION AUTO_INCREMENT = 1;
INSERT INTO LOCATION (name, address1, city, state, postalcode) VALUES ('My Ballroom', '123 Main St', 'Madison', 'WI','53705');
INSERT INTO LOCATION (name, address1, city, state, postalcode) VALUES ('Elite Dance', '124 Main St', 'Madison', 'WI','53704');
INSERT INTO USER_LOCATION (user_id, location_id) VALUES (1, 1);
INSERT INTO USER_LOCATION (user_id, location_id) VALUES (1, 2);
INSERT INTO USER_LOCATION (user_id, location_id) VALUES (2, 1);
ALTER TABLE LESSON AUTO_INCREMENT = 1;
INSERT INTO LESSON (date, start_time, end_time, location_id) VALUES ('2018-09-27', '17:00:00', '18:00:00', 1);
INSERT INTO LESSON (date, start_time, end_time, location_id) VALUES ('2018-10-01', '19:00:00', '18:00:00', 2);
INSERT INTO USER_LESSON (user_id, lesson_id, role_id) VALUES (1, 1, 1);
INSERT INTO USER_LESSON (user_id, lesson_id, role_id) VALUES (1, 2, 1);
INSERT INTO USER_LESSON (user_id, lesson_id, role_id) VALUES (2, 1, 2);
ALTER TABLE SCHEDULE AUTO_INCREMENT = 1;
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('17:00:00', '22:00:00', '2018-09-27', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('17:00:00', '22:00:00', '2018-09-28', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('17:00:00', '22:00:00', '2018-09-29', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('17:00:00', '22:00:00', '2018-09-30', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('17:00:00', '22:00:00', '2018-10-01', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('17:00:00', '22:00:00', '2018-10-27', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('17:00:00', '22:00:00', '2018-12-27', 1);
INSERT INTO SCHEDULE (start_time, end_time, date, user_id) VALUES ('15:00:00', '21:00:00', '2018-09-27', 2);
INSERT INTO NOTIFICATION (message, user_id, is_read, date_time) VALUES ('A lesson was booked.', 1, 0, '2018-11-13 21:10:09');