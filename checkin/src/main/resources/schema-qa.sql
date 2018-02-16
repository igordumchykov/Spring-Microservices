DROP TABLE IF EXISTS CHECK_IN_RECORD;

CREATE TABLE IF NOT EXISTS `check_in_record` (
  `id`            BIGINT(20) NOT NULL AUTO_INCREMENT,
  `booking_id`    BIGINT(20) NOT NULL,
  `bus_number`    VARCHAR(255)        DEFAULT NULL,
  `check_in_time` DATETIME            DEFAULT NULL,
  `first_name`    VARCHAR(255)        DEFAULT NULL,
  `last_name`     VARCHAR(255)        DEFAULT NULL,
  `seat_number`   VARCHAR(255)        DEFAULT NULL,
  `trip_date`     VARCHAR(255)        DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;