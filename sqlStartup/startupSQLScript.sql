/**
 * ========================== WARNING ============================
 *       THIS SCRIPT WILL ERASE ALL DATA FROM recipe_factory      
 *       AND WILL RESTORE DATABASE TO DEFAULT STARTUP STATE       
 * ===============================================================
 **/

-- Create a temporary database to use whilst dropping recipe_factory
-- It must fail if this exists, to prevevent big disasters...
CREATE DATABASE temporary_database_recipe_factory;
USE temporary_database_recipe_factory;

DROP DATABASE IF EXISTS recipe_factory;
CREATE DATABASE recipe_factory;

USE recipe_factory;
DROP DATABASE IF EXISTS temporary_database_recipe_factory;

DROP TABLE IF EXISTS ingredient;
CREATE TABLE `ingredient` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`name`),
   UNIQUE KEY (`id`,`name`)
--   KEY(`created_date`),
--   KEY(`last_updated`),
--   KEY(`created_by`),
--   KEY(`updated_by`)
 );
 
INSERT INTO ingredient (`name`, `created_by`, `updated_by`) VALUES("name", "me", "me");
UPDATE ingredient SET name = "Banana", updated_by = "moi" WHERE id = 1;
INSERT INTO ingredient (`name`, `created_by`, `updated_by`) VALUES("Coconut", "me", "me");
INSERT INTO ingredient (`name`) VALUES("Coconut Light");
INSERT INTO ingredient (`name`) VALUES("Coconut Milk");
INSERT INTO ingredient (`name`) VALUES("Coconut powder");
INSERT INTO ingredient (`name`) VALUES("Coconut ground");
INSERT INTO ingredient (`name`) VALUES("Vanilla pods");
INSERT INTO ingredient (`name`) VALUES("Vanilla powder");



DROP TABLE IF EXISTS change_history_log;
CREATE TABLE `change_history_log` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `db` varchar(20) NOT NULL DEFAULT 'recipe_factory',
   `table_db` varchar(100) NOT NULL,
   `cmd` ENUM('INSERT','UPDATE','DROP','DELETE'),
   `sql_cmd` TEXT NOT NULL,
   `blob` BLOB,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY(`db`,`table_db`,`cmd`),
   KEY(`created_date`),
   KEY(`created_by`)
 );
 
INSERT INTO change_history_log (db, table_db, cmd, sql_cmd)
    VALUES('recipe_factory', 'ingredient', 'INSERT', "INSERT INTO ingredient (`name`, `created_by`, `updated_by`) VALUES('name', 'me', 'me')"),
    ('recipe_factory', 'ingredient', 'DELETE', "INSERT INTO ingredient (`name`, `created_by`, `updated_by`) VALUES('name', 'me', 'me')"),
    ('recipe_factory', 'ingredient', 'UPDATE', "INSERT INTO ingredient (`name`, `created_by`, `updated_by`) VALUES('name', 'me', 'me')"),
    ('recipe_factory', 'ingredient', 'UPDATE', "INSERT INTO ingredient (`name`, `created_by`, `updated_by`) VALUES('name', 'me', 'me')"),
    ('recipe_factory', 'ingredient', 'DROP', "INSERT INTO ingredient (`name`, `created_by`, `updated_by`) VALUES('name', 'me', 'me')");




