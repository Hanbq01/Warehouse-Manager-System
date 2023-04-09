/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 16/03/2023 21:04:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `GoodsId` int NOT NULL AUTO_INCREMENT,
  `GoodsName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `GoodsPlace` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `GoodsType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Price` double(255, 2) NOT NULL,
  `GoodsNum` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`GoodsId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20230012 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (20230002, '小米13', '数码产品', '北京仓', 3999.00, '14');
INSERT INTO `goods` VALUES (20230003, '米家剃须刀', '济南仓', '日用品', 99.00, '107');
INSERT INTO `goods` VALUES (20230004, '舒肤佳除螨皂', '济南仓', '洗护日化', 10.00, '207');
INSERT INTO `goods` VALUES (20230005, '乐事薯片（原味）', '德州仓', '食品', 5.00, '197');
INSERT INTO `goods` VALUES (20230006, '三体2：黑暗森林', '德州仓', '图书', 34.00, '77');
INSERT INTO `goods` VALUES (20230007, '小米平板5', '北京仓', '数码产品', 1999.00, '13');
INSERT INTO `goods` VALUES (20230008, '华为mate50', '北京仓', '数码产品', 6999.00, '21');
INSERT INTO `goods` VALUES (20230012, '华为mate40', '广州仓', '数码产品', 4999.00, '22');
INSERT INTO `goods` VALUES (20230013, '冻梨', '哈尔滨仓', '食品', 5.00, '60');

-- ----------------------------
-- Table structure for goodsout
-- ----------------------------
DROP TABLE IF EXISTS `goodsout`;
CREATE TABLE `goodsout`  (
  `OutId` int NOT NULL AUTO_INCREMENT,
  `GoodsId` int NOT NULL,
  `GoodsName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `GoodsType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userid` int NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `OutTime` date NOT NULL,
  `OutNum` int NOT NULL,
  PRIMARY KEY (`OutId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goodsout
-- ----------------------------
INSERT INTO `goodsout` VALUES (31, 20230007, '小米平板5', '数码产品', 1, 'user', '2023-03-14', 2);
INSERT INTO `goodsout` VALUES (32, 20230007, '小米平板5', '数码产品', 180901, 'user', '2023-03-16', 1);

-- ----------------------------
-- Table structure for root
-- ----------------------------
DROP TABLE IF EXISTS `root`;
CREATE TABLE `root`  (
  `rootID` int NOT NULL,
  `rootName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rootPWD` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`rootID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of root
-- ----------------------------
INSERT INTO `root` VALUES (1, 'admin', 'admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `userPWD` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `useremail` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `useraddr` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `userphone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`userID`) USING BTREE,
  INDEX `userid`(`userID` ASC, `userName` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 180903 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (180901, 'user', '0000', '123@163.com', '山东曲阜', '12345678901');
INSERT INTO `user` VALUES (180902, 'bbb', '456', '112233@163.com', '山东德州', '12345678123');
INSERT INTO `user` VALUES (180903, 'han', '1234', '123@123.com', '山东德州', '1234567890');

SET FOREIGN_KEY_CHECKS = 1;
