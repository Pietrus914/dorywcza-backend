-- INSERT INTO industry (name) VALUES ('Edukacja');
-- INSERT INTO industry (name) VALUES ('Prace biurowe & Tłumaczenia');
-- INSERT INTO industry (name) VALUES ('IT & Grafika');
-- INSERT INTO industry (name) VALUES ('Gastronomia & Eventy');
-- INSERT INTO industry (name) VALUES ('Prace budowlane');
INSERT INTO INDUSTRY (INDUSTRY_ID, NAME) VALUES (1, 'Edukacja');
INSERT INTO INDUSTRY (INDUSTRY_ID, NAME) VALUES (2, 'Gastronomia & Eventy');

INSERT INTO SALARY_TIME_UNIT (SALARY_TIME_UNIT_ID, name) VALUES (1, 'per hour');
INSERT INTO SALARY_TIME_UNIT (SALARY_TIME_UNIT_ID, name) VALUES (2, 'per project');

INSERT INTO salary (MAX_SALARY, MIN_SALARY, SALARY_TIME_UNIT_ID) VALUES (20, 10, 1);
INSERT INTO salary (MAX_SALARY, MIN_SALARY, SALARY_TIME_UNIT_ID) VALUES (201, 101, 1);
INSERT INTO salary (MAX_SALARY, MIN_SALARY, SALARY_TIME_UNIT_ID) VALUES (120, 110, 1);

INSERT INTO date_range (start_date, end_date) VALUES ('2012-09-17', '2020-09-17');
INSERT INTO date_range (start_date, end_date) VALUES ('2012-09-17', '2020-09-17');
INSERT INTO date_range (start_date, end_date) VALUES ('2012-09-17', '2020-09-17');

INSERT INTO offer_location (x_position, y_position) VALUES (0.1, 0.1);
INSERT INTO offer_location (x_position, y_position) VALUES (0.1, 0.1);
INSERT INTO offer_location (x_position, y_position) VALUES (0.1, 0.1);

INSERT INTO offer_schedule (monday_morning, monday_afternoon, monday_evening, tuesday_morning, tuesday_afternoon, tuesday_evening,
                            wednesday_morning, wednesday_afternoon, wednesday_evening, thursday_morning, thursday_afternoon, thursday_evening, friday_morning,
                            friday_afternoon, friday_evening, saturday_morning, saturday_afternoon, saturday_evening, sunday_morning, sunday_afternoon, sunday_evening)
VALUES (true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);

INSERT INTO offer_schedule (monday_morning, monday_afternoon, monday_evening, tuesday_morning, tuesday_afternoon, tuesday_evening,
                            wednesday_morning, wednesday_afternoon, wednesday_evening, thursday_morning, thursday_afternoon, thursday_evening, friday_morning,
                            friday_afternoon, friday_evening, saturday_morning, saturday_afternoon, saturday_evening, sunday_morning, sunday_afternoon, sunday_evening)
VALUES (true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);

INSERT INTO offer_schedule (monday_morning, monday_afternoon, monday_evening, tuesday_morning, tuesday_afternoon, tuesday_evening,
                            wednesday_morning, wednesday_afternoon, wednesday_evening, thursday_morning, thursday_afternoon, thursday_evening, friday_morning,
                            friday_afternoon, friday_evening, saturday_morning, saturday_afternoon, saturday_evening, sunday_morning, sunday_afternoon, sunday_evening)
VALUES (true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);


INSERT INTO JOB_OFFER (DESCRIPTION, TITLE, USER_ID, DATE_CREATED, DATE_UPDATED, date_range_id, salary_id, offer_location_id, offer_schedule_id, has_experience, industry_id)
VALUES ('test_description', 'test_title', 1, '2021-04-01', '2021-04-02', 1, 1, 1, 1, true, 1);

INSERT INTO JOB_OFFER (DESCRIPTION, TITLE, USER_ID, DATE_CREATED, DATE_UPDATED, date_range_id, salary_id, offer_location_id, offer_schedule_id, has_experience, industry_id)
VALUES ('test_description 2', 'test_title 2', 1, '2021-04-01', '2021-04-02', 1, 1, 1, 1, true, 2);





