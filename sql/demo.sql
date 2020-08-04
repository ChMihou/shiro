/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2020-08-04 14:07:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(255) NOT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '删除', 'admin:admin');
INSERT INTO `permission` VALUES ('2', '删除全部', 'admin:admin');
INSERT INTO `permission` VALUES ('3', '查找', 'admin:normal');
INSERT INTO `permission` VALUES ('4', '查找全部', 'admin:normal');
INSERT INTO `permission` VALUES ('5', '新增', 'admin:employee');
INSERT INTO `permission` VALUES ('6', '修改', 'admin:employee');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'staff');
INSERT INTO `role` VALUES ('3', 'normal');

-- ----------------------------
-- Table structure for rolepermission
-- ----------------------------
DROP TABLE IF EXISTS `rolepermission`;
CREATE TABLE `rolepermission` (
  `roleid` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rolepermission
-- ----------------------------
INSERT INTO `rolepermission` VALUES ('1', '1', '1');
INSERT INTO `rolepermission` VALUES ('1', '2', '2');
INSERT INTO `rolepermission` VALUES ('1', '3', '3');
INSERT INTO `rolepermission` VALUES ('1', '4', '4');
INSERT INTO `rolepermission` VALUES ('1', '5', '5');
INSERT INTO `rolepermission` VALUES ('1', '6', '6');
INSERT INTO `rolepermission` VALUES ('2', '7', '3');
INSERT INTO `rolepermission` VALUES ('2', '8', '4');
INSERT INTO `rolepermission` VALUES ('3', '9', '5');
INSERT INTO `rolepermission` VALUES ('3', '10', '6');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('41', '小绿1', 'bf07909ea2ddf25c0763337b72b0905a', '123@qq.com', '123456', '1', '小绿1');
INSERT INTO `users` VALUES ('42', '小绿2', '18317463f565ffa17c65ec78c4e82818', '123@qq.com', '123456', '2', '小绿2');
INSERT INTO `users` VALUES ('43', '小绿3', 'bf14434114a176898a86138e1b246d43', '123@qq.com', '123456', '3', '小绿3');
