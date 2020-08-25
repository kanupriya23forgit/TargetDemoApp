CREATE TABLE product (
	  id INT AUTO_INCREMENT  PRIMARY KEY,
	  name VARCHAR(250) NOT NULL ,
	  price VARCHAR(250) NOT NULL,
	  currency VARCHAR(250) DEFAULT NULL,
	  UNIQUE(name)
	);


INSERT into product

values(1 , 'RedMI Note 3', '123' , 'USD');

INSERT into product

values(2 , 'Iphone 8', '250' , 'INR');

