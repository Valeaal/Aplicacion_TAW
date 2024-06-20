-- Insertar datos en la tabla tipo_usuario
INSERT INTO TAW.tipo_usuario (id, tipo) VALUES 
(1, 'admin'),
(2, 'entrenador_bodybuilding'),
(3, 'entrenador_crossfit'),
(4, 'dietista'),
(5, 'cliente');

-- Insertar datos en la tabla tipo_ejercicio
INSERT INTO TAW.tipo_ejercicio (id, tipo) VALUES 
(1, 'Fuerza'),
(2, 'Velocidad'),
(3, 'Resistencia'),
(4, 'Flexibilidad');

-- Insertar datos en la tabla grupo_muscular
INSERT INTO TAW.grupo_muscular (id, grupo) VALUES 
(1, 'Pecho'),
(2, 'Espalda'),
(3, 'Hombro'),
(4, 'Tricep'),
(5, 'Bicep'),
(6, 'Cuadricep'),
(7, 'Aductor'),
(8, 'Femoral'),
(9, 'Gluteo'),
(10, 'Gemelo');

-- Insertar datos en la tabla usuario
-- Insertar datos en la tabla usuario
INSERT INTO TAW.usuario (id, email, password, nombre, apellidos, fecha_nacimiento, pertenece_desde, tipo_usuario_id) VALUES 
(1, 'admin@example.com', 'password', 'Admin', 'Admin', '1980-01-01', '2020-01-01', 1),
(2, 'bbtrainer@example.com', 'password', 'John', 'Doe', '1990-05-15', '2021-06-01', 2),
(3, 'cftrainer@example.com', 'password', 'Jane', 'Smith', '1985-07-22', '2021-06-01', 3),
(4, 'dietista@example.com', 'password', 'Emily', 'Brown', '1992-03-10', '2021-06-01', 4),
(5, 'client@example.com', 'password', 'client', 'client', '1992-03-10', '2021-06-01', 5),
(6, 'client1@example.com', 'password', 'Pedro', 'Gomez', '1992-03-10', '2021-06-01', 5),
(7, 'client67@example.com', 'password', 'Javier', 'Smith', '1992-03-10', '2021-06-01', 5),
(8, 'migueltrainer@example.com', '123', 'Miguel', 'Pérez', '1992-03-10', '2020-01-01', 2),
(9, 'client9@example.com', 'password', 'Laura', 'Martínez', '1987-04-05', '2022-02-01', 5),
(10, 'client8@example.com', 'password', 'Carlos', 'Hernández', '1985-09-25', '2022-03-01', 5),
(11, 'dietista2@example.com', 'password', 'Michael', 'Johnson', '1991-04-10', '2021-06-01', 4),
(12, 'dietista3@example.com', 'password', 'Sarah', 'Lee', '1990-08-12', '2021-06-01', 4);

-- Insertar datos en la tabla dieta
INSERT INTO TAW.dieta (id, dietista_id, nombre, descripcion, fecha, calorias) VALUES 
(1, 4, 'Dieta Keto', 'Dieta basada en altos niveles de grasas y bajos en carbohidratos', '2023-01-01', 2000),
(2, 11, 'Dieta Mediterránea', 'Dieta basada en alimentos de la región mediterránea', '2023-02-01', 1800),
(3, 11, 'Dieta Paleo', 'Dieta basada en alimentos que se consumían en la era paleolítica', '2023-03-01', 2200),
(4, 12, 'Dieta Vegana', 'Dieta basada exclusivamente en alimentos de origen vegetal', '2023-04-01', 2000),
(5, 12, 'Dieta Sin Gluten', 'Dieta libre de alimentos que contienen gluten', '2023-05-01', 1900);

-- Insertar datos en la tabla cliente
INSERT INTO TAW.cliente (id, usuario_id, dieta_id, entrenador_id, peso, altura, edad, dietista_id) VALUES 
(1, 5, 1, 2, 70.5, 175, 30, 4),
(4, 6, 2, 3, 83, 188, 30, 11),
(6, 7, 3, 3, 67, 168, 28, 11),
(7, 9, 4, 8, 64, 160, 22, 12),
(8, 10, 5, 8, 70, 163, 30, 12);

