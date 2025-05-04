Create TABLE ramal (
	id INT PRIMARY KEY IDENTITY,
	numero_ramal INT NOT NULL,
	status_ramal VARCHAR(10) NOT NULL CHECK (status_ramal IN ('Disponivel', 'Ocupado'))
);


CREATE TABLE usuario (
	id INT PRIMARY KEY IDENTITY,
	nome VARCHAR(50) NOT NULL,
	sobrenome VARCHAR(50) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	senha VARCHAR(50) NOT NULL
);
