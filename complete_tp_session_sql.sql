#*----------------------------------------------------------------------------*/
#* TP 01                                                                      */
#*----------------------------------------------------------------------------*/

-- qa

SELECT DISTINCT nom,remise
FROM fournisseur,mafourniture,produit
WHERE fournisseur.f=mafourniture.f
AND mafourniture.p=produit.p
AND origine='Dijon';

-- qb

SELECT DISTINCT nom
FROM fournisseur,mafourniture,produit
WHERE fournisseur.f=mafourniture.f
AND mafourniture.p=produit.p
AND ville='Paris'
AND ((origine='Riec') OR (origine='Reims'));

-- qc

SELECT DISTINCT nom_p
FROM fournisseur,mafourniture,produit
WHERE fournisseur.f=mafourniture.f
AND mafourniture.p=produit.p
AND nom='Bornibus'
AND qte<5;

-- q1

SELECT DISTINCT nom,ville
FROM fournisseur,mafourniture,produit
WHERE fournisseur.f=mafourniture.f
AND mafourniture.p=produit.p
AND ville=origine;

-- q2

SELECT DISTINCT f
FROM mafourniture
WHERE f NOT IN (
SELECT DISTINCT f
FROM mafourniture,produit
WHERE mafourniture.p=produit.p
AND couleur<>'vert');

-- q3

SELECT f
FROM MF
EXCEPT
SELECT f
FROM MF,P
WHERE MF.p=P.p
AND P.couleur='vert';

-- q4

SELECT F2.nom,F2.ville
FROM (SELECT * FROM F) AS F1 , (SELECT * FROM F) AS F2
WHERE F1.nom = 'Bornibus'
AND F2.remise > F1.remise;

-- q5

SELECT f
FROM MF
WHERE p IN
    (SELECT p
    FROM P
    WHERE couleur='vert')
GROUP BY f
HAVING COUNT (DISTINCT p) =
    (SELECT COUNT (DISTINCT p)
    FROM P
    WHERE couleur = 'vert');

-- q6

SELECT f
FROM MF
EXCEPT
SELECT MF.f
FROM F,MF,P
WHERE F.f=MF.f
AND MF.p=P.p
AND origine<>ville;

-- q7

SELECT f
FROM F,P
WHERE ville=origine
EXCEPT
SELECT f
FROM (
  	SELECT f,p
	FROM F,P
	WHERE ville=origine
	EXCEPT
	SELECT f,p
	FROM MF
) AS nom_d_alias;

-- TP1 -----------------------------

-- 5)

SELECT AVG(qte)
FROM MF natural join P
WHERE nom_p = 'moutarde';

-- 6)

SELECT SUM(qte)
FROM MF natural join P
WHERE nom_p = 'champagne';

-- 7)

SELECT COUNT(DISTINCT ville) FROM F;

-- 8)

SELECT nom,avg
FROM F natural join
(
  SELECT f,AVG(qte)
  FROM MF
  GROUP BY f
) AS machin;

-- 9)

SELECT nom
FROM F
WHERE f in
(
  SELECT f
  FROM
  (
    SELECT f,COUNT(DISTINCT p)
    FROM MF
    GROUP BY f
  ) AS bidule
  WHERE count =
  (
    SELECT MAX(count)
    FROM
    (
      SELECT f,COUNT(DISTINCT p)
      FROM MF
      GROUP BY f
    ) AS  bidule2
  )
);

-- Nicolas Lescouarnec

-- Q5 Trouver la moyenne  des quantites

SELECT avg(qte) FROM mf NATURAL INNER JOIN p
       WHERE nom_p = 'moutarde'
       AND qte <> 0;
/*
     avg      
--------------
 1.2500000000
(1 row)
*/

-- Q6 Trouver la quantite totale de champagne

SELECT sum(qte) FROM mf NATURAL INNER JOIN p
       WHERE nom_p = 'champagne';
/* 
sum 
-----
   6
*/

