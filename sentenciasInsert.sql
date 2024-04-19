-- Sentencias INSERT usuario
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('ejemplo1@gmail.com', ' password123', ' Juan', ' Pérez Gómez', ' 1990-05-15', ' 2020-01-01', ' 1');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('usuario2@example.com', ' securepass', ' María', ' Rodríguez López', ' 1985-08-22', ' 2019-11-10', ' 2');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('user3@mail.com', ' mypass123', ' Pablo', ' Martínez Fernández', ' 1995-03-10', ' 2021-02-28', ' 2');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('testuser4@example.org', ' testpass456', ' Laura', ' Sánchez Ruiz', ' 1988-11-03', ' 2018-07-15', ' ');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('demo5@hotmail.com', ' demopass789', ' Andrés', ' López González', ' 1979-09-20', ' 2017-12-05', ' 3');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('usuario6@domain.com', ' userpass321', ' Ana', ' Pérez García', ' 1992-06-28', ' 2022-03-10', ' 4');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('exampleuser7@gmail.com', ' examplepass', ' Diego', ' Gómez Martín', ' 1983-04-17', ' 2016-10-20', ' ');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('sampleuser8@example.net', ' sample123', ' Marta', ' Rodríguez Díaz', ' 1975-12-08', ' 2015-05-30', ' ');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('user9@mail.org', ' pass1234', ' Sergio', ' López Fernández', ' 1987-07-25', ' 2014-09-12', ' ');
INSERT INTO usuario (email,  password,  nombre,  apellidos,  fecha_nacimiento,  pertenece_desde,  tipo_usuario_id) VALUES ('usuario10@example.com', ' pass4567', ' Elena', ' Sánchez Pérez', ' 1998-02-14', ' 2023-08-18', ' ');

-- Sentencias INSERT dieta
INSERT INTO dieta (dietista_id, nombre, descripción, fecha) VALUES ('3', ' Dieta cetogénica', ' Una dieta alta en grasas y baja en carbohidratos diseñada para inducir la cetosis.', ' 2024-04-19');
INSERT INTO dieta (dietista_id, nombre, descripción, fecha) VALUES ('2', ' Dieta mediterránea', ' Una dieta rica en frutas', ' verduras', ' pescado y aceite de oliva', ' inspirada en los hábitos alimenticios de los países mediterráneos.', ' 2024-04-18');
INSERT INTO dieta (dietista_id, nombre, descripción, fecha) VALUES ('1', ' Dieta alta en proteínas', ' Una dieta que se centra en consumir alimentos ricos en proteínas para aumentar la masa muscular y perder grasa.', ' 2024-04-17');
INSERT INTO dieta (dietista_id, nombre, descripción, fecha) VALUES ('4', ' Dieta sin gluten', ' Una dieta que excluye el gluten', ' diseñada para personas con enfermedad celíaca o sensibilidad al gluten.', ' 2024-04-16');
INSERT INTO dieta (dietista_id, nombre, descripción, fecha) VALUES ('5', ' Dieta vegetariana', ' Una dieta que excluye la carne y los productos derivados de animales', ' centrándose en alimentos de origen vegetal.', ' 2024-04-15');

-- Sentencias INSERT rutina
INSERT INTO rutina (nombre, descripcion, fecha_creacion) VALUES ('Rutina de entrenamiento de fuerza', ' Una rutina diseñada para aumentar la fuerza muscular.', ' 2024-04-19');
INSERT INTO rutina (nombre, descripcion, fecha_creacion) VALUES ('Rutina de cardio', ' Una rutina centrada en mejorar la resistencia cardiovascular.', ' 2024-04-18');
INSERT INTO rutina (nombre, descripcion, fecha_creacion) VALUES ('Rutina de yoga matutina', ' Una rutina de yoga diseñada para practicar por la mañana.', ' 2024-04-17');
INSERT INTO rutina (nombre, descripcion, fecha_creacion) VALUES ('Rutina de entrenamiento HIIT', ' Una rutina de entrenamiento de intervalos de alta intensidad.', ' 2024-04-16');
INSERT INTO rutina (nombre, descripcion, fecha_creacion) VALUES ('Rutina de estiramiento', ' Una rutina diseñada para mejorar la flexibilidad y prevenir lesiones.', ' 2024-04-15');

