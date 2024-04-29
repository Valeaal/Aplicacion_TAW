-- Inserting into `tipo_usuario`
INSERT INTO `TAW`.`tipo_usuario` (`tipo`) VALUES ('admin'), ('entrenador_bodybuilding'), ('dietista');

-- Inserting into `usuario`
INSERT INTO `TAW`.`usuario` (`email`, `password`, `nombre`, `apellidos`, `fecha_nacimiento`, `pertenece_desde`, `tipo_usuario_id`) 
VALUES ('admin@example.com', 'adminpass', 'Admin', 'Istrador', '1980-01-01', '2020-01-01', 1),
       ('trainer@example.com', 'trainerpass', 'Trainer', 'Fitness', '1990-02-02', '2020-02-01', 2),
       ('diet@example.com', 'dietpass', 'Diet', 'Expert', '1995-03-03', '2021-01-01', 3);

-- Inserting into `dieta`
INSERT INTO `TAW`.`dieta` (`dietista_id`, `nombre`, `descripcion`, `fecha`) 
VALUES (7, 'Weight Loss', 'A diet plan designed for weight loss', '2024-01-01'),
       (7, 'Muscle Gain', 'A diet plan designed to increase muscle mass', '2024-01-02');

-- Inserting into `cliente`
INSERT INTO `TAW`.`cliente` (`usuario_id`, `dieta_id`, `peso`, `altura`, `edad`) 
VALUES (8, 3, 80.5, 180, 30),
       (8, 4, 85.0, 180, 32);

-- Inserting into `rutina`
INSERT INTO `TAW`.`rutina` (`nombre`, `entrenador_id`, `descripcion`, `fecha_creacion`) 
VALUES ('General Fitness', 6,'Routine for overall fitness', '2024-04-01'),
       ('Strength Training',6, 'Routine for strength training', '2024-04-02');

-- Inserting into `cliente_rutina`
INSERT INTO `TAW`.`cliente_rutina` (`cliente_id`, `rutina_id`, `vigente`) 
VALUES (1, 1, b'1'),
       (2, 2, b'1');

-- Inserting into `comida`
INSERT INTO `TAW`.`comida` (`nombre`, `descripcion`) 
VALUES ('Chicken Salad', 'A healthy salad with grilled chicken breast'),
       ('Protein Shake', 'A nutritious protein shake with banana and peanut butter');

-- Inserting into `menu`
INSERT INTO `TAW`.`menu` (`nombre`, `descripcion`, `alergenos`) 
VALUES ('Healthy Choice', 'Menu focused on healthy, low-calorie options', 'None'),
       ('Power Boost', 'Menu designed to maximize energy and performance', 'Peanuts');

-- Inserting into `comida_menu`
INSERT INTO `TAW`.`comida_menu` (`comida_id`, `menu_id`) 
VALUES (1, 1),
       (2, 2);

-- Inserting into `desempeno`
INSERT INTO `TAW`.`desempeno` (`cliente_id`, `valoracion`, `peso_realizado`, `comentarios`) 
VALUES (1, 5, 80.5, 'Buen entrenamiento'),
       (2, 4, 85.0, 'Está bien , pero podría mejorarse');

-- Inserting into `dieta_comida`
INSERT INTO `TAW`.`dieta_comida` (`dieta_id`, `comida_id`, `momento_dia`) 
VALUES (3, 1, 1), -- 1: Breakfast
       (4, 2, 2); -- 2: Lunch

-- Inserting into `tipo_ejercicio`
INSERT INTO `TAW`.`tipo_ejercicio` (`tipo`) VALUES ('fuerza'), ('flexibilidad');

-- Inserting into `grupo_muscular`
INSERT INTO `TAW`.`grupo_muscular` (`grupo`) VALUES ('pecho'), ('bicep');

-- Inserting into `ejercicio`
INSERT INTO `TAW`.`ejercicio` (`nombre`, `tipo`, `descripcion`, `url_video`, `grupo_muscular_id`) 
VALUES ('Bench Press', 1, 'Standard bench press', '', 1),
       ('Bicep Curl', 2, 'Dumbbell bicep curl', '', 2);

-- Inserting into `entrenamiento`
INSERT INTO `TAW`.`entrenamiento` (`nombre`, `descripcion`, `tipo`) 
VALUES ('Upper Body Strength', 'Focused upper body strength training', 1),
       ('Flexibility Routine', 'Routine for improved flexibility', 2);

-- Inserting into `ejercicio_entrenamiento`
INSERT INTO `TAW`.`ejercicio_entrenamiento` (`ejercicio_id`, `entrenamiento_id`, `desempeno_id`, `series`, `repeticiones`, `peso`, `tiempo`, `distancia`, `orden`) 
VALUES (1, 1, 1, 3, 10, 50.0, 0, 0.0, 1),
       (2, 2, 2, 3, 12, 20.0, 0, 0.0, 1);

-- Inserting into `entrenamiento_rutina`
INSERT INTO `TAW`.`entrenamiento_rutina` (`entrenamiento_id`, `rutina_id`, `dia_semana`) 
VALUES (1, 1, 1),  -- 1: Monday
       (2, 2, 3);  -- 3: Wednesday
