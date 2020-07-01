/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : qixin

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2020-06-30 02:57:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_image
-- ----------------------------
DROP TABLE IF EXISTS `tb_image`;
CREATE TABLE `tb_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(1023) DEFAULT NULL COMMENT '图片路径',
  `size` bigint(20) DEFAULT NULL COMMENT '图片大小',
  `media_type` varchar(20) DEFAULT NULL COMMENT '图片类型',
  `suffix` varchar(50) DEFAULT NULL COMMENT '图片后缀',
  `height` int(20) DEFAULT NULL COMMENT '图片高度',
  `width` int(20) DEFAULT NULL COMMENT '图片宽度',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_image
-- ----------------------------

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `username` varchar(50) DEFAULT NULL COMMENT '操作用户',
  `operation` text COMMENT '操作内容',
  `time` decimal(11,0) DEFAULT NULL COMMENT '耗时',
  `method` text COMMENT '操作方法',
  `params` text COMMENT '方法参数',
  `ip` varchar(64) DEFAULT NULL COMMENT '操作者IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `location` varchar(50) DEFAULT NULL COMMENT '操作地点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1854 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';

-- ----------------------------
-- Records of tb_log
-- ----------------------------

-- ----------------------------
-- Table structure for tb_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_log`;
CREATE TABLE `tb_login_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `location` varchar(50) DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `user_system` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `user_browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1561 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='登录日志表';

