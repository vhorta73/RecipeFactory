/**
 * ========================== WARNING ============================
 *       THIS SCRIPT WILL ERASE ALL DATA FROM rfact_wh     
 *       AND WILL RESTORE DATABASE TO DEFAULT STARTUP STATE       
 * ===============================================================
 **/

-- Create a temporary database to use whilst dropping recipe_factory
-- It must fail if this exists, to prevevent big disasters...
CREATE DATABASE temporary_database_rfact_wh;
USE temporary_database_rfact_wh;
DROP DATABASE IF EXISTS rfact_wh;
CREATE DATABASE rfact_wh;
USE rfact_wh;
DROP DATABASE IF EXISTS temporary_database_rfact_wh;

/**
 * ============================== WH =============================
 *   ORDERS: 
 *     - order            - Main order id
 *     - order_client     - Order made by the client
 *     - order_client_box - Set of boxes chosed to accomodate 
 *                          the total quantity ordered by the client
 *     - order_vendor     - Order made to the vendor
 * ===============================================================
**/

-- order
CREATE TABLE `order` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(50) NOT NULL,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY (`name`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );
  
-- order_vendor
CREATE TABLE `order_vendor` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `order_id` int(11) unsigned NOT NULL,
   `type` enum('ingredient','box') NOT NULL,
   `type_id` int(11) unsigned not null,
   `quantity` decimal(10,3) not null,
   `unit` enum('kg','g','mg','l','ml') not null,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   KEY (`order_id`,`type`,`type_id`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );
  
-- order_client
CREATE TABLE `order_client` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `order_id` int(11) unsigned NOT NULL,
   `type` enum('recipe') NOT NULL,
   `type_id` int(11) unsigned not null,
   `quantity` decimal(10,3) not null,
   `unit` enum('kg','g','mg','l','ml') not null,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY(`order_id`,`type`,`type_id`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );
  
-- order_client_box
CREATE TABLE `order_client_box` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `order_client_id` int(11) unsigned not null,
   `box_id` int(11) unsigned NOT NULL,
   `quantity` decimal(10,3) not null,
   `created_by` varchar(25) NOT NULL DEFAULT 'system',
   `created_date` timestamp not NULL DEFAULT CURRENT_TIMESTAMP,
   `last_updated_by` varchar(25) NOT NULL DEFAULT 'system',
   `last_updated_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY (`order_client_id`,`box_id`),
   KEY(`created_by`),
   KEY(`last_updated_by`),
   KEY(`created_date`),
   KEY(`last_updated_date`)
 );
  
-- dispatcher
-- daily_production
-- stock
