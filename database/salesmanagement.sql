-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 11 fév. 2020 à 00:58
-- Version du serveur :  10.3.16-MariaDB
-- Version de PHP :  7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `salesmanagment`
--

-- --------------------------------------------------------

--
-- Structure de la table `bank`
--

CREATE TABLE `bank` (
  `bankCode` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bank`
--

INSERT INTO `bank` (`bankCode`, `Name`) VALUES
(1, 'BANKPOP');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `categoryCode` int(11) NOT NULL,
  `categoryName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`categoryCode`, `categoryName`) VALUES
(1, 'food'),
(2, 'cleaning products'),
(3, 'personal care products'),
(4, 'electronic products');

-- --------------------------------------------------------

--
-- Structure de la table `checkout`
--

CREATE TABLE `checkout` (
  `checkOutCode` int(11) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `ownerCode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `checkout`
--

INSERT INTO `checkout` (`checkOutCode`, `description`, `ownerCode`) VALUES
(2, 'Compte local', 4),
(3, 'Compte local', 2),
(4, 'Compte local', 1),
(17, 'Compte local', 3);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `Nom` varchar(45) DEFAULT NULL,
  `Prenom` varchar(45) DEFAULT NULL,
  `Telephone` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `Nom`, `Prenom`, `Telephone`) VALUES
(1, 'Anas', 'Dadi', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `operationCode` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `descriptionAuto` varchar(255) DEFAULT NULL,
  `checkoutcode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`operationCode`, `amount`, `type`, `date`, `descriptionAuto`, `checkoutcode`) VALUES
(1, 100, 'VERS', '2020-01-14', 'Test', 4),
(2, 5, 'RETR', '2020-01-14', 'hello', 4),
(3, 100, 'VERS', '2020-01-14', 'dsfsd', 3),
(4, 56, 'RETR', '2020-01-14', 'dsfds', 3);

-- --------------------------------------------------------

--
-- Structure de la table `payment`
--

