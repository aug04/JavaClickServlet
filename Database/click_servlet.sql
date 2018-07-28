/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : 127.0.0.1:3306
Source Database       : click_servlet

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-07-28 12:43:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ads`
-- ----------------------------
DROP TABLE IF EXISTS `ads`;
CREATE TABLE `ads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `url` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ads
-- ----------------------------
INSERT INTO `ads` VALUES ('1', 'Google', '1', 'https://www.google.com');
INSERT INTO `ads` VALUES ('2', 'Youtube', '1', 'https://www.youtube.com');
INSERT INTO `ads` VALUES ('3', 'Facebook', '1', 'https://www.facebook.com');
INSERT INTO `ads` VALUES ('4', 'Mp3 Zing', '1', 'https://mp3.zing.vn');

-- ----------------------------
-- Table structure for `deliver_log`
-- ----------------------------
DROP TABLE IF EXISTS `deliver_log`;
CREATE TABLE `deliver_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `ad_id` int(11) NOT NULL,
  `media_id` int(11) NOT NULL,
  `user_agent` text NOT NULL,
  `query_string` text NOT NULL,
  `error_type` tinyint(4) NOT NULL DEFAULT '1',
  `error_msg` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of deliver_log
-- ----------------------------

-- ----------------------------
-- Table structure for `media`
-- ----------------------------
DROP TABLE IF EXISTS `media`;
CREATE TABLE `media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of media
-- ----------------------------
INSERT INTO `media` VALUES ('1', 'Tivi', '1');
INSERT INTO `media` VALUES ('2', 'Speaker', '1');
INSERT INTO `media` VALUES ('3', 'Internet', '1');
INSERT INTO `media` VALUES ('4', 'Advert', '1');
