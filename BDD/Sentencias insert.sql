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
(7, 'client67@example.com', 'password', 'Javier', 'Smith', '1992-03-10', '2021-06-01', 5),
(8, 'migueltrainer@example.com', '123', 'Miguel', 'Pérez', '1992-03-10', '2020-01-01', 2),
(9, 'client9@example.com', 'password', 'Laura', 'Martínez', '1987-04-05', '2022-02-01', 5),
(10, 'client8@example.com', 'password', 'Carlos', 'Hernández', '1985-09-25', '2022-03-01', 5);

-- Insertar datos en la tabla dieta
INSERT INTO TAW.dieta (id, dietista_id, nombre, descripcion, fecha, calorias) VALUES 
(1, 4, 'Dieta Keto', 'Dieta basada en altos niveles de grasas y bajos en carbohidratos', '2023-01-01', 2000);

-- Insertar datos en la tabla cliente
INSERT INTO TAW.cliente (id, usuario_id, dieta_id, entrenador_id, peso, altura, edad, dietista_id) VALUES 
(1, 5, 1, 2, 70.5, 175, 30, 4),
(4, 6, 1, 3, 83, 188, 30, 4),
(6, 7, 1, 3, 67, 168, 28, 4),
(7, 9, 1, 8, 64, 160, 22, 4),
(8, 10, 1, 8, 70, 163, 30, 4);

-- Insertar datos en la tabla tipo_rutina
INSERT INTO TAW.tipo_rutina (id, tipo) VALUES 
(1, 'Bodybuilding'),
(2, 'Cross-Training');

-- Insertar datos en la tabla rutina
INSERT INTO TAW.rutina (id, nombre, descripcion, fecha_creacion, entrenador_id, tipo_rutina) VALUES 
(1, 'Rutina Fuerza', 'Rutina de fuerza para principiantes', '2023-01-01', 2, 1),
(2, 'Push-Pull', 'Rutina de fuerza para avanzados', '2023-06-14', 8, 1),
(3, 'Rutina Fuerza', 'Rutina de fuerza para principiantes', '2023-01-01', 8, 1);

-- Insertar datos en la tabla cliente_rutina
INSERT INTO TAW.cliente_rutina (id, cliente_id, rutina_id, vigente) VALUES 
(1, 7, 2, b'1'),
(2, 8, 3, b'1');

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
(6, 4, 4, 70, 'Mejoras significativas'),
(7, 7, 2, 50, 'No me gusta mucho el ejercicio'),
(8, 8, 5, 100, 'Genial');


-- Insertar datos en la tabla dieta_comida
INSERT INTO TAW.dieta_comida (id, dieta_id, comida_id, momento_dia) VALUES 
(1, 1, 1, 1);

-- Insertar datos en la tabla tipo_ejercicio
INSERT INTO TAW.tipo_ejercicio (id, tipo) VALUES 
(1, 'Fuerza');

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