-- Q7 Trouver le nombre de villes enregistrees

SELECT count(DISTINCT ville) FROM (
    (SELECT ville FROM f)
    union
    (SELECT origine AS ville FROM p)
) AS toto;
/*
 count 
-------
     6
*/

-- Q8 trouver la moyenne des quantites par fournisseur

SELECT f,avg(qte) FROM mf 
    GROUP BY f ;
/*
  f  |      avg      
-----+---------------
 f1  |  3.0000000000
 f2  |  1.0000000000
 f3  |  3.0000000000
 f4  |  4.2500000000
 f5  | 10.0000000000
*/

-- Q9 trouver les fournisseurs qui offrent le plus grand choix

SELECT f FROM mf
        GROUP BY f
    ORDER BY count(DISTINCT p) DESC
    LIMIT 1;
/*
  f  
-----
 f1 
*/

-- Q10 solution delivrant tous en cas d'egalite

SELECT f FROM mf 
    GROUP BY f
    HAVING count(DISTINCT p) = (
        SELECT max(c) FROM (
            SELECT (count(DISTINCT p)) AS c FROM mf
            GROUP BY f
        ) AS TITI
    );
/*
  f  
-----
 f1 
 f4 
*/

#*----------------------------------------------------------------------------*/
#* TP 02                                                                      */
#*----------------------------------------------------------------------------*/

CREATE TABLE bar
(
  b       INTEGER PRIMARY KEY,
  nom     CHAR(15),
  adresse CHAR(30)
);

CREATE TABLE sert
(
  b     INTEGER REFERENCES bar(b),
  biere CHAR(10),
  qte   INTEGER,
  PRIMARY KEY (b,biere)
);

CREATE TABLE frequente
(
  buveur CHAR(15),
  b      INTEGER REFERENCES bar(b),
  PRIMARY KEY (buveur,b)
);

CREATE TABLE aime (
  buveur CHAR(15),
  biere  CHAR(10),
  PRIMARY KEY (buveur,biere)
);

-- 4)

UPDATE bar
SET nom='chez Ghislene'
WHERE b=1;

-- 5)

UPDATE sert
SET qte=2*qte
WHERE b=1;

-- 6)

DELETE FROM sert
WHERE b=1
AND biere NOT IN
(
  SELECT biere
  FROM aime
  WHERE buveur='robert'
);

-- 9)

DELETE FROM aime
WHERE buveur NOT IN
(
  SELECT buveur
  FROM frequente
);

DELETE FROM frequente
WHERE buveur NOT IN
(
  SELECT buveur
  FROM aime
);

-- Nicolas Lescouarnec

CREATE TABLE bar
    (b       INTEGER PRIMARY KEY DEFAULT nextval('serial_nono'),
     nom     VARCHAR(15) NOT NULL CHECK(nom<>''),
     adresse VARCHAR(30) NOT NULL CHECK(adresse<>''));

CREATE TABLE sert
    (b INTEGER REFERENCES bar,
     biere VARCHAR(10) NOT NULL CHECK(biere <>''),
     qte   INTEGER DEFAULT 0,
     UNIQUE (b,biere));


CREATE TABLE frequente
    (buveur VARCHAR(20) NOT NULL CHECK(buveur<>''),
     b      INTEGER REFERENCES bar,
     PRIMARY KEY (buveur,b));

CREATE TABLE aime
    (buveur VARCHAR(20) NOT NULL CHECK(buveur<>''),
     biere  VARCHAR(10) NOT NULL CHECK(biere <>''),
     PRIMARY KEY (buveur,biere));

update sert SET qte=qte*2 WHERE b=2;
update bar  SET nom='FreeDOM' WHERE b=3;
update sert SET qte=qte*2 WHERE b=3;

#*----------------------------------------------------------------------------*/
#* TP 03                                                                      */
#*----------------------------------------------------------------------------*/

