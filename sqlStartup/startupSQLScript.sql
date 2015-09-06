/**
 * ========================== WARNING ============================
 *       THIS SCRIPT WILL ERASE ALL DATA FROM recipe_factory      
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

-- ingredient
DROP TABLE IF EXISTS ingredient;
CREATE TABLE `ingredient` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`)   
 );
  
-- recipe
DROP TABLE IF EXISTS recipe;
CREATE TABLE `recipe` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`)
 );
  
-- recipe ingredients
DROP TABLE IF EXISTS recipe_ingredient;
CREATE TABLE `recipe_ingredient` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `recipe_id` int(11) unsigned NOT NULL,
   `ingredient_id` int(11) unsigned NOT NULL,
   `quantity` decimal(10,3) not null,
   `unit` enum('kg','g','mg','l','ml','units') not null default 'units',
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`recipe_id`,`ingredient_id`)
 );
  
-- box
DROP TABLE IF EXISTS box;
CREATE TABLE `box` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `size` decimal(10,3) NOT NULL,
   `unit` enum('kg','g','mg','l','ml') NOT NULL,
   `status` char(1) NOT NULL DEFAULT 'Y',
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`,`size`,`unit`)
 );
  
-- machine
DROP TABLE IF EXISTS machine;
CREATE TABLE `machine` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `capacity` decimal(10,3) NOT NULL,
   `unit` enum('kg','g','mg','l','ml') NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`)
 );

-- vendor  
DROP TABLE IF EXISTS vendor;
CREATE TABLE `vendor` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `password` varchar(255) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`)
 );

-- user
DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `username` varchar(50) NOT NULL,
   `password` varchar(255) NOT NULL,
   `type` enum('super_admin','admin','manager','normal_user','temporary','visitor') NOT NULL DEFAULT 'temporary',
   `status` enum('active', 'inactive', 'blocked', 'password_reset', 'deleted', 'new') NOT NULL DEFAULT 'new',
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`username`)
 );

-- client
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `password` varchar(255) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`)
 );

-- customer  
DROP TABLE IF EXISTS customer;
CREATE TABLE `customer` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `password` varchar(255) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`name`)
 );

-- orders
DROP TABLE IF EXISTS orders;
CREATE TABLE `orders` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY (`name`)
 );
  
-- orders_vendor
DROP TABLE IF EXISTS `orders_vendor`;
CREATE TABLE `orders_vendor` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `order_id` int(11) unsigned NOT NULL,
   `type` enum('ingredient','box') NOT NULL,
   `type_id` int(11) unsigned not null,
   `quantity` decimal(10,3) not null,
   `unit` enum('kg','g','mg','l','ml') not null,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY (`order_id`,`type`,`type_id`)
 );
  
-- orders_client
DROP TABLE IF EXISTS `orders_client`;
CREATE TABLE `orders_client` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `order_id` int(11) unsigned NOT NULL,
   `type` enum('recipe') NOT NULL,
   `type_id` int(11) unsigned not null,
   `quantity` decimal(10,3) not null,
   `unit` enum('kg','g','mg','l','ml') not null,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY (`order_id`,`type`,`type_id`)
 );
  
-- dispatcher
-- daily_production
-- stock
