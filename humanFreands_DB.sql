DROP DATABASE IF EXISTS human_friends;
CREATE DATABASE human_friends;
USE human_friends;

# 1. Создать таблицы с иерархией из диаграммы в БД

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

# 2. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения

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

# 3. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку.
#	 Объединить таблицы лошади, и ослы в одну таблицу.

DELETE FROM camels
WHERE type_id=2;

DROP TABLE IF EXISTS hourses_and_donkeys;
CREATE TABLE hourses_and_donkeys
SELECT * FROM horses
UNION
SELECT * FROM donkeys;

# 4. Создать новую таблицу “молодые животные” в которую попадут все
#	 животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
#	 до месяца подсчитать возраст животных в новой таблице

DROP TABLE IF EXISTS young_animals;
CREATE TABLE young_animals AS
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
HAVING age < 3
ORDER BY age;
select * from young_animals;

# 5. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
# 	 прошлую принадлежность к старым таблицам.
SET @i = 0;
DROP TABLE IF EXISTS all_animals;
CREATE TABLE all_animals AS
SELECT *
FROM
(
	SELECT @i := @i + 1 num, subclass_name, animal_name, birthday, commands
	FROM pets
	LEFT JOIN
	(
		SELECT * FROM dogs
		UNION
		SELECT * FROM cats
		UNION
		SELECT * FROM hamsters
	) AS all_pets
	ON pets.id = all_pets.type_id
	UNION
	SELECT @i := @i + 1 num, subclass_name, animal_name, birthday, commands
	FROM pack_animals
	LEFT JOIN
	(
		SELECT * FROM horses
		UNION
		SELECT * FROM camels
		UNION
		SELECT * FROM donkeys
	) AS all_pack_animals
	ON pack_animals.id = all_pack_animals.type_id
) AS LIST;

SELECT * FROM all_animals;