-- Insertar datos en la tabla tipo_rutina
INSERT INTO TAW.tipo_rutina (id, tipo) VALUES 
(1, 'Bodybuilding'),
(2, 'Cross-Training');

-- Insertar datos en la tabla rutina
INSERT INTO TAW.rutina (id, nombre, descripcion, fecha_creacion, entrenador_id, tipo_rutina) VALUES 
(1, 'Rutina Fuerza', 'Rutina de fuerza para principiantes', '2023-01-01', 2, 1),
(2, 'Push-Pull', 'Rutina de fuerza para avanzados', '2023-06-14', 8, 1),
(3, 'Rutina CrossFit', 'Rutina intensa para crossfitters', '2023-03-01', 3, 2);

-- Insertar datos en la tabla cliente_rutina
INSERT INTO TAW.cliente_rutina (id, cliente_id, rutina_id, vigente) VALUES 
(1, 7, 2, b'1'),
(2, 8, 3, b'1');

-- Insertar datos en la tabla comida
INSERT INTO TAW.comida (id, nombre, descripcion) VALUES 
(1, 'Pollo a la plancha', 'Pollo cocido a la plancha con especias'),
(2, 'Ensalada César', 'Ensalada con lechuga, pollo, queso parmesano y aderezo César'),
(3, 'Batido de Proteínas', 'Batido rico en proteínas con plátano y leche'),
(4, 'Arroz Integral', 'Arroz integral cocido con verduras'),
(5, 'Salmón a la Parrilla', 'Salmón cocido a la parrilla con limón'),
(6, 'Frutas Mixtas', 'Mezcla de frutas frescas de temporada');

-- Insertar datos en la tabla menu
INSERT INTO TAW.menu (id, nombre, descripcion, alergenos) VALUES 
(1, 'Menu Saludable', 'Menu equilibrado sin alergenos', 'Ninguno'),
(2, 'Menu Deportivo', 'Menu rico en proteínas y carbohidratos', 'Ninguno'),
(3, 'Menu Vegano', 'Menu basado en alimentos de origen vegetal', 'Ninguno'),
(4, 'Menu Sin Gluten', 'Menu apto para personas celíacas', 'Ninguno'),
(5, 'Menu Bajo en Calorías', 'Menu diseñado para perder peso', 'Ninguno');

-- Insertar datos en la tabla comida_menu
INSERT INTO TAW.comida_menu (id, comida_id, menu_id) VALUES 
(1, 1, 1),
(2, 2, 2),
(3, 3, 2),
(4, 4, 3),
(5, 5, 3),
(6, 6, 4),
(7, 1, 5),
(8, 2, 5),
(9, 3, 4),
(10, 4, 2),
(11, 5, 2);


-- Insertar datos en la tabla desempeno
INSERT INTO TAW.desempeno (id, cliente_id, valoracion, peso_realizado, comentarios) VALUES 
(1, 1, 5, 70.5, 'Muy buen desempeño'),
(6, 4, 4, 70, 'Mejoras significativas'),
(7, 7, 2, 50, 'No me gusta mucho el ejercicio'),
(8, 8, 5, 100, 'Genial');

-- Insertar datos en la tabla dieta_comida
INSERT INTO TAW.dieta_comida (id, dieta_id, comida_id, momento_dia) VALUES 
(1, 1, 1, 1),
(2, 2, 1, 1),
(3, 2, 2, 2),
(4, 3, 3, 3),
(5, 3, 4, 1),
(6, 4, 5, 2),
(7, 4, 6, 3),
(8, 5, 1, 1),
(9, 5, 3, 2),
(10, 5, 4, 3);

