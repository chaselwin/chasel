/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : family

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-11-26 00:24:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_type` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_title` varchar(255) DEFAULT NULL,
  `image_memo` varchar(255) DEFAULT NULL,
  `image_createTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `image_creator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `wechat` varchar(50) DEFAULT NULL,
  `photo` varchar(50) DEFAULT NULL,
  `synopsis` varchar(255) DEFAULT NULL,
  `permissions` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
