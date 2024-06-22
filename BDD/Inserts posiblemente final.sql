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
(10, 'client8@example.com', 'password', 'Carlos', 'Hernández', '1985-09-25', '2022-03-01', 5),
(11, 'dietista2@example.com', 'password', 'James', 'Green', '1992-03-10', '2021-06-01', 4),
(12, 'dietista3@example.com', 'password', 'Javi', 'Fernández', '1992-03-10', '2021-06-01', 4);

-- Insertar datos en la tabla dieta
INSERT INTO TAW.dieta (id, dietista_id, nombre, descripcion, fecha, calorias) VALUES 
(1, 4, 'Dieta Keto', 'Dieta basada en altos niveles de grasas y bajos en carbohidratos', '2023-01-01', 2000),
(2, 11, 'Dieta Paleo', 'Dieta basada en alimentos no procesados', '2023-01-15', 1800),
(3, 12, 'Dieta Vegana', 'Dieta basada en plantas', '2023-02-01', 1600),
(4, 4, 'Dieta Mediterránea', 'Dieta rica en frutas, verduras y grasas saludables', '2023-02-15', 2200),
(5, 4, 'Dieta Low Carb', 'Dieta baja en carbohidratos', '2023-03-01', 1500),
(6, 11, 'Dieta Proteica', 'Dieta alta en proteínas', '2023-03-15', 2100),
(7, 4, 'Dieta Detox', 'Dieta de desintoxicación', '2023-04-01', 1200),
(8, 4, 'Dieta Sin Gluten', 'Dieta libre de gluten', '2023-04-15', 1700),
(9, 12, 'Dieta Dash', 'Dieta para la hipertensión', '2023-05-01', 2000),
(10, 4, 'Dieta HCG', 'Dieta basada en el uso de hormona HCG', '2023-05-15', 1300);

-- Insertar datos en la tabla cliente
INSERT INTO TAW.cliente (id, usuario_id, dieta_id, entrenador_id, peso, altura, edad, dietista_id) VALUES 
(1, 5, 1, 2, 70.5, 175, 30, 4),
(2, 6, 2, 3, 83, 188, 30, 11),
(3, 7, 3, 3, 67, 168, 28, 12),
(4, 9, 4, 8, 64, 160, 22, 4),
(5, 10, 5, 8, 70, 163, 30, 4);

-- Insertar datos en la tabla tipo_rutina
INSERT INTO TAW.tipo_rutina (id, tipo) VALUES 
(1, 'Bodybuilding'),
(2, 'Cross-Training');

-- Insertar datos en la tabla rutina
INSERT INTO TAW.rutina (id, nombre, descripcion, fecha_creacion, entrenador_id, tipo_rutina) VALUES 
(1, 'Rutina Pilates', 'Rutina de pilates para flexibilidad', '2023-01-01', 2, 1),
(2, 'Push-Pull', 'Rutina de fuerza para avanzados', '2023-06-14', 8, 1),
(3, 'Rutina HIIT', 'Rutina de alta intensidad', '2023-02-10', 3, 2),
(4, 'Rutina Fuerza', 'Rutina de fuerza para principiantes', '2023-03-01', 2, 2),
(5, 'Rutina Yoga', 'Rutina de yoga para relajación', '2023-03-15', 3, 2),
(6, 'Rutina Cardio', 'Rutina para mejorar la resistencia cardiovascular', '2023-04-01', 3, 2),
(7, 'Rutina Funcional', 'Rutina de entrenamiento funcional', '2023-04-15', 8, 1),
(8, 'Rutina Powerlifting', 'Rutina para levantar pesado', '2023-05-01', 2, 1),
(9, 'Rutina Calistenia', 'Rutina con ejercicios de peso corporal', '2023-05-15', 3, 1),
(10, 'Rutina Strongman', 'Rutina para ganar fuerza y masa muscular', '2023-06-01', 8, 1);

-- Insertar datos en la tabla cliente_rutina
INSERT INTO TAW.cliente_rutina (id, cliente_id, rutina_id, vigente) VALUES 
(3, 1, 4, b'1'),
(4, 2, 5, b'1'),
(5, 3, 6, b'1'),
(6, 4, 7, b'1'),
(7, 5, 8, b'1');

-- Insertar datos en la tabla comida
INSERT INTO TAW.comida (id, nombre, descripcion) VALUES 
(1, 'Menu Saludable', 'Menu equilibrado sin alergenos'),
(2, 'Menu Proteico', 'Menu alto en proteínas'),
(3, 'Menu Vegano', 'Menu basado en plantas'),
(4, 'Menu Keto', 'Menu bajo en carbohidratos'),
(5, 'Menu Paleo', 'Menu con alimentos no procesados'),
(6, 'Menu Mediterráneo', 'Menu con frutas y verduras'),
(7, 'Menu Low Carb', 'Menu bajo en carbohidratos'),
(8, 'Menu Detox', 'Menu de desintoxicación'),
(9, 'Menu Sin Gluten', 'Menu libre de gluten'),
(10, 'Menu Dash', 'Menu para la hipertensión');

