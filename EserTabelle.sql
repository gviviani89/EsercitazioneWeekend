CREATE DATABASE IF NOT EXISTS Rubrica
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_unicode_ci;

USE Rubrica;


CREATE TABLE IF NOT EXISTS Contatti(
    nome VARCHAR(32),
    cognome VARCHAR(32),
    data_nascita DATE,
    numero VARCHAR(32),
    modello INT(11),
    CONSTRAINT b_id PRIMARY KEY (numero),
    CONSTRAINT tel FOREIGN KEY (modello)
        REFERENCES telefono (id)
);



CREATE TABLE IF NOT EXISTS telefono (
    id INT(11) AUTO_INCREMENT,
    brand VARCHAR(32),
    name VARCHAR(64),
    displaySize VARCHAR(12),
    opSys VARCHAR(32),
    CONSTRAINT tel_id PRIMARY KEY (id)
);

SELECT 
    *
FROM
    contatti;
SELECT 
    *
FROM
    telefono;
 
#Inserimento di un nuovo utente
 
              
INSERT INTO Contatti (nome,cognome,data_nascita,numero,indirizzo,citta,provincia,e_mail,modello) VALUES
						('Margherita','Pizza','1983-4-21','3257745858','via Leopardi 107','Napoli','NA','capricciosa88@yahoo.it',16);
 
#Visualizzazione delle informazioni anagrafiche e del modello di telefono posseduto
    
SELECT
c.*,t.brand,t.name
FROM
contatti AS c
JOIN 
telefono AS t
ON
c.modello=t.id;

#Update sia delle informazioni anagrafiche che del telefono

UPDATE 
contatti AS c 
JOIN telefono AS t 
ON c.modello=t.id
SET c.data_nascita='1958-11-6', 
	t.name='Iphone 7', 
	t.brand='APPLE'
WHERE c.numero='3387741252';

#Visualizzazione delle informazioni relative al telefono

SELECT c.nome,c.cognome,t.brand,t.name
FROM contatti AS c
JOIN telefono AS t
ON c.modello=t.id
WHERE c.provincia='AV';