-- Sentencias INSERT cliente
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('1', ' 1', ' 1', ' 70.5', ' 175', ' 30');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('2', ' 3', ' NULL', ' 65.2', ' 168', ' 28');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('3', ' NULL', ' 2', ' 80.0', ' 180', ' 35');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('4', ' 2', ' 4', ' 55.8', ' 160', ' 25');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('5', ' 1', ' 3', ' 72.3', ' 172', ' 32');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('6', ' 4', ' NULL', ' 68.9', ' 178', ' 29');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('7', ' NULL', ' NULL', ' 63.5', ' 165', ' 27');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('8', ' 5', ' 5', ' 75.1', ' 182', ' 33');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('9', ' 3', ' 2', ' 69.7', ' 170', ' 31');
INSERT INTO cliente (usuario_id,  rutina_id,  dieta_id,  peso,  altura,  edad) VALUES ('10', ' 2', ' 4', ' 61.4', ' 175', ' 26');

-- Sentencias INSERT cliente_rutina
INSERT INTO cliente_rutina (cliente_id,  rutina_id,  vigente) VALUES ('1', '1', '1');
INSERT INTO cliente_rutina (cliente_id,  rutina_id,  vigente) VALUES ('2', '2', '1');
INSERT INTO cliente_rutina (cliente_id,  rutina_id,  vigente) VALUES ('3', '3', '0');
INSERT INTO cliente_rutina (cliente_id,  rutina_id,  vigente) VALUES ('4', '4', '1');
INSERT INTO cliente_rutina (cliente_id,  rutina_id,  vigente) VALUES ('5', '5', '0');

-- Sentencias INSERT entrenamiento
INSERT INTO entrenamiento (nombre, descripcion, tipo) VALUES ('Empujes', 'Conjuntos de ejercicios de fuerza de empuje como pecho u hombros', '1');
INSERT INTO entrenamiento (nombre, descripcion, tipo) VALUES ('Tracciones', 'Conjuntos de ejercicios de fuerza como espalda o bíceps', '2');
INSERT INTO entrenamiento (nombre, descripcion, tipo) VALUES ('Piernas', 'Conjunto de ejercicios de fuerzas para las piernas', '3');
INSERT INTO entrenamiento (nombre, descripcion, tipo) VALUES ('Entrenamiento de cardio', ' Conjunto de ejercicios aeróbicos para mejorar la resistencia cardiovascular.', ' 4');
INSERT INTO entrenamiento (nombre, descripcion, tipo) VALUES ('Entrenamiento HIIT', ' Conjunto de ejercicios de intervalos de alta intensidad para quemar grasa y mejorar la resistencia.', ' 5');
INSERT INTO entrenamiento (nombre, descripcion, tipo) VALUES ('Entrenamiento de flexibilidad', ' Conjunto de estiramientos para mejorar la flexibilidad muscular y prevenir lesiones.', ' 6');
INSERT INTO entrenamiento (nombre, descripcion, tipo) VALUES ('Entrenamiento de resistencia', ' Conjunto de ejercicios de resistencia para mejorar la resistencia muscular y cardiovascular.', ' 7');

-- Sentencias INSERT entrenamiento_rutina
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('1', '1', '1');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('2', '1', '2');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('3', '2', '3');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('4', '2', '4');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('5', '3', '5');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('6', '3', '6');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('7', '4', '1');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('8', '4', '2');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('9', '5', '4');
INSERT INTO entrenamiento_rutina (entrenamiento_id,  rutina_id,  dia_semana) VALUES ('10', '5', '6');

