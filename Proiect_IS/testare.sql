use Proiect_IS;

select * from useri;
select * from angajati;
select * from istoricFurnizare;
select * from useri where nume = "user1" and parola = "parola";
select pret from meniu where nume = "produs1";
select idAngajat, nume from angajati where numeUser = "user5";

drop table istoricFurnizare;
drop database Proiect_IS;

DELETE FROM istoricComenzi where idComanda = 4;
DELETE FROM useri where idUser = 2;

UPDATE useri
SET email = "test@yahoo.com"
WHERE idUser = 3;