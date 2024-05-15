

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父部门id',
  `department_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `department_leader` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门负责人',
  `department_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门联系电话',
  `department_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门邮箱',
  `sort` int NOT NULL COMMENT '显示顺序',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES (1, '2024-05-05 15:11:28', 1, '2024-05-15 19:40:59', 1, 0, '清北高中', 'javaex', '', '', 1, 0);
INSERT INTO `sys_department` VALUES (2, '2024-05-05 15:17:22', 1, '2024-05-09 14:11:49', 1, 1, '高一年级', '', '', '', 1, 0);
INSERT INTO `sys_department` VALUES (3, '2024-05-05 15:17:47', 1, '2024-05-09 14:12:02', 1, 2, '高一（1）班', '', '', '', 1, 0);
INSERT INTO `sys_department` VALUES (4, '2024-05-05 15:17:55', 1, '2024-05-09 14:12:07', 1, 2, '高一（2）班', '', '', '', 2, 0);
INSERT INTO `sys_department` VALUES (5, '2024-05-05 15:18:02', 1, '2024-05-09 14:12:13', 1, 2, '高一（3）班', '', '', '', 3, 0);
INSERT INTO `sys_department` VALUES (6, '2024-05-05 15:18:16', 1, '2024-05-09 14:12:22', 1, 1, '高二年级', '', '', '', 2, 0);
INSERT INTO `sys_department` VALUES (7, '2024-05-05 15:18:34', 1, '2024-05-09 14:12:31', 1, 6, '高二（1）班', '', '', '', 1, 0);
INSERT INTO `sys_department` VALUES (8, '2024-05-05 15:18:39', 1, '2024-05-09 14:12:36', 1, 6, '高二（2）班', '', '', '', 2, 0);
INSERT INTO `sys_department` VALUES (17, '2024-05-09 14:12:43', 1, '2024-05-09 14:12:43', 1, 6, '高二（3）班', '', '', '', 3, 0);
INSERT INTO `sys_department` VALUES (18, '2024-05-09 14:12:51', 1, '2024-05-09 14:12:51', 1, 1, '高三年级', '', '', '', 3, 0);
INSERT INTO `sys_department` VALUES (19, '2024-05-09 14:13:00', 1, '2024-05-09 14:13:00', 1, 18, '高三（1）班', '', '', '', 1, 0);
INSERT INTO `sys_department` VALUES (20, '2024-05-09 14:13:11', 1, '2024-05-09 14:13:11', 1, 18, '高三（2）班', '', '', '', 2, 0);
INSERT INTO `sys_department` VALUES (21, '2024-05-09 14:13:17', 1, '2024-05-09 14:13:17', 1, 18, '高三（3）班', '', '', '', 3, 0);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `dict_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典编码',
  `dict_code_comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字典编码描述',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典键值',
  `dict_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典文本',
  `sort` int NULL DEFAULT NULL COMMENT '显示顺序',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认值',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, '2024-05-04 11:10:16', 1, '2024-05-04 13:04:41', 1, 'sex', '用户性别', '0', '男', 1, 1, '', 0);
INSERT INTO `sys_dict_data` VALUES (2, '2024-05-04 11:31:26', NULL, '2024-05-04 12:19:21', 1, 'sex', '用户性别', '1', '女', 2, 0, '', 0);
INSERT INTO `sys_dict_data` VALUES (4, '2024-05-04 12:08:17', 1, '2024-05-04 12:20:09', 1, 'sex', '用户性别', '2', '未知', 3, 0, '性别未知', 0);
INSERT INTO `sys_dict_data` VALUES (5, '2024-05-04 12:22:50', 1, '2024-05-04 12:22:50', 1, 'notice_type', '通知类型', '1', '通知', 1, 1, '', 0);
INSERT INTO `sys_dict_data` VALUES (6, '2024-05-04 12:23:06', 1, '2024-05-04 13:04:59', 1, 'notice_type', '通知类型', '2', '公告', 2, 0, '', 0);

