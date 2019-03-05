/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-03-05 08:34:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `title` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `href` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '系统管理', null, null, '0', 'icon-computer', '0');
INSERT INTO `menu` VALUES ('2', '1', '管理员管理', '/index/user', null, '1', 'icon-text', '1');
INSERT INTO `menu` VALUES ('3', '1', '角色管理', '/index/role', null, '1', 'icon-text', '2');
INSERT INTO `menu` VALUES ('4', '1', '菜单管理', '/index/menu', null, '1', 'icon-text', '3');
INSERT INTO `menu` VALUES ('7', '6', '查看', null, 'schedule:list,schedule:info', '2', null, '0');
INSERT INTO `menu` VALUES ('8', '6', '新增', null, 'schedule:save', '2', null, '0');
INSERT INTO `menu` VALUES ('9', '6', '修改', null, 'schedule:update', '2', null, '0');
INSERT INTO `menu` VALUES ('10', '6', '删除', null, 'schedule:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('11', '6', '暂停', null, 'schedule:pause', '2', null, '0');
INSERT INTO `menu` VALUES ('12', '6', '恢复', null, 'schedule:resume', '2', null, '0');
INSERT INTO `menu` VALUES ('13', '6', '立即执行', null, 'schedule:run', '2', null, '0');
INSERT INTO `menu` VALUES ('14', '6', '日志列表', null, 'schedule:log', '2', null, '0');
INSERT INTO `menu` VALUES ('15', '2', '查看', null, 'user:list,user:info', '2', null, '0');
INSERT INTO `menu` VALUES ('16', '2', '新增', null, 'user:save,role:select', '2', null, '0');
INSERT INTO `menu` VALUES ('17', '2', '修改', null, 'user:update,role:select,user:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('18', '2', '删除', null, 'user:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('19', '3', '查看', null, 'role:list,role:info', '2', null, '0');
INSERT INTO `menu` VALUES ('20', '3', '新增', null, 'role:save,menu:perms', '2', null, '0');
INSERT INTO `menu` VALUES ('21', '3', '修改', null, 'role:update,menu:perms', '2', null, '0');
INSERT INTO `menu` VALUES ('22', '3', '删除', null, 'role:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('23', '4', '查看', null, 'menu:list,menu:info', '2', null, '0');
INSERT INTO `menu` VALUES ('24', '4', '新增', null, 'menu:save,menu:select', '2', null, '0');
INSERT INTO `menu` VALUES ('25', '4', '修改', null, 'menu:update,menu:select', '2', null, '0');
INSERT INTO `menu` VALUES ('26', '4', '删除', null, 'menu:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'log:list', '1', 'icon-text', '7');
INSERT INTO `menu` VALUES ('30', '1', '文件上传', 'modules/oss/oss.html', 'oss:all', '1', 'icon-text', '6');
INSERT INTO `menu` VALUES ('32', '31', '查看', null, 'dept:list,dept:info', '2', null, '0');
INSERT INTO `menu` VALUES ('33', '31', '新增', null, 'dept:save,dept:select', '2', null, '0');
INSERT INTO `menu` VALUES ('34', '31', '修改', null, 'dept:update,dept:select', '2', null, '0');
INSERT INTO `menu` VALUES ('35', '31', '删除', null, 'dept:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('37', '36', '查看', null, 'dict:list,dict:info', '2', null, '6');
INSERT INTO `menu` VALUES ('38', '36', '新增', null, 'dict:save', '2', null, '6');
INSERT INTO `menu` VALUES ('39', '36', '修改', null, 'sys:dict:update', '2', null, '6');
INSERT INTO `menu` VALUES ('40', '36', '删除', null, 'sys:dict:delete', '2', null, '6');

-- ----------------------------
-- Table structure for `oss`
-- ----------------------------
DROP TABLE IF EXISTS `oss`;
CREATE TABLE `oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of oss
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'role1', '', '1', '2019-02-14 13:33:38');
INSERT INTO `role` VALUES ('2', 'role2', null, '1', '2019-02-27 14:13:56');

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1');
INSERT INTO `role_menu` VALUES ('2', '1', '2');
INSERT INTO `role_menu` VALUES ('3', '1', '15');
INSERT INTO `role_menu` VALUES ('4', '1', '16');
INSERT INTO `role_menu` VALUES ('5', '1', '17');
INSERT INTO `role_menu` VALUES ('6', '1', '18');
INSERT INTO `role_menu` VALUES ('7', '1', '3');
INSERT INTO `role_menu` VALUES ('8', '1', '19');
INSERT INTO `role_menu` VALUES ('9', '1', '20');
INSERT INTO `role_menu` VALUES ('10', '1', '21');
INSERT INTO `role_menu` VALUES ('11', '1', '22');
INSERT INTO `role_menu` VALUES ('12', '3', '1');
INSERT INTO `role_menu` VALUES ('13', '3', '2');
INSERT INTO `role_menu` VALUES ('23', '4', '38');
INSERT INTO `role_menu` VALUES ('24', '4', '39');
INSERT INTO `role_menu` VALUES ('25', '4', '40');
INSERT INTO `role_menu` VALUES ('26', '4', '33');
INSERT INTO `role_menu` VALUES ('27', '4', '35');
INSERT INTO `role_menu` VALUES ('28', '5', '3');
INSERT INTO `role_menu` VALUES ('29', '5', '4');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sex` bit(1) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'ad', 'b730466880360ca678ea910d9dedf41a', 'f6a3f0b398db2210c8c9a504c8a80ad7', '840267572@qq.com', '15202862531', '1', '2019-02-14 13:53:06', '');
INSERT INTO `user` VALUES ('4', 'jinxu', 'b2619e99a5a836e124b607a6d0b77f04', 'a21a219894cb243b45740f3c22f0325e', '840267572@qq.com', '15202862531', '0', '2019-02-26 11:56:37', '');
INSERT INTO `user` VALUES ('6', 'jsonXxxx', '62546bf55a28614f99932e4b684b7300', '5bffbdf840906f4072e3cf103ef3bc3c', '191936793@qq.com', null, '1', null, '');
INSERT INTO `user` VALUES ('16', 'Sccc', 'af17bc4dbadf4896e6d1e195ec36ffb8', '3cc07172e506b9d6f49d79a9600d73bf', '191936793@qq.com', '13618050721', '1', '2019-02-27 05:15:22', '');
INSERT INTO `user` VALUES ('17', 'XXX', 'b80549f10af101a2941b0f955cbf7186', '629ca2a80e7f02a3b63f03f4d224359f', '321321@qq.com', '13618050721', '1', '2019-02-27 05:47:05', '');
INSERT INTO `user` VALUES ('18', 'aaa', 'f4fed5b9463bf90ea0ea10c9844fb3a1', '0976f79d30a7314aad05acf45762d657', '191936793@qq.com', '13618050721', '1', '2019-02-27 05:50:35', '');
INSERT INTO `user` VALUES ('19', '321', '8392b724f1869e84d7d2d4ffc284f848', '8fc081336ae9e7da7cfdfb2ed2686d5b', '191936793@qq.com', '13618050721', '1', '2019-02-27 05:55:24', '');
INSERT INTO `user` VALUES ('20', 'Sjno', '481d21828d59f8aec24d50a658c730e8', '0534746a67f8c354f0400385e3bcc53e', '191936793@qq.com', '13618050721', '1', '2019-02-27 05:57:24', '');
INSERT INTO `user` VALUES ('21', '23121', 'b6010bbf977f9f61c12fb8727e9c599e', '4340f0c2b8b51373200d526a40c480aa', '1919@qq.com', '13618050721', '1', '2019-02-27 06:02:57', '');
INSERT INTO `user` VALUES ('22', 'llll', 'c9a8c19e379f123e0a0f996b6eb6110e', 'ba8eb8a616a8f1c1b9fe6dbdafa72f9f', '1919@qq.com', '13618050721', '1', '2019-02-27 06:10:58', '');
INSERT INTO `user` VALUES ('24', 'asdddd', '9260bc18d424cf39fd23bb3ea0eaa32f', 'cc15de36a8eca0b88d082985630aa2d7', '1919@qq.com', '13618050721', '1', '2019-02-27 07:23:23', '');
INSERT INTO `user` VALUES ('25', 'aaa111', '6b5b92ad78bb235c67a18de6faa40fba', '4971982d9e17d727f4e2d2fdf4496dd7', '1919@qq.com', '13618050721', '1', '2019-02-27 07:41:31', '');
INSERT INTO `user` VALUES ('30', '1111', 'b8776d81e3c65efefe3cf870ebf00e9d', '1b26b757f60bf439a36545e8b10aa103', '191936@qq.com', '13618050721', '1', '2019-02-27 08:17:59', '');
INSERT INTO `user` VALUES ('34', 'Z22', 'cde8e4de65c2509ba9bc3ed4672fd7ce', '83d4e97c8333dab9593d82d59f146d95', '191936793@qq.com', '13618050721', '1', '2019-02-27 08:33:40', '');
INSERT INTO `user` VALUES ('35', '小2', '667b276e47813a7809e0ed658b3d2372', 'cd1ccbd07c9bab3ba4fdeb75bb493659', '1919@qq.com', '13618050721', '1', '2019-02-27 09:09:48', '');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '2', '1');
INSERT INTO `user_role` VALUES ('8', '34', '1');
INSERT INTO `user_role` VALUES ('9', '34', '2');
INSERT INTO `user_role` VALUES ('10', '35', '1');
INSERT INTO `user_role` VALUES ('11', '35', '2');