-- Insertar datos en la tabla ejercicio
INSERT INTO TAW.ejercicio (id, nombre, descripcion, url_video, grupo_muscular_id, tipo) VALUES 
(1, 'Press de banca', 'Ejercicio para trabajar el pecho', 'http://example.com/video', 1, 1),
(2, 'Correr', 'Trabajamos la resistencia', 'http://example.com/video', NULL, 2),
(3, 'Yoga', 'Ejercicio para mejorar la flexibilidad', 'http://example.com/video', NULL, 4),
(4, 'Extensión de Tríceps', 'Ejercicio para trabajar los tríceps', 'http://example.com/extension_triceps', 4, 1),
(5, 'Aperturas de Pecho', 'Ejercicio para trabajar el pecho', 'http://example.com/aperturas_pecho', 1, 1),
(6, 'Jalón', 'Ejercicio para trabajar la espalda', 'http://example.com/jalon', 2, 1),
(7, 'Remo', 'Ejercicio para trabajar la espalda', 'http://example.com/remo', 2, 1),
(8, 'Curl de Bíceps', 'Ejercicio para trabajar los bíceps', 'http://example.com/curl_biceps', 5, 1),
(9, 'Extensión de Cuádriceps', 'Ejercicio para trabajar los cuádriceps', 'http://example.com/extension_cuadriceps', 6, 1),
(10, 'Sentadilla', 'Ejercicio para trabajar las piernas', 'http://example.com/sentadilla', 6, 1),
(11, 'Peso Muerto', 'Ejercicio para trabajar la espalda baja y las piernas', 'http://example.com/peso_muerto', 2, 1),
(12, 'Press Militar', 'Ejercicio para trabajar los hombros', 'http://example.com/press_militar', 3, 1),
(13, 'Elevaciones Laterales', 'Ejercicio para trabajar los hombros', 'http://example.com/elevaciones_laterales', 3, 1),
(14, 'Prensa de Piernas', 'Ejercicio para trabajar las piernas', 'http://example.com/prensa_piernas', 6, 1),
(15, 'Curl Femoral', 'Ejercicio para trabajar los isquiotibiales', 'http://example.com/curl_femoral', 8, 1);

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
(11, 'Elizabeth', '21-15-9 repeticiones de cleans (135/95 lb) y ring dips'),
(12, 'Entrenamiento de Empuje', 'Entrenamiento enfocado en ejercicios de empuje para el tren superior'),
(13, 'Entrenamiento de Tirón', 'Entrenamiento enfocado en ejercicios de tirón para el tren superior'),
(14, 'Entrenamiento de Pierna', 'Entrenamiento enfocado en ejercicios para las piernas'),
(15, 'Entrenamiento de Pecho y Bíceps', 'Entrenamiento enfocado en el pecho y los bíceps'),
(16, 'Entrenamiento de Espalda y Tríceps', 'Entrenamiento enfocado en la espalda y los tríceps'),
(17, 'Entrenamiento de Hombros', 'Entrenamiento enfocado en los hombros'),
(18, 'Entrenamiento de Cuerpo Completo', 'Entrenamiento que cubre todos los grupos musculares'),
(19, 'Entrenamiento de Resistencia', 'Entrenamiento para mejorar la resistencia cardiovascular'),
(20, 'Entrenamiento de Fuerza Máxima', 'Entrenamiento para desarrollar la fuerza máxima en los principales levantamientos'),
(21, 'Entrenamiento de Hipertrofia', 'Entrenamiento para aumentar la masa muscular');