-- ----------------------------
-- Table structure for sys_gen_table
-- ----------------------------
DROP TABLE IF EXISTS `sys_gen_table`;
CREATE TABLE `sys_gen_table`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `table_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '表名',
  `table_comment` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '表描述',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除（0=否；1=是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '代码生成-数据库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_gen_table
-- ----------------------------
INSERT INTO `sys_gen_table` VALUES (2, '2024-04-28 00:05:40', 1, '2024-04-29 01:56:44', 1, 'sys_dict_data', '数据字典表', '', 0);
INSERT INTO `sys_gen_table` VALUES (23, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 'sys_gen_table', '代码生成-数据库表', NULL, 0);
INSERT INTO `sys_gen_table` VALUES (24, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 'sys_gen_table_column', '代码生成-数据库表字段', NULL, 0);
INSERT INTO `sys_gen_table` VALUES (25, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 'sys_menu', '菜单表', NULL, 0);
INSERT INTO `sys_gen_table` VALUES (29, '2024-04-30 14:00:49', 1, '2024-04-30 14:00:49', 1, 'sys_user', '用户表', NULL, 0);
INSERT INTO `sys_gen_table` VALUES (32, '2024-05-05 14:29:52', 1, '2024-05-05 14:29:52', 1, 'sys_department', '部门表', '', 0);
INSERT INTO `sys_gen_table` VALUES (33, '2024-05-05 22:38:49', 1, '2024-05-05 22:38:49', 1, 'sys_role_menu_rel', '角色菜单关联表', NULL, 0);
INSERT INTO `sys_gen_table` VALUES (34, '2024-05-05 22:38:49', 1, '2024-05-05 22:38:49', 1, 'sys_role_user_rel', '角色用户关联', NULL, 0);
INSERT INTO `sys_gen_table` VALUES (35, '2024-05-06 20:55:00', 1, '2024-05-06 20:55:00', 1, 'sys_role', '角色表', '', 0);

-- ----------------------------
-- Table structure for sys_gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `sys_gen_table_column`;
CREATE TABLE `sys_gen_table_column`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `table_id` bigint NOT NULL COMMENT '表ID',
  `column_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字段名',
  `column_comment` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字段描述',
  `column_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字段类型',
  `length` smallint NULL DEFAULT NULL COMMENT '字段长度',
  `point` smallint NULL DEFAULT 0 COMMENT '小数点',
  `is_primary_key` tinyint(1) NULL DEFAULT 0 COMMENT '该字段是否是主键',
  `is_not_null` tinyint(1) NULL DEFAULT 0 COMMENT '是否不为NULL',
  `default_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '字段默认值',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `java_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'java类型',
  `is_query` tinyint NULL DEFAULT 0 COMMENT '是否查询字段',
  `is_unique` tinyint(1) NULL DEFAULT 0 COMMENT '是否唯一不重复的',
  `sort` int NULL DEFAULT 1 COMMENT '显示排序',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否逻辑删除（0=否；1=是）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 294 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '代码生成-数据库表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_gen_table_column
-- ----------------------------
INSERT INTO `sys_gen_table_column` VALUES (1, '2024-04-28 01:48:14', 1, '2024-04-29 20:01:27', 1, 2, 'id', '主键', 'bigint', 20, NULL, 1, 1, '', '', 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (2, '2024-04-28 22:31:01', 1, '2024-04-29 02:08:42', 1, 2, 'create_time', '创建时间', 'datetime', 0, NULL, 0, 0, '', '', 'LocalDateTime', 0, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (3, '2024-04-28 22:35:50', 1, '2024-04-29 20:01:02', 1, 2, 'create_by', '创建人ID', 'bigint', 20, NULL, 0, 0, '', '', 'String', 0, 0, 3, 0);
INSERT INTO `sys_gen_table_column` VALUES (4, '2024-04-28 22:36:06', 1, '2024-04-29 02:08:48', 1, 2, 'update_time', '更新时间', 'datetime', 0, NULL, 0, 0, '', '', 'LocalDateTime', 0, 0, 4, 0);
INSERT INTO `sys_gen_table_column` VALUES (5, '2024-04-28 22:38:15', 1, '2024-05-10 17:04:15', 1, 2, 'update_by', '更新人ID', 'bigint', 20, NULL, 0, 0, '', '', 'String', 0, 0, 5, 0);
INSERT INTO `sys_gen_table_column` VALUES (6, '2024-04-28 22:39:20', 1, '2024-05-10 17:04:16', 1, 2, 'dict_code', '字典编码', 'varchar', 100, NULL, 0, 0, '', '', 'String', 1, 0, 6, 0);
INSERT INTO `sys_gen_table_column` VALUES (7, '2024-04-28 22:40:39', 1, '2024-05-04 10:59:04', 1, 2, 'dict_text', '字典文本', 'varchar', 255, NULL, 0, 1, '', '', 'String', 1, 0, 9, 0);
INSERT INTO `sys_gen_table_column` VALUES (8, '2024-04-28 22:41:40', 1, '2024-05-04 10:59:05', 1, 2, 'dict_value', '字典键值', 'varchar', 100, NULL, 0, 1, '', '', 'String', 1, 0, 8, 0);
INSERT INTO `sys_gen_table_column` VALUES (9, '2024-04-28 22:52:59', 1, '2024-05-04 11:00:21', 1, 2, 'is_default', '是否默认值', 'tinyint', 1, NULL, 0, 0, '0', '0=不是；1=是', 'Integer', 0, 0, 11, 0);
INSERT INTO `sys_gen_table_column` VALUES (10, '2024-04-28 22:56:13', 1, '2024-05-10 17:04:10', 1, 2, 'remark', '备注', 'varchar', 500, NULL, 0, 0, '', '', 'String', 0, 0, 12, 0);
INSERT INTO `sys_gen_table_column` VALUES (11, '2024-04-28 22:56:59', 1, '2024-05-10 17:04:10', 1, 2, 'is_deleted', '是否逻辑删除', 'tinyint', 1, NULL, 0, 0, '0', '0=不是；1=是', 'Integer', 0, 0, 13, 0);
INSERT INTO `sys_gen_table_column` VALUES (155, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'id', '主键', 'bigint', 0, 0, 1, 1, NULL, NULL, 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (156, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'create_time', '创建时间', 'datetime', 0, 0, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (157, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'create_by', '创建人ID', 'bigint', 0, 0, 0, 0, NULL, NULL, 'String', 0, 0, 3, 0);
INSERT INTO `sys_gen_table_column` VALUES (158, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'update_time', '更新时间', 'datetime', 0, 0, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 4, 0);
INSERT INTO `sys_gen_table_column` VALUES (159, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'update_by', '更新人ID', 'bigint', 0, 0, 0, 0, NULL, NULL, 'String', 0, 0, 5, 0);
INSERT INTO `sys_gen_table_column` VALUES (160, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'table_name', '表名', 'varchar', 50, 0, 0, 1, NULL, NULL, 'String', 0, 0, 6, 0);
INSERT INTO `sys_gen_table_column` VALUES (161, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'table_comment', '表描述', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 0, 0, 7, 0);
INSERT INTO `sys_gen_table_column` VALUES (162, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'remark', '备注', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 0, 0, 8, 0);
INSERT INTO `sys_gen_table_column` VALUES (163, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 23, 'is_deleted', '是否逻辑删除（0=否；1=是）', 'tinyint', 1, 0, 0, 1, '0', NULL, 'Integer', 0, 0, 9, 0);
INSERT INTO `sys_gen_table_column` VALUES (164, '2024-04-30 14:00:45', 1, '2024-04-30 14:00:45', 1, 24, 'id', '主键', 'bigint', 0, 0, 1, 1, NULL, NULL, 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (165, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'create_time', '创建时间', 'datetime', 0, 0, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (166, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'create_by', '创建人ID', 'bigint', 0, 0, 0, 0, NULL, NULL, 'String', 0, 0, 3, 0);
INSERT INTO `sys_gen_table_column` VALUES (167, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'update_time', '更新时间', 'datetime', 0, 0, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 4, 0);
INSERT INTO `sys_gen_table_column` VALUES (168, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'update_by', '更新人ID', 'bigint', 0, 0, 0, 0, NULL, NULL, 'String', 0, 0, 5, 0);
INSERT INTO `sys_gen_table_column` VALUES (169, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'table_id', '表ID', 'bigint', 0, 0, 0, 1, NULL, NULL, 'String', 0, 0, 6, 0);
INSERT INTO `sys_gen_table_column` VALUES (170, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'column_name', '字段名', 'varchar', 50, 0, 0, 1, NULL, NULL, 'String', 0, 0, 7, 0);
INSERT INTO `sys_gen_table_column` VALUES (171, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'column_comment', '字段描述', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 0, 0, 8, 0);
INSERT INTO `sys_gen_table_column` VALUES (172, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'column_type', '字段类型', 'varchar', 50, 0, 0, 1, NULL, NULL, 'String', 0, 0, 9, 0);
INSERT INTO `sys_gen_table_column` VALUES (173, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'length', '字段长度', 'smallint', 0, 0, 0, 0, NULL, NULL, 'Integer', 0, 0, 10, 0);
INSERT INTO `sys_gen_table_column` VALUES (174, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'point', '小数点', 'smallint', 0, 0, 0, 0, '0', NULL, 'Integer', 0, 0, 11, 0);
INSERT INTO `sys_gen_table_column` VALUES (175, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'is_primary_key', '该字段是否是主键', 'tinyint', 1, 0, 0, 0, '0', NULL, 'Integer', 0, 0, 12, 0);
INSERT INTO `sys_gen_table_column` VALUES (176, '2024-04-30 14:00:46', 1, '2024-04-30 14:00:46', 1, 24, 'is_not_null', '是否不为NULL', 'tinyint', 1, 0, 0, 0, '0', NULL, 'Integer', 0, 0, 13, 0);
INSERT INTO `sys_gen_table_column` VALUES (177, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 24, 'default_value', '字段默认值', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 0, 0, 14, 0);
INSERT INTO `sys_gen_table_column` VALUES (178, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 24, 'remark', '备注', 'varchar', 500, 0, 0, 0, NULL, NULL, 'String', 0, 0, 15, 0);
INSERT INTO `sys_gen_table_column` VALUES (179, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 24, 'java_type', 'java类型', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 0, 0, 16, 0);
INSERT INTO `sys_gen_table_column` VALUES (180, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 24, 'is_query', '是否查询字段', 'tinyint', 0, 0, 0, 0, '0', NULL, 'Integer', 0, 0, 17, 0);
INSERT INTO `sys_gen_table_column` VALUES (181, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 24, 'is_unique', '是否唯一不重复的', 'tinyint', 1, 0, 0, 0, '0', NULL, 'Integer', 0, 0, 18, 0);
INSERT INTO `sys_gen_table_column` VALUES (182, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 24, 'sort', '显示排序', 'int', 0, 0, 0, 0, '1', NULL, 'Integer', 0, 0, 19, 0);
INSERT INTO `sys_gen_table_column` VALUES (183, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 24, 'is_deleted', '是否逻辑删除（0=否；1=是）', 'tinyint', 1, 0, 0, 0, '0', NULL, 'Integer', 0, 0, 20, 0);
INSERT INTO `sys_gen_table_column` VALUES (184, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 25, 'id', '主键', 'smallint', 0, 0, 1, 1, NULL, NULL, 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (185, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 25, 'parent_id', '父级节点id', 'smallint', 0, 0, 0, 1, '0', NULL, 'String', 0, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (186, '2024-04-30 14:00:47', 1, '2024-04-30 14:00:47', 1, 25, 'name', '菜单名称', 'varchar', 50, 0, 0, 1, NULL, NULL, 'String', 0, 0, 3, 0);
INSERT INTO `sys_gen_table_column` VALUES (187, '2024-04-30 14:00:48', 1, '2024-04-30 14:00:48', 1, 25, 'url', '菜单链接', 'varchar', 255, 0, 0, 0, '', NULL, 'String', 0, 0, 4, 0);
INSERT INTO `sys_gen_table_column` VALUES (188, '2024-04-30 14:00:48', 1, '2024-04-30 14:00:48', 1, 25, 'sort', '显示顺序', 'smallint', 0, 0, 0, 1, '1', NULL, 'Integer', 0, 0, 5, 0);
INSERT INTO `sys_gen_table_column` VALUES (189, '2024-04-30 14:00:48', 1, '2024-04-30 14:00:48', 1, 25, 'icon', '图标', 'varchar', 50, 0, 0, 0, '', NULL, 'String', 0, 0, 6, 0);
INSERT INTO `sys_gen_table_column` VALUES (190, '2024-04-30 14:00:48', 1, '2024-04-30 14:00:48', 1, 25, 'perm_code', '按钮权限标识', 'varchar', 50, 0, 0, 0, '', NULL, 'String', 0, 0, 7, 0);
INSERT INTO `sys_gen_table_column` VALUES (191, '2024-04-30 14:00:48', 1, '2024-04-30 14:00:48', 1, 25, 'type', '菜单类型', 'varchar', 50, 0, 0, 0, '', NULL, 'String', 0, 0, 8, 0);
INSERT INTO `sys_gen_table_column` VALUES (201, '2024-04-30 14:00:49', 1, '2024-04-30 14:00:49', 1, 29, 'id', '主键', 'bigint', 0, 0, 1, 1, NULL, NULL, 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (202, '2024-04-30 14:00:49', 1, '2024-05-06 00:55:22', 1, 29, 'create_time', '创建时间', 'datetime', 0, 0, 0, 0, NULL, NULL, 'LocalDateTime', 1, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (203, '2024-04-30 14:00:49', 1, '2024-04-30 14:00:49', 1, 29, 'create_by', '创建人ID', 'bigint', 0, 0, 0, 0, NULL, NULL, 'String', 0, 0, 3, 0);
INSERT INTO `sys_gen_table_column` VALUES (204, '2024-04-30 14:00:49', 1, '2024-04-30 14:00:49', 1, 29, 'update_time', '更新时间', 'datetime', 0, 0, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 4, 0);
INSERT INTO `sys_gen_table_column` VALUES (205, '2024-04-30 14:00:49', 1, '2024-04-30 14:00:49', 1, 29, 'update_by', '更新人ID', 'bigint', 0, 0, 0, 0, NULL, NULL, 'String', 0, 0, 5, 0);
INSERT INTO `sys_gen_table_column` VALUES (206, '2024-04-30 14:00:50', 1, '2024-05-06 00:55:25', 1, 29, 'username', '用户名（登录账号）', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 1, 1, 6, 0);
INSERT INTO `sys_gen_table_column` VALUES (207, '2024-04-30 14:00:50', 1, '2024-04-30 14:00:50', 1, 29, 'password', '密码', 'varchar', 50, 0, 0, 1, NULL, NULL, 'String', 0, 0, 7, 0);
INSERT INTO `sys_gen_table_column` VALUES (208, '2024-04-30 14:00:50', 1, '2024-05-06 00:55:28', 1, 29, 'nickname', '用户昵称', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 1, 0, 8, 0);
INSERT INTO `sys_gen_table_column` VALUES (209, '2024-04-30 14:00:50', 1, '2024-05-06 00:55:52', 1, 29, 'phone', '手机号', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 1, 0, 9, 0);
INSERT INTO `sys_gen_table_column` VALUES (210, '2024-04-30 14:00:50', 1, '2024-05-06 00:55:53', 1, 29, 'email', '邮箱', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 1, 0, 10, 0);
INSERT INTO `sys_gen_table_column` VALUES (211, '2024-04-30 14:00:50', 1, '2024-05-09 16:42:31', 1, 29, 'sex', '性别（0=男；1=女；2=未知）', 'smallint', 6, 0, 0, 0, '2', '', 'Integer', 1, 0, 11, 0);
INSERT INTO `sys_gen_table_column` VALUES (212, '2024-04-30 14:00:50', 1, '2024-04-30 14:00:50', 1, 29, 'avatar', '头像', 'varchar', 255, 0, 0, 0, NULL, NULL, 'String', 0, 0, 12, 0);
INSERT INTO `sys_gen_table_column` VALUES (213, '2024-04-30 14:00:50', 1, '2024-04-30 14:00:50', 1, 29, 'department_id', '部门ID', 'bigint', 0, 0, 0, 0, NULL, NULL, 'String', 0, 0, 13, 0);
INSERT INTO `sys_gen_table_column` VALUES (214, '2024-04-30 14:00:50', 1, '2024-05-06 00:55:38', 1, 29, 'status', '用户状态（0=正常；1=停用）', 'tinyint', 1, 0, 0, 0, '0', NULL, 'Integer', 1, 0, 14, 0);
INSERT INTO `sys_gen_table_column` VALUES (215, '2024-04-30 14:00:50', 1, '2024-04-30 14:00:50', 1, 29, 'last_login_ip', '最后登录IP', 'varchar', 50, 0, 0, 0, NULL, NULL, 'String', 0, 0, 15, 0);
INSERT INTO `sys_gen_table_column` VALUES (216, '2024-04-30 14:00:50', 1, '2024-04-30 14:00:50', 1, 29, 'last_login_date', '最后登录时间', 'datetime', 0, 0, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 16, 0);
INSERT INTO `sys_gen_table_column` VALUES (217, '2024-04-30 14:00:50', 1, '2024-04-30 14:00:50', 1, 29, 'remark', '备注', 'varchar', 500, 0, 0, 0, NULL, NULL, 'String', 0, 0, 17, 0);
INSERT INTO `sys_gen_table_column` VALUES (232, '2024-05-04 10:58:55', 1, '2024-05-04 11:05:46', 1, 2, 'dict_code_comment', '字典编码描述', 'varchar', 100, NULL, 0, 0, '', '', 'String', 1, 0, 7, 0);
INSERT INTO `sys_gen_table_column` VALUES (233, '2024-05-04 10:59:51', 1, '2024-05-04 11:00:21', 1, 2, 'sort', '显示顺序', 'int', 11, NULL, 0, 0, '', '', 'Integer', 0, 0, 10, 0);
INSERT INTO `sys_gen_table_column` VALUES (234, '2024-05-05 14:29:53', 1, '2024-05-05 14:29:53', 1, 32, 'id', '主键', 'bigint', 20, NULL, 1, 1, NULL, NULL, 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (235, '2024-05-05 14:29:53', 1, '2024-05-05 14:29:53', 1, 32, 'create_time', '创建时间', 'datetime', 0, NULL, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (236, '2024-05-05 14:29:53', 1, '2024-05-05 14:29:53', 1, 32, 'create_by', '创建人ID', 'bigint', 20, NULL, 0, 0, NULL, NULL, 'String', 0, 0, 3, 0);
INSERT INTO `sys_gen_table_column` VALUES (237, '2024-05-05 14:29:53', 1, '2024-05-05 14:29:53', 1, 32, 'update_time', '更新时间', 'datetime', 0, NULL, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 4, 0);
INSERT INTO `sys_gen_table_column` VALUES (238, '2024-05-05 14:29:53', 1, '2024-05-05 14:29:53', 1, 32, 'update_by', '更新人ID', 'bigint', 20, NULL, 0, 0, NULL, NULL, 'String', 0, 0, 5, 0);
INSERT INTO `sys_gen_table_column` VALUES (239, '2024-05-05 14:31:41', 1, '2024-05-05 14:32:11', 1, 32, 'parent_id', '父部门id', 'bigint', 20, NULL, 0, 0, '0', '为0时，表示顶级部门', 'String', 0, 0, 6, 0);
INSERT INTO `sys_gen_table_column` VALUES (240, '2024-05-05 14:35:02', 1, '2024-05-05 15:06:55', 1, 32, 'department_name', '部门名称', 'varchar', 100, NULL, 0, 1, '', '', 'String', 1, 0, 7, 0);
INSERT INTO `sys_gen_table_column` VALUES (241, '2024-05-05 14:35:49', 1, '2024-05-05 14:36:27', 1, 32, 'department_leader', '部门负责人', 'varchar', 50, NULL, 0, 0, '', '', 'String', 0, 0, 8, 0);
INSERT INTO `sys_gen_table_column` VALUES (242, '2024-05-05 14:36:44', 1, '2024-05-05 14:36:44', 1, 32, 'department_phone', '部门联系电话', 'varchar', 50, NULL, 0, 0, '', '', 'String', 0, 0, 9, 0);
INSERT INTO `sys_gen_table_column` VALUES (243, '2024-05-05 14:36:57', 1, '2024-05-05 14:36:57', 1, 32, 'department_email', '部门邮箱', 'varchar', 50, NULL, 0, 0, '', '', 'String', 0, 0, 10, 0);
INSERT INTO `sys_gen_table_column` VALUES (244, '2024-05-05 14:37:24', 1, '2024-05-05 14:37:28', 1, 32, 'sort', '显示顺序', 'int', 11, NULL, 0, 1, '', '', 'Integer', 0, 0, 11, 0);
INSERT INTO `sys_gen_table_column` VALUES (245, '2024-05-05 14:38:04', 1, '2024-05-05 14:38:15', 1, 32, 'is_deleted', '是否逻辑删除', 'tinyint', 1, NULL, 0, 0, '0', '0=正常；1=停用', 'Integer', 1, 0, 12, 0);
INSERT INTO `sys_gen_table_column` VALUES (246, '2024-05-05 22:38:49', 1, '2024-05-05 22:38:49', 1, 33, 'role_id', '角色id', 'bigint', 0, 0, 0, 1, NULL, NULL, 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (247, '2024-05-05 22:38:49', 1, '2024-05-05 22:38:49', 1, 33, 'menu_id', '菜单id', 'bigint', 0, 0, 0, 1, NULL, NULL, 'String', 0, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (248, '2024-05-05 22:38:49', 1, '2024-05-05 22:38:49', 1, 34, 'role_id', '角色id', 'bigint', 0, 0, 0, 1, NULL, NULL, 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (249, '2024-05-05 22:38:49', 1, '2024-05-05 22:38:49', 1, 34, 'user_id', '用户id', 'bigint', 0, 0, 0, 1, NULL, NULL, 'String', 0, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (250, '2024-05-06 20:55:01', 1, '2024-05-06 20:55:01', 1, 35, 'id', '主键', 'bigint', 20, NULL, 1, 1, NULL, NULL, 'String', 0, 0, 1, 0);
INSERT INTO `sys_gen_table_column` VALUES (251, '2024-05-06 20:55:01', 1, '2024-05-06 20:55:01', 1, 35, 'create_time', '创建时间', 'datetime', 0, NULL, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 2, 0);
INSERT INTO `sys_gen_table_column` VALUES (252, '2024-05-06 20:55:01', 1, '2024-05-06 20:55:01', 1, 35, 'create_by', '创建人ID', 'bigint', 20, NULL, 0, 0, NULL, NULL, 'String', 0, 0, 3, 0);
INSERT INTO `sys_gen_table_column` VALUES (253, '2024-05-06 20:55:01', 1, '2024-05-06 20:55:01', 1, 35, 'update_time', '更新时间', 'datetime', 0, NULL, 0, 0, NULL, NULL, 'LocalDateTime', 0, 0, 4, 0);
INSERT INTO `sys_gen_table_column` VALUES (254, '2024-05-06 20:55:01', 1, '2024-05-06 20:55:01', 1, 35, 'update_by', '更新人ID', 'bigint', 20, NULL, 0, 0, NULL, NULL, 'String', 0, 0, 5, 0);
INSERT INTO `sys_gen_table_column` VALUES (255, '2024-05-06 20:55:20', 1, '2024-05-06 20:56:15', 1, 35, 'role_name', '角色名称', 'varchar', 50, NULL, 0, 0, '', '', 'String', 1, 0, 6, 0);
INSERT INTO `sys_gen_table_column` VALUES (256, '2024-05-06 20:55:32', 1, '2024-05-06 20:56:18', 1, 35, 'role_code', '角色标识', 'varchar', 50, NULL, 0, 0, '', '', 'String', 1, 1, 7, 0);
INSERT INTO `sys_gen_table_column` VALUES (257, '2024-05-06 20:55:58', 1, '2024-05-06 20:57:28', 1, 35, 'sort', '显示顺序', 'int', 11, NULL, 0, 0, '1', '', 'Integer', 0, 0, 9, 0);
INSERT INTO `sys_gen_table_column` VALUES (258, '2024-05-06 20:57:23', 1, '2024-05-08 22:52:41', 1, 35, 'data_scope', '数据范围（1：全部数据权限；2：本部门及以下数据权限；3：本部门数据权限；4：仅自己数据权限）', 'int', 11, NULL, 0, 0, '', '1：全部数据权限\r\n2：本部门及以下数据权限\r\n3：本部门数据权限\r\n4：仅自己数据权限', 'Integer', 0, 0, 8, 0);
INSERT INTO `sys_gen_table_column` VALUES (259, '2024-05-06 20:59:18', 1, '2024-05-06 20:59:18', 1, 25, 'create_time', '创建时间', 'datetime', 0, NULL, 0, 0, '', '', 'LocalDateTime', 0, 0, 9, 0);
INSERT INTO `sys_gen_table_column` VALUES (260, '2024-05-06 20:59:30', 1, '2024-05-06 20:59:30', 1, 25, 'create_by', '创建人ID', 'bigint', 20, NULL, 0, 0, '', '', 'String', 0, 0, 10, 0);
INSERT INTO `sys_gen_table_column` VALUES (261, '2024-05-06 20:59:48', 1, '2024-05-06 20:59:48', 1, 25, 'update_time', '更新时间', 'datetime', 0, NULL, 0, 0, '', '', 'LocalDateTime', 0, 0, 11, 0);
INSERT INTO `sys_gen_table_column` VALUES (262, '2024-05-06 20:59:59', 1, '2024-05-06 20:59:59', 1, 25, 'update_by', '更新人ID', 'bigint', 20, NULL, 0, 0, '', '', 'String', 0, 0, 12, 0);
INSERT INTO `sys_gen_table_column` VALUES (292, '2024-05-13 13:04:30', 1, '2024-05-13 13:04:30', 1, 25, 'is_hidden', '是否隐藏', 'tinyint', 1, NULL, 0, 0, '0', '', 'Integer', 0, 0, 13, 0);
INSERT INTO `sys_gen_table_column` VALUES (293, '2024-05-13 15:56:11', 1, '2024-05-13 15:56:11', 1, 25, 'is_system', '是否系统内置菜单', 'tinyint', 1, NULL, 0, 0, '0', '', 'Integer', 0, 0, 14, 0);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` smallint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` smallint NOT NULL DEFAULT 0 COMMENT '父级节点id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单链接',
  `sort` smallint NOT NULL DEFAULT 1 COMMENT '显示顺序',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图标',
  `perm_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '按钮权限标识',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `is_hidden` tinyint(1) NULL DEFAULT 0 COMMENT '是否隐藏',
  `is_system` tinyint(1) NULL DEFAULT 0 COMMENT '是否系统内置菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 298 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (4, 237, '菜单管理', '/sys/menu/page/list', 2, NULL, NULL, '菜单', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (56, 0, '系统', '/system', 100, NULL, NULL, '导航', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (119, 237, '角色管理', '/sys/role/page/list', 3, NULL, NULL, '菜单', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (124, 237, '用户管理', '/sys/user/page/list', 1, NULL, NULL, '菜单', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (237, 56, '系统管理', '', 1, NULL, NULL, '目录', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (238, 56, '系统工具', '', 2, NULL, NULL, '目录', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (239, 238, '代码生成', '/sys/gen/table/page/list', 1, NULL, NULL, '菜单', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (240, 237, '字典管理', '/sys/dict/data/page/list', 5, NULL, NULL, '菜单', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (248, 237, '部门管理', '/sys/department/page/list', 4, NULL, NULL, '菜单', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (250, 4, '菜单查询', NULL, 1, NULL, '/sys/menu/list-all', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (251, 4, '菜单新增', NULL, 2, NULL, '/sys/menu/add', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (252, 4, '菜单更新', NULL, 3, NULL, '/sys/menu/update', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (253, 4, '菜单删除', NULL, 4, NULL, '/sys/menu/delete', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (254, 119, '角色查询', NULL, 1, NULL, '/sys/role/list', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (255, 119, '角色新增', NULL, 2, NULL, '/sys/role/add', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (256, 119, '角色更新', NULL, 3, NULL, '/sys/role/update', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (257, 119, '角色删除', NULL, 4, NULL, '/sys/role/delete', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (258, 248, '部门查询', NULL, 1, NULL, '/sys/department/list-all', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (259, 248, '部门新增', NULL, 2, NULL, '/sys/department/add', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (260, 248, '部门更新', NULL, 3, NULL, '/sys/department/update', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (261, 248, '部门删除', NULL, 4, NULL, '/sys/department/delete', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (262, 240, '字典查询', NULL, 1, NULL, '/sys/dict/data/list', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (263, 240, '字典新增', NULL, 2, NULL, '/sys/dict/data/add', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (264, 240, '字典更新', NULL, 3, NULL, '/sys/dict/data/update', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (265, 240, '字典删除', NULL, 4, NULL, '/sys/dict/data/delete', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (266, 239, '同步到数据库', NULL, 1, NULL, '/sys/gen/table/synchrony-to-db', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (275, 239, '代码生成查询', NULL, 2, NULL, '/sys/gen/table/list', '按钮', NULL, NULL, '2024-05-15 19:47:38', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (276, 124, '用户查询', NULL, 1, NULL, '/sys/user/list', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (277, 124, '用户新增', NULL, 2, NULL, '/sys/user/add', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (278, 124, '用户更新', NULL, 3, NULL, '/sys/user/update', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (279, 124, '用户删除', NULL, 4, NULL, '/sys/user/delete', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (280, 119, '向角色添加用户', NULL, 5, NULL, '/sys/role-user/add/user', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (281, 119, '将用户从角色移除', NULL, 6, NULL, '/sys/role-user/delete/user', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (282, 119, '将用户从角色批量移除', NULL, 7, NULL, '/sys/role-user/batch-delete/user', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (284, 119, '保存角色-菜单配置', NULL, 8, NULL, '/sys/role-menu/save', '按钮', NULL, NULL, '2024-05-15 19:47:37', 1, 0, 1);
