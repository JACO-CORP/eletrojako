CREATE TABLE produto 
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    descricao VARCHAR(500),
    valor_Unidade DECIMAL(10,2) NOT NULL,
    quantidade_Estoque integer
)
