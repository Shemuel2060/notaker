DROP DATABASE IF EXISTS arola_cornellnotaker;

CREATE DATABASE arola_cornellnotaker;

-- GRANT ALL PRIVILEGES ON arola_cornellnotaker.* TO 'arola'@'localhost';

USE arola_cornellnotaker; 

-- CRUD OPERATIONS
SELECT * FROM users;
SELECT * FROM notes;
SELECT * FROM notebooks;
SELECT * FROM summary;
SELECT * FROM note_tags;
SELECT * FROM refs;
SELECT * FROM refs_notes;
SELECT * FROM reminders;
SELECT * FROM tags;



-- disable safe update model
SET SQL_SAFE_UPDATES = 0;

-- Delete some fields to test them again
DELETE FROM `note` WHERE `note_title` = 'java.time.LocalDate class';
-- Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column.  To disable safe mode, toggle the option in Preferences -> SQL Editor and reconnect.

-- checking for size constraints
SHOW CREATE TABLE arola_cornellnotaker.note;




