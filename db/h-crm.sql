/*
 Navicat Premium Data Transfer

 Source Server         : mariadb-10.2.12-win32
 Source Server Type    : MySQL
 Source Server Version : 100212
 Source Host           : 127.0.0.1:3306
 Source Schema         : h-crm

 Target Server Type    : MySQL
 Target Server Version : 100212
 File Encoding         : 65001

 Date: 02/03/2018 02:23:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for h_content
-- ----------------------------
DROP TABLE IF EXISTS `h_content`;
CREATE TABLE `h_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `sub_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子标题',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `static_link` varchar(1200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '静态连接',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `cover_img` varchar(1200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  `small_img` varchar(1200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图片',
  `cover_width` float NULL DEFAULT NULL COMMENT '封面图片宽度',
  `cover_height` float NULL DEFAULT NULL COMMENT '封面图片高度',
  `hits` int(11) NULL DEFAULT NULL COMMENT '点击数',
  `loves` int(11) NULL DEFAULT NULL COMMENT '喜欢数',
  `collections` int(11) NULL DEFAULT NULL COMMENT '收藏数',
  `comments` int(11) NULL DEFAULT NULL COMMENT '评论数',
  `keywords` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seo关键词',
  `description` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seo描述',
  `html_code` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `js_code` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `css_code` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `is_code` tinyint(1) NULL DEFAULT NULL COMMENT '是否预览 0 不可 1可',
  `header_pic` varchar(1200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者名称',
  `user_id` bigint(1) NULL DEFAULT NULL COMMENT '用户id',
  `channel_id` bigint(1) NULL DEFAULT NULL COMMENT '类型1Java，2前端，3音乐 4咖啡 5茶文化 6工具',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除 0删除1未删除',
  `is_top` tinyint(1) NULL DEFAULT NULL COMMENT '是否置顶1置顶 0未置顶',
  `is_push` tinyint(1) NULL DEFAULT NULL COMMENT '是否精华推荐 0否1是',
  `is_comment` tinyint(1) NULL DEFAULT NULL COMMENT '是否允许评论 1允许0不允许',
  `issuance` tinyint(1) NULL DEFAULT NULL COMMENT '0未发布1发布',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '1文章2音乐3视频4其他',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1182 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for h_permission
-- ----------------------------
DROP TABLE IF EXISTS `h_permission`;
CREATE TABLE `h_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `description` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限详情',
  `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `purl` varchar(1200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限地址',
  `module` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限模块',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限方法',
  `parent_id` bigint(1) NULL DEFAULT NULL,
  `user_id` bigint(1) NULL DEFAULT NULL,
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除 0 -未删除 1-删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of h_permission
-- ----------------------------
INSERT INTO `h_permission` VALUES (1, '内容管理', '内容管理', '2018-01-27 21:22:54', NULL, '/sys/content/list', 'content', NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (2, '搜索内容', '内容管理', '2018-01-27 21:23:15', NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `h_permission` VALUES (4, '修改内容', '内容管理', '2018-01-27 21:23:27', NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `h_permission` VALUES (5, '是否删除', '内容管理', '2018-01-27 21:23:32', NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `h_permission` VALUES (6, '是否发布', '内容管理', '2018-01-27 21:23:38', NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `h_permission` VALUES (7, '是否置顶', '内容管理', '2018-01-27 21:23:48', NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `h_permission` VALUES (8, '是否评论', '内容管理', '2018-01-27 21:24:00', NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `h_permission` VALUES (9, '是否精华', '内容管理', '2018-01-27 21:24:10', NULL, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `h_permission` VALUES (10, '用户管理', '用户管理', '2018-01-27 21:29:43', NULL, '/sys/user/list', 'user', NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (11, '日志管理', '日志管理', '2018-01-27 21:29:53', NULL, '/sys/stat/list', 'stat', NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (13, '爬虫管理', '爬虫管理', '2018-01-27 21:30:21', NULL, '/sys/gather/list', 'gather', NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (14, '角色管理', '角色管理', '2018-02-27 19:22:00', NULL, '/sys/role/list', 'role', NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (15, '权限管理', '权限管理', '2018-02-27 19:23:13', NULL, '/sys/permission/list', 'permission', NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (16, '日志管理', '日志管理', '2018-02-27 19:24:07', NULL, '/sys/stat/list', 'stat', NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (17, '统计报表', '统计报表', '2018-02-27 19:25:24', NULL, NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (18, '采集管理', '采集管理', '2018-02-27 19:25:50', NULL, NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `h_permission` VALUES (20, '添加用户', '用户管理--添加用户', '2018-02-27 19:29:05', NULL, NULL, NULL, NULL, 10, 1, 0);
INSERT INTO `h_permission` VALUES (21, '编辑用户', '用户管理--编辑用户', '2018-02-27 19:32:18', NULL, '', NULL, NULL, 10, 1, 0);
INSERT INTO `h_permission` VALUES (22, '删除用户', '用户管理--删除用户', '2018-02-27 19:32:49', NULL, NULL, NULL, NULL, 10, 1, 0);
INSERT INTO `h_permission` VALUES (23, '是否删除', '用户管理--是否删除', '2018-02-27 19:35:18', NULL, NULL, NULL, NULL, 10, 1, 0);
INSERT INTO `h_permission` VALUES (24, '是否禁用', '用户管理--是否禁用', '2018-02-27 19:35:49', NULL, NULL, NULL, NULL, 10, 1, 0);

-- ----------------------------
-- Table structure for h_role
-- ----------------------------
DROP TABLE IF EXISTS `h_role`;
CREATE TABLE `h_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp(),
  `update_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` bigint(1) NULL DEFAULT NULL,
  `is_delete` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of h_role
-- ----------------------------
INSERT INTO `h_role` VALUES (1, '超级管理员', '2018-01-28 15:24:27', NULL, '超级管理员', 1, 0);
INSERT INTO `h_role` VALUES (2, '管理员', '2018-01-28 15:24:50', NULL, '管理员', 2, 0);
INSERT INTO `h_role` VALUES (3, '编辑人员', '2018-01-28 15:25:09', NULL, '编辑人员', 2, 0);

-- ----------------------------
-- Table structure for h_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `h_role_permission`;
CREATE TABLE `h_role_permission`  (
  `role_id` bigint(1) NULL DEFAULT NULL,
  `permission_id` bigint(1) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of h_role_permission
-- ----------------------------
INSERT INTO `h_role_permission` VALUES (1, 1);
INSERT INTO `h_role_permission` VALUES (1, 2);
INSERT INTO `h_role_permission` VALUES (1, 3);
INSERT INTO `h_role_permission` VALUES (1, 4);
INSERT INTO `h_role_permission` VALUES (1, 5);
INSERT INTO `h_role_permission` VALUES (1, 6);
INSERT INTO `h_role_permission` VALUES (1, 7);
INSERT INTO `h_role_permission` VALUES (1, 8);
INSERT INTO `h_role_permission` VALUES (1, 9);
INSERT INTO `h_role_permission` VALUES (1, 10);
INSERT INTO `h_role_permission` VALUES (1, 11);
INSERT INTO `h_role_permission` VALUES (1, 13);

-- ----------------------------
-- Table structure for h_stat
-- ----------------------------
DROP TABLE IF EXISTS `h_stat`;
CREATE TABLE `h_stat`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '耗时(单位毫秒)',
  `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `host_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机地址',
  `user_id` bigint(1) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(42) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录用户名',
  `module` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块',
  `description` varchar(600) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5010 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for h_user
-- ----------------------------
DROP TABLE IF EXISTS `h_user`;
CREATE TABLE `h_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(42) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '0 女 1 男 -1保密',
  `age` tinyint(3) NULL DEFAULT NULL,
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `user_pic` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像图片',
  `email` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `weixin` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信',
  `create_time` timestamp(0) NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `login_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录ip',
  `is_delete` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除 0 未删除 1删除',
  `is_email` tinyint(1) NULL DEFAULT NULL COMMENT '是否绑定邮箱 0 未绑定 1 绑定',
  `is_phone` tinyint(1) NULL DEFAULT NULL COMMENT '是否绑定手机 0 未绑定 1 绑定',
  `is_weixin` tinyint(1) NULL DEFAULT NULL COMMENT '是否绑定微信 0 未绑定 1绑定',
  `is_disable` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用 0 未禁用 1禁用',
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of h_user
-- ----------------------------
INSERT INTO `h_user` VALUES (1, 'haima', '海马', '!HFLv9sUpIVC2BkYJTIdX33t3kiHy05CBn8bsciBrZDXvRYN67LkrjWec4117lTeODUCrKbvU3\r\nzP4jt/qyMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'haimaclan@gmail.com', '18788886666', 'haima', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (2, 'lairen', '濑人', '!HFLv9sUpIVC2BkYJTIdX33t3kiHy05CBn8bsciBrZDXvRYN67LkrjWec4117lTeODUCrKbvU3\r\nzP4jt/qyMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'haimaclan@gmail.com', '18788886666', 'haima', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `h_user` VALUES (3, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (4, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (5, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (6, 'test2', 'test2', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `h_user` VALUES (7, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (8, 'test2', 'test2', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `h_user` VALUES (9, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (10, 'test2', 'test2', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `h_user` VALUES (11, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (12, 'test2', 'test2', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `h_user` VALUES (13, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (14, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (15, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (16, 'test2', 'test2', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `h_user` VALUES (17, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (18, 'test2', 'test2', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `h_user` VALUES (19, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (20, 'test2', 'test2', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `h_user` VALUES (21, 'test1', 'test1', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 0, NULL);
INSERT INTO `h_user` VALUES (22, 'test2', 'test2', '!Jr+wlNU1VHa4hWQuybjjVPyFzuNPcPu5MBH56scHri4UQPjvnumE7MbtcnDYhTcnxSkL9ei/bhIVrylxEMx4xfIWvxDD+5rmvOiMpFg==', 0, 22, '1997-03-21 00:00:00', '', 'test@test.com', '18788886666', 'test', '2018-02-11 22:14:12', NULL, '2018-02-16 17:10:00', '127.0.0.1', 0, NULL, NULL, NULL, 1, NULL);

-- ----------------------------
-- Table structure for h_user_role
-- ----------------------------
DROP TABLE IF EXISTS `h_user_role`;
CREATE TABLE `h_user_role`  (
  `user_id` bigint(1) NULL DEFAULT NULL,
  `role_id` bigint(1) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of h_user_role
-- ----------------------------
INSERT INTO `h_user_role` VALUES (1, 3);
INSERT INTO `h_user_role` VALUES (1, 1);
INSERT INTO `h_user_role` VALUES (1, 2);
INSERT INTO `h_user_role` VALUES (2, 2);
INSERT INTO `h_user_role` VALUES (2, 3);

SET FOREIGN_KEY_CHECKS = 1;
