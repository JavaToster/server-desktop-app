CREATE TABLE users_database(
	id SERIAL PRIMARY KEY,
	username VARCHAR(100),
	firstname VARCHAR(100),
	surname VARCHAR(100),
	phone BIGINT,
	password_user VARCHAR(16)
	);

CREATE TABLE score(
	id SERIAL PRIMARY KEY,
	username VARCHAR(100),
	score BIGINT
	);

CREATE TABLE history(
	id SERIAL PRIMARY KEY,
	username VARCHAR(100),
	score_history VARCHAR(150),
	summa BIGINT,
	type_of_operation INT
	);