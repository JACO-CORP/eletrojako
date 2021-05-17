CREATE TABLE endereco
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cep VARCHAR(200) NOT NULL,
    endereco VARCHAR(500),
    numero INT NOT NULL,
    bairro VARCHAR(50),
    cidade VARCHAR(150),
    estado VARCHAR(50)
)
