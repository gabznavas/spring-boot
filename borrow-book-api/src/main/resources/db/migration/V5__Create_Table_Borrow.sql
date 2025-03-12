CREATE TABLE IF NOT EXISTS `borrows` (
    `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
    `borrow_at` DATETIME(6) NOT NULL,
    `person_id` BIGINT(10) NOT NULL,
    `books_id` BIGINT(10) NOT NULL,
    FOREIGN KEY(`person_id`) REFERENCES person(`id`),
    FOREIGN KEY(`books_id`) REFERENCES books(`id`),
    PRIMARY KEY(`id`)
);
