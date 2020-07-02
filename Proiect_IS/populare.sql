use Proiect_IS;

insert into useri (nume, parola, email, functie) values("client1", "11297114111108979949", "client1@gmail.com", "user");
insert into useri (nume, parola, email, functie) values("client2", "11297114111108979950", "client2@gmail.com", "user");
insert into useri (nume, parola, email, functie) values("manager1", "112971141111089710949", "manager1@gmail.com", "manager");
insert into useri (nume, parola, email, functie) values("bucatar1", "11297114111108979849", "bucatar1@gmail.com", "bucatar");
insert into useri (nume, parola, email, functie) values("bucatar2", "11297114111108979850", "bucatar2@gmail.com", "bucatar");
insert into useri (nume, parola, email, functie) values("livrator1", "112971141111089710849", "livrator1@gmail.com", "livrator");
insert into useri (nume, parola, email, functie) values("livrator2", "112971141111089710850", "livrator2@gmail.com", "livrator");

insert into angajati (nume, prenume, functie, email, adresa, salar, numeUser) values("Pacurar", "Cristian", "manager", "manager1@gmail.com", "adresa1", 2500, "manager1");
insert into angajati (nume, prenume, functie, email, adresa, salar, numeUser) values("Pop", "Ionescu", "bucatar", "bucatar1@gmail.com", "adresa2", 1000, "bucatar1");
insert into angajati (nume, prenume, functie, email, adresa, salar, numeUser) values("Vasile", "Ion", "bucatar", "bucatar2@gmail.com", "adresa3", 1000, "bucatar2");
insert into angajati (nume, prenume, functie, email, adresa, salar, numeUser) values("Popovici", "Razvan", "livrator", "livrator1@gmail.com", "adresa4", 700, "livrator1");
insert into angajati (nume, prenume, functie, email, adresa, salar, numeUser) values("Mihailescu", "Stefan", "livrator", "livrator2@gmail.com", "adresa5", 700, "livrator2");

insert into meniu (nume, ingrediente, gramaj, pret, imagine) values("Supa de pui", "Carne, Cartofi, Orez", 300, 15.50, "supa.jpg");
insert into meniu (nume, ingrediente, gramaj, pret, imagine) values("Paltou", "Carne, Branza, Cartofi", 500, 25, "platou.jpg");
insert into meniu (nume, ingrediente, gramaj, pret, imagine) values("Friptura porc", "Carne, Cartofi", 200, 20, "friptura.jpg");
insert into meniu (nume, ingrediente, gramaj, pret, imagine) values("Papanasi", "Aluat, Dulceata", 100, 10, "papanasi.jpg");
insert into meniu (nume, ingrediente, gramaj, pret, imagine) values("Vin", "Struguri", 50, 30, "vin.jpg");

insert into ingrediente (nume, cantitate) values("Carne de pui", 100);
insert into ingrediente (nume, cantitate) values("Cartofi", 50);
insert into ingrediente (nume, cantitate) values("Orez", 200);

insert into furnizori (numeFirma, numeIngredient) values("Firma1", "Cartofi");
insert into istoricFurnizare (numeFirma, numeIngredient, cantitate, idFurnizor) values("Firma1", "Cartofi", 20, 1);

-- insert into istoricComenzi (numeClient, adresa, telefon, email, produse) values("numeClient1", "adresa1", "0752", "test@gmail.com", "produs1, produs2");
