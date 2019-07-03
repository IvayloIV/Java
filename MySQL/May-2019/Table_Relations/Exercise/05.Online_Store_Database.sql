CREATE TABLE `item_types` (
	`item_type_id` INT(11) NOT NULL,
    `name` VARCHAR(50),
    PRIMARY KEY (`item_type_id`)
);

CREATE TABLE `items` (
	`item_id` INT(11) NOT NULL,
    `name` VARCHAR(50),
    `item_type_id` INT(11),
    PRIMARY KEY(`item_id`),
    CONSTRAINT `fk_items_item_types`
    FOREIGN KEY (`item_type_id`)
    REFERENCES `item_types` (`item_type_id`)
);

CREATE TABLE `cities` (
	`city_id` INT(11) NOT NULL,
    `name` VARCHAR(50),
    PRIMARY KEY (`city_id`)
);

CREATE TABLE `customers` (
	`customer_id` INT(11) NOT NULL,
    `name` VARCHAR(50),
    `birthday` DATE,
    `city_id` INT(11),
    PRIMARY KEY (`customer_id`),
    CONSTRAINT `fk_customers_cities`
    FOREIGN KEY (`city_id`)
    REFERENCES `cities` (`city_id`)
);

CREATE TABLE `orders` (
	`order_id` INT(11) NOT NULL,
    `customer_id` INT(11),
    PRIMARY KEY (`order_id`),
    CONSTRAINT `fk_orders_customers`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customers` (`customer_id`)
);

CREATE TABLE `order_items` (
	`order_id` INT(11) NOT NULL,
	`item_id` INT(11) NOT NULL,
    PRIMARY KEY (`order_id`, `item_id`),
    CONSTRAINT `fk_orders_items_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`order_id`),
    CONSTRAINT `fk_orders_items_item_id`
    FOREIGN KEY (`item_id`)
    REFERENCES `items` (`item_id`)
);