DROP TABLE IF EXISTS PRICE;

CREATE TABLE IF NOT EXISTS `price` (
  `id`           BIGINT(20) NOT NULL AUTO_INCREMENT,
  `bus_number`   VARCHAR(255)        DEFAULT NULL,
  `price_amount` VARCHAR(255)        DEFAULT NULL,
  `trip_date`    VARCHAR(255)        DEFAULT NULL,
  `created_time` DATETIME            DEFAULT NULL,
  `created_by`   VARCHAR(255)        DEFAULT NULL,
  `enabled`      INT(11)             DEFAULT NULL,
  `updated_time` DATETIME            DEFAULT NULL,
  `updated_by`   VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;