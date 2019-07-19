CREATE TABLE users (
	id INT(11) AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE repositories (
    id INT(11) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE repositories_contributors (
	repository_id INT(11),
	contributor_id INT(11),
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    FOREIGN KEY (contributor_id)
    REFERENCES users(id)
);

CREATE TABLE issues (
	id INT(11) AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    issue_status VARCHAR(6) NOT NULL,
    repository_id INT(11) NOT NULL,
    assignee_id INT(11) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    FOREIGN KEY (assignee_id)
    REFERENCES users(id)
);

CREATE TABLE commits (
	id INT(11) AUTO_INCREMENT,
    message VARCHAR(255) NOT NULL,
    issue_id INT(11),
    repository_id INT(11) NOT NULL,
    contributor_id INT(11) NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (issue_id)
    REFERENCES issues(id),
    FOREIGN KEY (repository_id)
    REFERENCES repositories(id),
    FOREIGN KEY (contributor_id)
    REFERENCES users(id)
);

CREATE TABLE files (
	id INT(11) AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    size DECIMAL(10, 2) NOT NULL,
    parent_id INT(11),
    commit_id INT(11) NOT NULL,
	PRIMARY KEY (id),
    FOREIGN KEY (parent_id)
    REFERENCES files(id),
    FOREIGN KEY (commit_id)
    REFERENCES commits(id)
);