insert into "brakes" (id, part_name, part_number, price, amount_of_parts, outer_diameter, center_diameter, height,
                      min_thickness, surface, disc_thickness, bore_type_number_of_holes, wheel_stud_diameter,
                      without_wheel_mounting_bolts, without_wheel_hub)
values (1, 'Wheel Assembly', 'W123', 99.99, 4, 18.5, 12.3, 4.8, 3.2, 75.0, 5.0, '4x100', 12.0, false, true),
       (2, 'Wheel Assembly', 'W124', 114.99, 5, 20.5, 13.00, 6.00, 4, 90, 5.5, '5x120', 15.0, true, false);

insert into "spark_plug" (id, part_name, part_number, price, amount_of_parts, spanner_size, quality, warmth_degree,
                          thread_length, torque, spark_position)
values (1, 'Spark Plug 1', 'SP123', 9.99, 10, 14, 'Good', 200, 50.0, 20, 1),
       (2, 'Spark Plug 2', 'SP456', 8.99, 12, 16, 'Excellent', 180, 55.0, 18, 2),
       (3, 'Spark Plug 3', 'SP789', 7.99, 8, 12, 'Average', 220, 48.5, 22, 3);

insert into "cars" (id, brand, model, year_of_build, color, license_plate, mile_age, engine_type, body, transmission, fuel)
values (1, '1cb', '1cr', '2020-07-10', 'c1', 'G-943-JD', 10999, 'et1', 'b1', 't2', 'f2');
insert into "users" (username, password, email, enabled)
values ('admin', '$2a$12$EMqaRwbOZNSces91akpqHOSVNqcqNB6P9s6xc7HW24/h/198Plw7a', 'admin@test.nl', true),
       ('Ad', '$2a$12$9GHM0tml7Q0V8hhLzsuSS.CEx47rqHT/OzUPpMda97VMeyFGyGrB2', 'user@test.nl', true);
insert into "authorities" (username, authority)
values ('admin', 'ADMIN'),
       ('Ad', 'MECHANIC');