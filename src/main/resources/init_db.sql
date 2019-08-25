CREATE TABLE `accounts` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '账号id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `userpass` char(41) NOT NULL COMMENT '用户密码',
  `role` varchar(20) NOT NULL DEFAULT 'User',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `account_bills` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '账单号',
  `account_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `money` double DEFAULT '0' COMMENT '金额',
  `info` varchar(50) DEFAULT NULL COMMENT '备注',
  `date` date DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;