CREATE TABLE IF NOT EXISTS `person`(
    `id` BIGINT(10) AUTO_INCREMENT NOT NULL,
    `first_name` VARCHAR(100) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `gender` VARCHAR(6) NOT NULL,
    PRIMARY KEY (`id`)
);