INSERT INTO service_offer (user_id,description, title, date_range_id, has_experience, salary_id, offer_location_id, offer_schedule_id, DATE_CREATED, DATE_UPDATED, industry_id)
VALUES (1, 'test SERVIC OFFER 1', 'test SERVIC OFFER 1', 2, false, 2, 2, 2, '2021-04-01', '2021-04-02', 1);
INSERT INTO service_offer (user_id, description, title, date_range_id, has_experience, salary_id, offer_location_id, offer_schedule_id, DATE_CREATED, DATE_UPDATED, industry_id)
VALUES (1, 'test SERVIC OFFER 2', 'test SERVIC OFFER 2', 3, false, 3, 3, 3, '2021-04-01', '2021-04-02', 1);


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

INSERT INTO User (deleted, email, password, overall_rating, verified) VALUES ( false, 'mark@gmail.com' , 'mark_pass', 25, false );
INSERT INTO User (deleted, email, password, overall_rating, verified) VALUES ( false, 'kazik@gmail.com' , 'kazik_pass', 25, false );
INSERT INTO User (deleted, email, password, overall_rating, verified) VALUES ( false, 'mati@gmail.com' , 'mati_pass', 25, false );

INSERT INTO user_profile (user_id, first_name, last_name, address_id, description, experience_id, avatar_id)
VALUES ( 1, 'Mark', 'Markowski', 1, 'brave', 1, 1 );
INSERT INTO user_profile (user_id, first_name, last_name, address_id, description, experience_id, avatar_id)
VALUES ( 2, 'Kazik', 'Kaziowski', 2, 'smart', 2, 3 );
INSERT INTO user_profile (user_id, first_name, last_name, address_id, description, experience_id, avatar_id)
VALUES ( 3, 'Mati', 'Matiuszka', 3, 'cute', 3, 4 );


INSERT INTO rating (score, review, reviewed_by_user_id, user_id) VALUES ( 10, 'OK', 2, 1 );

-- INSERT INTO offer_schedule (monday_morning, monday_afternoon, monday_evening, tuesday_morning, tuesday_afternoon, tuesday_evening,
--                               wednesday_morning, wednesday_afternoon, wednesday_evening, thursday_morning, thursday_afternoon, thursday_evening, friday_morning,
--                               friday_afternoon, friday_evening, saturday_morning, saturday_afternoon, saturday_evening, sunday_morning, sunday_afternoon, sunday_evening)
-- VALUES (true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);
-- 
-- INSERT INTO offer_schedule (monday_morning, monday_afternoon, monday_evening, tuesday_morning, tuesday_afternoon, tuesday_evening,
--                               wednesday_morning, wednesday_afternoon, wednesday_evening, thursday_morning, thursday_afternoon, thursday_evening, friday_morning,
--                               friday_afternoon, friday_evening, saturday_morning, saturday_afternoon, saturday_evening, sunday_morning, sunday_afternoon, sunday_evening)
-- VALUES (true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);
-- 
-- INSERT INTO offer_location (x_position, y_position) VALUES (0.1, 0.1);
-- 
-- INSERT INTO offer_location (x_position, y_position) VALUES (0.1, 0.1);
-- 
-- INSERT INTO service_salary (min, max, salary_unit) VALUES (1000, 1200, 'week');
-- 
-- INSERT INTO service_salary (min, max, salary_unit) VALUES (1000, 1200, 'week');
-- 
-- INSERT INTO date_range (start_date, end_date) VALUES ('2012-09-17', '2020-09-17');
-- 
-- INSERT INTO date_range (start_date, end_date) VALUES ('2012-09-17', '2020-09-17');
-- 
-- INSERT INTO service_offer (user_id,description, title, date_range_id, has_experience, salary_id, offer_location_id, offer_schedule_id, DATE_CREATED, DATE_UPDATED)
-- VALUES (1, 'test', 'test', 1, false, 3, 3, 1, '2021-04-01', '2021-04-02');
-- INSERT INTO service_offer (user_id, description, title, date_range_id, has_experience, salary_id, offer_location_id, offer_schedule_id, DATE_CREATED, DATE_UPDATED)
-- VALUES (1, 'test', 'test', 2, false, 2, 2, 2, '2021-04-01', '2021-04-02');
