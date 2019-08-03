CREATE TABLE pictures (
	id INT(11) AUTO_INCREMENT,
    path VARCHAR(255) NOT NULL,
    size DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
	id INT(11) AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    profile_picture_id INT(11),
    PRIMARY KEY (id),
    CONSTRAINT fk_users_pictures
    FOREIGN KEY (profile_picture_id)
    REFERENCES pictures (id)
);

CREATE TABLE posts (
	id INT(11) AUTO_INCREMENT,
    caption VARCHAR(255) NOT NULL,
    user_id INT(11) NOT NULL,
    picture_id INT(11) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id_users
    FOREIGN KEY (user_id)
    REFERENCES users (id),
    CONSTRAINT fk_picture_id_pictures
    FOREIGN KEY (picture_id)
    REFERENCES pictures (id)
);

CREATE TABLE comments (
	id INT(11) AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    user_id INT(11) NOT NULL,
    post_id INT(11) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_comments_users_id
    FOREIGN KEY (user_id)
    REFERENCES users (id),
    CONSTRAINT fk_post_id_posts
    FOREIGN KEY (post_id)
    REFERENCES posts (id)
);

CREATE TABLE users_followers (
	user_id INT(11),
    follower_id INT(11),
    CONSTRAINT fk_user_followers_id
    FOREIGN KEY (user_id)
    REFERENCES users (id),
    CONSTRAINT fk_user_followers_follower
    FOREIGN KEY (follower_id)
    REFERENCES users (id)
);