CREATE TABLE `payment` (
  `paymentCode` int(11) NOT NULL,
  `transactionCode` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `stateCode` int(11) DEFAULT NULL,
  `dueDate` date DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `chequeNumber` varchar(50) DEFAULT NULL,
  `firstNameCheque` varchar(50) DEFAULT NULL,
  `lastNameCheque` varchar(50) DEFAULT NULL,
  `bankCode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `payment`
--

INSERT INTO `payment` (`paymentCode`, `transactionCode`, `number`, `amount`, `date`, `type`, `stateCode`, `dueDate`, `paymentDate`, `chequeNumber`, `firstNameCheque`, `lastNameCheque`, `bankCode`) VALUES
(1, 1, 0, 100, '2020-01-17', 'offline', 1, '2020-01-17', '2020-01-17', 'cheque10', 'dadi', 'anasdadf', 1),
(2, 1, 0, 150, '2020-01-17', 'offline', 1, '2020-01-17', '2020-01-17', 'hello', 'dadi', 'anasdadf', 1),
(3, 1, 0, 100, '2020-01-17', 'offline', 1, '2020-01-17', '2020-01-17', 'dsfsd4564', 'dadi', 'anasdadf', 1),
(4, NULL, 0, 1000, '2020-01-17', 'offline', 1, '2020-01-17', '2020-01-17', 'test', 'dadi', 'anasdadf', 1),
(5, NULL, 0, 10, '2020-01-17', 'offline', 1, '2020-01-17', '2020-01-17', 'testol', 'dadi', 'anasdadf', 1),
(6, NULL, 0, 1000, '2020-01-18', 'offline', 1, '2020-01-18', '2020-01-18', '654464', 'dadi', 'anasdadf', 1);

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

CREATE TABLE `person` (
  `personCode` int(11) NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`personCode`, `firstName`, `lastName`, `telephone`, `email`, `type`) VALUES
(1, 'anas', 'dadi', '0651341766', 'dadi.anas@hotmail.fr', 'admin'),
(2, 'person', 'preperson', '0656147537', 'dadi.anasse@gmail.com', 'CL'),
(3, 'dadi', 'dadi', '56465465', 'dsfsd@dgds.rg', 'CL'),
(4, 'fournisseur', 'fero', '484s68df4', 'sdfd@fourni', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `productCode` int(11) NOT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `purchasePrice` float DEFAULT NULL,
  `salePrice` float DEFAULT NULL,
  `categoryCode` int(11) DEFAULT NULL,
  `barreCode` int(11) DEFAULT NULL,
  `stockquantity` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`productCode`, `designation`, `purchasePrice`, `salePrice`, `categoryCode`, `barreCode`, `stockquantity`) VALUES
(7, 'Cleaning1', 10, 10, 1, NULL, 20),
(8, 'jkljlk', 10, 20, 1, NULL, 15),
(9, 'care1', 200, 150, 3, NULL, 10),
(10, 'care1', 150, 200, 3, NULL, 10),
(11, 'care1', 150, 150, 1, NULL, 10),
(13, 'hello', 15, 15, 3, NULL, 16),
(14, 'dfgdfg', 10, 20, 4, NULL, 35),
(15, 'hello', 15, 15, 2, NULL, 10);

-- --------------------------------------------------------

--
-- Structure de la table `purchase`
--

CREATE TABLE `purchase` (
  `purchaseCode` int(11) NOT NULL,
  `total` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `stateCode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `purchaseitem`
--

CREATE TABLE `purchaseitem` (
  `purchaseCode` int(11) NOT NULL,
  `purchasePrice` double DEFAULT NULL,
  `productCode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sale`
--

CREATE TABLE `sale` (
  `saleCode` int(11) NOT NULL,
  `total` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `customerCode` int(11) DEFAULT NULL,
  `stateCode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sale`
--

INSERT INTO `sale` (`saleCode`, `total`, `date`, `customerCode`, `stateCode`) VALUES
(1, 10, '2020-01-02', 2, 1),
(2, 440, '2020-01-16', 2, 1),
(3, 1450, '2020-01-09', 3, 1),
(4, 2550, '2020-01-08', 3, 1),
(5, 50, '2020-01-02', 4, 1),
(6, 50, '2020-01-03', 4, 1),
(7, 340, '2020-01-08', 4, 1),
(8, 800, '2020-01-02', 4, 1),
(9, 600, '2020-01-02', 2, 1),
(10, 1700, '2020-01-09', 1, 1),
(11, 1700, '2020-01-09', 1, 1),
(12, 1050, '2020-01-08', 3, 1),
(13, 1800, '2020-01-10', 3, 1),
(14, 5000, '2020-01-01', 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `saleitem`
--

CREATE TABLE `saleitem` (
  `saleItemCode` int(11) NOT NULL,
  `productCode` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `subTotal` double DEFAULT NULL,
  `salePrice` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `saleCode` int(11) DEFAULT NULL,
  `qteret` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `saleitem`
--

INSERT INTO `saleitem` (`saleItemCode`, `productCode`, `quantite`, `subTotal`, `salePrice`, `number`, `saleCode`, `qteret`) VALUES
(1, 7, 10, 100, 1, 10, 1, 5),
(2, 7, 4, 40, 10, 1, 7, 0),
(3, 11, 2, 300, 150, 2, 7, 0),
(4, 7, 5, 50, 10, 1, 8, 0),
(5, 11, 5, 750, 150, 2, 8, 0),
(6, 8, 30, 600, 20, 2, 9, 0),
(7, 7, 5, 50, 10, 1, 12, 0),
(8, 10, 5, 1000, 200, 2, 12, 0),
(9, 9, 12, 1800, 150, 1, 13, 0),
(10, 9, 20, 3000, 150, 1, 14, 0),
(11, 10, 10, 2000, 200, 2, 14, 0);

-- --------------------------------------------------------

--
-- Structure de la table `state`
--

CREATE TABLE `state` (
  `stateCode` int(11) NOT NULL,
  `stateName` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `state`
--

INSERT INTO `state` (`stateCode`, `stateName`) VALUES
(1, 'en cours');

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `transactionCode` int(11) NOT NULL,
  `total` float DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `transaction`
--

INSERT INTO `transaction` (`transactionCode`, `total`, `date`) VALUES
(1, 1500, '2020-01-17');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `userCode` int(11) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `personCode` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`userCode`, `login`, `password`, `role`, `personCode`) VALUES
(1, 'anas', 'anas', 'admin', 1),
(2, 'dadi', 'dadi', 'cl', 2),
(3, 'anas', 'dadi', 'FR', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`bankCode`);

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryCode`);

--
-- Index pour la table `checkout`
--
ALTER TABLE `checkout`
  ADD PRIMARY KEY (`checkOutCode`),
  ADD KEY `checkout_person_personCode_fk` (`ownerCode`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`operationCode`),
  ADD KEY `codecheckout` (`checkoutcode`);

--
-- Index pour la table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentCode`),
  ADD KEY `payment_transaction_transactionCode_fk` (`transactionCode`),
  ADD KEY `payment_state_stateCode_fk` (`stateCode`),
  ADD KEY `payment_bank_bankCode_fk` (`bankCode`);

--
-- Index pour la table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`personCode`);

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productCode`),
  ADD UNIQUE KEY `product_barreCode_uindex` (`barreCode`),
  ADD KEY `categoryCode` (`categoryCode`);

--
-- Index pour la table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`purchaseCode`),
  ADD KEY `stateCode__pk` (`stateCode`);

--
-- Index pour la table `purchaseitem`
--
ALTER TABLE `purchaseitem`
  ADD PRIMARY KEY (`purchaseCode`),
  ADD KEY `purchaseitem_product_productCode_fk` (`productCode`);

--
-- Index pour la table `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`saleCode`),
  ADD KEY `sale_state_stateCode_fk` (`stateCode`),
  ADD KEY `sale_person_personCode_fk` (`customerCode`);

--
-- Index pour la table `saleitem`
--
ALTER TABLE `saleitem`
  ADD PRIMARY KEY (`saleItemCode`),
  ADD KEY `saleitem_product_productCode_fk` (`productCode`),
  ADD KEY `saleitem_sale_saleCode_fk` (`saleCode`);

--
-- Index pour la table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`stateCode`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transactionCode`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userCode`),
  ADD KEY `user_person_personCode_fk` (`personCode`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bank`
--
ALTER TABLE `bank`
  MODIFY `bankCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `checkout`
--
ALTER TABLE `checkout`
  MODIFY `checkOutCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `operationCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `payment`
--
ALTER TABLE `payment`
  MODIFY `paymentCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `person`
--
ALTER TABLE `person`
  MODIFY `personCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `productCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `purchaseCode` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `purchaseitem`
--
ALTER TABLE `purchaseitem`
  MODIFY `purchaseCode` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `sale`
--
ALTER TABLE `sale`
  MODIFY `saleCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `saleitem`
--
ALTER TABLE `saleitem`
  MODIFY `saleItemCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `state`
--
ALTER TABLE `state`
  MODIFY `stateCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transactionCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `userCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `checkout`
--
ALTER TABLE `checkout`
  ADD CONSTRAINT `checkout_person_personCode_fk` FOREIGN KEY (`ownerCode`) REFERENCES `person` (`personCode`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `codecheckout` FOREIGN KEY (`checkoutcode`) REFERENCES `checkout` (`checkOutCode`);

--
-- Contraintes pour la table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_bank_bankCode_fk` FOREIGN KEY (`bankCode`) REFERENCES `bank` (`bankCode`),
  ADD CONSTRAINT `payment_state_stateCode_fk` FOREIGN KEY (`stateCode`) REFERENCES `state` (`stateCode`),
  ADD CONSTRAINT `payment_transaction_transactionCode_fk` FOREIGN KEY (`transactionCode`) REFERENCES `transaction` (`transactionCode`);

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `categoryCode` FOREIGN KEY (`categoryCode`) REFERENCES `category` (`categoryCode`);

--
-- Contraintes pour la table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `stateCode__pk` FOREIGN KEY (`stateCode`) REFERENCES `state` (`stateCode`);

--
-- Contraintes pour la table `purchaseitem`
--
ALTER TABLE `purchaseitem`
  ADD CONSTRAINT `purchaseitem_product_productCode_fk` FOREIGN KEY (`productCode`) REFERENCES `product` (`productCode`);

--
-- Contraintes pour la table `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `sale_person_personCode_fk` FOREIGN KEY (`customerCode`) REFERENCES `person` (`personCode`),
  ADD CONSTRAINT `sale_state_stateCode_fk` FOREIGN KEY (`stateCode`) REFERENCES `state` (`stateCode`);

--
-- Contraintes pour la table `saleitem`
--
ALTER TABLE `saleitem`
  ADD CONSTRAINT `saleitem_product_productCode_fk` FOREIGN KEY (`productCode`) REFERENCES `product` (`productCode`),
  ADD CONSTRAINT `saleitem_sale_saleCode_fk` FOREIGN KEY (`saleCode`) REFERENCES `sale` (`saleCode`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_person_personCode_fk` FOREIGN KEY (`personCode`) REFERENCES `person` (`personCode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
