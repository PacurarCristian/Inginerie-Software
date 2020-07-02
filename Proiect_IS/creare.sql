create database Proiect_IS;
use Proiect_IS;

create table useri(
idUser int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nume VARCHAR(30) unique NOT NULL,
parola VARCHAR(30) NOT NULL,
email VARCHAR(50),
functie VARCHAR(30) NOT NULL,
dataInregistrare TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table angajati(
idAngajat int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nume VARCHAR(30) NOT NULL,
prenume VARCHAR(30) NOT NULL,
functie VARCHAR(30) NOT NULL,
email VARCHAR(30),
adresa VARCHAR(30) NOT NULL,
salar float NOT NULL,
numeUser VARCHAR(30) unique NOT NULL,
FOREIGN KEY (numeUser) REFERENCES useri(nume)
);

create table meniu(
idProdus int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nume VARCHAR(30) NOT NULL,
ingrediente VARCHAR(200) NOT NULL,
gramaj int NOT NULL,
pret float NOT NULL,
imagine VARCHAR(30) NOT NULL
);

create table ingrediente(
idIngredient int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nume VARCHAR(30) unique NOT NULL,
cantitate int NOT NULL
);

create table furnizori(
idFurnizor int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
numeFirma VARCHAR(30) NOT NULL,
numeIngredient VARCHAR(30) unique NOT NULL,
FOREIGN KEY (numeIngredient) REFERENCES ingrediente(nume)
);

create table istoricFurnizare(
idFurnizare int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
numeFirma VARCHAR(30) NOT NULL,
numeIngredient VARCHAR(30) NOT NULL,
cantitate int NOT NULL,
idFurnizor int unsigned unique NOT NULL,
FOREIGN KEY (idFurnizor) REFERENCES furnizori(idFurnizor),
dataFurnizare TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table istoricComenzi(
idComanda int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
numeClient VARCHAR(30) NOT NULL,
adresa VARCHAR(30) NOT NULL,
telefon VARCHAR(30) NOT NULL,
email VARCHAR(30),
produse VARCHAR(200) NOT NULL,
dataComanda TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
