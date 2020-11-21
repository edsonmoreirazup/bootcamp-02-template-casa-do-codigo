SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema casadocodigo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema casadocodigo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `casadocodigo` DEFAULT CHARACTER SET utf8 ;
USE `casadocodigo` ;

-- -----------------------------------------------------
-- Table `casadocodigo`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`autor` (
    `autor_id` BIGINT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(160) NOT NULL,
    `email` VARCHAR(254) NOT NULL,
    `descricao` VARCHAR(400) NOT NULL,
    `data_registro` DATETIME NOT NULL,
    PRIMARY KEY (`autor_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`categoria` (
    `categoria_id` BIGINT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(160) NOT NULL,
    PRIMARY KEY (`categoria_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`livro` (
    `livro_isbn` VARCHAR(45) NOT NULL,
    `titulo` VARCHAR(160) NOT NULL,
    `resumo` VARCHAR(255) NOT NULL,
    `sumario` VARCHAR(255) NOT NULL,
    `preco` DECIMAL(10,2) NOT NULL,
    `nr_paginas` INT NOT NULL,
    `data_publicacao` DATE NOT NULL,
    `categoria_id` BIGINT NOT NULL,
    PRIMARY KEY (`livro_isbn`),
    INDEX `fk_livro_categoria1_idx` (`categoria_id` ASC) VISIBLE,
    CONSTRAINT `fk_livro_categoria1`
      FOREIGN KEY (`categoria_id`)
          REFERENCES `casadocodigo`.`categoria` (`categoria_id`)
          ON DELETE NO ACTION
          ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`autor_livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`autor_livro` (
    `autor_id` BIGINT NOT NULL,
    `livro_isbn` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`autor_id`, `livro_isbn`),
    INDEX `fk_autor_has_livro_livro1_idx` (`livro_isbn` ASC) VISIBLE,
    INDEX `fk_autor_has_livro_autor1_idx` (`autor_id` ASC) VISIBLE,
    CONSTRAINT `fk_autor_has_livro_autor1`
        FOREIGN KEY (`autor_id`)
            REFERENCES `casadocodigo`.`autor` (`autor_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_autor_has_livro_livro1`
        FOREIGN KEY (`livro_isbn`)
            REFERENCES `casadocodigo`.`livro` (`livro_isbn`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`pais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`pais` (
    `pais_id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(150) NOT NULL,
    PRIMARY KEY (`pais_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`estado` (
    `estado_id` INT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(150) NOT NULL,
    `pais_id` INT NOT NULL,
    PRIMARY KEY (`estado_id`),
    INDEX `fk_estado_pais1_idx` (`pais_id` ASC) VISIBLE,
    CONSTRAINT `fk_estado_pais1`
       FOREIGN KEY (`pais_id`)
           REFERENCES `casadocodigo`.`pais` (`pais_id`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`cidade` (
    `cidade_id` BIGINT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(150) NOT NULL,
    `estado_id` INT NOT NULL,
    PRIMARY KEY (`cidade_id`),
    INDEX `fk_cidade_estado1_idx` (`estado_id` ASC) VISIBLE,
    CONSTRAINT `fk_cidade_estado1`
       FOREIGN KEY (`estado_id`)
           REFERENCES `casadocodigo`.`estado` (`estado_id`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`pedido` (
    `pedido_id` BIGINT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`pedido_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`cupom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`cupom` (
    `cupom_id` BINARY(16) NOT NULL,
    `codigo` VARCHAR(45) NOT NULL,
    `percent_desconto` DECIMAL(3,2) NOT NULL,
    `validade` DATETIME NOT NULL,
    PRIMARY KEY (`cupom_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`compra` (
    `compra_id` BINARY(16) NOT NULL,
    `cpf_cnpj` VARCHAR(14) NOT NULL,
    `email` VARCHAR(254) NOT NULL,
    `nome` VARCHAR(30) NOT NULL,
    `sobrenome` VARCHAR(130) NOT NULL,
    `endereco` VARCHAR(100) NOT NULL,
    `complemento` VARCHAR(60) NOT NULL,
    `telefone` VARCHAR(20) NOT NULL,
    `cep` VARCHAR(8) NOT NULL,
    `valor_pago` DECIMAL(10,2) NOT NULL,
    `pedido_id` BIGINT NOT NULL,
    `cidade_id` BIGINT NOT NULL,
    `cupom_id` BINARY(16) NOT NULL,
    INDEX `fk_pagamento_cidade1_idx` (`cidade_id` ASC) VISIBLE,
    PRIMARY KEY (`compra_id`),
    INDEX `fk_compra_pedido1_idx` (`pedido_id` ASC) VISIBLE,
    INDEX `fk_compra_cupom1_idx` (`cupom_id` ASC) VISIBLE,
    CONSTRAINT `fk_pagamento_cidade1`
    FOREIGN KEY (`cidade_id`)
       REFERENCES `casadocodigo`.`cidade` (`cidade_id`)
       ON DELETE NO ACTION
       ON UPDATE NO ACTION,
    CONSTRAINT `fk_compra_pedido1`
    FOREIGN KEY (`pedido_id`)
       REFERENCES `casadocodigo`.`pedido` (`pedido_id`)
       ON DELETE NO ACTION
       ON UPDATE NO ACTION,
    CONSTRAINT `fk_compra_cupom1`
    FOREIGN KEY (`cupom_id`)
       REFERENCES `casadocodigo`.`cupom` (`cupom_id`)
       ON DELETE NO ACTION
       ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `casadocodigo`.`livro_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `casadocodigo`.`livro_pedido` (
    `livro_isbn` VARCHAR(45) NOT NULL,
    `pedido_id` BIGINT NOT NULL,
    PRIMARY KEY (`livro_isbn`, `pedido_id`),
    INDEX `fk_livro_has_pedido_pedido1_idx` (`pedido_id` ASC) VISIBLE,
    INDEX `fk_livro_has_pedido_livro1_idx` (`livro_isbn` ASC) VISIBLE,
    CONSTRAINT `fk_livro_has_pedido_livro1`
     FOREIGN KEY (`livro_isbn`)
         REFERENCES `casadocodigo`.`livro` (`livro_isbn`)
         ON DELETE NO ACTION
         ON UPDATE NO ACTION,
    CONSTRAINT `fk_livro_has_pedido_pedido1`
     FOREIGN KEY (`pedido_id`)
         REFERENCES `casadocodigo`.`pedido` (`pedido_id`)
         ON DELETE NO ACTION
         ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
