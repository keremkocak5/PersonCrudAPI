CREATE TABLE PERSON (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   first_name VARCHAR(250) NOT NULL,
   last_name VARCHAR(250) NOT NULL,
   favourite_colour VARCHAR(250) NOT NULL,
   age INT NOT NULL,
   person_status INT NOT NULL,
   create_time DATE NOT NULL,
   last_update_time DATE,
   delete_time DATE,
   create_user VARCHAR(250)  NOT NULL,
   last_update_user VARCHAR(250),
   delete_user VARCHAR(250)
 );
