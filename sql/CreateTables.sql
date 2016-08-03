CREATE TABLE customer (
	customer_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	fname	TEXT,
	age	INTEGER,
	gender	TEXT
); 

CREATE TABLE loc (
	loc_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	loc_name	TEXT,
	latitude	REAL,
	longitude	REAL
); 

CREATE TABLE machine (
	machine_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	latitude	REAL,
	longitude	INTEGER
); 

CREATE TABLE product (
	product_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	product_name	TEXT,
	price	REAL
); 


CREATE TABLE machine_location (
	machine_id	INTEGER NOT NULL,
	loc_id	INTEGER NOT NULL,
	PRIMARY KEY ( machine_id, loc_id)
); 

CREATE TABLE location_products (
	loc_id	INTEGER NOT NULL,
	product_id	INTEGER NOT NULL,
	PRIMARY KEY ( loc_id, product_id)
); 

CREATE TABLE customer_products (
	customer_id	INTEGER NOT NULL,
	product_id	INTEGER NOT NULL,
	PRIMARY KEY ( customer_id, product_id)
);

CREATE TABLE customer_location (
	customer_id	INTEGER NOT NULL,
	loc_id	INTEGER NOT NULL,
	PRIMARY KEY ( customer_id, loc_id)
); 


CREATE TABLE purchases (
	purchase_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	purchase_date	DATETIME,
	product_id	INTEGER,
	machine_id INTEGER,
	pos_price REAL,
	customer_id INTEGER
); 
