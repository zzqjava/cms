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
  `path` varchar(500) NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_resource_name` (`name`),
  KEY `idx_path` (`path`),
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

INSERT INTO `c_resource` VALUES (1, '', 0, 1, 0, 1, '系统管理', '', 0, 2, '', '2014-12-27 18:10:27', '2015-1-16 14:00:30', '/1');
INSERT INTO `c_resource` VALUES (2, '', 0, 2, 1, 1, '用户管理', '/user/list', 0, 1, '', '2014-12-27 18:11:00', '2015-1-16 14:00:59', '/1/2');
INSERT INTO `c_resource` VALUES (3, '', 0, 2, 1, 1, '资源管理', '/resource/list', 0, 1, '', '2014-12-27 18:11:22', '2015-1-16 14:01:04', '/1/3');
INSERT INTO `c_resource` VALUES (4, '', 0, 2, 1, 1, '角色管理', '/role/list', 0, 1, '', '2014-12-27 18:11:39', '2015-1-16 14:01:10', '/1/4');
INSERT INTO `c_resource` VALUES (5, 'sys:user:update', 2, 3, 2, 2, '用户修改', '/user/update', 0, 1, '', '2014-12-30 10:26:02', '2015-1-16 14:01:18', '/1/2/5');
INSERT INTO `c_resource` VALUES (6, 'sys:user:create', 2, 3, 2, 2, '用户添加', '/user/create', 0, 1, '', '2014-12-30 10:27:26', '2015-1-16 14:01:23', '/1/2/6');
INSERT INTO `c_resource` VALUES (7, 'sys:resource:create', 2, 3, 3, 2, '资源添加', '/resource/create', 0, 1, '', '2014-12-31 16:45:53', '2015-1-16 14:01:29', '/1/3/7');
INSERT INTO `c_resource` VALUES (8, 'sys:resource:update', 2, 3, 3, 2, '  资源修改', '/resource/update', 0, 1, '', '2014-12-31 16:46:20', '2015-1-16 14:01:49', '/1/3/8');
INSERT INTO `c_resource` VALUES (9, 'sys:role:create', 2, 3, 4, 2, '角色添加', '/role/create', 0, 1, '', '2014-12-31 16:46:45', '2015-1-16 14:01:54', '/1/4/9');
INSERT INTO `c_resource` VALUES (10, 'sys:role:update', 2, 3, 4, 2, '角色修改', '/role/update', 0, 1, '', '2014-12-31 16:47:01', '2015-1-16 14:02:00', '/1/4/10');
INSERT INTO `c_resource` VALUES (11, 'sys:user:list', 2, 3, 2, 2, '用户列表', '/user/list', 0, 1, '', '2015-1-8 10:44:04', '2015-1-16 14:02:06', '/1/2/11');
INSERT INTO `c_resource` VALUES (12, 'sys:user:detail', 2, 3, 2, 2, '用户查看', '/user/detail', 0, 1, '', '2015-1-14 16:19:22', '2015-1-16 14:02:11', '/1/2/12');
INSERT INTO `c_resource` VALUES (13, 'sys:user:switchStatus', 2, 3, 2, 2, '启用/禁用', '/user/switch/status', 0, 1, '', '2015-1-14 17:01:13', '2015-1-16 14:02:16', '/1/2/13');
INSERT INTO `c_resource` VALUES (14, 'sys:user:changePassword', 2, 3, 2, 2, '修改密码', '/user/password/change', 0, 1, '', '2015-1-14 17:14:25', '2015-1-16 14:02:21', '/1/2/14');
INSERT INTO `c_resource` VALUES (15, 'sys:user:forgetPassword', 2, 3, 2, 2, '忘记密码', '/password/forget', 0, 1, '', '2015-1-14 17:15:02', '2015-1-16 14:02:26', '/1/2/15');
INSERT INTO `c_resource` VALUES (16, 'sys:user:resetPassword', 2, 3, 2, 2, '重置密码', '/password/reset', 0, 1, '', '2015-1-14 17:15:28', '2015-1-16 14:02:32', '/1/2/16');
INSERT INTO `c_resource` VALUES (17, 'sys:resource:list', 2, 3, 3, 2, '资源列表', '/resource/list', 0, 2, '', '2015-1-14 17:34:56', '2015-1-16 14:02:37', '/1/3/17');
INSERT INTO `c_resource` VALUES (18, 'sys:resource:switchStatus', 2, 3, 3, 2, '启用/禁用', '/resource/switchStatus', 0, 1, '', '2015-1-14 17:37:21', '2015-1-16 15:18:21', '/1/3/18');
INSERT INTO `c_resource` VALUES (19, 'sys:resource:detail', 2, 3, 3, 2, '资源查看', '/resource/detail', 0, 1, '', '2015-1-14 17:38:15', '2015-1-16 14:02:47', '/1/3/19');
INSERT INTO `c_resource` VALUES (20, 'sys:role:list', 2, 3, 4, 2, '角色列表', '/role/list', 0, 1, '', '2015-1-14 18:16:26', '2015-1-16 14:11:53', '/1/4/20');
INSERT INTO `c_resource` VALUES (21, 'sys:role:switchStatus', 2, 3, 4, 2, '启用/禁用', '/role/switchStatus', 0, 1, '', '2015-1-14 18:17:12', '2015-1-21 15:38:54', '/1/4/21');
INSERT INTO `c_resource` VALUES (22, 'sys:userRole:allot', 2, 3, 2, 2, '分配角色', '/userRole/allot', 0, 1, '', '2015-1-14 18:20:25', '2015-1-16 14:03:03', '/1/2/22');
INSERT INTO `c_resource` VALUES (23, 'sys:roleResource:allot', 2, 3, 3, 2, '分配资源', '/roleResource/allot', 0, 1, '', '2015-1-14 18:22:10', '2015-1-16 14:03:08', '/1/3/23');
INSERT INTO `c_resource` VALUES (24, 'sys:role:detail', 2, 3, 4, 2, ' 角色查看', '/role/detail', 0, 1, '', '2015-1-14 18:22:56', '2015-1-16 14:03:14', '/1/4/24');

INSERT INTO `c_role` VALUES ('1', '', '系统管理员', '系统管理员', '2', '1', '0000-00-00 00:00:00', '2014-12-27 19:07:03');
INSERT INTO `c_role` VALUES ('2', '', '普通用户', '普通用户', '1', '1', '2014-12-24 11:30:14', '2014-12-24 20:45:00');

INSERT INTO `c_user` VALUES ('1', 'qatang', '79926f441a7c8dca5a2e0711ac1d8173', 'd92d97bfab3275222b49b361a4db2b50', '唐鹏飞', 'qatang@gmail.com', '15901298088', '1', '2014-12-18 16:24:59', '2014-12-27 15:34:17', '2015-01-14 18:54:51', '2015-01-14 18:51:56', '977269167', '1');

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
INSERT INTO `c_role_resource` VALUES ('13', '1', '13');
INSERT INTO `c_role_resource` VALUES ('14', '1', '14');
INSERT INTO `c_role_resource` VALUES ('15', '1', '15');
INSERT INTO `c_role_resource` VALUES ('16', '1', '16');
INSERT INTO `c_role_resource` VALUES ('17', '1', '17');
INSERT INTO `c_role_resource` VALUES ('18', '1', '18');
INSERT INTO `c_role_resource` VALUES ('19', '1', '19');
INSERT INTO `c_role_resource` VALUES ('20', '1', '20');
INSERT INTO `c_role_resource` VALUES ('21', '1', '21');
INSERT INTO `c_role_resource` VALUES ('22', '1', '22');
INSERT INTO `c_role_resource` VALUES ('23', '1', '23');
INSERT INTO `c_role_resource` VALUES ('24', '1', '24');