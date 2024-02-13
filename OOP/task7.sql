CREATE DATABASE human_friends;
USE human_friends;

# 1. Создать таблицы, соответствующие иерархии из вашей диаграммы классов.

CREATE TABLE animals
(
	id INT PRIMARY KEY NOT NULL, 
	animal_type VARCHAR(45) NOT NULL
);

INSERT INTO animals
	(id, animal_type)
VALUES
	(1, 'Pets'),
    (2, 'Pack animals'); 
    
CREATE TABLE pets
(
	id INT PRIMARY KEY NOT NULL,
    subclass_name VARCHAR (45) NOT NULL,
    class_id INT DEFAULT 1,
    FOREIGN KEY (class_id) REFERENCES animals (id)
);

INSERT INTO pets
	(id, subclass_name)
VALUES 
	(1, 'Dogs'),
	(2, 'Cats'),
	(3, 'Hamsters'); 

CREATE TABLE pack_animals
(
	id INT PRIMARY KEY NOT NULL,
    subclass_name VARCHAR (45) NOT NULL,
    class_id INT DEFAULT 2,
    FOREIGN KEY (class_id) REFERENCES animals (id)
);

INSERT INTO pack_animals
	(id, subclass_name)
VALUES
	(1, 'Horses'),
    (2, 'Camels'),
	(3, 'Donkeys');  
	 
CREATE TABLE dogs
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    animal_name VARCHAR(45) NOT NULL, 
    type_id INT DEFAULT 1, 
    birthday DATE NOT NULL,
    commands VARCHAR(80),
    FOREIGN KEY (type_id) REFERENCES pets (id)
);

CREATE TABLE cats
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    animal_name VARCHAR(45) NOT NULL, 
    type_id INT DEFAULT 2, 
    birthday DATE NOT NULL,
    commands VARCHAR(80),
    FOREIGN KEY (type_id) REFERENCES pets (id)
);

CREATE TABLE hamsters
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    animal_name VARCHAR(45) NOT NULL, 
    type_id INT DEFAULT 3, 
    birthday DATE NOT NULL,
    commands VARCHAR(80),
    FOREIGN KEY (type_id) REFERENCES pets (id)
);

CREATE TABLE horses
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    animal_name VARCHAR(45) NOT NULL, 
    type_id INT DEFAULT 1, 
    birthday DATE NOT NULL,
    commands VARCHAR(80),
    FOREIGN KEY (type_id) REFERENCES pack_animals (id)
);

CREATE TABLE camels
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    animal_name VARCHAR(45) NOT NULL, 
    type_id INT DEFAULT 2, 
    birthday DATE NOT NULL,
    commands VARCHAR(80),
    FOREIGN KEY (type_id) REFERENCES pack_animals (id)
);

CREATE TABLE donkeys
(       
    id INT AUTO_INCREMENT PRIMARY KEY, 
    animal_name VARCHAR(45) NOT NULL, 
    type_id INT DEFAULT 3, 
    birthday DATE NOT NULL,
    commands VARCHAR(80),
    FOREIGN KEY (type_id) REFERENCES pack_animals (id)
);

# 2. Заполнить таблицы данными о животных, их командах и датами рождения.

INSERT INTO dogs
	(animal_name, birthday, commands)
VALUES 
	('Fido', '2020-01-01', 'Sit, Stay, Fetch'),
	('Buddy', '2018-12-10', 'Sit, Paw, Bark'),  
	('Bella', '2019-11-11', 'Sit, Stay, Roll');

INSERT INTO cats
	(animal_name, birthday, commands)
VALUES 
	('Whiskers', '2019-05-15', 'Sit, Pounce'),
	('Smudge', '2020-02-20', 'Sit, Pounce, Scratch'),  
	('Oliver', '2020-06-30', 'Meow, Scratch, Jump'); 

INSERT INTO hamsters
	(animal_name, birthday, commands)
VALUES 
	('Hammy', '2021-03-10', 'Roll, Hide'),
	('Peanut', '2021-08-01', 'Roll, Spin'); 

INSERT INTO horses
	(animal_name, birthday, commands)
VALUES 
	('Thunder', '2015-07-21', 'Trot, Canter, Gallop'),
	('Storm', '2014-05-05', 'Trot, Canter'),  
	('Blaze', '2016-02-29', 'Trot, Jump, Gallop'); 
    
INSERT INTO camels
	(animal_name, birthday, commands)
VALUES 
	('Sandy', '2016-11-03', 'Walk, Carry Load'),
	('Dune', '2018-12-12', 'Walk, Sit'), 
	('Sahara', '2015-08-14', 'Walk, Run');

INSERT INTO donkeys
	(animal_name, birthday, commands)
VALUES 
	('Eeyore', '2017-09-18', 'Walk, Carry Load, Bray'), 
	('Burro', '2019-01-23', 'Walk, Bray, Kick');

# 3. Удалить записи о верблюдах и объединить таблицы лошадей и ослов.

DELETE FROM camels
WHERE type_id=2;

SELECT * FROM horses
UNION
SELECT * FROM donkeys;

# 4. Создать новую таблицу для животных в возрасте от 1 до 3 лет и вычислить их возраст с точностью до месяца.

DROP TABLE IF EXISTS young_animals;
CREATE TABLE young_animals
SELECT animal_name, DATEDIFF(CURDATE(), birthday) / 365 AS age
FROM
(
	SELECT * FROM dogs
    UNION
    SELECT * FROM cats
    UNION
    SELECT * FROM hamsters
    UNION
    SELECT * FROM horses
    UNION
    SELECT * FROM camels
    UNION
    SELECT * FROM donkeys
) AS LIST
ORDER BY age;
select * from young_animals;

# 5. Объединить все созданные таблицы в одну, сохраняя информацию о принадлежности к исходным таблицам.


