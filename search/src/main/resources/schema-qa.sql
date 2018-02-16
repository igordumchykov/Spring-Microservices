DROP TABLE IF EXISTS SEARCH_TRIP;
DROP TABLE IF EXISTS SEARCH_INVENTORY;
DROP TABLE IF EXISTS SEARCH_PRICE;

CREATE TABLE IF NOT EXISTS `SEARCH_INVENTORY` (
  `inv_id`       BIGINT(20) NOT NULL AUTO_INCREMENT,
  `count`        INT(11)             DEFAULT NULL,
  `created_time` DATETIME            DEFAULT NULL,
  `created_by`   VARCHAR(255)        DEFAULT NULL,
  `enabled`      INT(11)             DEFAULT NULL,
  `updated_time` DATETIME            DEFAULT NULL,
  `updated_by`   VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (`inv_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `SEARCH_PRICE` (
  `price_id`     BIGINT(20) NOT NULL AUTO_INCREMENT,
  `currency`     VARCHAR(255)        DEFAULT NULL,
  `price_amount` VARCHAR(255)        DEFAULT NULL,
  `created_time` DATETIME            DEFAULT NULL,
  `created_by`   VARCHAR(255)        DEFAULT NULL,
  `enabled`      INT(11)             DEFAULT NULL,
  `updated_time` DATETIME            DEFAULT NULL,
  `updated_by`   VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (`price_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `SEARCH_TRIP` (
  `id`           BIGINT(20) NOT NULL AUTO_INCREMENT,
  `bus_number`   VARCHAR(255)        DEFAULT NULL,
  `destination`  VARCHAR(255)        DEFAULT NULL,
  `origin`       VARCHAR(255)        DEFAULT NULL,
  `trip_date`    VARCHAR(255)        DEFAULT NULL,
  `inv_id`       BIGINT(20)          DEFAULT NULL,
  `price_id`     BIGINT(20)          DEFAULT NULL,
  `created_time` DATETIME            DEFAULT NULL,
  `created_by`   VARCHAR(255)        DEFAULT NULL,
  `enabled`      INT(11)             DEFAULT NULL,
  `updated_time` DATETIME            DEFAULT NULL,
  `updated_by`   VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`inv_id`) REFERENCES `SEARCH_INVENTORY` (`inv_id`),
  CONSTRAINT FOREIGN KEY (`price_id`) REFERENCES `SEARCH_PRICE` (`price_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;