-- ----------------------------
-- Records of tb_login_log
-- ----------------------------
INSERT INTO `tb_login_log` VALUES ('1554', '系统测试人员', '2020-06-21 17:36:46', null, '127.0.0.1', 'Unknown', 'Unknown');
INSERT INTO `tb_login_log` VALUES ('1555', '系统测试人员', '2020-06-21 17:37:08', null, '127.0.0.1', 'Unknown', 'Unknown');
INSERT INTO `tb_login_log` VALUES ('1556', '系统测试人员', '2020-06-22 02:18:58', null, '127.0.0.1', 'Unknown', 'Unknown');
INSERT INTO `tb_login_log` VALUES ('1557', '系统测试人员', '2020-06-22 02:24:01', null, '127.0.0.1', 'Unknown', 'Unknown');
INSERT INTO `tb_login_log` VALUES ('1558', '系统测试人员', '2020-06-30 01:50:22', null, '127.0.0.1', 'Unknown', 'Unknown');
INSERT INTO `tb_login_log` VALUES ('1559', '系统测试人员', '2020-06-30 02:42:04', null, '127.0.0.1', 'Unknown', 'Unknown');
INSERT INTO `tb_login_log` VALUES ('1560', '系统测试人员', '2020-06-30 02:42:14', null, '127.0.0.1', 'Unknown', 'Unknown');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单URL',
  `perms` text COMMENT '权限标识',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` char(2) NOT NULL COMMENT '类型 0菜单 1按钮',
  `order_num` bigint(20) DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `available` int(11) DEFAULT '1' COMMENT '0：不可用，1：可用',
  `open` int(1) DEFAULT '1' COMMENT '0:不展开，1：展开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=344 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '0', '系统管理', '', null, 'el-icon-setting', '0', '1', '2020-03-07 17:41:30', '2020-05-16 16:54:50', '1', '1');
INSERT INTO `tb_menu` VALUES ('4', '1', '菜单权限', '/menus', null, 'el-icon-help', '0', '3', '2020-03-07 18:57:42', '2020-03-20 11:50:46', '1', '0');
INSERT INTO `tb_menu` VALUES ('5', '0', '日志管理', '/logs', null, 'el-icon-camera', '0', '6', '2020-03-07 18:58:18', '2020-05-07 19:35:19', '1', '0');
INSERT INTO `tb_menu` VALUES ('226', '1', '用户管理', '/users', 'users', 'el-icon-user', '0', '2', '2020-03-10 05:27:54', '2020-04-23 20:04:36', '1', '0');
INSERT INTO `tb_menu` VALUES ('229', '312', '物资管理', '', 'el-icon-date', 'el-icon-date', '0', '1', '2020-03-10 05:34:08', '2020-04-30 18:29:20', '1', '1');
INSERT INTO `tb_menu` VALUES ('230', '229', '物资入库', '/inStocks', 'el-icon-date', 'el-icon-date', '0', '1', '2020-03-10 05:34:28', '2020-04-30 19:13:01', '1', '1');
INSERT INTO `tb_menu` VALUES ('234', '226', '用户添加', '', 'user:add', 'el-icon-plus', '1', '1', '2020-03-10 05:50:44', '2020-03-10 07:51:56', '1', '0');
INSERT INTO `tb_menu` VALUES ('235', '1', '角色管理', '/roles', '', 'el-icon-postcard', '0', '3', '2020-03-10 05:51:28', '2020-05-16 16:37:58', '1', '0');
INSERT INTO `tb_menu` VALUES ('239', '226', '用户删除', '', 'user:delete', 'el-icon-picture', '1', '1', '2020-03-10 06:05:30', '2020-03-10 08:10:19', '1', '0');
INSERT INTO `tb_menu` VALUES ('240', '226', '用户编辑', '', 'user:edit', 'el-icon-video-camera-solid', '1', '1', '2020-03-10 06:06:30', '2020-03-10 07:52:28', '1', '0');
INSERT INTO `tb_menu` VALUES ('241', '235', '角色编辑', '', 'role:edit', 'el-icon-s-promotion', '1', '2', '2020-03-10 06:11:03', '2020-03-11 11:40:19', '1', '0');
INSERT INTO `tb_menu` VALUES ('242', '235', '角色删除', '', 'role:delete', 'el-icon-s-marketing', '1', '3', '2020-03-10 06:15:29', '2020-03-11 11:43:36', '1', '0');
INSERT INTO `tb_menu` VALUES ('247', '4', '添加菜单', '', 'menu:add', 'el-icon-s-opportunity', '1', '1', '2020-03-10 07:55:10', '2020-04-27 09:59:43', '1', '0');
INSERT INTO `tb_menu` VALUES ('249', '4', '修改菜单', '', 'menu:update', 'el-icon-share', '1', '2', '2020-03-10 07:56:55', '2020-03-15 13:29:29', '1', '0');
INSERT INTO `tb_menu` VALUES ('250', '4', '删除菜单', '', 'menu:delete', 'el-icon-folder-opened', '1', '3', '2020-03-10 07:57:38', '2020-03-15 13:29:41', '1', '0');
INSERT INTO `tb_menu` VALUES ('251', '235', '分配权限', '', 'role:authority', 'el-icon-document-add', '1', '1', '2020-03-10 08:13:22', '2020-03-11 11:39:30', '1', '0');
INSERT INTO `tb_menu` VALUES ('253', '1', '欢迎页面', '/welcome', 'welcome:view', 'el-icon-star-off', '0', '1', '2020-03-10 08:46:44', '2020-04-15 20:49:47', '1', '0');
INSERT INTO `tb_menu` VALUES ('254', '226', '分配角色', '', 'user:assign', 'el-icon-s-tools', '1', '3', '2020-03-11 01:32:29', '2020-04-27 10:58:30', '1', '0');
INSERT INTO `tb_menu` VALUES ('255', '235', '添加角色', '', 'role:add', 'el-icon-help', '1', '1', '2020-03-11 01:34:18', '2020-03-11 01:34:18', '1', '0');
INSERT INTO `tb_menu` VALUES ('256', '226', '禁用用户', '', 'user:status', 'el-icon-circle-close', '1', '1', '2020-03-11 06:50:04', '2020-03-14 05:05:49', '1', '0');
INSERT INTO `tb_menu` VALUES ('258', '226', '用户更新', '', 'user:update', 'el-icon-refresh', '1', '1', '2020-03-11 08:26:54', '2020-03-11 08:26:54', '1', '0');
INSERT INTO `tb_menu` VALUES ('259', '235', '角色更新', '', 'role:update', 'el-icon-refresh-left', '1', '1', '2020-03-11 11:45:20', '2020-03-11 11:45:20', '1', '0');
INSERT INTO `tb_menu` VALUES ('260', '235', '状态更新', '', 'role:status', 'el-icon-refresh', '1', '1', '2020-03-14 05:07:02', '2020-03-14 05:07:24', '1', '0');
INSERT INTO `tb_menu` VALUES ('261', '1', '部门管理', '/departments', '', 'el-icon-s-home', '0', '3', '2020-03-15 06:05:48', '2020-03-22 22:53:42', '1', '0');
INSERT INTO `tb_menu` VALUES ('262', '261', '添加部门', '', 'department:add', 'el-icon-plus', '1', '1', '2020-03-15 11:57:42', '2020-03-21 12:37:21', '1', '0');
INSERT INTO `tb_menu` VALUES ('263', '261', '编辑院系', '', 'department:edit', 'el-icon-edit', '1', '1', '2020-03-15 11:59:52', '2020-03-15 12:16:36', '1', '0');
INSERT INTO `tb_menu` VALUES ('264', '261', '更新院系', '', 'department:update', 'el-icon-refresh', '1', '1', '2020-03-15 12:02:34', '2020-03-15 12:16:32', '1', '0');
INSERT INTO `tb_menu` VALUES ('265', '261', '删除院系', null, 'department:delete', 'el-icon-delete', '1', '1', '2020-03-15 12:03:21', '2020-03-15 12:03:21', '1', '0');
INSERT INTO `tb_menu` VALUES ('267', '229', '物资资料', '/products', '', 'el-icon-goods', '0', '2', '2020-03-16 09:01:02', '2020-04-30 19:11:56', '1', '0');
INSERT INTO `tb_menu` VALUES ('268', '229', '物资类别', '/productCategorys', '', 'el-icon-star-off', '0', '2', '2020-03-16 09:01:48', '2020-03-22 22:45:40', '1', '0');
INSERT INTO `tb_menu` VALUES ('269', '311', '物资来源', '/suppliers', '', 'el-icon-coordinate', '0', '5', '2020-03-16 12:35:10', '2020-04-11 13:56:16', '1', '0');
INSERT INTO `tb_menu` VALUES ('270', '229', '物资发放', '/outStocks', '', 'el-icon-goods', '0', '5', '2020-03-16 13:55:49', '2020-05-10 14:46:53', '1', '1');
INSERT INTO `tb_menu` VALUES ('271', '5', '登入日志', '/loginLog', 'login:log', 'el-icon-date', '0', '1', '2020-03-20 10:31:12', '2020-04-04 20:01:02', '1', '0');
INSERT INTO `tb_menu` VALUES ('272', '303', '疫情辟谣', '/rumors', null, 'el-icon-help', '0', '5', '2020-03-20 19:29:40', '2020-05-01 12:41:01', '1', '0');
INSERT INTO `tb_menu` VALUES ('273', '303', '全国疫情', '/map', 'map:view', 'el-icon-s-opportunity', '0', '1', '2020-03-20 11:32:02', '2020-03-20 11:32:02', '1', '1');
INSERT INTO `tb_menu` VALUES ('274', '267', '添加物资', '', 'product:add', 'el-icon-s-opportunity', '1', '1', '2020-03-21 02:04:24', '2020-03-21 02:04:24', '1', '0');
INSERT INTO `tb_menu` VALUES ('276', '267', '上传图片', null, 'upload:image', 'el-icon-finished', '1', '2', '2020-03-21 02:05:21', '2020-03-21 02:05:21', '1', '0');
INSERT INTO `tb_menu` VALUES ('277', '267', '更新物资', null, 'product:update', 'el-icon-folder', '1', '3', '2020-03-21 02:05:56', '2020-03-21 02:05:56', '1', '0');
INSERT INTO `tb_menu` VALUES ('278', '267', '编辑物资', null, 'product:edit', 'el-icon-edit', '1', '1', '2020-03-21 02:06:23', '2020-03-21 02:06:23', '1', '0');
INSERT INTO `tb_menu` VALUES ('279', '269', '删除来源', '', 'supplier:delete', 'el-icon-document-delete', '1', '1', '2020-03-21 02:17:29', '2020-03-21 12:32:22', '1', '0');
INSERT INTO `tb_menu` VALUES ('280', '269', '更新来源', '', 'supplier:update', 'el-icon-paperclip', '1', '1', '2020-03-21 02:18:34', '2020-03-21 12:36:41', '1', '0');
INSERT INTO `tb_menu` VALUES ('281', '269', '添加来源', null, 'supplier:add', 'el-icon-document-add', '1', '1', '2020-03-21 02:19:02', '2020-03-21 02:19:02', '1', '1');
INSERT INTO `tb_menu` VALUES ('282', '269', '编辑来源', null, 'supplier:edit', 'el-icon-scissors', '1', '2', '2020-03-21 02:19:36', '2020-03-21 02:19:36', '1', '1');
INSERT INTO `tb_menu` VALUES ('283', '268', '添加类别', '', 'productCategory:add', ' el-icon-folder-add', '1', '1', '2020-03-21 02:26:12', '2020-03-21 02:44:22', '1', '0');
INSERT INTO `tb_menu` VALUES ('284', '268', '删除类别', null, 'productCategory:delete', 'el-icon-delete', '1', '1', '2020-03-21 02:27:05', '2020-03-21 02:28:49', '1', '0');
INSERT INTO `tb_menu` VALUES ('285', '268', '编辑类别', null, 'productCategory:edit', 'el-icon-scissors', '1', '2', '2020-03-21 02:27:42', '2020-03-21 02:27:42', '1', '0');
INSERT INTO `tb_menu` VALUES ('286', '268', '更新类别', null, 'productCategory:update', ' el-icon-coordinate', '1', '1', '2020-03-21 02:28:17', '2020-03-21 02:28:17', '1', '0');
INSERT INTO `tb_menu` VALUES ('295', '0', '其他管理', '', '', 'el-icon-s-marketing', '0', '5', '2020-03-22 01:21:56', '2020-05-16 16:54:43', '1', '0');
INSERT INTO `tb_menu` VALUES ('296', '295', 'swagger文档', '/swagger', null, 'el-icon-document', '0', '2', '2020-03-22 01:22:48', '2020-05-07 19:43:42', '1', '0');
INSERT INTO `tb_menu` VALUES ('297', '295', '监控管理', '', '', 'el-icon-warning', '0', '1', '2020-03-22 02:47:10', '2020-05-16 16:54:39', '1', '0');
INSERT INTO `tb_menu` VALUES ('298', '297', 'SQL监控', '/druid', null, 'el-icon-view', '0', '1', '2020-03-22 02:48:05', '2020-03-22 02:49:50', '1', '0');
INSERT INTO `tb_menu` VALUES ('299', '271', '删除日志', '', 'loginLog:delete', 'el-icon-delete', '1', '1', '2020-03-22 21:55:44', '2020-03-22 21:55:44', '1', '0');
INSERT INTO `tb_menu` VALUES ('300', '271', '批量删除', '', 'loginLog:batchDelete', 'el-icon-delete-solid', '1', '1', '2020-03-22 22:19:26', '2020-03-22 22:19:26', '1', '0');
INSERT INTO `tb_menu` VALUES ('301', '4', '编辑菜单', '', 'menu:edit', 'el-icon-edit', '1', '1', '2020-03-22 23:12:25', '2020-03-22 23:12:25', '1', '0');
INSERT INTO `tb_menu` VALUES ('303', '0', '健康报备', '', '', 'el-icon-platform-eleme', '0', '3', '2020-03-24 10:11:53', '2020-05-16 11:53:36', '1', '0');
INSERT INTO `tb_menu` VALUES ('304', '303', '健康打卡', '/health', '', 'el-icon-s-cooperation', '0', '1', '2020-03-24 10:12:57', '2020-05-06 09:37:51', '1', '0');
INSERT INTO `tb_menu` VALUES ('305', '303', '查看情况', null, null, 'el-icon-c-scale-to-original', '0', '2', '2020-03-24 10:13:49', '2020-03-24 10:16:44', '1', '1');
INSERT INTO `tb_menu` VALUES ('307', '5', '操作日志', '/logs', '', 'el-icon-edit', '0', '1', '2020-04-04 19:04:53', '2020-05-16 16:39:18', '1', '1');
INSERT INTO `tb_menu` VALUES ('308', '307', '删除日志', '', 'log:delete', 'el-icon-circle-close', '1', '1', '2020-04-04 19:59:20', '2020-04-04 19:59:20', '1', '1');
INSERT INTO `tb_menu` VALUES ('309', '307', '批量删除', null, 'log:batchDelete', 'el-icon-document-delete', '1', '2', '2020-04-04 19:59:59', '2020-04-04 19:59:59', '1', '0');
INSERT INTO `tb_menu` VALUES ('310', '311', '物资去处', '/consumers', '', 'el-icon-edit', '0', '1', '2020-04-05 10:08:21', '2020-04-05 10:08:21', '1', '0');
INSERT INTO `tb_menu` VALUES ('311', '312', '物资流向', null, null, 'el-icon-edit', '0', '3', '2020-04-05 10:16:59', '2020-04-30 18:40:43', '1', '0');
INSERT INTO `tb_menu` VALUES ('312', '0', '业务管理', null, null, 'el-icon-s-goods', '0', '2', '2020-04-05 10:19:07', '2020-05-16 17:09:22', '1', '0');
INSERT INTO `tb_menu` VALUES ('316', '229', '物资库存', '/stocks', '', 'el-icon-tickets', '0', '5', '2020-04-16 08:45:08', '2020-05-16 16:56:53', '1', '0');
INSERT INTO `tb_menu` VALUES ('317', '226', '导出表格', '', 'user:export', 'el-icon-edit', '1', '1', '2020-04-17 18:02:05', '2020-04-17 18:02:05', '1', '0');
INSERT INTO `tb_menu` VALUES ('318', '295', '图标管理', '/icons', '', 'el-icon-star-off', '0', '2', '2020-04-21 12:06:33', '2020-05-16 17:07:35', '1', '1');
INSERT INTO `tb_menu` VALUES ('319', '1', '公告管理', '/notices', '', 'el-icon-s-flag', '0', '4', '2020-04-21 12:07:46', '2020-05-05 19:55:08', '0', '0');
INSERT INTO `tb_menu` VALUES ('320', '319', '公告发布', '/notices/add', '', 'el-icon-edit', '1', '1', '2020-04-21 12:26:56', '2020-04-21 12:26:56', '1', '1');
INSERT INTO `tb_menu` VALUES ('321', '1', '附件管理', '/attachments', '', 'el-icon-picture-outline', '0', '2', '2020-04-25 10:52:17', '2020-04-25 10:52:17', '1', '1');
INSERT INTO `tb_menu` VALUES ('322', '310', '添加去处', '', 'consumer:add', 'el-icon-plus', '1', '2', '2020-04-27 16:57:04', '2020-04-27 16:58:21', '1', '1');
INSERT INTO `tb_menu` VALUES ('323', '310', '删除去处', null, 'consumer:delete', 'el-icon-delete', '1', '1', '2020-04-27 16:57:42', '2020-04-27 16:57:42', '1', '0');
INSERT INTO `tb_menu` VALUES ('324', '310', '编辑去处', '', 'consumer:edit', 'el-icon-edit', '1', '1', '2020-04-27 16:59:17', '2020-04-27 16:59:17', '1', '0');
INSERT INTO `tb_menu` VALUES ('325', '310', '更新去处', null, 'consumer:update', 'el-icon-star-off', '1', '1', '2020-04-27 17:00:18', '2020-04-27 17:00:18', '1', '1');
INSERT INTO `tb_menu` VALUES ('326', '230', '添加入库', '', 'inStock:in', 'el-icon-plus', '1', '1', '2020-04-27 17:07:04', '2020-04-27 17:07:04', '1', '1');
INSERT INTO `tb_menu` VALUES ('328', '230', '入库明细', null, 'inStock:detail', 'el-icon-zoom-in', '1', '2', '2020-04-27 17:08:25', '2020-04-27 17:08:25', '1', '0');
INSERT INTO `tb_menu` VALUES ('329', '4', '导出菜单', null, 'menu:export', 'el-icon-edit', '1', '1', '2020-04-27 17:26:40', '2020-04-27 17:26:40', '1', '0');
INSERT INTO `tb_menu` VALUES ('331', '267', '删除物资', null, 'product:delete', 'el-icon-delete', '1', '1', '2020-04-30 18:27:02', '2020-04-30 19:05:31', '1', '0');
INSERT INTO `tb_menu` VALUES ('332', '267', '回收物资', '', 'product:remove', 'el-icon-remove', '1', '1', '2020-04-30 18:56:48', '2020-04-30 18:56:48', '1', '1');
INSERT INTO `tb_menu` VALUES ('333', '267', '物资审核', null, 'product:publish', 'el-icon-edit', '1', '1', '2020-04-30 18:58:38', '2020-04-30 19:05:42', '1', '0');
INSERT INTO `tb_menu` VALUES ('336', '267', '物资还原', null, 'product:back', 'el-icon-top-left', '1', '1', '2020-04-30 19:06:22', '2020-04-30 19:06:22', '1', '0');
INSERT INTO `tb_menu` VALUES ('337', '230', '入库回收', '', 'inStock:remove', 'el-icon-remove', '1', '1', '2020-04-30 19:12:56', '2020-04-30 19:12:56', '1', '1');
INSERT INTO `tb_menu` VALUES ('338', '230', '入库审核', null, 'inStock:publish', 'el-icon-edit', '1', '1', '2020-04-30 19:13:32', '2020-04-30 19:13:32', '1', '0');
INSERT INTO `tb_menu` VALUES ('339', '230', '删除记录', null, 'inStock:delete', 'el-icon-delete', '1', '1', '2020-04-30 19:14:03', '2020-04-30 19:14:03', '1', '0');
INSERT INTO `tb_menu` VALUES ('340', '230', '入库还原', '', 'inStock:back', 'el-icon-d-arrow-left', '1', '1', '2020-04-30 19:17:27', '2020-04-30 19:17:27', '1', '0');
INSERT INTO `tb_menu` VALUES ('341', '295', '个人博客', '/blog', '', 'el-icon-view', '0', '1', '2020-05-07 19:34:31', '2020-05-07 19:34:31', '1', '0');
INSERT INTO `tb_menu` VALUES ('343', '304', '健康上报', '', 'health:report', 'el-icon-edit', '1', '1', '2020-05-14 20:21:09', '2020-05-14 20:21:09', '1', '0');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(1) DEFAULT '1' COMMENT '是否可用,0:不可用，1：可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('77', 'Redis监控员', '负责Redis模块', '2019-06-14 20:49:22', '2019-11-11 16:46:57', '1');
INSERT INTO `tb_role` VALUES ('78', '系统监控员', '负责整个系统监控模块', '2019-06-14 20:50:07', '2019-11-11 22:30:02', '1');
INSERT INTO `tb_role` VALUES ('79', '跑批人员', '负责任务调度跑批模块', '2019-06-14 20:51:02', '2019-11-16 10:42:54', '1');
INSERT INTO `tb_role` VALUES ('80', '开发人员', '拥有代码生成模块的权限', '2019-06-14 20:51:26', '2019-11-11 16:47:05', '1');
INSERT INTO `tb_role` VALUES ('125', '测试用户', '测试用户', '2019-11-15 18:59:19', '2019-11-15 18:59:19', '1');
INSERT INTO `tb_role` VALUES ('126', '蔡徐坤', '美国校队队长', '2019-11-22 15:57:08', '2019-11-22 15:57:08', '1');
INSERT INTO `tb_role` VALUES ('127', '鹿晗', '铁血硬汉', '2019-11-22 15:57:32', '2019-11-22 15:57:32', '1');
INSERT INTO `tb_role` VALUES ('128', '乔碧萝', '美落天仙', '2019-11-22 15:58:00', '2019-11-22 15:58:00', '1');
INSERT INTO `tb_role` VALUES ('135', '用户添加人员', '只拥有用户添加的权限', '2020-03-11 02:39:57', '2020-03-11 02:39:57', '1');
INSERT INTO `tb_role` VALUES ('136', '进货员', '采购口罩的帅哥，拥有采购商品的权限', '2020-03-11 00:00:00', '2020-03-11 03:12:38', '1');
INSERT INTO `tb_role` VALUES ('137', 'linux运维人员', '负责产品的运维。', '2020-03-11 03:13:29', '2020-03-11 03:13:29', '1');
INSERT INTO `tb_role` VALUES ('138', '测试员工', '登入测试系统的员工，无删除，无修改权限', '2020-03-14 04:57:52', '2020-03-14 04:57:52', '1');
INSERT INTO `tb_role` VALUES ('139', '辅导员老师', '可以查看学生健康报备的详细情况', '2020-03-15 05:15:31', '2020-03-15 05:15:31', '1');
INSERT INTO `tb_role` VALUES ('140', '部门主任', '系主任，管理院系。', '2020-03-15 00:00:00', '2020-05-16 18:33:28', '1');
INSERT INTO `tb_role` VALUES ('141', '物资入库操作员', '角色拥有物资管理入库的权限', '2020-03-24 00:00:00', '2020-03-24 10:23:36', '1');
INSERT INTO `tb_role` VALUES ('143', '超级管理员', '拥有系统的最高权限', '2020-04-21 10:34:12', '2020-04-21 10:34:12', '1');
INSERT INTO `tb_role` VALUES ('144', 'admin33', '拥有系统的最高权限.', '2019-06-14 00:00:00', '2020-04-27 21:29:29', '1');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单/按钮ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('125', '253');
INSERT INTO `tb_role_menu` VALUES ('125', '226');
INSERT INTO `tb_role_menu` VALUES ('125', '234');
INSERT INTO `tb_role_menu` VALUES ('125', '239');
INSERT INTO `tb_role_menu` VALUES ('125', '240');
INSERT INTO `tb_role_menu` VALUES ('125', '256');
INSERT INTO `tb_role_menu` VALUES ('125', '258');
INSERT INTO `tb_role_menu` VALUES ('125', '317');
INSERT INTO `tb_role_menu` VALUES ('125', '254');
INSERT INTO `tb_role_menu` VALUES ('125', '321');
INSERT INTO `tb_role_menu` VALUES ('125', '4');
INSERT INTO `tb_role_menu` VALUES ('125', '247');
INSERT INTO `tb_role_menu` VALUES ('125', '301');
INSERT INTO `tb_role_menu` VALUES ('125', '329');
INSERT INTO `tb_role_menu` VALUES ('125', '249');
INSERT INTO `tb_role_menu` VALUES ('125', '250');
INSERT INTO `tb_role_menu` VALUES ('125', '235');
INSERT INTO `tb_role_menu` VALUES ('125', '251');
INSERT INTO `tb_role_menu` VALUES ('125', '255');
INSERT INTO `tb_role_menu` VALUES ('125', '259');
INSERT INTO `tb_role_menu` VALUES ('125', '260');
INSERT INTO `tb_role_menu` VALUES ('125', '241');
INSERT INTO `tb_role_menu` VALUES ('125', '242');
INSERT INTO `tb_role_menu` VALUES ('125', '261');
INSERT INTO `tb_role_menu` VALUES ('125', '262');
INSERT INTO `tb_role_menu` VALUES ('125', '263');
INSERT INTO `tb_role_menu` VALUES ('125', '264');
INSERT INTO `tb_role_menu` VALUES ('125', '265');
INSERT INTO `tb_role_menu` VALUES ('125', '320');
INSERT INTO `tb_role_menu` VALUES ('125', '312');
INSERT INTO `tb_role_menu` VALUES ('125', '229');
INSERT INTO `tb_role_menu` VALUES ('125', '230');
INSERT INTO `tb_role_menu` VALUES ('125', '326');
INSERT INTO `tb_role_menu` VALUES ('125', '337');
INSERT INTO `tb_role_menu` VALUES ('125', '338');
INSERT INTO `tb_role_menu` VALUES ('125', '339');
INSERT INTO `tb_role_menu` VALUES ('125', '340');
INSERT INTO `tb_role_menu` VALUES ('125', '328');
INSERT INTO `tb_role_menu` VALUES ('125', '267');
INSERT INTO `tb_role_menu` VALUES ('125', '274');
INSERT INTO `tb_role_menu` VALUES ('125', '278');
INSERT INTO `tb_role_menu` VALUES ('125', '331');
INSERT INTO `tb_role_menu` VALUES ('125', '332');
INSERT INTO `tb_role_menu` VALUES ('125', '333');
INSERT INTO `tb_role_menu` VALUES ('125', '336');
INSERT INTO `tb_role_menu` VALUES ('125', '276');
INSERT INTO `tb_role_menu` VALUES ('125', '277');
INSERT INTO `tb_role_menu` VALUES ('125', '268');
INSERT INTO `tb_role_menu` VALUES ('125', '283');
INSERT INTO `tb_role_menu` VALUES ('125', '284');
INSERT INTO `tb_role_menu` VALUES ('125', '286');
INSERT INTO `tb_role_menu` VALUES ('125', '285');
INSERT INTO `tb_role_menu` VALUES ('125', '270');
INSERT INTO `tb_role_menu` VALUES ('125', '316');
INSERT INTO `tb_role_menu` VALUES ('125', '311');
INSERT INTO `tb_role_menu` VALUES ('125', '310');
INSERT INTO `tb_role_menu` VALUES ('125', '323');
INSERT INTO `tb_role_menu` VALUES ('125', '324');
INSERT INTO `tb_role_menu` VALUES ('125', '325');
INSERT INTO `tb_role_menu` VALUES ('125', '322');
INSERT INTO `tb_role_menu` VALUES ('125', '269');
INSERT INTO `tb_role_menu` VALUES ('125', '279');
INSERT INTO `tb_role_menu` VALUES ('125', '280');
INSERT INTO `tb_role_menu` VALUES ('125', '281');
INSERT INTO `tb_role_menu` VALUES ('125', '282');
INSERT INTO `tb_role_menu` VALUES ('125', '303');
INSERT INTO `tb_role_menu` VALUES ('125', '273');
INSERT INTO `tb_role_menu` VALUES ('125', '304');
INSERT INTO `tb_role_menu` VALUES ('125', '343');
INSERT INTO `tb_role_menu` VALUES ('125', '305');
INSERT INTO `tb_role_menu` VALUES ('125', '272');
INSERT INTO `tb_role_menu` VALUES ('125', '295');
INSERT INTO `tb_role_menu` VALUES ('125', '297');
INSERT INTO `tb_role_menu` VALUES ('125', '298');
INSERT INTO `tb_role_menu` VALUES ('125', '341');
INSERT INTO `tb_role_menu` VALUES ('125', '296');
INSERT INTO `tb_role_menu` VALUES ('125', '318');
INSERT INTO `tb_role_menu` VALUES ('125', '5');
INSERT INTO `tb_role_menu` VALUES ('125', '271');
INSERT INTO `tb_role_menu` VALUES ('125', '299');
INSERT INTO `tb_role_menu` VALUES ('125', '300');
INSERT INTO `tb_role_menu` VALUES ('125', '307');
INSERT INTO `tb_role_menu` VALUES ('125', '308');
INSERT INTO `tb_role_menu` VALUES ('125', '309');
INSERT INTO `tb_role_menu` VALUES ('125', '1');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `avatar` text COMMENT '头像',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` int(1) NOT NULL COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '0:超级管理员，1：系统用户',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `birth` date DEFAULT NULL,
  `department_id` bigint(20) DEFAULT '1' COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('5', 'zhangyukang', 'zhangyu', 'Jana@126.com', 'http://thirdqq.qlogo.cn/g?b=oidb&k=icTYjyV5afABvE1v4ge9SLg&s=100&t=1584195695', '17744444444', '1', '2019-06-14 21:12:16', '2020-03-19 04:20:40', '0', 'E46E184B-BAE5-4EE0-B3D9-674A04F37DDE', '0', '9690bbd5a58d2eeefb0b7ca1324a1dca', '2020-03-27', '1');
