CREATE TABLE `employees` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(30),
    `last_name` VARCHAR(30),
    `project_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `projects` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `client_id` INT(11) NOT NULL,
    `project_lead_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_projects_employees`
    FOREIGN KEY (`project_lead_id`)
    REFERENCES `employees`(`id`)
);

ALTER TABLE `employees`
ADD CONSTRAINT `fk_employees_projects`
FOREIGN KEY (`project_id`)
REFERENCES `projects`(`id`);

CREATE TABLE `clients` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `client_name` VARCHAR(100),
    `project_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_clients_projects`
    FOREIGN KEY (`project_id`)
    REFERENCES `projects`(`id`)
);

ALTER TABLE `projects`
ADD CONSTRAINT `fk_projects_clients`
FOREIGN KEY (`client_id`)
REFERENCES `clients`(`id`);