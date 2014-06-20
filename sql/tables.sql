CREATE database `qatang_cms` DEFAULT CHARACTER SET=utf8;
use `qatang_cms`
CREATE TABLE `c_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) NOT NULL DEFAULT '',
  `gender` tinyint(2) NOT NULL DEFAULT 1,
  `created_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE (`username`),
  KEY `idx_user_gender` (`gender`),
  KEY `idx_user_created_time` (`created_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;