DROP TABLE restaurant;
CREATE TABLE restaurant
(
  id       INTEGER  PRIMARY KEY,
  ville    CHAR(20) NOT NULL,
  capacite INTEGER  NOT NULL
);

DROP TABLE prix;
CREATE TABLE prix
(
  categorie  INTEGER,
  restaurant INTEGER REFERENCES restaurant(id),
  prix       INTEGER NOT NULL,
  PRIMARY KEY (categorie,restaurant)
);

DROP TABLE employe;
CREATE TABLE employe
(
  id        INTEGER  PRIMARY KEY,
  nom       CHAR(50) NOT NULL,
  categorie INTEGER  CHECK
  (
    categorie IN
    (
      SELECT categorie
      FROM   prix
      WHERE  restaurant IN
        ( SELECT id FROM restaurant )
      GROUP BY categorie
      HAVING COUNT(restaurant) =
        ( SELECT COUNT(id) FROM restaurant )
    )
  )
);

DROP TABLE mange;
CREATE TABLE mange
(
  employe    INTEGER REFERENCES employe(id),
  jour       DATE,
  restaurant INTEGER REFERENCES restaurant(id) CHECK
  (
    (
      SELECT COUNT(*)
      FROM mange
      WHERE mange.restaurant = restaurant
      AND mange.jour = jour
    ) BETWEEN 0 and (
      SELECT capacite
      FROM restaurant
      WHERE id = restaurant
    )
  ),
  PRIMARY KEY (employe,date)
);

-- Nicolas Lescouarnec

-- Style de date europeen '14/02/2005'

SET DateStyle TO European;

-- Creation des sequences pour la generation automatique des cles

CREATE SEQUENCE n_sr;
CREATE SEQUENCE n_se;

-- Creation de la table des restaurants

CREATE TABLE n_resto
    ( r INTEGER PRIMARY KEY DEFAULT nextval('n_sr'),
      rnom VARCHAR(20) NOT NULL CHECK(rnom <> ''),
      ville VARCHAR(20) NOT NULL CHECK(ville <> ''),
      capacite INTEGER NOT NULL
    );
-- Creation de la table des prix

CREATE TABLE n_prix
    ( r INTEGER REFERENCES n_resto,
      categorie INTEGER NOT NULL,
      prix INTEGER NOT NULL,
      PRIMARY KEY (r,categorie)
    );

-- Creation de la table des employes

CREATE TABLE n_employes 
    ( e INTEGER PRIMARY KEY DEFAULT nextval('n_se'),
      nom VARCHAR(20) NOT NULL CHECK(nom <> ''),
      categorie INTEGER NOT NULL
    );


-- Creation de la table des reservations

CREATE TABLE n_reservations 
    ( r INTEGER REFERENCES n_resto,
      e INTEGER REFERENCES n_employes,
      d DATE NOT NULL,
      PRIMARY KEY (e,d)
    );
 
-- Creation de la table des places dispos
-- (Rangee a verrouiller pour assurer une reservation)

CREATE TABLE n_dispo
    ( r INTEGER REFERENCES n_resto,
      d DATE NOT NULL,
      places INTEGER CHECK (places >= 0),
      PRIMARY KEY (r,d)
    );

/*
 * Preparation de la facture
 */
/*
SELECT e,sum(prix) AS a_payer FROM n_reservations 
    NATURAL LEFT JOIN n_employes
    NATURAL LEFT JOIN n_prix
    WHERE d >= '01/03/2005' 
    AND d <= '31/03/2005'
    GROUP BY e; 
*/

/*
 * Reservation
 */
/*
BEGIN TRANSACTION;

LOCK TABLE n_dispo IN ROW EXCLUSIVE MODE;

SELECT n_dispo FROM   
 WHERE ...
 FOR UPDATE 

UPDATE n_dispo SET places = places-1
    WHERE d = '12/03/2005'
        AND r = 3;

INSERT INTO n_reservations
    (e,d,r) VALUES (4,'12/03/2005',3); 

COMMIT TRANSACTION;
*/