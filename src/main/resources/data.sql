-- INSERT INTO industry (name) VALUES ('Edukacja');
-- INSERT INTO industry (name) VALUES ('Prace biurowe & Tłumaczenia');
-- INSERT INTO industry (name) VALUES ('IT & Grafika');
-- INSERT INTO industry (name) VALUES ('Gastronomia & Eventy');
-- INSERT INTO industry (name) VALUES ('Prace budowlane');
INSERT INTO INDUSTRY (INDUSTRY_ID, NAME) VALUES (1, 'Edukacja');
INSERT INTO INDUSTRY (INDUSTRY_ID, NAME) VALUES (2, 'Gastronomia & Eventy');
INSERT INTO SALARY_TIME_UNIT (SALARY_TIME_UNIT_ID, name) VALUES (1, 'per hour');
INSERT INTO SALARY_TIME_UNIT (SALARY_TIME_UNIT_ID, name) VALUES (2, 'per project');
INSERT INTO JOB_SALARY (MAX_SALARY, MIN_SALARY, SALARY_TIME_UNIT_ID) VALUES (20, 10, 1);
INSERT INTO JOB_OFFER (DESCRIPTION, TITLE, INDUSTRY_ID, JOB_SALARY_ID, DATE_CREATED, DATE_UPDATED)
VALUES ('test_description', 'test_title', 1, 1, '2021-04-01', '2021-04-02');
INSERT INTO DATES_RANGE (END_DATE, START_DATE, JOB_OFFER_ID) VALUES ('2021-12-31', '2021-01-01', 1);


INSERT INTO image_box (id) VALUES ( 1 );
INSERT INTO image_box (id) VALUES ( 2 );

INSERT INTO image (image_box_id) VALUES ( 1 );
INSERT INTO image (image_box_id) VALUES ( 1 );
INSERT INTO image (image_box_id) VALUES ( 1 );
INSERT INTO image (image_box_id) VALUES ( 2 );


INSERT INTO experience (description, image_box_id) VALUES ('Always on time', 1 );
INSERT INTO experience (description, image_box_id) VALUES ('Fast and cost-efficient', 2 );
INSERT INTO experience (description) VALUES ('no' );


INSERT INTO address (street) VALUES ( 'SunnyStreet' );
INSERT INTO address (street) VALUES ( 'StormyStreet' );
INSERT INTO address (street) VALUES ( 'RainyStreet' );

INSERT INTO User (email, password, overall_rating, verified) VALUES ( 'mark@gmail.com' , 'mark_pass', 25, false );
INSERT INTO User (email, password, overall_rating, verified) VALUES ( 'kazik@gmail.com' , 'kazik_pass', 25, false );
INSERT INTO User (email, password, overall_rating, verified) VALUES ( 'mati@gmail.com' , 'mati_pass', 25, false );

INSERT INTO user_profile (user_id, first_name, last_name, address_id, description, experience_id, avatar_id)
VALUES ( 1, 'Mark', 'Markowski', 1, 'brave', 1, 1 );
INSERT INTO user_profile (user_id, first_name, last_name, address_id, description, experience_id, avatar_id)
VALUES ( 2, 'Kazik', 'Kaziowski', 2, 'smart', 2, 3 );
INSERT INTO user_profile (user_id, first_name, last_name, address_id, description, experience_id, avatar_id)
VALUES ( 3, 'Mati', 'Matiuszka', 3, 'cute', 3, 4 );


INSERT INTO rating (score, review, reviewed_by_user_id, user_id) VALUES ( 10, 'OK', 2, 1 );