-- Insertar datos en la tabla ejercicio
INSERT INTO TAW.ejercicio (id, nombre, descripcion, url_video, grupo_muscular_id, tipo) VALUES 
(1, 'Press de banca', 'Ejercicio para trabajar el pecho', 'http://example.com/video', 1, 1),
(4, 'Extensión de Tríceps', 'Ejercicio para trabajar los tríceps', 'http://example.com/extension_triceps', 2, 1),
(5, 'Aperturas de Pecho', 'Ejercicio para trabajar el pecho', 'http://example.com/aperturas_pecho', 1, 1),
(6, 'Jalón', 'Ejercicio para trabajar la espalda', 'http://example.com/jalon', 2, 1),
(7, 'Remo', 'Ejercicio para trabajar la espalda', 'http://example.com/remo', 2, 1),
(8, 'Curl de Bíceps', 'Ejercicio para trabajar los bíceps', 'http://example.com/curl_biceps', 2, 1),
(9, 'Extensión de Cuádriceps', 'Ejercicio para trabajar los cuádriceps', 'http://example.com/extension_cuadriceps', 3, 1),
(10, 'Sentadilla', 'Ejercicio para trabajar las piernas', 'http://example.com/sentadilla', 3, 1),
(11, 'Peso Muerto', 'Ejercicio para trabajar la espalda baja y las piernas', 'http://example.com/peso_muerto', 2, 1),
(12, 'Press Militar', 'Ejercicio para trabajar los hombros', 'http://example.com/press_militar', 1, 1),
(13, 'Elevaciones Laterales', 'Ejercicio para trabajar los hombros', 'http://example.com/elevaciones_laterales', 1, 1),
(14, 'Prensa de Piernas', 'Ejercicio para trabajar las piernas', 'http://example.com/prensa_piernas', 3, 1),
(15, 'Curl Femoral', 'Ejercicio para trabajar los isquiotibiales', 'http://example.com/curl_femoral', 3, 1),
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
(1, 1, 1, 1, 3, 10, 70.0, NULL, NULL, 1),
(21, 23, 2, 6, NULL, NULL, NULL, 3, 80, 1);
-- Empuje
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(2, 1, 12, 7, 4, 12, 60.0, NULL, NULL, 1),  -- Press de banca
(3, 12, 12, 8, 3, 10, 70.0, NULL, NULL, 2);  -- Press Militar

-- Tirón
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(4, 6, 13, NULL, 4, 10, 50.0, NULL, NULL, 1),  -- Jalón
(5, 7, 13, NULL, 4, 10, 60.0, NULL, NULL, 2);  -- Remo

-- Pierna
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(6, 10, 14, NULL, 4, 12, 80.0, NULL, NULL, 1),  -- Sentadilla
(7, 14, 14, NULL, 4, 15, 100.0, NULL, NULL, 2);  -- Prensa de Piernas

-- Pecho y Bíceps
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(8, 1, 15, NULL, 4, 10, 70.0, NULL, NULL, 1),  -- Press de banca
(9, 8, 15, NULL, 3, 12, 30.0, NULL, NULL, 2);  -- Curl de Bíceps

-- Espalda y Tríceps
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(10, 7, 16, NULL, 4, 10, 60.0, NULL, NULL, 1),  -- Remo
(11, 4, 16, NULL, 3, 12, 25.0, NULL, NULL, 2);  -- Extensión de Tríceps

-- Hombros
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(12, 12, 17, NULL, 4, 10, 50.0, NULL, NULL, 1),  -- Press Militar
(13, 13, 17, NULL, 3, 12, 15.0, NULL, NULL, 2);  -- Elevaciones Laterales

-- Cuerpo Completo
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(14, 1, 18, NULL, 3, 10, 70.0, NULL, NULL, 1),  -- Press de banca
(15, 10, 18, NULL, 3, 10, 80.0, NULL, NULL, 2);  -- Sentadilla

-- Resistencia
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(16, 23, 19, NULL, NULL, NULL, NULL, 30, NULL, 1);  -- Correr

-- Fuerza Máxima
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(17, 11, 20, NULL, 3, 5, 100.0, NULL, NULL, 1),  -- Peso Muerto
(18, 1, 20, NULL, 3, 5, 90.0, NULL, NULL, 2);  -- Press de banca

-- Hipertrofia
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(19, 1, 21, NULL, 4, 12, 70.0, NULL, NULL, 1),  -- Press de banca
(20, 10, 21, NULL, 4, 12, 80.0, NULL, NULL, 2);  -- Sentadilla

-- Insertar datos en la tabla entrenamiento_rutina
INSERT INTO TAW.entrenamiento_rutina (id, entrenamiento_id, rutina_id, dia_semana) VALUES 
(1, 1, 1, 1),
(2, 12, 2, 1),
(3, 13, 2, 3),
(4, 14, 2, 5);

