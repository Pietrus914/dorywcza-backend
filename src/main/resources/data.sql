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

INSERT INTO service_schedule (monday_morning, monday_afternoon, monday_evening, tuesday_morning, tuesday_afternoon, tuesday_evening,
                              wednesday_morning, wednesday_afternoon, wednesday_evening, thursday_morning, thursday_afternoon, thursday_evening, friday_morning,
                              friday_afternoon, friday_evening, saturday_morning, saturday_afternoon, saturday_evening, sunday_morning, sunday_afternoon, sunday_evening)
VALUES (true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);

INSERT INTO service_schedule (monday_morning, monday_afternoon, monday_evening, tuesday_morning, tuesday_afternoon, tuesday_evening,
                              wednesday_morning, wednesday_afternoon, wednesday_evening, thursday_morning, thursday_afternoon, thursday_evening, friday_morning,
                              friday_afternoon, friday_evening, saturday_morning, saturday_afternoon, saturday_evening, sunday_morning, sunday_afternoon, sunday_evening)
VALUES (true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);

INSERT INTO service_location (x_position, y_position) VALUES (0.1, 0.1);

INSERT INTO service_location (x_position, y_position) VALUES (0.1, 0.1);

INSERT INTO service_job_salary (min, max, salary_unit) VALUES (1000, 1200, 'week');

INSERT INTO service_job_salary (min, max, salary_unit) VALUES (1000, 1200, 'week');

INSERT INTO service_date_range (start_date, end_date) VALUES ('2012-09-17', '2020-09-17');

INSERT INTO service_date_range (start_date, end_date) VALUES ('2012-09-17', '2020-09-17');

INSERT INTO service_offer (user_id, industry, title, service_date_range_id, has_experience, service_job_salary_id, service_location_id, service_schedule_id)
VALUES (1, 'test', 'test', 1, false, 1, 1, 1);
INSERT INTO service_offer (user_id, industry, title, service_date_range_id, has_experience, service_job_salary_id, service_location_id, service_schedule_id)
VALUES (1, 'test', 'test', 2, false, 2, 2, 2);
