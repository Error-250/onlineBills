CREATE TABLE `user` (
  `id`       BIGINT(20) AUTO_INCREMENT,
  `username` VARCHAR (20) NOT NULL,
  `password` VARCHAR (32) NOT NULL,
  `role`     VARCHAR (20) NOT NULL,
  PRIMARY KEY (`id`)
)CHARSET=utf8;

CREATE TABLE `bills` (
);