CREATE TABLE IF NOT EXISTS `barcos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL DEFAULT '',
  `eslora` smallint NOT NULL DEFAULT '0',
  `potencia_cv` float NOT NULL DEFAULT '0',
  `potencia_kw` double NOT NULL DEFAULT '0',
  `es_extrangero` boolean DEFAULT 'false',

  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `tripulantes`(
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL DEFAULT '',
  `apellido1` varchar(30) NOT NULL DEFAULT '',
  `apellido2` varchar(30) NOT NULL DEFAULT '',
  `rol` varchar(60) NOT NULL DEFAULT '',
  `barco` bigint,

  PRIMARY KEY (`id`),
  FOREIGN KEY (`barco`) REFERENCES barcos (`id`)
);

CREATE TABLE IF NOT EXISTS `demo`(
  `id` bigint NOT NULL AUTO_INCREMENT, 
  `float` float,
  `varchar` varchar,
  `bool` boolean,
  `char` nchar,
  `date` date,

  PRIMARY KEY (`id`) 
);