INSERT INTO `sys_menu` VALUES (296, 0, '首页', '/init', 1, NULL, NULL, '导航', '2024-05-15 19:47:33', 1, '2024-05-15 19:47:38', 1, 0, 0);
INSERT INTO `sys_menu` VALUES (297, 296, '主页', '/home', 1, NULL, NULL, '菜单', '2024-05-15 19:48:02', 1, '2024-05-15 19:48:21', 1, 0, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色标识',
  `sort` smallint NOT NULL DEFAULT 1 COMMENT '显示顺序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `data_scope` int NULL DEFAULT NULL COMMENT '数据范围（1：全部数据权限；2：本部门及以下数据权限；3：本部门数据权限；4：仅自己数据权限）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统开发管理员', 'super_admin', 1, NULL, NULL, '2024-05-06 21:14:08', 1, 1);
INSERT INTO `sys_role` VALUES (2, '年级组长', 'grade _leader', 3, NULL, NULL, '2024-05-15 19:49:40', 1, 2);
INSERT INTO `sys_role` VALUES (3, '班主任', 'class_teacher', 4, '2024-05-09 14:15:50', 1, '2024-05-15 19:49:39', 1, 3);
INSERT INTO `sys_role` VALUES (5, '校长', 'principal', 2, '2024-05-15 19:49:33', 1, '2024-05-15 19:49:40', 1, 1);

-- ----------------------------
-- Table structure for sys_role_menu_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_rel`;
CREATE TABLE `sys_role_menu_rel`  (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `menu_id` bigint NOT NULL COMMENT '菜单id'
) ENGINE = InnoDB AUTO_INCREMENT = 786 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu_rel
-- ----------------------------
INSERT INTO `sys_role_menu_rel` VALUES (1, 296);
INSERT INTO `sys_role_menu_rel` VALUES (1, 297);
INSERT INTO `sys_role_menu_rel` VALUES (1, 56);
INSERT INTO `sys_role_menu_rel` VALUES (1, 237);
INSERT INTO `sys_role_menu_rel` VALUES (1, 124);
INSERT INTO `sys_role_menu_rel` VALUES (1, 276);
INSERT INTO `sys_role_menu_rel` VALUES (1, 277);
INSERT INTO `sys_role_menu_rel` VALUES (1, 278);
INSERT INTO `sys_role_menu_rel` VALUES (1, 279);
INSERT INTO `sys_role_menu_rel` VALUES (1, 4);
INSERT INTO `sys_role_menu_rel` VALUES (1, 250);
INSERT INTO `sys_role_menu_rel` VALUES (1, 251);
INSERT INTO `sys_role_menu_rel` VALUES (1, 252);
INSERT INTO `sys_role_menu_rel` VALUES (1, 253);
INSERT INTO `sys_role_menu_rel` VALUES (1, 119);
INSERT INTO `sys_role_menu_rel` VALUES (1, 254);
INSERT INTO `sys_role_menu_rel` VALUES (1, 255);
INSERT INTO `sys_role_menu_rel` VALUES (1, 256);
INSERT INTO `sys_role_menu_rel` VALUES (1, 257);
INSERT INTO `sys_role_menu_rel` VALUES (1, 280);
INSERT INTO `sys_role_menu_rel` VALUES (1, 281);
INSERT INTO `sys_role_menu_rel` VALUES (1, 282);
INSERT INTO `sys_role_menu_rel` VALUES (1, 284);
INSERT INTO `sys_role_menu_rel` VALUES (1, 248);
INSERT INTO `sys_role_menu_rel` VALUES (1, 258);
INSERT INTO `sys_role_menu_rel` VALUES (1, 259);
INSERT INTO `sys_role_menu_rel` VALUES (1, 260);
INSERT INTO `sys_role_menu_rel` VALUES (1, 261);
INSERT INTO `sys_role_menu_rel` VALUES (1, 240);
INSERT INTO `sys_role_menu_rel` VALUES (1, 262);
INSERT INTO `sys_role_menu_rel` VALUES (1, 263);
INSERT INTO `sys_role_menu_rel` VALUES (1, 264);
INSERT INTO `sys_role_menu_rel` VALUES (1, 265);
INSERT INTO `sys_role_menu_rel` VALUES (1, 238);
INSERT INTO `sys_role_menu_rel` VALUES (1, 239);
INSERT INTO `sys_role_menu_rel` VALUES (1, 266);
INSERT INTO `sys_role_menu_rel` VALUES (1, 275);
INSERT INTO `sys_role_menu_rel` VALUES (2, 296);
INSERT INTO `sys_role_menu_rel` VALUES (2, 297);
INSERT INTO `sys_role_menu_rel` VALUES (2, 56);
INSERT INTO `sys_role_menu_rel` VALUES (2, 237);
INSERT INTO `sys_role_menu_rel` VALUES (2, 124);
INSERT INTO `sys_role_menu_rel` VALUES (2, 276);
INSERT INTO `sys_role_menu_rel` VALUES (2, 277);
INSERT INTO `sys_role_menu_rel` VALUES (2, 4);
INSERT INTO `sys_role_menu_rel` VALUES (2, 250);
INSERT INTO `sys_role_menu_rel` VALUES (2, 251);
INSERT INTO `sys_role_menu_rel` VALUES (2, 119);
INSERT INTO `sys_role_menu_rel` VALUES (2, 254);
INSERT INTO `sys_role_menu_rel` VALUES (2, 255);
INSERT INTO `sys_role_menu_rel` VALUES (2, 248);
INSERT INTO `sys_role_menu_rel` VALUES (2, 258);
INSERT INTO `sys_role_menu_rel` VALUES (2, 259);
INSERT INTO `sys_role_menu_rel` VALUES (2, 240);
INSERT INTO `sys_role_menu_rel` VALUES (2, 262);
INSERT INTO `sys_role_menu_rel` VALUES (2, 263);
INSERT INTO `sys_role_menu_rel` VALUES (2, 264);
INSERT INTO `sys_role_menu_rel` VALUES (2, 265);
INSERT INTO `sys_role_menu_rel` VALUES (2, 238);
INSERT INTO `sys_role_menu_rel` VALUES (2, 239);
INSERT INTO `sys_role_menu_rel` VALUES (2, 275);
INSERT INTO `sys_role_menu_rel` VALUES (3, 296);
INSERT INTO `sys_role_menu_rel` VALUES (3, 297);
INSERT INTO `sys_role_menu_rel` VALUES (3, 56);
INSERT INTO `sys_role_menu_rel` VALUES (3, 237);
INSERT INTO `sys_role_menu_rel` VALUES (3, 124);
INSERT INTO `sys_role_menu_rel` VALUES (3, 276);
INSERT INTO `sys_role_menu_rel` VALUES (3, 277);
INSERT INTO `sys_role_menu_rel` VALUES (3, 278);
INSERT INTO `sys_role_menu_rel` VALUES (3, 4);
INSERT INTO `sys_role_menu_rel` VALUES (3, 250);
INSERT INTO `sys_role_menu_rel` VALUES (3, 251);
INSERT INTO `sys_role_menu_rel` VALUES (3, 119);
INSERT INTO `sys_role_menu_rel` VALUES (3, 254);
INSERT INTO `sys_role_menu_rel` VALUES (3, 255);
INSERT INTO `sys_role_menu_rel` VALUES (3, 248);
INSERT INTO `sys_role_menu_rel` VALUES (3, 258);
INSERT INTO `sys_role_menu_rel` VALUES (3, 259);
INSERT INTO `sys_role_menu_rel` VALUES (3, 240);
INSERT INTO `sys_role_menu_rel` VALUES (3, 262);
INSERT INTO `sys_role_menu_rel` VALUES (3, 263);
INSERT INTO `sys_role_menu_rel` VALUES (3, 264);
INSERT INTO `sys_role_menu_rel` VALUES (3, 265);
INSERT INTO `sys_role_menu_rel` VALUES (3, 238);
INSERT INTO `sys_role_menu_rel` VALUES (3, 239);
INSERT INTO `sys_role_menu_rel` VALUES (3, 275);
INSERT INTO `sys_role_menu_rel` VALUES (5, 296);
INSERT INTO `sys_role_menu_rel` VALUES (5, 297);
INSERT INTO `sys_role_menu_rel` VALUES (5, 56);
INSERT INTO `sys_role_menu_rel` VALUES (5, 237);
INSERT INTO `sys_role_menu_rel` VALUES (5, 124);
INSERT INTO `sys_role_menu_rel` VALUES (5, 276);
INSERT INTO `sys_role_menu_rel` VALUES (5, 277);
INSERT INTO `sys_role_menu_rel` VALUES (5, 4);
INSERT INTO `sys_role_menu_rel` VALUES (5, 250);
INSERT INTO `sys_role_menu_rel` VALUES (5, 251);
INSERT INTO `sys_role_menu_rel` VALUES (5, 119);
INSERT INTO `sys_role_menu_rel` VALUES (5, 254);
INSERT INTO `sys_role_menu_rel` VALUES (5, 255);
INSERT INTO `sys_role_menu_rel` VALUES (5, 248);
INSERT INTO `sys_role_menu_rel` VALUES (5, 258);
INSERT INTO `sys_role_menu_rel` VALUES (5, 259);
INSERT INTO `sys_role_menu_rel` VALUES (5, 240);
INSERT INTO `sys_role_menu_rel` VALUES (5, 262);
INSERT INTO `sys_role_menu_rel` VALUES (5, 263);
INSERT INTO `sys_role_menu_rel` VALUES (5, 264);
INSERT INTO `sys_role_menu_rel` VALUES (5, 265);
INSERT INTO `sys_role_menu_rel` VALUES (5, 238);
INSERT INTO `sys_role_menu_rel` VALUES (5, 239);
INSERT INTO `sys_role_menu_rel` VALUES (5, 275);

-- ----------------------------
-- Table structure for sys_role_user_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user_rel`;
CREATE TABLE `sys_role_user_rel`  (
  `role_id` bigint NOT NULL COMMENT '角色id',
  `user_id` bigint NOT NULL COMMENT '用户id'
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色用户关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user_rel
-- ----------------------------
INSERT INTO `sys_role_user_rel` VALUES (2, 2);
INSERT INTO `sys_role_user_rel` VALUES (1, 1);
INSERT INTO `sys_role_user_rel` VALUES (3, 4);
INSERT INTO `sys_role_user_rel` VALUES (2, 3);
INSERT INTO `sys_role_user_rel` VALUES (5, 5);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名（登录账号）',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` smallint NULL DEFAULT 2 COMMENT '性别（0=男；1=女；2=未知）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `department_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户状态（0=正常；1=停用）',
  `last_login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后登录IP',
  `last_login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2024-04-27 20:30:26', 1, '2024-05-15 19:40:36', 1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '校长', '', '', 0, NULL, 1, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, '2024-05-06 00:07:12', 1, '2024-05-09 14:20:22', 1, 'grade1', 'e10adc3949ba59abbe56e057f20f883e', '高一年级组长', '', '', 0, NULL, 2, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, '2024-05-09 14:26:44', 1, '2024-05-09 16:07:19', 4, 'grade2', 'e10adc3949ba59abbe56e057f20f883e', '高二年级组长', '', '', 0, NULL, 6, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (4, '2024-05-09 14:27:20', 1, '2024-05-09 14:27:20', 1, 'teacher1', 'e10adc3949ba59abbe56e057f20f883e', '高一班主任', '', '', 0, NULL, 3, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (5, '2024-05-15 19:50:41', 1, '2024-05-15 19:50:41', 1, 'test', 'e10adc3949ba59abbe56e057f20f883e', '测试账号', '', '', 0, NULL, 1, 0, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
