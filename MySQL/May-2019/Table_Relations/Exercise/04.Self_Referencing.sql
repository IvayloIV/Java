CREATE TABLE `teachers` (
	`teacher_id` INT NOT NULL,
    `name` VARCHAR(45),
    `manager_id` INT,
    PRIMARY KEY (`teacher_id`),
    CONSTRAINT `fk_manager_id_teacher_id`
    FOREIGN KEY (`manager_id`)
    REFERENCES `teachers`(`teacher_id`)
);

INSERT INTO `teachers`(`teacher_id`, `name`, `manager_id`)
VALUES 
	(101, 'John', NULL),
    (106, 'Greta', 101),
    (102, 'Maya', 106),
    (103, 'Silvia', 106),
    (105, 'Mark', 101),
    (104, 'Ted', 105);