-- Insertar datos en la tabla menu
INSERT INTO TAW.menu (id, nombre, descripcion, alergenos) VALUES 
(1, 'Pollo a la plancha', 'Pollo cocido a la plancha con especias', 'Ninguno'),
(2, 'Ensalada de quinoa', 'Ensalada fresca con quinoa y verduras', 'Ninguno'),
(3, 'Batido de proteínas', 'Batido con leche y proteínas', 'Ninguno'),
(4, 'Tacos de pescado', 'Tacos con pescado fresco y vegetales', 'Ninguno'),
(5, 'Salmón al horno', 'Salmón horneado con hierbas', 'Ninguno'),
(6, 'Avena con frutas', 'Avena cocida con frutas frescas', 'Ninguno'),
(7, 'Pasta integral', 'Pasta integral con salsa de tomate', 'Ninguno'),
(8, 'Arroz con pollo', 'Arroz cocido con pollo y vegetales', 'Ninguno'),
(9, 'Sopa de verduras', 'Sopa caliente con variedad de verduras', 'Ninguno'),
(10, 'Tortilla de espinacas', 'Tortilla hecha con espinacas frescas', 'Ninguno'),
(11, 'Yogur con fruta', 'Yogur griego alto en proteínas con fruta', 'Ninguno');

-- Insertar datos en la tabla comida_menu
INSERT INTO TAW.comida_menu (id, comida_id, menu_id) VALUES 
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 1, 9),
(12, 2, 11),
(13, 7, 10),
(14, 7, 7);

-- Insertar datos en la tabla desempeno
INSERT INTO TAW.desempeno (id, cliente_id, valoracion, peso_realizado, comentarios) VALUES 
(1, 1, 5, 70.5, 'Muy buen desempeño'),
(2, 2, 4, 80, 'Mejoras visibles'),
(3, 3, 3, 65, 'Promedio, seguir trabajando'),
(4, 4, 5, 62.5, 'Excelente progreso'),
(5, 4, 4, 70, 'Muy contenta con el progreso'),
(6, 5, 5, 110, 'Hoy ha sido un gran dia'),
(7, 5, 3, 72.5, 'Descanso insuficiente'),
(8, 5, 2, 50, 'Dolor en el codo'),
(9, 5, 2, 25, 'Debo esforzarme más');

-- Insertar datos en la tabla dieta_comida
INSERT INTO TAW.dieta_comida (id, dieta_id, comida_id, momento_dia) VALUES 
(1, 1, 1, 2),
(2, 2, 2, 2),
(3, 3, 3, 3),
(4, 4, 4, 1),
(5, 5, 5, 1),
(6, 6, 6, 1),
(7, 7, 7, 2),
(8, 8, 8, 3),
(9, 9, 9, 2),
(10, 10, 10, 2),
(11, 1, 2, 1),
(12, 1, 7, 3);

-- Insertar datos en la tabla tipo_ejercicio
INSERT INTO TAW.tipo_ejercicio (id, tipo) VALUES 
(1, 'Fuerza'),
(2, 'Velocidad'),
(3, 'Flexibilidad'),
(4, 'Resistencia'),
(5, 'Potencia'),
(6, 'Estabilidad'),
(7, 'Movilidad');

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
(1, 'Press de banca', 'Ejercicio para trabajar el pecho', 'https://www.youtube.com/watch?v=rT7DgCr-3pg', 1, 1),
(4, 'Extensión de Tríceps', 'Ejercicio para trabajar los tríceps', 'https://www.youtube.com/watch?v=8fOyov2P4ZQ', 4, 1),
(5, 'Aperturas de Pecho', 'Ejercicio para trabajar el pecho', 'https://www.youtube.com/watch?v=eozdVDA78K0', 1, 1),
(6, 'Jalón', 'Ejercicio para trabajar la espalda', 'https://www.youtube.com/watch?v=CAwf7n6Luuc', 2, 5),
(7, 'Remo', 'Ejercicio para trabajar la espalda', 'https://www.youtube.com/watch?v=kBWAon7ItDw', 2, 5),
(8, 'Curl de Bíceps', 'Ejercicio para trabajar los bíceps', 'https://www.youtube.com/watch?v=ykJmrZ5v0Oo', 5, 1),
(9, 'Extensión de Cuádriceps', 'Ejercicio para trabajar los cuádriceps', 'https://www.youtube.com/watch?v=YyvSfVjQeL0', 6, 1),
(10, 'Sentadilla', 'Ejercicio para trabajar las piernas', 'https://www.youtube.com/watch?v=1oed-UmAxFs', 6, 1),
(11, 'Peso Muerto', 'Ejercicio para trabajar la espalda baja y las piernas', 'https://www.youtube.com/watch?v=op9kVnSso6Q', 2, 1),
(12, 'Press Militar', 'Ejercicio para trabajar los hombros', 'https://www.youtube.com/watch?v=B-aVuyhvLHU', 3, 1),
(13, 'Elevaciones Laterales', 'Ejercicio para trabajar los hombros', 'https://www.youtube.com/watch?v=oLQHnfMVS0I', 3, 1),
(14, 'Prensa de Piernas', 'Ejercicio para trabajar las piernas', 'https://www.youtube.com/watch?v=IZxyjW7MPJQ', 6, 1),
(15, 'Curl Femoral', 'Ejercicio para trabajar los isquiotibiales', 'https://www.youtube.com/watch?v=1Tq3QdYUuHs', 8, 1),
(16, 'Flexiones', 'Ejercicio básico para trabajar el pecho y tríceps', 'https://www.youtube.com/watch?v=_l3ySVKYVJ8', 1, 1),
(17, 'Dominadas', 'Ejercicio para trabajar la espalda y los bíceps', 'https://www.youtube.com/watch?v=eGo4IYlbE5g', 2, 5),
(18, 'Abdominales', 'Ejercicio para trabajar el abdomen', 'https://www.youtube.com/watch?v=ZJZzdVt4L1s', 9, 6),
(19, 'Fondos de Tríceps', 'Ejercicio para trabajar los tríceps', 'https://www.youtube.com/watch?v=6kALZikXxLc', 4, 1),
(20, 'Press Francés', 'Ejercicio para trabajar los tríceps', 'https://www.youtube.com/watch?v=ZO81bExngMI', 4, 1),
(21, 'Correr', 'Trabajamos la resistencia', 'http://example.com/video', NULL, 4),
(22, 'Estiramiento de espalda', 'Estiramientos para flexibilizar la espalda baja', 'https://www.youtube.com/watch?v=XcJ1zVXfTfQ', 2, 3);

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