INSERT INTO `tb_user` VALUES ('6', 'Georgie', 'zhangyu', 'Georgie@qq.com', 'https://shiro.mrbird.cn:8080/febs/images/avatar/default.jpg', '17766666666', '1', '2019-06-14 21:15:09', '2020-05-18 10:02:42', '0', null, '1', 'dffc683378cdaa015a0ee9554c532225', '2020-03-25', '14');
INSERT INTO `tb_user` VALUES ('42', 'coderman', 'zhangyu', 'zhangyukang@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426528317', '1', '2020-03-08 10:26:47', '2020-04-27 12:23:12', '1', '980783d3-618f-4b91-a7b8-9b9fafa6', '1', '23c2a4e509ab9ab6f46ef35df9127c1d', '2020-03-13', '12');
INSERT INTO `tb_user` VALUES ('55', 'coderman', 'zhangyu', 'zhangyukang@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426528317', '1', '2020-03-08 10:32:57', '2020-05-06 10:54:51', '1', '00f68ad3-3a85-4323-bcb4-0af41d4c', '1', 'd5fe4ee094895c35c0dc40fbd0befe83', '2020-03-12', '16');
INSERT INTO `tb_user` VALUES ('56', 'coderman', 'zhangyu', 'zhangyukang@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426528317', '1', '2020-03-08 10:33:00', '2020-04-04 19:57:35', '1', '37eb6e49-8ead-404f-9325-93fc9507', '1', '5bf3d15435f5d970292d28f44d0cde1a', '2020-03-20', '1');
INSERT INTO `tb_user` VALUES ('67', 'xizhang', 'zhangyu', 'zhangyukang@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426528317', '1', '2020-03-08 10:59:33', '2020-03-08 10:59:33', '1', '7fc38a7e-a509-4282-9ae4-6fda9a4c', '1', '3f3f4e9eb26d6a8ed86884776a18a493', '2020-03-24', '1');
INSERT INTO `tb_user` VALUES ('69', 'coderman', 'zhangyu', 'zhangyukang@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426528317', '1', '2020-03-08 11:02:39', '2020-03-09 03:44:36', '1', 'ded0c6c5-36aa-4c81-a28b-22854d4b', '1', 'a4a36a084752c5edbb404a87cecabb83', '2020-03-22', '1');
INSERT INTO `tb_user` VALUES ('71', 'huangjing', 'xiaohuang', '30531614@163.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15079437282', '1', '2020-03-08 11:08:58', '2020-03-09 05:26:15', '0', 'fb90be8e-c71a-4ee0-81aa-2acc4197', '1', 'ffd25a3b6d3cc7fb0e2b7ae99cd72cfd', '2020-03-06', '1');
INSERT INTO `tb_user` VALUES ('72', '章宇康', 'kangs~', 'zhang@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15079437282', '1', '2020-03-08 11:18:35', '2020-03-09 05:26:08', '0', '7a235999-57c5-488e-85cf-54cb216d', '1', '98ed1a23386f76486fc65a359e32817e', '2020-03-03', '1');
INSERT INTO `tb_user` VALUES ('74', '2313', '123213', '1232323@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15245141241', '1', '2020-03-08 11:33:14', '2020-03-10 01:28:27', '0', '566fc473-2781-44b3-ae6c-666606e1', '1', '587d62b2ec410f30d970ac2f42046c6c', '2020-03-24', '1');
INSERT INTO `tb_user` VALUES ('75', 'coderman2', 'nickname', '3053161401@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426524512', '1', '2020-03-09 01:30:31', '2020-04-18 12:29:03', '1', 'fa74ca50-6e42-4c5d-a454-39e6fe06', '1', 'e8c84adc4ef37e7d1385aa774e9431b0', '2020-03-23', '14');
INSERT INTO `tb_user` VALUES ('76', 'xiaopie', 'nanana', '3051215@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426521215', '1', '2020-03-09 01:33:09', '2020-03-09 03:49:32', '1', 'cf94c27b-b752-4d9d-a4a9-d096f166', '1', '2e2a3935ec97359657d82bfb88b3175f', '2020-03-30', '1');
INSERT INTO `tb_user` VALUES ('77', 'xiaope', 'kimikn', '3053161401@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15079437282', '1', '2020-03-09 01:40:03', '2020-03-09 01:40:03', '1', 'b5f9288d-1691-443f-99ff-a4ac2773', '1', '863123f92dc86ce62d27245ec758335e', '2020-03-16', '1');
INSERT INTO `tb_user` VALUES ('78', 'uttest', 'setast', '3053161401@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13425124541', '1', '2020-03-09 01:59:52', '2020-03-09 03:45:37', '0', 'cd58a932-72d1-4a79-b3ef-cf8ed38c', '1', '82a9345697d86864798daeba24656a6c', '2020-03-18', '1');
INSERT INTO `tb_user` VALUES ('79', '9c07a0', '喜欢123', 'efbc7@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13656451241', '1', '2020-03-09 02:08:56', '2020-03-09 03:52:09', '1', 'b1f83d88-c843-46fb-8125-0934fe84cf4e', '1', '0ae4c4e4a50dfcf5132e2d7041de0841', '2020-03-19', '1');
INSERT INTO `tb_user` VALUES ('80', '4f9dac', 'xiaohfdsa', 'b5501@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13521245214', '1', '2020-03-09 02:08:57', '2020-05-06 10:54:57', '0', '8a8b902e-cf22-48c0-b749-944a7e690452', '1', '6dd80bb758f15cae9140dccb7bc57a4c', '2020-03-09', '15');
INSERT INTO `tb_user` VALUES ('81', '0ae292', 'febsc', '5a7d0@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13425621245', '1', '2020-03-09 02:08:57', '2020-03-14 09:09:51', '1', '14621dcf-183b-4816-8d64-7670cc4b9928', '1', '98993da499514930dea786f87a7f436e', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('84', '73b298', null, 'ab361@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:57', '2020-03-09 02:08:57', null, 'e1665066-f984-4a1e-a0ed-1a2ccc175694', '1', 'd51156a36bf8b3ae847c39c26e5f2dd2', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('86', '265f4f', null, 'fa10a@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '0', '2020-03-09 02:08:57', '2020-03-09 02:08:57', null, 'f1261ed6-bc65-4b74-9571-a40562b8073b', '1', '623ae1075f296dbdc9f2af3c82d10bcc', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('87', '844bcc', null, '7674f@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:57', '2020-03-09 02:08:57', null, '10a0f209-0e87-4a9c-9ec0-45b69fc5510b', '1', 'eeded64da4b37869a72a19772f0f600e', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('89', '32c5a6', null, 'e9b70@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:57', '2020-03-09 02:08:57', null, 'f69446ef-5db0-48ef-a54d-2e8971d9c006', '1', '704dfafc0d09035c0ace3ab86dd10009', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('94', '6b551c', '121212', '789b4@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15325154141', '1', '2020-03-09 02:08:57', '2020-04-01 10:39:10', '1', 'd7562c1c-2f5e-4f41-b240-81f024ea156d', '1', 'a9823c3147891c4b10cc640916fa6f85', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('96', '946fcc', null, 'cdc70@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:57', '2020-03-09 02:08:57', null, 'd4bc0c6f-7f09-4209-a726-1024a0746cd8', '1', '635a5225f7259af87e6aaf8eae995feb', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('97', '00f2d9', null, 'aa2a1@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:58', '2020-03-09 02:08:58', null, '0216e676-636f-4bfa-99f0-25eb22914f7f', '1', '26f79165456125fb068a9f0182a4edc3', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('98', '0aee3e', null, 'e2ad0@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:58', '2020-03-09 02:08:58', null, 'bac524d1-8dd7-41ec-8033-ac78d7dd7b71', '1', 'd51fc9d2a0b1a3bae488a8c4e4ba5e16', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('100', 'b8c39d', null, '9e259@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:58', '2020-03-09 02:08:58', null, '6a54d7a7-fd8b-479d-9297-e4704f07aef6', '1', '2dd394b5d3a53aac040e0bbffc158721', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('101', '2c4ad3', null, '8c896@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:58', '2020-03-09 02:08:58', null, '32c77485-445e-43a3-8314-2b55779bc655', '1', '537f71c48e00d613c4623c53c4e8006d', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('102', 'a33a05', null, '12677@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:58', '2020-03-09 02:08:58', null, '296f5a9a-d0f9-4b3a-9d8d-877376244fb8', '1', '26bb804ab0bacc465cb54c59ef869c71', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('107', '003f2b', null, 'c70f2@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '0', '2020-03-09 02:08:58', '2020-03-09 02:08:58', null, '609e2529-d540-4d47-82ed-128364d13d18', '1', '84aa6a5777edeb9246ec9ba8691e93d9', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('109', 'e070f8', null, 'ad982@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:58', '2020-03-09 02:08:58', null, 'b393c78d-1e73-46d4-aafe-4f4451eb6a34', '1', 'cc871a434dacd26416f079eb0cd57d7e', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('110', '98ec73', '32323213', 'ef7a1@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15097141421', '1', '2020-03-09 02:08:58', '2020-04-16 13:29:27', '1', '437750f1-2bcf-44fa-b328-022fb270a61e', '1', 'b3c55b7b85a03d91481c4f62afd6ff79', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('111', 'f280c9', null, '7d496@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:58', '2020-03-09 02:08:58', null, '8e364a9e-008a-4f0b-9e01-797c9645f2a3', '1', '052b758f6e0ffd7ec28ebfecceb1a9c3', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('112', 'cce680', null, '517a4@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '0', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '9f94ff1e-2fde-4017-a445-d00ec0fe8069', '1', '6c7953179599158f302042209dcff8f2', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('113', 'b4328f', null, '128e6@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '6a563765-ee32-4263-a52e-689ecd980957', '1', 'a650cc2995f2285ee6a022d115e70a28', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('114', 'b11bf8', null, 'e7f0a@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '0', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '641c375b-c2ba-453e-87f9-86690867cb8c', '1', 'a03783cf4552fb976af567a63cb2ea85', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('115', '077649', null, 'dd188@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '89afca28-51c1-4b88-b9eb-2985d8fcbfdb', '1', 'acd5d843118a020fb8a9e59753126898', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('117', '245705', null, 'adfba@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '4366355f-aaf7-4ae2-9cac-3fe5c2010b7f', '1', 'fa02e2d5eb0fece583c66a059caeff7b', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('118', '5d8f22', null, 'deb9c@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '0', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, 'db3479a4-d6b6-47be-beb7-d0c8d4aa8f46', '1', '1023b75372c2de61e76545adc2d34307', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('119', 'd0ff21', null, 'b2c9f@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '295be040-bcd0-4809-a66e-2dc0d846fe7d', '1', 'ab071ac26d0324f494344d31effe3867', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('120', '7b3fe5', null, '214bb@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '3ec4354a-a2c4-4221-8122-b36e069350c6', '1', '561e77e1c02cd1089c3bfa5b244a68af', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('121', '401378', null, 'd21b4@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '644963b8-cb61-4630-bc00-b0247c9264b6', '1', 'fb7c8c3b7c552f8b5f712bfe1a98cdd8', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('122', '983279', null, '3d680@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, 'deeff240-81f8-4833-b047-2c1bb3d4bdb6', '1', '157f8243aad225d75895055e18528d35', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('123', 'ad96c3', '23131', '40e2f@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15078454141', '1', '2020-03-09 02:08:59', '2020-05-18 10:09:00', '1', '5946627b-e684-4267-9a2f-57f9399ad803', '1', '45e33362a0df93310be52c912a63d138', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('124', 'd03c7d', null, '6dde1@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '3edaead9-275f-4fc2-a2d3-0a2dfd6f7b0b', '1', '444dfa242b30dbec35110730c145a955', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('125', 'c50b91', null, 'aa163@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, 'aebe7ec2-63dd-4a44-863e-4b2ef02873dd', '1', '808087cd1a083518eaf66818a9a6ed19', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('126', '480bc4', null, 'db6a4@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '0', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '86888065-10a4-4169-b3e9-c894cf5d0e62', '1', '04bf244726d70a67dacf03918f45d768', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('127', '358a56', null, '829d0@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '5abd53db-9b85-4bad-9d43-994ee303cd94', '1', '34952fe64965b2b2dd39b1dcb580ce8f', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('128', 'd27438', null, '61d2a@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '0', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '5e3cab06-1514-4743-8b3b-6d62114e7c71', '1', '450328064b64f50f6cf5b69c7fc01c5f', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('129', '1b7339', null, '12e01@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:08:59', '2020-03-09 02:08:59', null, '9d608dce-e845-4c56-bda9-6135363f64a8', '1', '77d65f76f425b6580a92376d506de333', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('130', '8a5afc', null, 'e4ad7@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:09:00', '2020-03-09 02:09:00', null, 'a4bfce2b-7f79-4c8d-8e69-1cdfe490884b', '1', 'd1684b5c966f1f12b19c4028f9c599f3', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('131', '78f7ce', null, '691a3@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:09:00', '2020-03-09 02:09:00', null, '84940ace-aae5-4bce-bdf0-797503980cb0', '1', 'af527ae2167fc88b103f02584392a3f7', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('132', 'f5013b', null, 'ce8d4@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:09:00', '2020-03-09 02:09:00', null, '8a08560c-2d7e-44dd-adeb-2e23dbc265d5', '1', '901bdb83bbd304ab9cb1faf8aa1e104c', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('133', '333c93', 'xixixixi', 'ac879@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15217454741', '1', '2020-03-09 02:09:00', '2020-03-11 01:30:33', '0', '6470d76d-f7fd-43dc-9512-2b3af113b861', '1', '08c405fb4a30b3d23f191c59cbe9ce5d', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('134', '0ad14c', '111111e', '47dde@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426521241', '0', '2020-03-09 02:09:00', '2020-03-11 01:30:29', '1', 'fc431fb5-0c11-4bbe-8dc6-536b3b168964', '1', 'f629ddfbdcfae8b5231a37c2b1897a0a', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('135', 'a11233', null, '73f26@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:09:00', '2020-03-09 02:09:00', null, 'adcb9723-98b5-4410-9e7f-2da7457e8ec0', '1', '6bcfb623e7ef643c61674ce008847fae', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('136', 'f1ba92', null, '7260e@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:09:00', '2020-03-09 02:09:00', null, '2bc42f7f-a96d-425b-98a7-916646def1a4', '1', 'be7ecc92a11c3e9d6f01d67d7150e0bd', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('137', 'd85408', null, '040ed@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:09:00', '2020-03-09 02:09:00', null, 'e51ea121-63c4-44ae-a005-ea4912f5e463', '1', 'ad0b8e0545924a917285e0b7e1084aa6', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('138', '3a8c12', null, '5d71f@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:09:00', '2020-03-09 02:09:00', null, '26eb1d33-85bf-47bf-be70-a316cc41bfbb', '1', '2953352d93a6fc69f3c2092a7aea6994', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('140', 'e42e02', null, 'ca931@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', null, '1', '2020-03-09 02:09:00', '2020-03-09 02:09:00', null, 'e6c93320-54ae-4a89-8235-68086e05f20c', '1', 'f6ad3623b2b80ab63e876a163211fd53', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('143', '71a327', 'xzzzz', '62c14@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15079487585', '1', '2020-03-09 02:09:00', '2020-03-16 13:11:31', '1', 'cd9dd9d7-a369-4018-80d2-dbd727033e9a', '1', '3db7d3268e883452af1a889c0fd0fa2b', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('146', '61a9e8', '123456', '391e1@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15078451241', '1', '2020-03-09 02:09:00', '2020-03-09 03:53:49', '1', '0656f059-f957-498b-8920-3e8f20be1571', '1', 'd67d9ec53ed783270ddc33c289c40696', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('149', '1fffc0', '78451', '22b34@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15214124178', '1', '2020-03-09 02:09:00', '2020-03-09 03:54:02', '1', 'b7b971ab-c424-4f2e-a787-7985c72253ba', '1', 'b5b71c6a9db10c3d8c1b7d125e45b83a', '2020-03-09', '1');
INSERT INTO `tb_user` VALUES ('180', 'tester', 'testfesa', 'fasdfasdf@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15265451241', '1', '2020-03-09 03:25:03', '2020-03-09 03:25:03', '1', '46b6f678-570d-42a0-9841-241ecb29', '1', '71633f62c14d9776099f42ace88b4a9a', '2020-03-16', '1');
INSERT INTO `tb_user` VALUES ('181', '小白兔~~', 'fdsaf', '3021211@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15278451425', '1', '2020-03-09 03:50:23', '2020-03-09 03:50:32', '0', '343b600c-b384-4649-ae9c-6faf5022', '1', 'a0665e39cf5e7e7ed8f578fc2432352f', '2020-03-02', '1');
INSERT INTO `tb_user` VALUES ('182', 'test', 'testman', '123456@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426528317', '1', '2020-03-11 02:41:06', '2020-03-11 02:41:06', '1', '90e820b5-eee2-4f06-b842-98fffe0b', '1', '0c0349bd702e6aeb2483bcbd3b7707ad', '2020-03-26', '1');
INSERT INTO `tb_user` VALUES ('183', 'cesicesi', 'nicknamefd', '30512141214@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426524512', '1', '2020-03-14 05:02:52', '2020-03-14 05:02:52', '1', '2a8e7b62-193a-4696-bf7f-01b1866b', '1', 'cf55bb2194bbba2539d67b8312a76e94', '2020-03-10', '1');
INSERT INTO `tb_user` VALUES ('184', '系统测试人员', 'Sys-Test', '3053161401@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15079454152', '1', '2020-03-21 02:07:32', '2020-05-10 10:32:56', '1', '3d66cd24-01ae-4832-93e3-fea557b2', '1', '73ab4f4590d4ffb7a4404a46387dcb9e', '2020-03-19', '15');
INSERT INTO `tb_user` VALUES ('185', '2222222', '22222222', '22222@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15079437485', '1', '2020-04-01 11:17:29', '2020-04-01 11:45:25', '1', '9bf3e60a-c643-46d1-9bb8-c200c739', '1', 'd1db198e73e3c81342636dcb832c1fce', '2020-04-27', '14');
INSERT INTO `tb_user` VALUES ('186', 'xixxix', 'fdasfsf', '12121515@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '13426525414', '0', '2020-04-11 10:15:26', '2020-04-11 10:15:26', '1', '711eda86-843d-4222-8f8e-6bb72305', '1', '2dc3358e8beb909e8eb208b86d5e5f66', '2020-04-28', '12');
INSERT INTO `tb_user` VALUES ('187', '324234', 'fsdfasf', 'fdsafsf@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15078485741', '1', '2020-04-11 13:41:00', '2020-04-11 13:41:00', '1', '1c62485f-547c-407e-875e-c91029fe', '1', '7b14923f4f75e6af0e247bb9abaaa3b8', '2020-04-15', '12');
INSERT INTO `tb_user` VALUES ('188', '1231313', '312313', '31312313@qq.com', 'http://badidol.com/uploads/images/avatars/201910/24/18_1571921832_HG9E55x9NY.jpg', '15079485858', '1', '2020-05-18 10:05:48', '2020-05-18 10:05:48', '1', '3f1f7870-62b0-4928-9f9b-e706fb7b', '1', '8e1aea821462a1e6dff18e18c137f48d', '2020-05-06', '12');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('184', '125');
INSERT INTO `tb_user_role` VALUES ('6', '0');

CREATE TABLE `tb_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '企业ID',
  `name` varchar(50) NOT NULL COMMENT '企业名称',
  `logo` varchar(100) DEFAULT NULL,
  `audit_status` int(1) NOT NULL COMMENT '审核状态 1=未审核,2=审核中、待审核, 3=审核通过, 4=审核未通过',
  `create_by_id` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) NOT NULL COMMENT '最新更新人',
  `update_time` datetime NOT NULL COMMENT '最新更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='企业信息表';

CREATE TABLE `tb_company_declare_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '企业信息申报历史记录ID',
  `company_id` bigint(20) NOT NULL COMMENT '企业ID',
  `company_name` varchar(50) NOT NULL COMMENT '企业名称',
  `create_by_id` bigint(20) NOT NULL COMMENT '创建人id',
  `create_by_name` varchar(50) NOT NULL COMMENT '创建人name',
  `content_json_str` varchar(1000) NOT NULL COMMENT '本次申报内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='企业信息申报历史记录表';

CREATE TABLE `tb_company_register_info` (
    `id` bigint(20) NOT NULL COMMENT '企业注册信息ID',
    `registered_capital` int(5) NOT NULL DEFAULT '0' COMMENT '注册资本',
    `actual_paid_capital` int(5) NOT NULL DEFAULT '0' COMMENT '实缴资本',
    `capital_unit` int(1) NOT NULL DEFAULT '0' COMMENT '资本金单位',
    `set_up_date` datetime DEFAULT '1970-01-01 00:00:00' COMMENT '成立日期',
    `opera_status` int(1) NOT NULL DEFAULT '0' COMMENT '经营状态',
    `unified_social_credit_code` varchar(20) DEFAULT NULL COMMENT '统一社会信用代码',
    `business_registration_num` varchar(20) DEFAULT NULL COMMENT '工商注册号',
    `taxpayer_identification_num` varchar(20) DEFAULT NULL COMMENT '纳税人识别号',
    `organization_code` varchar(20) DEFAULT NULL COMMENT '组织机构代码',
    `company_type` int(1) NOT NULL DEFAULT '0' COMMENT '公司类型',
    `company_industry` int(1) NOT NULL DEFAULT '0' COMMENT '公司行业',
    `approval_date` datetime DEFAULT '1970-01-01 00:00:00' COMMENT '核准日期',
    `registration_authority` varchar(20) DEFAULT NULL COMMENT '登记机关',
    `business_term` datetime DEFAULT '1970-01-01 00:00:00' COMMENT '营业期限',
    `taxpayer_qualification` int(1) NOT NULL DEFAULT '0' COMMENT '纳税人资质',
    `staff_size` int(1) NOT NULL DEFAULT '0' COMMENT '人员规模',
    `insured_num` int(6) NOT NULL DEFAULT '0' COMMENT '参保人数',
    `history_name` varchar(20) DEFAULT NULL COMMENT '曾用名',
    `english_name` varchar(50) DEFAULT NULL COMMENT '英文名',
    `update_by_name` varchar(50) NOT NULL COMMENT '最新更新人',
    `update_time` datetime NOT NULL COMMENT '最新更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='企业注册信息表';




