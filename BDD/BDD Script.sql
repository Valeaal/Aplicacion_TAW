USE TAW;
CREATE TABLE IF NOT EXISTS tipo_usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('admin', 'entrenador_bodybuilding', 'entrenador_crossfit', 'dietista', 'cliente') NOT NULL
);

CREATE TABLE IF NOT EXISTS tipo_ejercicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo ENUM('fuerza', 'resistencia', 'velocidad', 'flexibilidad', 'potencia', 'estabilidad', 'movilidad') NOT NULL
);

CREATE TABLE IF NOT EXISTS grupo_muscular (
    id INT AUTO_INCREMENT PRIMARY KEY,
    grupo ENUM('pecho', 'espalda', 'bicep', 'tricep', 'hombro', 'femoral', 'cuadricep', 'gluteo', 'aductor', 'gemelo') NOT NULL
);

-- Then, we proceed to create the rest of the tables following the order to avoid dependency issues
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    pertenece_desde DATE NOT NULL,
    tipo_usuario_id INT ,
    FOREIGN KEY (tipo_usuario_id) REFERENCES tipo_usuario(id)
);

CREATE TABLE IF NOT EXISTS dieta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dietista_id INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha DATE NOT NULL,
    FOREIGN KEY (dietista_id) REFERENCES usuario(id)
);


CREATE TABLE IF NOT EXISTS cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    dieta_id INT,
    peso FLOAT NOT NULL,
    altura INT NOT NULL,
    edad INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (dieta_id) REFERENCES dieta(id)
);

CREATE TABLE IF NOT EXISTS rutina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entrenador_id INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_creacion DATE NOT NULL,
	FOREIGN KEY (entrenador_id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS cliente_rutina (
    cliente_id INT NOT NULL,
    rutina_id INT NOT NULL,
    vigente BIT NOT NULL,
    PRIMARY KEY (cliente_id, rutina_id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (rutina_id) REFERENCES rutina(id)
);

CREATE TABLE IF NOT EXISTS entrenamiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    tipo INT NOT NULL,
    FOREIGN KEY (tipo) REFERENCES tipo_ejercicio(id)
);

CREATE TABLE IF NOT EXISTS entrenamiento_rutina (
    entrenamiento_id INT NOT NULL,
    rutina_id INT NOT NULL,
    dia_semana INT NOT NULL,
    PRIMARY KEY (entrenamiento_id, rutina_id),
    FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(id),
    FOREIGN KEY (rutina_id) REFERENCES rutina(id)
);

CREATE TABLE IF NOT EXISTS ejercicio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo INT NOT NULL,
    descripcion TEXT,
    url_video TEXT,
    grupo_muscular_id INT NOT NULL,
    FOREIGN KEY (tipo) REFERENCES tipo_ejercicio(id),
    FOREIGN KEY (grupo_muscular_id) REFERENCES grupo_muscular(id)
);

CREATE TABLE IF NOT EXISTS ejercicio_entrenamiento (
    ejercicio_id INT NOT NULL,
    entrenamiento_id INT NOT NULL,
    desempeno_id INT NOT NULL,
    series INT NOT NULL,
    repeticiones INT NOT NULL,
    peso FLOAT NOT NULL,
    tiempo INT NOT NULL,
    distancia FLOAT NOT NULL,
    orden INT NOT NULL,
    PRIMARY KEY (ejercicio_id, entrenamiento_id, desempeno_id),
    FOREIGN KEY (ejercicio_id) REFERENCES ejercicio(id),
    FOREIGN KEY (entrenamiento_id) REFERENCES entrenamiento(id),
    FOREIGN KEY (desempeno_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    alergenos TEXT
);
CREATE TABLE IF NOT EXISTS comida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT
);

CREATE TABLE IF NOT EXISTS comida_menu (
    comida_id INT NOT NULL,
    menu_id INT NOT NULL,
    PRIMARY KEY (comida_id, menu_id),
    FOREIGN KEY (comida_id) REFERENCES comida(id),
    FOREIGN KEY (menu_id) REFERENCES menu(id)
);

CREATE TABLE IF NOT EXISTS dieta_comida (
    dieta_id INT NOT NULL,
    comida_id INT NOT NULL,
    momento_dia INT NOT NULL,
    PRIMARY KEY (dieta_id, comida_id),
    FOREIGN KEY (dieta_id) REFERENCES dieta(id),
    FOREIGN KEY (comida_id) REFERENCES comida(id)
);

CREATE TABLE IF NOT EXISTS desempeno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    valoracion INT NOT NULL,
    peso_realizado FLOAT NOT NULL,
    comentarios TEXT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);