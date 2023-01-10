DROP TABLE IF EXISTS person;
CREATE TABLE person(
    person_id SERIAL,
	name varchar(20)not null,
	age integer not null,
	email varchar not null
);
INSERT INTO person(name,age,email) VALUES('Timur',19,'tirukilum09@gmail.com');
INSERT INTO person(name,age,email) VALUES('Jumbluqing',18,'sepherhervar@gmail.com');
INSERT INTO person(name,age,email) VALUES('Qollungirqi',27,'chansanuka@gmail.com');
INSERT INTO person(name,age,email) VALUES('Arridjakren',16,'terekeren@gmail.com');


SELECT * FROM person;