-- Sentencias INSERT
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Flexiones de pecho', ' 2', ' Ejercicio para trabajar los músculos del pecho y los brazos.', " 'https://www.ejemplo.com/videos/flexiones'", ' 2');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Dominadas', ' 3', ' Ejercicio para fortalecer los músculos de la espalda y los brazos.', " 'https://www.ejemplo.com/videos/dominadas'", ' 3');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Plancha', ' 4', ' Ejercicio para fortalecer el core y mejorar la estabilidad.', " 'https://www.ejemplo.com/videos/plancha'", ' 4');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Press de hombros', ' 1', ' Ejercicio para desarrollar los músculos de los hombros.', " 'https://www.ejemplo.com/videos/press-hombros'", ' 5');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Abdominales', ' 2', ' Ejercicio para fortalecer los músculos abdominales.', " 'https://www.ejemplo.com/videos/abdominales'", ' 6');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Zancadas', ' 1', ' Ejercicio para trabajar los músculos de las piernas y glúteos.', " 'https://www.ejemplo.com/videos/zancadas'", ' 1');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Fondos en paralelas', ' 3', ' Ejercicio para trabajar los tríceps y el pecho.', " 'https://www.ejemplo.com/videos/fondos-paralelas'", ' 2');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Curl de bíceps', ' 4', ' Ejercicio para aislar y fortalecer los músculos bíceps.', " 'https://www.ejemplo.com/videos/curl-biceps'", ' 3');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Peso muerto', ' 1', ' Ejercicio compuesto para trabajar la espalda baja y los glúteos.', " 'https://www.ejemplo.com/videos/peso-muerto'", ' 4');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Correr', ' 5', ' Ejercicio cardiovascular para mejorar la resistencia.', " 'https://www.ejemplo.com/videos/correr'", ' 7');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Natación', ' 5', ' Ejercicio de cuerpo completo que mejora la resistencia cardiovascular y la fuerza muscular.', " 'https://www.ejemplo.com/videos/natacion'", ' 8');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Yoga', ' 6', ' Ejercicio que combina posturas físicas', ' respiración y meditación para mejorar la flexibilidad y reducir el estrés.', " 'https://www.ejemplo.com/videos/yoga'", ' 9');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Pilates', ' 6', ' Ejercicio centrado en el fortalecimiento de los músculos centrales del cuerpo.', " 'https://www.ejemplo.com/videos/pilates'", ' 10');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Ciclismo', ' 5', ' Ejercicio cardiovascular que mejora la resistencia y fortalece las piernas.', " 'https://www.ejemplo.com/videos/ciclismo'", ' 7');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Ejercicio de estiramiento', ' 7', ' Rutina de estiramientos para mejorar la flexibilidad muscular y prevenir lesiones.', " 'https://www.ejemplo.com/videos/estiramientos'", ' 11');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Baile', ' 8', ' Ejercicio divertido que mejora la coordinación', ' el equilibrio y la resistencia.', " 'https://www.ejemplo.com/videos/baile'", ' 12');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Pilates acuático', ' 6', ' Variante de Pilates realizada en el agua para mejorar la fuerza y flexibilidad sin impacto en las articulaciones.', " 'https://www.ejemplo.com/videos/pilates-acuatico'", ' 10');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Saltar a la cuerda', ' 5', ' Ejercicio cardiovascular que mejora la coordinación y la resistencia.', " 'https://www.ejemplo.com/videos/saltar-cuerda'", ' 7');
INSERT INTO nombre, tipo, descripcion, url_video, grupo_muscular_id (nombre,  tipo,  descripcion,  url_video,  grupo_muscular_id) VALUES ('Tai Chi', ' 6', ' Arte marcial chino que combina movimientos suaves y fluidos con técnicas de respiración para mejorar la salud física y mental.', " 'https://www.ejemplo.com/videos/tai-chi'", ' 13');

-- Sentencias INSERT
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('1', ' 1', ' 1', ' 3', ' 10', ' 20.5', ' 0', ' 0', ' 1');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('2', ' 2', ' 1', ' 4', ' 12', ' 0', ' 0', ' 0', ' 2');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('3', ' 3', ' 2', ' 3', ' 8', ' 0', ' 0', ' 0', ' 1');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('4', ' 4', ' 2', ' 3', ' 15', ' 0', ' 0', ' 0', ' 2');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('5', ' 5', ' 3', ' 4', ' 10', ' 25.0', ' 0', ' 0', ' 1');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('6', ' 6', ' 3', ' 3', ' 12', ' 0', ' 0', ' 0', ' 2');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('7', ' 7', ' 4', ' 3', ' 15', ' 0', ' 0', ' 0', ' 1');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('8', ' 8', ' 4', ' 3', ' 12', ' 0', ' 0', ' 0', ' 2');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('9', ' 9', ' 5', ' 4', ' 10', ' 20.0', ' 0', ' 0', ' 1');
INSERT INTO ejercicio_entrenamiento (ejercicio_id,  entrenamiento_id,  desempeno_id,  series,  repeticiones,  peso,  tiempo,  distancia,  orden) VALUES ('10', ' 10', ' 5', ' 3', ' 15', ' 0', ' 0', ' 0', ' 2');


