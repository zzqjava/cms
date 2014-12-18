CREATE database `qatang_cms` DEFAULT CHARACTER SET=utf8;
use `qatang_cms`
CREATE TABLE `c_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL DEFAULT '',
  `password` varchar(32) NOT NULL DEFAULT '',
  `salt` varchar(64) NOT NULL DEFAULT '',
  `name` varchar(32) NOT NULL DEFAULT '',
  `email` varchar(128) NOT NULL DEFAULT '',
  `mobile` varchar(32) NULL DEFAULT '',
  `gender` tinyint(2) NULL DEFAULT 0,
  `created_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `login_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_login_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `qq` varchar(32) NULL DEFAULT '',
  `valid` tinyint(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE (`username`),
  UNIQUE (`email`),
  INDEX `idx_user_name` (`name`),
  INDEX `idx_user_mobile` (`mobile`),
  INDEX `idx_user_gender_valid` (`gender`, `valid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `c_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `identifier` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) NOT NULL DEFAULT '',
  `description` varchar(128) DEFAULT '',
  `is_default` tinyint(2) DEFAULT 0,
  `valid` tinyint(2) DEFAULT 0,
  `created_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `idx_role_name` (`name`),
  KEY `idx_role_created_time` (`created_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `c_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idx_user_id_role_id` (`user_id`, `role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `c_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `identifier` varchar(32) NOT NULL DEFAULT '',
  `type` tinyint(2) NULL DEFAULT 0,
  `tree_level` int(11) NOT NULL DEFAULT 0,
  `parent_id` bigint(20) NOT NULL DEFAULT 0,
  `has_children` tinyint(2) NOT NULL DEFAULT 0,
  `name` varchar(128) NOT NULL DEFAULT '',
  `url` varchar(256) NOT NULL DEFAULT '',
  `priority` int(11) NOT NULL DEFAULT 0,
  `valid` tinyint(2) NOT NULL DEFAULT 0,
  `memo` varchar(32) NULL DEFAULT '',
  `created_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `idx_resource_name` (`name`),
  KEY `idx_resource_created_time` (`created_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `c_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_resource_id` (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
