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

INSERT INTO `c_resource` VALUES ('1', 'sys', '1', '1', '0', '1', '系统管理', '', '0', '1', '', '2014-12-27 18:10:27', '2014-12-31 16:42:40');
INSERT INTO `c_resource` VALUES ('2', 'sys:user', '1', '2', '1', '1', '用户管理', '/user/list', '0', '1', '', '2014-12-27 18:11:00', '2014-12-31 16:42:56');
INSERT INTO `c_resource` VALUES ('3', 'sys:resource', '1', '2', '1', '1', '资源管理', '/resource/list', '0', '1', '', '2014-12-27 18:11:22', '2014-12-31 16:45:21');
INSERT INTO `c_resource` VALUES ('4', 'sys:role', '1', '2', '1', '1', '角色管理', '/role/list', '0', '1', '', '2014-12-27 18:11:39', '2014-12-31 16:47:53');
INSERT INTO `c_resource` VALUES ('5', 'sys:user:update', '2', '3', '2', '2', '用户修改', '', '0', '1', '', '2014-12-30 10:26:02', '2014-12-30 10:43:10');
INSERT INTO `c_resource` VALUES ('6', 'sys:user:create', '2', '3', '2', '2', '用户添加', '', '0', '1', '', '2014-12-30 10:27:26', '2014-12-30 10:43:29');
INSERT INTO `c_resource` VALUES ('7', 'sys:user:del', '2', '3', '2', '2', '用户删除', '', '0', '1', '', '2014-12-31 16:45:04', '2014-12-31 16:45:11');
INSERT INTO `c_resource` VALUES ('8', 'sys:resource:create', '2', '3', '3', '2', '资源添加', '', '0', '1', '', '2014-12-31 16:45:53', '2014-12-31 16:45:59');
INSERT INTO `c_resource` VALUES ('9', 'sys:resource:update', '2', '3', '3', '2', '  资源修改', '', '0', '1', '', '2014-12-31 16:46:20', '2014-12-31 16:46:20');
INSERT INTO `c_resource` VALUES ('10', 'sys:role:create', '2', '3', '4', '2', '角色添加', '', '0', '1', '', '2014-12-31 16:46:45', '2014-12-31 16:46:45');
INSERT INTO `c_resource` VALUES ('11', 'sys:role:update', '2', '3', '4', '2', '角色修改', '', '0', '1', '', '2014-12-31 16:47:01', '2014-12-31 16:47:01');
INSERT INTO `c_resource` VALUES ('12', 'sys:role:del', '2', '3', '4', '2', '角色删除', '', '0', '1', '', '2014-12-31 16:47:27', '2014-12-31 16:47:27');

INSERT INTO `c_role` VALUES ('1', 'sys', '系统管理员', '1', '2', '1', '0000-00-00 00:00:00', '2014-12-27 19:07:03');

INSERT INTO `c_user` VALUES ('1', 'qatang', '79926f441a7c8dca5a2e0711ac1d8173', 'd92d97bfab3275222b49b361a4db2b50', '唐鹏飞', 'qatang@gmail.com', '15901298088', '1', '2014-12-18 16:24:59', '2014-12-27 15:34:17', '2014-12-31 20:47:21', '2014-12-31 20:34:32', '977269167', '1');

INSERT INTO `c_user_role` VALUES ('1', '1', '1');

INSERT INTO `c_role_resource` VALUES ('1', '1', '1');
INSERT INTO `c_role_resource` VALUES ('2', '1', '2');
INSERT INTO `c_role_resource` VALUES ('3', '1', '3');
INSERT INTO `c_role_resource` VALUES ('4', '1', '4');
INSERT INTO `c_role_resource` VALUES ('5', '1', '5');
INSERT INTO `c_role_resource` VALUES ('6', '1', '6');
INSERT INTO `c_role_resource` VALUES ('7', '1', '7');
INSERT INTO `c_role_resource` VALUES ('8', '1', '8');
INSERT INTO `c_role_resource` VALUES ('9', '1', '9');
INSERT INTO `c_role_resource` VALUES ('10', '1', '10');
INSERT INTO `c_role_resource` VALUES ('11', '1', '11');
INSERT INTO `c_role_resource` VALUES ('12', '1', '12');

