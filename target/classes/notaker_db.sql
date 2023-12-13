DROP DATABASE IF EXISTS arola_cornellnotaker;

CREATE DATABASE arola_cornellnotaker;

-- GRANT ALL PRIVILEGES ON arola_cornellnotaker.* TO 'arola'@'localhost';

USE arola_cornellnotaker; 

-- CRUD OPERATIONS
SELECT * FROM note;
SELECT * FROM note_tags;
SELECT * FROM reference_sources;
SELECT * FROM reference_sources_note;
SELECT * FROM notebook;
SELECT * FROM reminders;
SELECT * FROM tags;
SELECT * FROM user;

-- checking for size constraints
SHOW CREATE TABLE arola_cornellnotaker.note;




