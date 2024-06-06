-- MySQL Workbench Forward Engineering

-- begin attached script 'script'

-- end attached script 'script'
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema TAW
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TAW
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TAW` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `TAW` ;

-- -----------------------------------------------------
-- Table `TAW`.`tipo_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`tipo_usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('admin', 'entrenador_bodybuilding', 'entrenador_crossfit', 'dietista') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `apellidos` VARCHAR(255) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `pertenece_desde` DATE NOT NULL,
  `tipo_usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tipo_usuario_id` (`tipo_usuario_id` ASC) VISIBLE,
  CONSTRAINT `usuario_ibfk_1`
    FOREIGN KEY (`tipo_usuario_id`)
    REFERENCES `TAW`.`tipo_usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`dieta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`dieta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dietista_id` INT NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `dietista_id` (`dietista_id` ASC) VISIBLE,
  CONSTRAINT `dieta_ibfk_1`
    FOREIGN KEY (`dietista_id`)
    REFERENCES `TAW`.`usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `dieta_id` INT NULL DEFAULT NULL,
  `peso` FLOAT NOT NULL,
  `altura` INT NOT NULL,
  `edad` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `usuario_id` (`usuario_id` ASC) VISIBLE,
  INDEX `dieta_id` (`dieta_id` ASC) VISIBLE,
  CONSTRAINT `cliente_ibfk_1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `TAW`.`usuario` (`id`),
  CONSTRAINT `cliente_ibfk_3`
    FOREIGN KEY (`dieta_id`)
    REFERENCES `TAW`.`dieta` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`rutina` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `fecha_creacion` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`cliente_rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`cliente_rutina` (
  `cliente_id` INT NOT NULL,
  `rutina_id` INT NOT NULL,
  `vigente` BIT(1) NOT NULL,
  PRIMARY KEY (`cliente_id`, `rutina_id`),
  INDEX `rutina_id` (`rutina_id` ASC) VISIBLE,
  CONSTRAINT `cliente_rutina_ibfk_1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `TAW`.`cliente` (`id`),
  CONSTRAINT `cliente_rutina_ibfk_2`
    FOREIGN KEY (`rutina_id`)
    REFERENCES `TAW`.`rutina` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`comida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`comida` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `alergenos` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`comida_menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`comida_menu` (
  `comida_id` INT NOT NULL,
  `menu_id` INT NOT NULL,
  PRIMARY KEY (`comida_id`, `menu_id`),
  INDEX `menu_id` (`menu_id` ASC) VISIBLE,
  CONSTRAINT `comida_menu_ibfk_1`
    FOREIGN KEY (`comida_id`)
    REFERENCES `TAW`.`comida` (`id`),
  CONSTRAINT `comida_menu_ibfk_2`
    FOREIGN KEY (`menu_id`)
    REFERENCES `TAW`.`menu` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`desempeno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`desempeno` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT NOT NULL,
  `valoracion` INT NOT NULL,
  `peso_realizado` FLOAT NOT NULL,
  `comentarios` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `cliente_id` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `desempeno_ibfk_1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `TAW`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`dieta_comida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`dieta_comida` (
  `dieta_id` INT NOT NULL,
  `comida_id` INT NOT NULL,
  `momento_dia` INT NOT NULL,
  PRIMARY KEY (`dieta_id`, `comida_id`),
  INDEX `comida_id` (`comida_id` ASC) VISIBLE,
  CONSTRAINT `dieta_comida_ibfk_1`
    FOREIGN KEY (`dieta_id`)
    REFERENCES `TAW`.`dieta` (`id`),
  CONSTRAINT `dieta_comida_ibfk_2`
    FOREIGN KEY (`comida_id`)
    REFERENCES `TAW`.`comida` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`tipo_ejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`tipo_ejercicio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` ENUM('fuerza', 'resistencia', 'velocidad', 'flexibilidad', 'potencia', 'estabilidad', 'movilidad') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`grupo_muscular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`grupo_muscular` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `grupo` ENUM('pecho', 'espalda', 'bicep', 'tricep', 'hombro', 'femoral', 'cuadricep', 'gluteo', 'aductor', 'gemelo') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`ejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`ejercicio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `tipo` INT NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `url_video` TEXT NULL DEFAULT NULL,
  `grupo_muscular_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tipo` (`tipo` ASC) VISIBLE,
  INDEX `grupo_muscular_id` (`grupo_muscular_id` ASC) VISIBLE,
  CONSTRAINT `ejercicio_ibfk_1`
    FOREIGN KEY (`tipo`)
    REFERENCES `TAW`.`tipo_ejercicio` (`id`),
  CONSTRAINT `ejercicio_ibfk_2`
    FOREIGN KEY (`grupo_muscular_id`)
    REFERENCES `TAW`.`grupo_muscular` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`entrenamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`entrenamiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `tipo` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `tipo` (`tipo` ASC) VISIBLE,
  CONSTRAINT `entrenamiento_ibfk_1`
    FOREIGN KEY (`tipo`)
    REFERENCES `TAW`.`tipo_ejercicio` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`ejercicio_entrenamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`ejercicio_entrenamiento` (
  `ejercicio_id` INT NOT NULL,
  `entrenamiento_id` INT NOT NULL,
  `desempeno_id` INT NOT NULL,
  `series` INT NOT NULL,
  `repeticiones` INT NOT NULL,
  `peso` FLOAT NOT NULL,
  `tiempo` INT NOT NULL,
  `distancia` FLOAT NOT NULL,
  `orden` INT NOT NULL,
  PRIMARY KEY (`ejercicio_id`, `entrenamiento_id`, `desempeno_id`),
  INDEX `entrenamiento_id` (`entrenamiento_id` ASC) VISIBLE,
  INDEX `desempeno_id` (`desempeno_id` ASC) VISIBLE,
  CONSTRAINT `ejercicio_entrenamiento_ibfk_1`
    FOREIGN KEY (`ejercicio_id`)
    REFERENCES `TAW`.`ejercicio` (`id`),
  CONSTRAINT `ejercicio_entrenamiento_ibfk_2`
    FOREIGN KEY (`entrenamiento_id`)
    REFERENCES `TAW`.`entrenamiento` (`id`),
  CONSTRAINT `ejercicio_entrenamiento_ibfk_3`
    FOREIGN KEY (`desempeno_id`)
    REFERENCES `TAW`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `TAW`.`entrenamiento_rutina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAW`.`entrenamiento_rutina` (
  `entrenamiento_id` INT NOT NULL,
  `rutina_id` INT NOT NULL,
  `dia_semana` INT NOT NULL,
  PRIMARY KEY (`entrenamiento_id`, `rutina_id`),
  INDEX `rutina_id` (`rutina_id` ASC) VISIBLE,
  CONSTRAINT `entrenamiento_rutina_ibfk_1`
    FOREIGN KEY (`entrenamiento_id`)
    REFERENCES `TAW`.`entrenamiento` (`id`),
  CONSTRAINT `entrenamiento_rutina_ibfk_2`
    FOREIGN KEY (`rutina_id`)
    REFERENCES `TAW`.`rutina` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
