CREATE TABLE `mountains` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `peaks` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `mountain_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_peaks_mountains`
    FOREIGN KEY (`mountain_id`) 
    REFERENCES `mountains`(`id`)
);