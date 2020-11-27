DROP TABLE IF EXISTS livro_pedido;

ALTER TABLE pedido
    ADD COLUMN compra_id BINARY(16) NOT NULL,
    ADD CONSTRAINT fk_pedido_compra1 FOREIGN KEY (`compra_id`) REFERENCES compra (`compra_id`);

CREATE TABLE IF NOT EXISTS `casadocodigo`.`pedido_itens` (
     `pedido_id` BIGINT NOT NULL,
     `livro_isbn` VARCHAR(45) NOT NULL,
     `preco_momento` DECIMAL(19,2) NOT NULL,
     `quantidade` INT NOT NULL,
     INDEX `fk_pedido_itens_pedido1_idx` (`pedido_id` ASC) VISIBLE,
     PRIMARY KEY (`pedido_id`, `livro_isbn`),
     UNIQUE INDEX `pedido_id_UNIQUE` (`pedido_id` ASC) VISIBLE,
     INDEX `fk_pedido_itens_livro1_idx` (`livro_isbn` ASC) VISIBLE,
     CONSTRAINT `fk_pedido_itens_pedido1`
         FOREIGN KEY (`pedido_id`)
             REFERENCES `casadocodigo`.`pedido` (`pedido_id`)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION,
     CONSTRAINT `fk_pedido_itens_livro1`
         FOREIGN KEY (`livro_isbn`)
             REFERENCES `casadocodigo`.`livro` (`livro_isbn`)
             ON DELETE NO ACTION
             ON UPDATE NO ACTION)
ENGINE = InnoDB;