-- Insertar datos en la tabla ejercicio_entrenamiento
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(1, 1, 1, 1, 3, 10, 70, NULL, NULL, 1),
(2, 4, 1, 6, 3, 15, 40, NULL, NULL, 2),
(3, 5, 1, 7, 3, 12, 30, NULL, NULL, 3),
(4, 2, 2, 8, 1, 1, NULL, NULL, 1600, 1),
(5, 10, 2, 1, 1, 300, NULL, NULL, NULL, 2),
(6, 4, 2, 6, 1, 200, NULL, NULL, NULL, 3),
(7, 2, 2, 7, 1, 1, NULL, NULL, 1600, 4),
(8, 2, 3, 8, 5, 30, NULL, NULL, 400, 1),
(9, 7, 3, 1, 4, 12, 53, NULL, NULL, 2),
(10, 3, 4, 6, 5, 20, NULL, NULL, NULL, 1),
(11, 1, 5, 7, 3, 10, 70, NULL, NULL, 1),
(12, 5, 5, 8, 3, 12, 40, NULL, NULL, 2),
(13, 7, 5, 1, 3, 15, 53, NULL, NULL, 3),
(14, 12, 5, 6, 3, 10, 60, NULL, NULL, 4),
(15, 15, 5, 7, 3, 15, 35, NULL, NULL, 5),
(16, 8, 3, 8, 3, 21, 70, NULL, NULL, 3),
(17, 11, 4, 1, 1, 30, NULL, NULL, NULL, 1),
(18, 6, 6, 6, 1, 100, NULL, NULL, NULL, 1),
(19, 4, 6, 7, 1, 100, NULL, NULL, NULL, 2),
(20, 10, 6, 8, 1, 100, NULL, NULL, NULL, 3),
(21, 3, 6, 1, 1, 100, NULL, NULL, NULL, 4),
(22, 2, 7, 6, 1, 1000, NULL, NULL, 1000, 1),
(23, 7, 7, 7, 1, 30, 53, NULL, NULL, 2),
(24, 1, 7, 8, 1, 50, 70, NULL, NULL, 3),
(25, 2, 8, 1, 5, 800, NULL, NULL, 4000, 1),
(26, 8, 8, 6, 5, 30, 70, NULL, NULL, 2),
(27, 2, 9, 7, 5, 400, NULL, NULL, 2000, 1),
(28, 10, 9, 8, 5, 30, NULL, NULL, NULL, 2),
(29, 12, 9, 1, 5, 30, 60, NULL, NULL, 3),
(30, 6, 10, 6, 1, 5, NULL, NULL, NULL, 1),
(31, 4, 10, 7, 1, 10, NULL, NULL, NULL, 2),
(32, 10, 10, 8, 1, 15, NULL, NULL, NULL, 3),
(33, 11, 11, 1, 3, 21, NULL, NULL, NULL, 1),
(34, 1, 12, 6, 3, 10, 70, NULL, NULL, 1),
(35, 5, 12, 7, 3, 12, 40, NULL, NULL, 2),
(36, 6, 13, 8, 3, 15, 50, NULL, NULL, 1),
(37, 4, 13, 1, 3, 10, 40, NULL, NULL, 2),
(38, 1, 14, 6, 3, 10, 70, NULL, NULL, 1),
(39, 12, 14, 7, 3, 15, 60, NULL, NULL, 2),
(40, 8, 15, 8, 3, 15, 35, NULL, NULL, 1),
(41, 7, 15, 1, 3, 10, 53, NULL, NULL, 2),
(42, 2, 16, 6, 3, 20, NULL, NULL, 600, 1),
(43, 7, 16, 7, 3, 15, 53, NULL, NULL, 2),
(44, 12, 17, 8, 3, 10, 60, NULL, NULL, 1),
(45, 1, 17, 1, 3, 12, 70, NULL, NULL, 2),
(46, 1, 18, 6, 3, 10, 70, NULL, NULL, 1),
(47, 2, 18, 7, 3, 20, NULL, NULL, 600, 2),
(48, 3, 18, 8, 3, 15, NULL, NULL, NULL, 3),
(49, 2, 19, 1, 5, 300, NULL, NULL, 1500, 1),
(50, 10, 19, 6, 5, 100, NULL, NULL, NULL, 2),
(51, 11, 20, 7, 5, 5, NULL, NULL, NULL, 1),
(52, 9, 20, 8, 5, 5, NULL, NULL, NULL, 2),
(53, 6, 21, 1, 5, 12, 50, NULL, NULL, 1),
(54, 5, 21, 6, 5, 15, 40, NULL, NULL, 2);


-- Insertar datos en la tabla rutina_entrenamiento
INSERT INTO TAW.entrenamiento_rutina (id, rutina_id, entrenamiento_id, dia_semana) VALUES 
(1, 1, 1, 1),
(2, 2, 2, 1),
(3, 2, 5, 2),
(4, 3, 3, 2),
(5, 3, 4, 3),
(6, 3, 6, 3),
(7, 3, 7, 4),
(8, 3, 8, 5),
(9, 3, 9, 6),
(10, 3, 10, 7),
(11, 3, 11, 7);
