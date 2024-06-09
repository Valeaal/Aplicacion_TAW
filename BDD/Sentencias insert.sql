-- Insertar datos en la tabla tipo_usuario
INSERT INTO TAW.tipo_usuario (id, tipo) VALUES 
(1, 'admin'),
(2, 'entrenador_bodybuilding'),
(3, 'entrenador_crossfit'),
(4, 'dietista'),
(5, 'cliente');

-- Insertar datos en la tabla usuario
INSERT INTO TAW.usuario (id, email, password, nombre, apellidos, fecha_nacimiento, pertenece_desde, tipo_usuario_id) VALUES 
(1, 'admin@example.com', 'password', 'Admin', 'Admin', '1980-01-01', '2020-01-01', 1),
(2, 'bbtrainer@example.com', 'password', 'John', 'Doe', '1990-05-15', '2021-06-01', 2),
(3, 'cftrainer@example.com', 'password', 'Jane', 'Smith', '1985-07-22', '2021-06-01', 3),
(4, 'dietista@example.com', 'password', 'Emily', 'Brown', '1992-03-10', '2021-06-01', 4),
(5, 'client@example.com', 'password', 'client', 'client', '1992-03-10', '2021-06-01', 5),
(6, 'client1@example.com', 'password', 'Pedro', 'Gomez', '1992-03-10', '2021-06-01', 5),
(7, 'client67@example.com', 'password', 'Javier', 'Smith', '1992-03-10', '2021-06-01', 5);

-- Insertar datos en la tabla dieta
INSERT INTO TAW.dieta (id, dietista_id, nombre, descripcion, fecha, calorias) VALUES 
(1, 4, 'Dieta Keto', 'Dieta basada en altos niveles de grasas y bajos en carbohidratos', '2023-01-01', 2000);

-- Insertar datos en la tabla cliente
INSERT INTO TAW.cliente (id, usuario_id, dieta_id, entrenador_id, peso, altura, edad, dietista_id) VALUES 
(1, 5, 1, 2, 70.5, 175, 30, 4),
(4, 6, 1, 3, 83, 188, 30, 4),
(6, 7, 1, 3, 67, 168, 28, 4);
-- Insertar datos en la tabla tipo_rutina
INSERT INTO TAW.tipo_rutina (id, tipo) VALUES 
(1, 'Cross-Training'),
(2, 'Bodybuilding');

-- Insertar datos en la tabla rutina
INSERT INTO TAW.rutina (id, nombre, descripcion, fecha_creacion, entrenador_id, tipo_rutina) VALUES 
(1, 'Rutina Fuerza', 'Rutina de fuerza para principiantes', '2023-01-01', 2, 2);

-- Insertar datos en la tabla cliente_rutina
INSERT INTO TAW.cliente_rutina (id, cliente_id, rutina_id, vigente) VALUES 
(1, 1, 1, b'1');

-- Insertar datos en la tabla comida
INSERT INTO TAW.comida (id, nombre, descripcion) VALUES 
(1, 'Pollo a la plancha', 'Pollo cocido a la plancha con especias');

-- Insertar datos en la tabla menu
INSERT INTO TAW.menu (id, nombre, descripcion, alergenos) VALUES 
(1, 'Menu Saludable', 'Menu equilibrado sin alergenos', 'Ninguno');

-- Insertar datos en la tabla comida_menu
INSERT INTO TAW.comida_menu (id, comida_id, menu_id) VALUES 
(1, 1, 1);

-- Insertar datos en la tabla desempeno
INSERT INTO TAW.desempeno (id, cliente_id, valoracion, peso_realizado, comentarios) VALUES 
(1, 1, 5, 70.5, 'Muy buen desempeño'),
(6, 4, 4, 70, 'Mejoras significativas');

-- Insertar datos en la tabla dieta_comida
INSERT INTO TAW.dieta_comida (id, dieta_id, comida_id, momento_dia) VALUES 
(1, 1, 1, 1);

-- Insertar datos en la tabla tipo_ejercicio
INSERT INTO TAW.tipo_ejercicio (id, tipo) VALUES 
(1, 'fuerza');

-- Insertar datos en la tabla grupo_muscular
INSERT INTO TAW.grupo_muscular (id, grupo) VALUES 
(1, 'pecho');

-- Insertar datos en la tabla ejercicio
INSERT INTO TAW.ejercicio (id, nombre, descripcion, url_video, grupo_muscular_id, tipo) VALUES 
(1, 'Press de banca', 'Ejercicio para trabajar el pecho', 'http://example.com/video', 1, 1),
(23, 'Correr', 'Trabajamos la resistencia', 'http://example.com/video', NULL, 1);

-- Insertar datos en la tabla entrenamiento
INSERT INTO TAW.entrenamiento (id, nombre, descripcion) VALUES 
(1, 'Entrenamiento de fuerza', 'Entrenamiento de fuerza para todos los niveles'),
(2, 'Murph', '1 milla de carrera, 100 pull-ups, 200 push-ups, 300 squats, 1 milla de carrera (con chaleco de 20/14 lb opcional)'),
(3, 'Helen', '3 rondas por tiempo: 400 metros de carrera, 21 kettlebell swings (53/35 lb), 12 pull-ups'),
(4, 'Grace', '30 clean and jerks (135/95 lb) por tiempo'),
(5, 'Diane', '21-15-9 repeticiones de deadlifts (225/155 lb) y handstand push-ups'),
(6, 'Angie', '100 pull-ups, 100 push-ups, 100 sit-ups, 100 squats por tiempo'),
(7, 'Jackie', '1000 metros de remo, 50 thrusters (45 lb), 30 pull-ups por tiempo'),
(8, 'Eva', '5 rondas por tiempo: 800 metros de carrera, 30 kettlebell swings (70/53 lb), 30 pull-ups'),
(9, 'Kelly', '5 rondas por tiempo: 400 metros de carrera, 30 box jumps (24/20 pulgadas), 30 wall balls (20/14 lb)'),
(10, 'Cindy', 'AMRAP en 20 minutos: 5 pull-ups, 10 push-ups, 15 squats'),
(11, 'Elizabeth', '21-15-9 repeticiones de cleans (135/95 lb) y ring dips');

-- Insertar datos en la tabla ejercicio_entrenamiento
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(1, 1, 1, 1, 3, 10, 70.0, NULL, NULL, 1),
(5, 23, 2, 6, NULL, NULL, NULL, 3, 80, 1);

-- Insertar datos en la tabla entrenamiento_rutina
INSERT INTO TAW.entrenamiento_rutina (id, entrenamiento_id, rutina_id, dia_semana) VALUES 
(1, 1, 1, 1);
