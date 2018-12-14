/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : solar

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2018-12-14 09:36:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `buy`
-- ----------------------------
DROP TABLE IF EXISTS `buy`;
CREATE TABLE `buy` (
  `user_id` int(100) NOT NULL,
  `images_id` int(100) NOT NULL,
  `image_url` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buy
-- ----------------------------
INSERT INTO `buy` VALUES ('1', '1', 'http://10.7.89.187:8080/Solar/images/bg3.png');
INSERT INTO `buy` VALUES ('1', '2', 'http://10.7.89.187:8080/Solar/images/bg2.png');
INSERT INTO `buy` VALUES ('1', '3', 'http://10.7.89.187:8080/Solar/images/bg10.png');

-- ----------------------------
-- Table structure for `count`
-- ----------------------------
DROP TABLE IF EXISTS `count`;
CREATE TABLE `count` (
  `count_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `tomato_id` int(200) DEFAULT NULL,
  `task_id` int(200) DEFAULT NULL,
  `user_id` int(200) DEFAULT NULL,
  PRIMARY KEY (`count_id`),
  KEY `tomato_id` (`tomato_id`),
  KEY `task_id` (`task_id`),
  KEY `user_id2` (`user_id`),
  CONSTRAINT `tomato_id` FOREIGN KEY (`tomato_id`) REFERENCES `tomato` (`tomato_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of count
-- ----------------------------

-- ----------------------------
-- Table structure for `images`
-- ----------------------------
DROP TABLE IF EXISTS `images`;
CREATE TABLE `images` (
  `images_id` int(200) NOT NULL AUTO_INCREMENT,
  `images_src` char(200) DEFAULT NULL,
  `images_score` int(200) DEFAULT NULL,
  PRIMARY KEY (`images_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of images
-- ----------------------------
INSERT INTO `images` VALUES ('1', 'http://10.7.89.187:8080/Solar/images/bg3.png', '299');
INSERT INTO `images` VALUES ('2', 'http://10.7.89.187:8080/Solar/images/bg2.png', '199');
INSERT INTO `images` VALUES ('3', 'http://10.7.89.187:8080/Solar/images/bg13.png', '599');
INSERT INTO `images` VALUES ('4', 'http://10.7.89.187:8080/Solar/images/bg7.png', '699');
INSERT INTO `images` VALUES ('5', 'http://10.7.89.187:8080/Solar/images/bg20.png', '199');

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `manager_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `manager_name` char(200) DEFAULT NULL,
  `manager_role` varchar(40) DEFAULT NULL,
  `manager_password` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------

-- ----------------------------
-- Table structure for `score_detail`
-- ----------------------------
DROP TABLE IF EXISTS `score_detail`;
CREATE TABLE `score_detail` (
  `score_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `tomato_id` int(200) DEFAULT NULL,
  `task_id` int(200) DEFAULT NULL,
  `detail_score` int(200) DEFAULT NULL,
  PRIMARY KEY (`score_id`),
  KEY `tomato_id1` (`tomato_id`),
  KEY `task_id1` (`task_id`),
  CONSTRAINT `tomato_id1` FOREIGN KEY (`tomato_id`) REFERENCES `tomato` (`tomato_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `sing`
-- ----------------------------
DROP TABLE IF EXISTS `sing`;
CREATE TABLE `sing` (
  `sing_id` int(200) NOT NULL AUTO_INCREMENT,
  `sing_url` varchar(200) DEFAULT NULL,
  `sing_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`sing_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sing
-- ----------------------------
INSERT INTO `sing` VALUES ('1', 'http://www.9ku.com/play/11417.htm', '大海');
INSERT INTO `sing` VALUES ('2', 'http://www.9ku.com/play/882153.htm', '沙漠骆驼');
INSERT INTO `sing` VALUES ('3', 'http://www.9ku.com/play/863755.htm', '我们不一样');
INSERT INTO `sing` VALUES ('4', 'http://www.haolingsheng.com/lingsheng/h9x2gt.htm', '是我太傻了');
INSERT INTO `sing` VALUES ('5', 'http://www.haolingsheng.com/lingsheng/phltnf.htm', '体面');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_name` char(200) DEFAULT NULL,
  `task_id` int(200) NOT NULL AUTO_INCREMENT,
  `task_state` varchar(40) DEFAULT NULL,
  `task_time` int(200) DEFAULT NULL,
  `user_id` int(200) DEFAULT NULL,
  `task_year` char(100) DEFAULT NULL,
  `task_month` char(100) DEFAULT NULL,
  `task_day` char(100) DEFAULT NULL,
  `task_show_tag` char(100) DEFAULT NULL,
  `task_score` int(200) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `user_id1` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('背诵雅思单词50个', '1', '完成', '30', '1', '2018', '12', '06', '2', '10');
INSERT INTO `task` VALUES ('两组Keep训练', '2', '完成', '35', '1', '2018', '12', '06', '2', '10');
INSERT INTO `task` VALUES ('完成两组Keep训练', '3', '完成', '35', '1', '2018', '12', '06', '2', '10');
INSERT INTO `task` VALUES ('六级仔细阅读一篇', '4', '未完成', '15', '1', '2018', '12', '06', '2', null);
INSERT INTO `task` VALUES ('看书', '5', '未完成', '40', '1', '2018', '12', '06', '2', null);
INSERT INTO `task` VALUES ('慢跑', '6', '完成', '30', '1', '2018', '12', '06', '2', '10');
INSERT INTO `task` VALUES ('雅思阅读', '7', '完成', '60', '1', '2018', '12', '06', '1', '10');
INSERT INTO `task` VALUES ('扎马步', '9', '未完成', '10', '1', '2018', '12', '06', '2', null);
INSERT INTO `task` VALUES ('雅思阅读', '10', '未完成', '80', '1', '2018', '12', '06', '2', null);
INSERT INTO `task` VALUES ('不知道', '11', '完成', '20', '1', '2018', '12', '06', '1', '10');
INSERT INTO `task` VALUES ('托福', '12', '完成', '200', '1', '2018', '12', '12', '2', '10');
INSERT INTO `task` VALUES ('六级翻译', '13', '完成', '20', '1', '2018', '12', '06', '2', '10');
INSERT INTO `task` VALUES ('yasiyuedy', '14', '完成', '1', '1', '2018', '12', '07', '1', '10');
INSERT INTO `task` VALUES ('高考模拟英语完型一篇', '15', '完成', '20', '1', '2018', '12', '07', '2', null);
INSERT INTO `task` VALUES ('两篇六级仔细阅读', '16', '完成', '25', '1', '2018', '12', '07', '2', '5');
INSERT INTO `task` VALUES ('read', '17', '完成', '10', '1', '2018', '12', '10', '2', '2');
INSERT INTO `task` VALUES ('六', '18', '未完成', '20', '1', '2018', '12', '11', '2', '0');
INSERT INTO `task` VALUES ('六级', '19', '未完成', '123', '1', '2018', '12', '11', '2', '0');
INSERT INTO `task` VALUES ('四级', '20', '完成', '1', '1', '2018', '12', '12', '2', '0');
INSERT INTO `task` VALUES ('三', '21', '未完成', '5', '1', '2018', '12', '11', '2', '0');
INSERT INTO `task` VALUES ('二', '22', '未完成', '5', '1', '2018', '12', '11', '2', '0');
INSERT INTO `task` VALUES ('liu', '23', '未完成', '2', '1', '2018', '12', '11', '2', '0');
INSERT INTO `task` VALUES ('san', '24', '未完成', '10', '1', '2018', '12', '12', '2', '0');
INSERT INTO `task` VALUES ('六级仔细阅读两篇', '25', '完成', '1', '1', '2018', '12', '12', '1', '0');
INSERT INTO `task` VALUES ('学', '26', '未完成', '20', '1', '2018', '12', '14', '1', '0');

-- ----------------------------
-- Table structure for `tomato`
-- ----------------------------
DROP TABLE IF EXISTS `tomato`;
CREATE TABLE `tomato` (
  `tomato_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `user_id` int(200) DEFAULT NULL,
  `tomato_num` int(200) DEFAULT NULL,
  `tomato_year` char(200) DEFAULT NULL,
  `tomato_month` char(200) DEFAULT NULL,
  `tomato_day` char(200) DEFAULT NULL,
  PRIMARY KEY (`tomato_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tomato
-- ----------------------------
INSERT INTO `tomato` VALUES ('1', '1', '2', '2018', '12', '01');
INSERT INTO `tomato` VALUES ('2', '1', '3', '2018', '12', '02');
INSERT INTO `tomato` VALUES ('3', '1', '5', '2018', '12', '03');
INSERT INTO `tomato` VALUES ('4', '1', '3', '2018', '12', '04');
INSERT INTO `tomato` VALUES ('5', '1', '4', '2018', '12', '05');
INSERT INTO `tomato` VALUES ('6', '1', '4', '2018', '12', '06');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(200) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增长',
  `user_name` char(200) DEFAULT NULL,
  `user_email` varchar(40) DEFAULT NULL,
  `user_phone` varchar(40) DEFAULT NULL,
  `user_password` varchar(40) DEFAULT NULL,
  `user_score` int(200) DEFAULT NULL,
  `user_image` char(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'jiaxinrui', '1019785953@qq.com', '15226529795', '101978', '7', 'http://10.7.89.187:8080/Solar/images/default1.png');
INSERT INTO `user` VALUES ('3', 'zhangsan', '1019785953@qq.com', '15226529795', '101978', null, null);
INSERT INTO `user` VALUES ('5', 'wangwu', '1019785953@qq.com', '15226529795', '101978', null, null);
INSERT INTO `user` VALUES ('6', 'wangwu', '1019785953@qq.com', '15226529795', '101978', null, null);
INSERT INTO `user` VALUES ('10', 'huhu', '11111', '15226529795', '1133444', null, null);
INSERT INTO `user` VALUES ('11', 'hahhahaha', '28349875843', '82734674843', '12384375', null, null);
INSERT INTO `user` VALUES ('12', '123345', '222222', '222222', '22233333', null, null);
INSERT INTO `user` VALUES ('13', 'jia', '43785485', '74658932', '4589567659', null, null);
INSERT INTO `user` VALUES ('14', 'xixi', '112333', '1234565', '33333333', null, null);
INSERT INTO `user` VALUES ('15', 'jiajia', '22222222', '1234567890', '2222222222', null, null);
INSERT INTO `user` VALUES ('16', 'ji', '22222', '3456768977', '222222', null, null);
INSERT INTO `user` VALUES ('17', 'jiiiii', '22222222', '15226529795', '22222222', null, null);
INSERT INTO `user` VALUES ('18', 'lichen', '111111111', '15237844789', '221111111', null, null);
INSERT INTO `user` VALUES ('19', 'CHEN', '111111111', '13503258950', '1111111111111', null, null);
INSERT INTO `user` VALUES ('20', 'tian', '1019785953@qq.com', '15226529795', '11111111', null, null);
INSERT INTO `user` VALUES ('21', 'chenmengxuan', '2277394640@qq.com', '13503258950', '123456', null, null);
INSERT INTO `user` VALUES ('22', 'chenchen', '1019785953@qq.com', '13503258950', '1234567890', null, null);
INSERT INTO `user` VALUES ('23', 'fanyilin', '12345@qq.com', '15511111111', '123456', null, null);
