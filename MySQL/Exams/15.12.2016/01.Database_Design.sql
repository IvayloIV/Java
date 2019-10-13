CREATE TABLE credentials (
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(30),
    password VARCHAR(20)
);

CREATE TABLE locations (
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
    latitude FLOAT,
	longitude FLOAT
);

CREATE TABLE users (
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(25),
    gender CHAR(1),
    age INT(11),
    location_id INT(11),
    credential_id INT(11) UNIQUE NOT NULL,
    CONSTRAINT fk_users_locations FOREIGN KEY(location_id) REFERENCES locations(id),
    CONSTRAINT fk_users_credentials FOREIGN KEY(credential_id) REFERENCES credentials(id)
);

CREATE TABLE chats (
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(32),
    start_date DATE,
    is_active BIT
);

CREATE TABLE users_chats (
	user_id INT(11) NOT NULL,
    chat_id INT(11) NOT NULL,
    PRIMARY KEY(user_id, chat_id),
    CONSTRAINT fk_users_chats_users FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_users_chats_chats FOREIGN KEY(chat_id) REFERENCES chats(id)
);

CREATE TABLE messages (
	id INT(11) AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(200),
    sent_on DATE,
    chat_id INT(11) NOT NULL,
    user_id INT(11) NOT NULL,
    CONSTRAINT fk_messages_chats FOREIGN KEY(chat_id) REFERENCES chats(id),
    CONSTRAINT fk_messages_users FOREIGN KEY(user_id) REFERENCES users(id)
);