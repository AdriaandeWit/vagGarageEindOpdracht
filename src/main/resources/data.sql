insert into "brakes" (id, part_type, part_name, part_number, price, amount_of_parts, outer_diameter, center_diameter,
                      height,
                      min_thickness, surface, disc_thickness, bore_type_number_of_holes, wheel_stud_diameter,
                      without_wheel_mounting_bolts, without_wheel_hub)
values (101, 'BRAKE', 'Wheel Assembly', 'W-123', 99.99, 4, 18.5, 12.3, 4.8, 3.2, 75.0, 5.0, '4x100', 12.0, false, true),
       (102, 'BRAKE', 'Wheel Assembly', 'W-124', 114.99, 5, 20.5, 13.00, 6.00, 4, 90, 5.5, '5x120', 15.0, true, false);
insert into "tyres"(id, part_type, part_name, part_number, price, amount_of_parts, tyres_hight, tyres_width, diameter,
                    load_index, speed_index, production_date)
values (101, 'TYRES', 'Tyre', 'T-010', 67.00, 8, 26.5, 200, 300, '100', 'A', '2023-06-15'),
       (102, 'TYRES', 'Tyre', 'T-011', 72.50, 6, 28.0, 210, 310, '105', 'B', '2023-07-01'),
       (103, 'TYRES', 'Tyre', 'T-012', 82.00, 10, 25.0, 190, 290, '95', 'C', '2023-06-25');
insert into "spark_plug" (id, part_Type, part_name, part_number, price, amount_of_parts, spanner_size, quality,
                          warmth_degree,
                          thread_length, torque, spark_position)
values (101, 'SPARKPLUG', 'Spark Plug 1', 'SP-123', 9.99, 10, 14, 'Good', 200, 50.0, 20, 1),
       (102, 'SPARKPLUG', 'Spark Plug 2', 'SP-456', 8.99, 12, 16, 'Excellent', 180, 55.0, 18, 2),
       (103, 'SPARKPLUG ', 'Spark Plug 3', 'SP-789', 7.99, 8, 12, 'Average', 220, 48.5, 22, 3);

insert into "cars" (id, brand, model, year_of_build, color, license_plate, mile_age, engine_type, body, transmission,
                    fuel)
values (101, 'AUDI', 'A4', '2022-01-01', 'BLACK', 'AB-12-34', 10000, 'TSI', 'SEDAN', 'MANUAL', 'PETROL'),
       (102, 'VOLKSWAGEN', 'GOLF', '2021-12-01', 'BLUE', 'CD-56-78', 5000, 'FSI', 'HATCHBACK', 'AUTOMATIC', 'DIESEL');

insert into "customer_accounts"(customer_name, first_name, last_name, address, phone_number, billing_address,
                                bank_account_number)
values ('Jan de Vries', 'Jan', 'de Vries', 'Kerkstraat 1', '06-12345678', 'Postbus 123', 'NL12-ABCD-3456-7890-12'),
       ('Linda Jansen', 'Linda', 'Jansen', 'Dorpsweg 2', '06-98765432', 'Postbus 456', 'NL98-EFGH-6789-0123-45'),
       ('Pieter Bakker', 'Pieter', 'Bakker', 'Hoofdstraat 3', '06-55555555', 'Postbus 789', 'NL34-IJKL-9012-3456-78');

insert into "car_repairs"(id, car, car_problem, repair_date, part_cost, labor_cost, total_cost)
values (101, 'VOLKSWAGEN', 'car have two not working spark plug ', '2023-05-12', 140.00, 150.00, 290.00),
       (102, 'AUDI', 'car tyres have no profile', '2023-04-10', 300.00, 150.00, 450.00);

insert into "car_inspections"(id, mile_age, license_plate, inspection_date, car_is_Correct, car_is_fine, has_problem)
values (101, 10999, 'AB-12-34', '2023-06-09', true, null, 'car has problems with the brakes'),
       (102, 10999, 'AB-12-34', '2023-06-12', true, 'car has new brakes and is now good to go', null);

insert into "users" (username, password, email, enabled)
values ('admin', '$2a$12$oBbCZrhAMAJqszpO41ucW.pkPMr6aiZPOHERTKpeJifg1CSk.77ti', 'admin@test.nl', true),
       ('Ad', '$2a$12$RqQzlb9m0ziRA4arkTLDquJsTw8FF34UIH8AmnMtmoDQLFc8.TuD6', 'MECHANIC-AD@test.nl', true),
       ('Willem', '$2a$12$xlXSDCD37ndaIlx8nukGbu2hZfBomtQEuAsng3OSlllHkyi3r/LuW', 'BOE-Willem@test.nl', true),
       ('Joep', '$2a$12$7ap8dX/2I1L3f/.yliDF1.Ae.5oRY8xhX.GGpFEFpdQfGm7luVACq', 'SP-Joep@test.nl', true);
insert into "authorities" (username, authority)
values ('admin', 'ROLE_ADMIN'),
       ('Ad', 'ROLE_MECHANIC'),
       ('Willem', 'ROLE_BACK_OFFICE_EMPLOYEE'),
       ('Joep', 'ROLE_SERVICE_SPECIALIST');