-- Empuje
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(2, 1, 12, NULL, 4, 12, 60.0, NULL, NULL, 1),  -- Press de banca
(3, 12, 12, NULL, 3, 10, 70.0, NULL, NULL, 2);  -- Press Militar

-- Tirón
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(4, 6, 13, NULL, 4, 10, 50.0, NULL, NULL, 1),  -- Jalón
(5, 7, 13, 4, 4, 10, 60.0, NULL, NULL, 2);  -- Remo

-- Pierna
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(6, 10, 14, NULL, 4, 12, 80.0, NULL, NULL, 1),  -- Sentadilla
(7, 14, 14, NULL, 4, 15, 100.0, NULL, NULL, 2);  -- Prensa de Piernas

-- Pecho y Bíceps
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(8, 1, 15, NULL, 4, 10, 70.0, NULL, NULL, 1),  -- Press de banca
(9, 8, 15, 9, 3, 12, 30.0, NULL, NULL, 2);  -- Curl de Bíceps

-- Espalda y Tríceps
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(10, 7, 16, NULL, 4, 10, 60.0, NULL, NULL, 1),  -- Remo
(11, 4, 16, NULL, 3, 12, 25.0, NULL, NULL, 2);  -- Extensión de Tríceps

-- Hombros
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(12, 12, 17, 8, 4, 10, 70.0, NULL, NULL, 1),  -- Press Militar
(13, 13, 17, NULL, 3, 12, 15.0, NULL, NULL, 2);  -- Elevaciones Laterales

-- Cuerpo Completo
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(14, 1, 18, NULL, 3, 10, 70.0, NULL, NULL, 1),  -- Press de banca
(15, 10, 18, 6, 3, 10, 100.0, NULL, NULL, 2);  -- Sentadilla

-- Resistencia
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(16, 21, 19, 2, 0, 0, 0, 30, NULL, 1);  -- Correr

-- Fuerza Máxima
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(17, 11, 20, NULL, 3, 5, 100.0, NULL, NULL, 1),  -- Peso Muerto
(18, 1, 20, NULL, 3, 5, 90.0, NULL, NULL, 2);  -- Press de banca

-- Hipertrofia
INSERT INTO TAW.ejercicio_entrenamiento (id, ejercicio_id, entrenamiento_id, desempeno_id, series, repeticiones, peso, tiempo, distancia, orden) VALUES 
(19, 1, 21, 5, 4, 12, 70.0, NULL, NULL, 1), -- Press de banca
(20, 1, 21, 7, 3, 12, 80.0, NULL, NULL, 1), -- Press de banca
(21, 10, 21, NULL, 4, 12, 80.0, NULL, NULL, 2);  -- Sentadilla

-- Insertar datos en la tabla entrenamiento_rutina
INSERT INTO TAW.entrenamiento_rutina (id, entrenamiento_id, rutina_id, dia_semana) VALUES 
(1, 1, 1, 1),
(2, 12, 2, 1),
(3, 13, 2, 3),
(4, 14, 2, 5),
(5, 15, 3, 2),
(6, 16, 3, 4),
(7, 17, 4, 1),
(8, 18, 4, 3),
(9, 19, 5, 2),
(10, 20, 5, 4);
