/**
 * ========================== WARNING ============================
 *       THIS SCRIPT WILL ERASE ALL DATA FROM rfact_core      
 *       AND WILL RESTORE DATABASE TO DEFAULT STARTUP STATE       
 * ===============================================================
 **/

-- Create a temporary database to use whilst dropping recipe_factory
-- It must fail if this exists, to prevevent big disasters...
CREATE DATABASE temporary_database_rfact_core;
USE temporary_database_rfact_core;
DROP DATABASE IF EXISTS rfact_core;
CREATE DATABASE rfact_core;
USE rfact_core;
DROP DATABASE IF EXISTS temporary_database_rfact_core;

/**
 * ============================= CORE ============================
 *   ELEMENTS: 
 *     - ingredient        - Used in recipes
 *     - recipe            - Has ingredients, ordered by clients
 *     - box               - Used in client's recipe orders
 *     - machine           - Used to make recipes
 *     - unit              - Used for unit recognition and equivalences
 *   ACTORS:
 *     - client            - Ordering recipes
 *     - customer          - Ordering recipes online
 *     - user              - Internal user
 *     - vendor            - Stock supplier of ingredients and boxes
 *   LINKS:
 *     - recipe_ingredient - Linking recipes to its ingredients
 *   ADMIN:
 *     - privileges        - User access privileges
 * ===============================================================
**/

-- ingredient
CREATE TABLE `ingredient` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `description` varchar(255) not null default '',
   `notes` varchar(255) not null default '',
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );
  
-- recipe
CREATE TABLE `recipe` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );
  
-- box
CREATE TABLE `box` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `size` decimal(10,3) NOT NULL,
   `unit` enum('kg','g','mg','l','ml') NOT NULL,
   `status` char(1) NOT NULL DEFAULT 'Y',
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`,`size`,`unit`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );
  
-- machine
CREATE TABLE `machine` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `capacity` decimal(10,3) NOT NULL,
   `unit` enum('kg','g','mg','l','ml') NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );

-- unit
CREATE TABLE `unit` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `label` VARCHAR(25) NOT NULL,
   `parent_id` int(11) unsigned NOT NULL DEFAULT 0,
   `ratio_to_parent` decimal(24,12) NOT NULL,
   `child_id` int(11) unsigned NOT NULL DEFAULT 0,
   `ratio_to_child` decimal(24,12) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`parent_id`,`label`),
   KEY(`label`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );
 
-- Populate unit with initial static required data (Parent 0 uses capital on first letter)
INSERT INTO unit (id,label,parent_id,ratio_to_parent,child_id,ratio_to_child) VALUES (1,'Unit', 0,     0, 0, 0);    -- e.g. 10 Unit boxes
INSERT INTO unit (id,label,parent_id,ratio_to_parent,child_id,ratio_to_child) VALUES (2,'Kg',   0,     0, 4, 1000); -- e.g. 1 Kg sugar
INSERT INTO unit (id,label,parent_id,ratio_to_parent,child_id,ratio_to_child) VALUES (3,'L',    0,     0, 6, 1000);    -- e.g. 10 L milk
INSERT INTO unit (id,label,parent_id,ratio_to_parent,child_id,ratio_to_child) VALUES (4,'g',    2, 0.001, 5, 1000); -- e.g. 250 g vanilla
INSERT INTO unit (id,label,parent_id,ratio_to_parent,child_id,ratio_to_child) VALUES (5,'mg',   4, 0.001, 0, 0); -- e.g. 50 mg super powder
INSERT INTO unit (id,label,parent_id,ratio_to_parent,child_id,ratio_to_child) VALUES (6,'ml',   3, 0.001, 0, 0); -- e.g. 250 ml box

-- vendor  
CREATE TABLE `vendor` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `password` varchar(255) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );

-- user
CREATE TABLE `user` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `username` varchar(50) NOT NULL,
   `password` blob NOT NULL,
   `privilege_id` int(11) not null default 0,
   `status` enum('active', 'inactive', 'blocked', 'password_reset', 'deleted', 'new') NOT NULL DEFAULT 'new',
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`username`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );

-- client
CREATE TABLE `client` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `password` varchar(255) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );

-- customer  
CREATE TABLE `customer` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `password` varchar(255) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );

-- recipe ingredients
CREATE TABLE `recipe_ingredient` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `recipe_id` int(11) unsigned NOT NULL,
   `ingredient_id` int(11) unsigned NOT NULL,
   `quantity` decimal(10,3) not null,
   `unit` enum('kg','g','mg','l','ml','units') not null default 'units',
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`recipe_id`,`ingredient_id`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)   
 );
  
-- privilege
CREATE TABLE `privilege` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `type` enum('super_admin','admin','manager','normal', 'temporary') NOT NULL DEFAULT 'temporary',
   `status` enum('active', 'inactive', 'blocked', 'password_reset', 'deleted', 'new') NOT NULL DEFAULT 'new',
   `access` enum('rw','ro','wo') NOT NULL DEFAULT 'ro',
   `description` varchar(255) not null,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY(`name`),
   KEY(`type`),
   KEY(`access`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );

