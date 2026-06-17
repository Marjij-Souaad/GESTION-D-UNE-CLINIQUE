-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS `gestion_clinique` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gestion_clinique`;

-- Create patients table
CREATE TABLE IF NOT EXISTS `patients` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `nom` VARCHAR(50) NOT NULL,
    `prenom` VARCHAR(50) NOT NULL,
    `sexe` VARCHAR(10) NOT NULL,
    `date_naissance` DATE NOT NULL,
    `telephone` VARCHAR(20),
    `adresse` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create rendezvous table
CREATE TABLE IF NOT EXISTS `rendezvous` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `patient_id` INT NOT NULL,
    `date_rdv` DATE NOT NULL,
    `heure` VARCHAR(10) NOT NULL,
    `motif` TEXT,
    `urgence` INT DEFAULT 1,
    `statut` VARCHAR(20) DEFAULT 'Prévu',
    FOREIGN KEY (`patient_id`) REFERENCES `patients`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
