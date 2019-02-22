/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-02-22 13:59:40
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
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `menu` VALUES ('2', '1', '管理员管理', 'modules/sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `menu` VALUES ('3', '1', '角色管理', 'modules/sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `menu` VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', null, '1', 'fa fa-th-list', '3');
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
INSERT INTO `menu` VALUES ('17', '2', '修改', null, 'user:update,role:select', '2', null, '0');
INSERT INTO `menu` VALUES ('18', '2', '删除', null, 'user:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('19', '3', '查看', null, 'role:list,role:info', '2', null, '0');
INSERT INTO `menu` VALUES ('20', '3', '新增', null, 'role:save,menu:perms', '2', null, '0');
INSERT INTO `menu` VALUES ('21', '3', '修改', null, 'role:update,menu:perms', '2', null, '0');
INSERT INTO `menu` VALUES ('22', '3', '删除', null, 'role:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('23', '4', '查看', null, 'menu:list,menu:info', '2', null, '0');
INSERT INTO `menu` VALUES ('24', '4', '新增', null, 'menu:save,menu:select', '2', null, '0');
INSERT INTO `menu` VALUES ('25', '4', '修改', null, 'menu:update,menu:select', '2', null, '0');
INSERT INTO `menu` VALUES ('26', '4', '删除', null, 'menu:delete', '2', null, '0');
INSERT INTO `menu` VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'log:list', '1', 'fa fa-file-text-o', '7');
INSERT INTO `menu` VALUES ('30', '1', '文件上传', 'modules/oss/oss.html', 'oss:all', '1', 'fa fa-file-image-o', '6');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'role1', '', '1', '2019-02-14 13:33:38');

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

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

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'b730466880360ca678ea910d9dedf41a', 'f6a3f0b398db2210c8c9a504c8a80ad7', 'root@renren.io', '13612345678', '1', '2016-11-11 11:11:11');
INSERT INTO `user` VALUES ('2', 'ad', 'b730466880360ca678ea910d9dedf41a', 'f6a3f0b398db2210c8c9a504c8a80ad7', '840267572@qq.com', '15202862531', '1', '2019-02-14 13:53:06');
INSERT INTO `user` VALUES ('3', 'json', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '2', '1');
