insert into "brakes" (id, part_type, part_name, part_number, price, amount_of_parts, outer_diameter, center_diameter,
                      height,
                      min_thickness, surface, disc_thickness, bore_type_number_of_holes, wheel_stud_diameter,
                      without_wheel_mounting_bolts, without_wheel_hub)
values (1, 'BRAKE', 'Wheel Assembly', 'W123', 99.99, 4, 18.5, 12.3, 4.8, 3.2, 75.0, 5.0, '4x100', 12.0, false, true),
       (2, 'BRAKE', 'Wheel Assembly', 'W124', 114.99, 5, 20.5, 13.00, 6.00, 4, 90, 5.5, '5x120', 15.0, true, false);

insert into "spark_plug" (id, part_Type, part_name, part_number, price, amount_of_parts, spanner_size, quality,
                          warmth_degree,
                          thread_length, torque, spark_position)
values (1, 'SPARKPLUG', 'Spark Plug 1', 'SP123', 9.99, 10, 14, 'Good', 200, 50.0, 20, 1),
       (2, 'SPARKPLUG', 'Spark Plug 2', 'SP456', 8.99, 12, 16, 'Excellent', 180, 55.0, 18, 2),
       (3, 'SPARKPLUG ', 'Spark Plug 3', 'SP789', 7.99, 8, 12, 'Average', 220, 48.5, 22, 3);

insert into "cars" (id, brand, model, year_of_build, color, license_plate, mile_age, engine_type, body, transmission,
                    fuel)
values (1, 'AUDI', 'A4', '2022-01-01', 'BLACK', 'AB-12-34', 10000, 'TSI', 'SEDAN', 'MANUAL', 'PETROL'),
       (2, 'VOLKSWAGEN', 'GOLF', '2021-12-01', 'BLUE', 'CD-56-78', 5000, 'FSI', 'HATCHBACK', 'AUTOMATIC', 'DIESEL');

insert into "customer_accounts"(id, customer_name, first_name, last_name, address, phone_number, billing_address, bank_account_number)
values (1, 'Jan de Vries', 'Jan', 'de Vries', 'Kerkstraat 1', '06-12345678', 'Postbus 123', 'NL12ABCD3456789012'),
       (2, 'Linda Jansen', 'Linda', 'Jansen', 'Dorpsweg 2', '06-98765432', 'Postbus 456', 'NL98EFGH6789012345'),
       (3, 'Pieter Bakker', 'Pieter', 'Bakker', 'Hoofdstraat 3', '06-55555555', 'Postbus 789', 'NL34IJKL9012345678');

insert into "car_inspection"(id, mile_age, license_plate, inspection_date, car_is_Correct, car_is_fine, has_problem)
values (1, 10999, 'AB-12-34', '2023-06-09', true, null, 'car has problems with the brakes'),
       (2, 10999, 'AB-12-34', '2023-06-12', true, 'car has new brakes and is now good to go', null);

insert into "users" (username, password, email, enabled)
values ('admin', '$2a$12$EMqaRwbOZNSces91akpqHOSVNqcqNB6P9s6xc7HW24/h/198Plw7a', 'admin@test.nl', true),
       ('Ad', '$2a$12$9GHM0tml7Q0V8hhLzsuSS.CEx47rqHT/OzUPpMda97VMeyFGyGrB2', 'user@test.nl', true);
insert into "authorities" (username, authority)
values ('admin', 'ROLE_ADMIN'),
       ('Ad', 'ROLE_MECHANIC');