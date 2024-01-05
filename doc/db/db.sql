SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ---------------------------------------------- 系统管理 ----------------------------------------------
-- ----------------------------
-- Table：管理员信息表
-- Desc：该表中的用户是超级管理员，拥有所有权限。或者普通管理员，拥有部分权限。
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
    `id`                INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`          VARCHAR(30) NOT NULL COMMENT '用户名',
    `name`              VARCHAR(30) NOT NULL COMMENT '姓名',
    `password`          VARCHAR(255) NOT NULL COMMENT '密码',
    `tel`               VARCHAR(20) NOT NULL COMMENT '电话',
    `email`             VARCHAR(50) NOT NULL COMMENT '邮箱',
    `status`            TINYINT NOT NULL DEFAULT 0 COMMENT '启用状态：0-启用，1-禁用',
    `create_time`       DATETIME NOT NULL COMMENT '创建时间',
    `update_time`       DATETIME NOT NULL COMMENT '更新时间',
    `last_login_time`   DATETIME NULL DEFAULT NULL COMMENT '最后登录时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '管理员信息表';

-- ----------------------------
-- Table：用户信息表
-- Desc：该表主要用于存储用户和患者信息。
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
    `id`            INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`      VARCHAR(50) NOT NULL COMMENT '账户',
    `password`      VARCHAR(255) NOT NULL COMMENT '密码',
    `name`          VARCHAR(30) NOT NULL COMMENT '用户名',
    `id_card`       VARCHAR(30) NULL DEFAULT NULL COMMENT '身份证',
    `status`        TINYINT NOT NULL DEFAULT 0 COMMENT '启用状态：0-启用，1-禁用',
    `sex`           TINYINT NOT NULL DEFAULT 1 COMMENT '性别：0-女性，1-男性',
    `birthday`      DATE NOT NULL COMMENT '生日',
    `telephone`     VARCHAR(30) NULL DEFAULT NULL COMMENT '电话',
    `email`         VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    `address`       VARCHAR(50) NULL DEFAULT NULL COMMENT '地址',
    `create_time`   DATETIME NOT NULL COMMENT '创建时间',
    `update_time`   DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '用户信息表';

-- ----------------------------
-- Table：系统角色表
-- Desc：该表主要用于存储系统角色信息。如：超级管理员、管理员、医生、护士、患者等。
-- ----------------------------
DROP TABLE IF EXISTS `system_roles`;
CREATE TABLE `system_roles` (
    `role_id`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name`     VARCHAR(50) NOT NULL COMMENT '角色名称',
    `description`   VARCHAR(255) NULL DEFAULT NULL COMMENT '角色描述',
    `create_time`   DATETIME NOT NULL COMMENT '创建时间',
    `update_time`   DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET utf8mb4 COLLATE=utf8mb4_general_ci COMMENT '系统角色表';

-- ----------------------------
-- Records of system_roles
-- ----------------------------
INSERT INTO `system_roles` (`role_id`, `role_name`, `description`, `create_time`, `update_time`)
VALUES (1, '超级管理员', '拥有所有权限', NOW(), NOW()),
       (2, '管理员', '拥有部分权限', NOW(), NOW()),
       (3, '医生', '拥有部分权限', NOW(), NOW()),
       (4, '护士', '拥有部分权限', NOW(), NOW()),
       (5, '患者', '拥有部分权限', NOW(), NOW());

-- ----------------------------
-- Table：系统资源表
-- Desc：该表主要用于存储系统资源信息。如：用户管理、角色管理、资源管理、科室管理、医生管理、护士管理、患者管理等。
-- ----------------------------
DROP TABLE IF EXISTS `system_resources`;
CREATE TABLE `system_resources` (
    `resource_id`       INT(11) NOT NULL AUTO_INCREMENT,
    `resource_name`     VARCHAR(50) NOT NULL COMMENT '资源名称',
    `type`              TINYINT NOT NULL COMMENT '资源类型',
    `url`               VARCHAR(255) NULL DEFAULT NULL COMMENT '资源URL',
    `create_time`       DATETIME NOT NULL COMMENT '创建时间',
    `update_time`       DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET utf8mb4 COLLATE=utf8mb4_general_ci COMMENT '系统资源表';

-- ----------------------------
-- Table：用户角色关系表
-- Desc：该表主要用于存储用户和角色的关系。
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relations`;
CREATE TABLE `user_role_relations` (
    `user_id`   INT(11) NOT NULL COMMENT '用户ID',
    `role_id`   INT(11) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`),
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE=utf8mb4_general_ci COMMENT '用户角色关系表';

-- ----------------------------
-- Table：角色资源关系表
-- Desc：该表主要用于存储角色和资源的关系。
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_relations`;
CREATE TABLE `role_resource_relations` (
    `role_id`       INT(11) NOT NULL COMMENT '角色ID',
    `resource_id`   INT(11) NOT NULL COMMENT '资源ID',
    PRIMARY KEY (`role_id`, `resource_id`),
) ENGINE=InnoDB CHARACTER SET utf8mb4 COLLATE=utf8mb4_general_ci COMMENT '角色资源关系表';

-- ---------------------------------------------- 医院员工管理 ----------------------------------------------
-- ----------------------------
-- Table：职位信息表
-- Desc：该表主要用于存储职位信息。如：医生、护士、药剂师、放射科技师、实验室技师、营养师、物理治疗师、行政人员、医疗设备工程师、医疗记录员、病房协调员、清洁工、保安等。
-- ----------------------------
DROP TABLE IF EXISTS `position_info`;
CREATE TABLE `position_info` (
    `position_id`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '职位id',
    `position_name`     VARCHAR(50) NOT NULL COMMENT '职位名称',
    `description`       VARCHAR(255) NULL DEFAULT NULL COMMENT '职位描述',
    `create_time`       DATETIME NOT NULL COMMENT '创建时间',
    `update_time`       DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET utf8mb4 COLLATE=utf8mb4_general_ci COMMENT '职位信息表';

-- ----------------------------
-- Records of position_info
-- ----------------------------
INSERT INTO `position_info` (`position_name`, `description`, `create_time`, `update_time`)
VALUES  ('医生', '负责病人的诊断和治疗', NOW(), NOW()),
        ('护士', '负责病人的护理和康复', NOW(), NOW()),
        ('药剂师', '负责药品的配发和咨询', NOW(), NOW()),
        ('放射科技师', '负责放射影像的拍摄和解读', NOW(), NOW()),
        ('实验室技师', '负责实验室检测和分析', NOW(), NOW()),
        ('营养师', '负责病人的营养评估和膳食指导', NOW(), NOW()),
        ('物理治疗师', '负责病人的康复训练和物理治疗', NOW(), NOW()),
        ('行政人员', '负责医院的行政管理和服务', NOW(), NOW()),
        ('医疗设备工程师', '负责医疗设备的维护和修理', NOW(), NOW()),
        ('医疗记录员', '负责病人的医疗记录管理', NOW(), NOW()),
        ('病房协调员', '负责病房的管理和协调', NOW(), NOW()),
        ('清洁工', '负责医院的清洁和卫生', NOW(), NOW()),
        ('保安', '负责医院的安全和秩序维护', NOW(), NOW());

-- ----------------------------
-- Table：医生信息表
-- Desc：该表主要用于存储医生信息。
-- ----------------------------
DROP TABLE IF EXISTS `doctor_info`;
CREATE TABLE `doctor_info`  (
    `id`            INT(11) NOT NULL AUTO_INCREMENT COMMENT '医生id',
    `username`      VARCHAR(30) NOT NULL COMMENT '医生用户名',
    `password`      VARCHAR(255) NOT NULL COMMENT '医生密码',
    `name`          VARCHAR(30) NOT NULL COMMENT '医生名字',
    `job_number`    VARCHAR(30) NOT NULL COMMENT '医生工号',
    `department_id` INT(11) NOT NULL COMMENT '部门id',
    `position_id`   INT(11) NOT NULL COMMENT '职位id',
    `telephone`     VARCHAR(30) NULL DEFAULT NULL COMMENT '电话',
    `email`         VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    `sex`           TINYINT NOT NULL DEFAULT 1 COMMENT '性别：0-女性，1-男性',
    `birthday`      DATE NULL DEFAULT NULL COMMENT '生日',
    `address`       VARCHAR(50) NULL DEFAULT NULL COMMENT '住址',
    `status`        TINYINT NOT NULL DEFAULT 0 COMMENT '启用状态：0-启用，1-禁用',
    `remark`        VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    `create_time`   DATETIME NOT NULL COMMENT '创建时间',
    `update_time`   DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `unique_user`(`username`),
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '医生信息表';

-- ----------------------------
-- Table：护士信息表
-- Desc：该表主要用于存储护士信息。
-- ----------------------------
DROP TABLE IF EXISTS `nurse_info`;
CREATE TABLE `nurse_info` (
    `id`                INT(11) NOT NULL AUTO_INCREMENT COMMENT '护士id',
    `username`          VARCHAR(30) NOT NULL COMMENT '护士用户名',
    `password`          VARCHAR(255) NOT NULL COMMENT '护士密码',
    `name`              VARCHAR(30) NOT NULL COMMENT '护士名字',
    `job_number`        VARCHAR(30) NOT NULL COMMENT '护士工号',
    `department_id`     INT(11) NOT NULL COMMENT '部门id',
    `position_id`       INT(11) NOT NULL COMMENT '职位id',
    `telephone`         VARCHAR(30) NULL DEFAULT NULL COMMENT '电话',
    `email`             VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    `sex`               TINYINT NOT NULL DEFAULT 1 COMMENT '性别，0为女性，1为男性',
    `birthday`          DATE NULL DEFAULT NULL COMMENT '生日',
    `address`           VARCHAR(50) NULL DEFAULT NULL COMMENT '住址',
    `status`            TINYINT NOT NULL DEFAULT 0 COMMENT '启用状态，0为启用，1为禁用',
    `remark`            VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    `create_time`       DATETIME NOT NULL COMMENT '创建时间',
    `update_time`       DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `unique_user`(`username`),
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET utf8mb4 COLLATE=utf8mb4_general_ci COMMENT '护士信息表';

-- ----------------------------
-- Table：员工信息表
-- Desc：该表主要用于存储员工信息。包括了医院除患者外的所有员工。
-- ----------------------------
DROP TABLE IF EXISTS `employee_info`;
CREATE TABLE `employee_info` (
    `id`                INT(11) NOT NULL AUTO_INCREMENT,
    `name`              VARCHAR(30) NOT NULL COMMENT '员工名字',
    `job_number`        VARCHAR(30) NOT NULL COMMENT '员工工号',
    `department_id`     INT(11) NOT NULL COMMENT '部门id',
    `position_id`       INT(11) NOT NULL COMMENT '职位id',
    `telephone`         VARCHAR(30) NULL DEFAULT NULL COMMENT '电话',
    `email`             VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    `sex`               TINYINT NOT NULL DEFAULT 1 COMMENT '性别：0-女性，1-男性',
    `birthday`          DATE NULL DEFAULT NULL COMMENT '生日',
    `address`           VARCHAR(50) NULL DEFAULT NULL COMMENT '住址',
    `status`            TINYINT NOT NULL DEFAULT 0 COMMENT '启用状态：0-启用，1-禁用',
    `create_time`       DATETIME NOT NULL COMMENT '创建时间',
    `update_time`       DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET utf8mb4 COLLATE=utf8mb4_general_ci COMMENT '员工信息表';

-- ----------------------------
-- Table：员工考勤表
-- Desc：该表主要用于存储员工考勤信息。
-- ----------------------------
DROP TABLE IF EXISTS `employee_attendance`;
CREATE TABLE `employee_attendance` (
    `attendance_id`     INT(11) NOT NULL AUTO_INCREMENT COMMENT '考勤id',
    `employee_id`       INT(11) NOT NULL COMMENT '员工id',
    `date`              DATE NOT NULL COMMENT '考勤日期',
    `status`            TINYINT NOT NULL COMMENT '考勤状态：0-正常，1-迟到，2-早退，3-旷工，4-请假，5-加班，6-出差，7-外勤，8-休息，9-节假日，10-调休，11-其他，12-已删除',
    `remark`            VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`attendance_id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 CHARACTER SET utf8mb4 COLLATE=utf8mb4_general_ci COMMENT '员工考勤表';

-- ---------------------------------------------- 排班和预约 ----------------------------------------------
-- ----------------------------
-- Table：护士排班表
-- Desc：该表主要用于存储护士排班信息。
-- ----------------------------
DROP TABLE IF EXISTS `nurse_schedule`;
CREATE TABLE `nurse_schedule`  (
    `id`            INT(11) NOT NULL AUTO_INCREMENT COMMENT '排班id',
    `nurse_id`      INT(11) NOT NULL COMMENT '护士id',
    `dept_id`       INT(11) NOT NULL COMMENT '科室id',
    `work_date`     DATE NOT NULL COMMENT '工作日期',
    `work_time`     VARCHAR(50) NOT NULL COMMENT '工作时间',
    `status`        TINYINT NOT NULL DEFAULT 0 COMMENT '工作状态：0-上班，1-休息，2-请假，3-出差，4-外勤，5-已删除',
    `remark`        VARCHAR(255) NULL DEFAULT NULL COMMENT '备注',
    `create_time`   DATETIME NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '护士排班表';

-- ----------------------------
-- Table：用户预约表
-- Desc：该表主要用于存储用户预约信息。
-- ----------------------------
DROP TABLE IF EXISTS `user_appointment`;
CREATE TABLE `user_appointment`  (
    `id`            INT(11) NOT NULL AUTO_INCREMENT,
    `consult_time`  DATE NOT NULL COMMENT '就诊时间',
    `dept_id`       INT(11) NOT NULL COMMENT '预约科室号',
    `doctor_id`     INT(11) NOT NULL COMMENT '医生id',
    `user_id`       INT(11) NOT NULL COMMENT '就诊用户id',
    `status`        TINYINT NOT NULL DEFAULT 0 COMMENT '就诊状态：0-待就诊，1-已就诊，2-已取消，3-已删除',
    `remark`        VARCHAR(255) NOT NULL COMMENT '预约描述',
    `create_time`   DATETIME NOT NULL COMMENT '预约时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '用户预约表';

-- ----------------------------
-- Table：用户挂号表
-- Desc：该表主要用于存储用户挂号信息。
-- ----------------------------
DROP TABLE IF EXISTS `user_registration`;
CREATE TABLE `user_registration`  (
    `id`            INT(11) NOT NULL AUTO_INCREMENT COMMENT '挂号id',
    `user_id`       INT(11) NOT NULL COMMENT '用户id',
    `doctor_id`     INT(11) NOT NULL COMMENT '医生id',
    `dept_id`       INT(11) NOT NULL COMMENT '科室id',
    `register_date` DATE NOT NULL COMMENT '挂号日期',
    `register_time` VARCHAR(50) NOT NULL COMMENT '挂号时间',
    `price`         DECIMAL(10, 2) NOT NULL COMMENT '挂号费用',
    `status`        TINYINT NOT NULL DEFAULT 0 COMMENT '挂号状态：0-待挂号，1-已挂号，2-已取消，3-已叫号，4-已删除',
    `remark`        VARCHAR(255) NULL DEFAULT NULL COMMENT '挂号备注',
    `create_time`   DATETIME NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '用户挂号表';

-- ---------------------------------------------- 医院基础设施 ----------------------------------------------
-- ----------------------------
-- Table：科室信息表（也就是部门信息表）
-- Desc：该表主要用于存储科室信息。如：内科、外科、妇产科、儿科、眼科、耳鼻喉科、口腔科、皮肤科、肿瘤科、骨科、心血管内科、神经内科、肾内科、血液科、风湿免疫科、急诊科、康复医学科、精神科、感染科、肝病科、中医科、全科、其他等。
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '科室id',
    `name`          varchar(50) NOT NULL COMMENT '科室名称',
    `description`   varchar(255) NULL DEFAULT NULL COMMENT '科室描述',
    `create_time`   datetime NOT NULL COMMENT '创建时间',
    `update_time`   datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '科室信息表';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` (`id`, `name`, `description`, `create_time`, `update_time`)
VALUES (1, '内科', '负责内科疾病的诊断和治疗', NOW(), NOW()),
       (2, '外科', '负责外科疾病的诊断和治疗', NOW(), NOW()),
       (3, '妇产科', '负责妇产科疾病的诊断和治疗', NOW(), NOW()),
       (4, '儿科', '负责儿科疾病的诊断和治疗', NOW(), NOW()),
       (5, '眼科', '负责眼科疾病的诊断和治疗', NOW(), NOW()),
       (6, '耳鼻喉科', '负责耳鼻喉科疾病的诊断和治疗', NOW(), NOW()),
       (7, '口腔科', '负责口腔科疾病的诊断和治疗', NOW(), NOW()),
       (8, '皮肤科', '负责皮肤科疾病的诊断和治疗', NOW(), NOW()),
       (9, '肿瘤科', '负责肿瘤科疾病的诊断和治疗', NOW(), NOW()),
       (10, '骨科', '负责骨科疾病的诊断和治疗', NOW(), NOW()),
       (11, '心血管内科', '负责心血管内科疾病的诊断和治疗', NOW(), NOW()),
       (12, '神经内科', '负责神经内科疾病的诊断和治疗', NOW(), NOW()),
       (13, '肾内科', '负责肾内科疾病的诊断和治疗', NOW(), NOW()),
       (14, '血液科', '负责血液科疾病的诊断和治疗', NOW(), NOW()),
       (15, '风湿免疫科', '负责风湿免疫科疾病的诊断和治疗', NOW(), NOW()),
       (16, '急诊科', '负责急诊科疾病的诊断和治疗', NOW(), NOW()),
       (17, '康复医学科', '负责康复医学科疾病的诊断和治疗', NOW(), NOW()),
       (18, '精神科', '负责精神科疾病的诊断和治疗', NOW(), NOW()),
       (19, '感染科', '负责感染科疾病的诊断和治疗', NOW(), NOW()),
       (20, '肝病科', '负责肝病科疾病的诊断和治疗', NOW(), NOW()),
       (21, '中医科', '负责中医科疾病的诊断和治疗', NOW(), NOW()),
       (22, '全科', '负责全科疾病的诊断和治疗', NOW(), NOW()),
       (23, '其他', '负责其他疾病的诊断和治疗', NOW(), NOW());

-- ----------------------------
-- Table：病房信息表
-- Desc：该表主要用于存储病房信息。
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '病房id',
    `room_number`       varchar(20) NOT NULL COMMENT '病房编号，如 A-1-101 表示 A楼1层101房间，B-2-201 表示 B楼2层201房间',
    `department_id`     int(11) NOT NULL COMMENT '科室ID',
    `bed_count`         int(11) NOT NULL COMMENT '床位数量',
    `status`            int(11) NOT NULL COMMENT '病房状态：0-空闲（默认），1-可用，2-维修，3-已满，4-已删除',
    `remark`            varchar(255) NULL DEFAULT NULL COMMENT '病房备注',
    `create_time`       datetime NOT NULL COMMENT '创建时间',
    `update_time`       datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT '病房信息表';

-- ----------------------------
-- Records of room （假设有 A、B、C、D 四栋楼，每栋楼有 5 层，每层有 10 个房间，每个房间有 4 个床位，所属科室 id 对应 department 表中的 id，目前是 1～23）
-- ----------------------------
INSERT INTO `room` (`id`, `room_number`, `department_id`, `bed_count`, `status`, `remark`, `create_time`, `update_time`)
VALUES (1, 'A-1-101', 1, 4, 0, NULL, NOW(), NOW()),
       (2, 'A-1-102', 1, 4, 0, NULL, NOW(), NOW()),
       (3, 'A-1-103', 1, 4, 0, NULL, NOW(), NOW()),
       (4, 'A-1-104', 1, 4, 0, NULL, NOW(), NOW()),
       (5, 'A-1-105', 1, 4, 0, NULL, NOW(), NOW()),
       (6, 'A-1-106', 1, 4, 0, NULL, NOW(), NOW()),
       (7, 'A-1-107', 1, 4, 0, NULL, NOW(), NOW()),
       (8, 'A-1-108', 1, 4, 0, NULL, NOW(), NOW()),
       (9, 'A-1-109', 1, 4, 0, NULL, NOW(), NOW()),
       (10, 'A-1-110', 1, 4, 0, NULL, NOW(), NOW()),
       (11, 'A-2-201', 2, 4, 0, NULL, NOW(), NOW()),
       (12, 'A-2-202', 2, 4, 0, NULL, NOW(), NOW()),
       (13, 'A-2-203', 2, 4, 0, NULL, NOW(), NOW()),
       (14, 'A-2-204', 2, 4, 0, NULL, NOW(), NOW()),
       (15, 'A-2-205', 2, 4, 0, NULL, NOW(), NOW()),
       (16, 'A-2-206', 2, 4, 0, NULL, NOW(), NOW()),
       (17, 'A-2-207', 2, 4, 0, NULL, NOW(), NOW()),
       (18, 'A-2-208', 2, 4, 0, NULL, NOW(), NOW()),
       (19, 'A-2-209', 2, 4, 0, NULL, NOW(), NOW()),
       (20, 'A-2-210', 2, 4, 0, NULL, NOW(), NOW()),
       (21, 'A-3-301', 3, 4, 0, NULL, NOW(), NOW()),
       (22, 'A-3-302', 3, 4, 0, NULL, NOW(), NOW()),
       (23, 'A-3-303', 3, 4, 0, NULL, NOW(), NOW()),
       (24, 'A-3-304', 3, 4, 0, NULL, NOW(), NOW()),
       (25, 'A-3-305', 3, 4, 0, NULL, NOW(), NOW()),
       (26, 'A-3-306', 3, 4, 0, NULL, NOW(), NOW()),
       (27, 'A-3-307', 3, 4, 0, NULL, NOW(), NOW()),
       (28, 'A-3-308', 3, 4, 0, NULL, NOW(), NOW()),
       (29, 'A-3-309', 3, 4, 0, NULL, NOW(), NOW()),
       (30, 'A-3-310', 3, 4, 0, NULL, NOW(), NOW()),
       (31, 'A-4-401', 4, 4, 0, NULL, NOW(), NOW()),
       (32, 'A-4-402', 4, 4, 0, NULL, NOW(), NOW()),
       (33, 'A-4-403', 4, 4, 0, NULL, NOW(), NOW()),
       (34, 'A-4-404', 4, 4, 0, NULL, NOW(), NOW()),
       (35, 'A-4-405', 4, 4, 0, NULL, NOW(), NOW()),
       (36, 'A-4-406', 4, 4, 0, NULL, NOW(), NOW()),
       (37, 'A-4-407', 4, 4, 0, NULL, NOW(), NOW()),
       (38, 'A-4-408', 4, 4, 0, NULL, NOW(), NOW()),
       (39, 'A-4-409', 4, 4, 0, NULL, NOW(), NOW()),
       (40, 'A-4-410', 4, 4, 0, NULL, NOW(), NOW()),
       (41, 'A-5-501', 5, 4, 0, NULL, NOW(), NOW()),
       (42, 'A-5-502', 5, 4, 0, NULL, NOW(), NOW()),
       (43, 'A-5-503', 5, 4, 0, NULL, NOW(), NOW()),
       (44, 'A-5-504', 5, 4, 0, NULL, NOW(), NOW()),
       (45, 'A-5-505', 5, 4, 0, NULL, NOW(), NOW()),
       (46, 'A-5-506', 5, 4, 0, NULL, NOW(), NOW()),
       (47, 'A-5-507', 5, 4, 0, NULL, NOW(), NOW()),
       (48, 'A-5-508', 5, 4, 0, NULL, NOW(), NOW()),
       (49, 'A-5-509', 5, 4, 0, NULL, NOW(), NOW()),
       (50, 'A-5-510', 5, 4, 0, NULL, NOW(), NOW()),
       (51, 'B-1-101', 6, 4, 0, NULL, NOW(), NOW()),
       (52, 'B-1-102', 6, 4, 0, NULL, NOW(), NOW()),
       (53, 'B-1-103', 6, 4, 0, NULL, NOW(), NOW()),
       (54, 'B-1-104', 6, 4, 0, NULL, NOW(), NOW()),
       (55, 'B-1-105', 6, 4, 0, NULL, NOW(), NOW()),
       (56, 'B-1-106', 6, 4, 0, NULL, NOW(), NOW()),
       (57, 'B-1-107', 6, 4, 0, NULL, NOW(), NOW()),
       (58, 'B-1-108', 6, 4, 0, NULL, NOW(), NOW()),
       (59, 'B-1-109', 6, 4, 0, NULL, NOW(), NOW()),
       (60, 'B-1-110', 6, 4, 0, NULL, NOW(), NOW()),
       (61, 'B-2-201', 7, 4, 0, NULL, NOW(), NOW()),
       (62, 'B-2-202', 7, 4, 0, NULL, NOW(), NOW()),
       (63, 'B-2-203', 7, 4, 0, NULL, NOW(), NOW()),
       (64, 'B-2-204', 7, 4, 0, NULL, NOW(), NOW()),
       (65, 'B-2-205', 7, 4, 0, NULL, NOW(), NOW()),
       (66, 'B-2-206', 7, 4, 0, NULL, NOW(), NOW()),
       (67, 'B-2-207', 7, 4, 0, NULL, NOW(), NOW()),
       (68, 'B-2-208', 7, 4, 0, NULL, NOW(), NOW()),
       (69, 'B-2-209', 7, 4, 0, NULL, NOW(), NOW()),
       (70, 'B-2-210', 7, 4, 0, NULL, NOW(), NOW()),
       (71, 'B-3-301', 8, 4, 0, NULL, NOW(), NOW()),
       (72, 'B-3-302', 8, 4, 0, NULL, NOW(), NOW()),
       (73, 'B-3-303', 8, 4, 0, NULL, NOW(), NOW()),
       (74, 'B-3-304', 8, 4, 0, NULL, NOW(), NOW()),
       (75, 'B-3-305', 8, 4, 0, NULL, NOW(), NOW()),
       (76, 'B-3-306', 8, 4, 0, NULL, NOW(), NOW()),
       (77, 'B-3-307', 8, 4, 0, NULL, NOW(), NOW()),
       (78, 'B-3-308', 8, 4, 0, NULL, NOW(), NOW()),
       (79, 'B-3-309', 8, 4, 0, NULL, NOW(), NOW()),
       (80, 'B-3-310', 8, 4, 0, NULL, NOW(), NOW()),
       (81, 'B-4-401', 9, 4, 0, NULL, NOW(), NOW()),
       (82, 'B-4-402', 9, 4, 0, NULL, NOW(), NOW()),
       (83, 'B-4-403', 9, 4, 0, NULL, NOW(), NOW()),
       (84, 'B-4-404', 9, 4, 0, NULL, NOW(), NOW()),
       (85, 'B-4-405', 9, 4, 0, NULL, NOW(), NOW()),
       (86, 'B-4-406', 9, 4, 0, NULL, NOW(), NOW()),
       (87, 'B-4-407', 9, 4, 0, NULL, NOW(), NOW()),
       (88, 'B-4-408', 9, 4, 0, NULL, NOW(), NOW()),
       (89, 'B-4-409', 9, 4, 0, NULL, NOW(), NOW()),
       (90, 'B-4-410', 9, 4, 0, NULL, NOW(), NOW()),
       (91, 'B-5-501', 10, 4, 0, NULL, NOW(), NOW()),
       (92, 'B-5-502', 10, 4, 0, NULL, NOW(), NOW()),
       (93, 'B-5-503', 10, 4, 0, NULL, NOW(), NOW()),
       (94, 'B-5-504', 10, 4, 0, NULL, NOW(), NOW()),
       (95, 'B-5-505', 10, 4, 0, NULL, NOW(), NOW()),
       (96, 'B-5-506', 10, 4, 0, NULL, NOW(), NOW()),
       (97, 'B-5-507', 10, 4, 0, NULL, NOW(), NOW()),
       (98, 'B-5-508', 10, 4, 0, NULL, NOW(), NOW()),
       (99, 'B-5-509', 10, 4, 0, NULL, NOW(), NOW()),
       (100, 'B-5-510', 10, 4, 0, NULL, NOW(), NOW()),
       (101, 'C-1-101', 11, 4, 0, NULL, NOW(), NOW()),
       (102, 'C-1-102', 11, 4, 0, NULL, NOW(), NOW()),
       (103, 'C-1-103', 11, 4, 0, NULL, NOW(), NOW()),
       (104, 'C-1-104', 11, 4, 0, NULL, NOW(), NOW()),
       (105, 'C-1-105', 11, 4, 0, NULL, NOW(), NOW()),
       (106, 'C-1-106', 11, 4, 0, NULL, NOW(), NOW()),
       (107, 'C-1-107', 11, 4, 0, NULL, NOW(), NOW()),
       (108, 'C-1-108', 11, 4, 0, NULL, NOW(), NOW()),
       (109, 'C-1-109', 11, 4, 0, NULL, NOW(), NOW()),
       (110, 'C-1-110', 11, 4, 0, NULL, NOW(), NOW()),
       (111, 'C-2-201', 12, 4, 0, NULL, NOW(), NOW()),
       (112, 'C-2-202', 12, 4, 0, NULL, NOW(), NOW()),
       (113, 'C-2-203', 12, 4, 0, NULL, NOW(), NOW()),
       (114, 'C-2-204', 12, 4, 0, NULL, NOW(), NOW()),
       (115, 'C-2-205', 12, 4, 0, NULL, NOW(), NOW()),
     (116, 'C-2-206', 12, 4, 0, NULL, NOW(), NOW()),
     (117, 'C-2-207', 12, 4, 0, NULL, NOW(), NOW()),
     (118, 'C-2-208', 12, 4, 0, NULL, NOW(), NOW()),
     (119, 'C-2-209', 12, 4, 0, NULL, NOW(), NOW()),
     (120, 'C-2-210', 12, 4, 0, NULL, NOW(), NOW()),
     (121, 'C-3-301', 13, 4, 0, NULL, NOW(), NOW()),
     (122, 'C-3-302', 13, 4, 0, NULL, NOW(), NOW()),
     (123, 'C-3-303', 13, 4, 0, NULL, NOW(), NOW()),
     (124, 'C-3-304', 13, 4, 0, NULL, NOW(), NOW()),
     (125, 'C-3-305', 13, 4, 0, NULL, NOW(), NOW()),
     (126, 'C-3-306', 13, 4, 0, NULL, NOW(), NOW()),
     (127, 'C-3-307', 13, 4, 0, NULL, NOW(), NOW()),
     (128, 'C-3-308', 13, 4, 0, NULL, NOW(), NOW()),
     (129, 'C-3-309', 13, 4, 0, NULL, NOW(), NOW()),
     (130, 'C-3-310', 13, 4, 0, NULL, NOW(), NOW()),
     (131, 'C-4-401', 14, 4, 0, NULL, NOW(), NOW()),
     (132, 'C-4-402', 14, 4, 0, NULL, NOW(), NOW()),
     (133, 'C-4-403', 14, 4, 0, NULL, NOW(), NOW()),
     (134, 'C-4-404', 14, 4, 0, NULL, NOW(), NOW()),
     (135, 'C-4-405', 14, 4, 0, NULL, NOW(), NOW()),
     (136, 'C-4-406', 14, 4, 0, NULL, NOW(), NOW()),
     (137, 'C-4-407', 14, 4, 0, NULL, NOW(), NOW()),
     (138, 'C-4-408', 14, 4, 0, NULL, NOW(), NOW()),
     (139, 'C-4-409', 14, 4, 0, NULL, NOW(), NOW()),
     (140, 'C-4-410', 14, 4, 0, NULL, NOW(), NOW()),
     (141, 'C-5-501', 15, 4, 0, NULL, NOW(), NOW()),
     (142, 'C-5-502', 15, 4, 0, NULL, NOW(), NOW()),
     (143, 'C-5-503', 15, 4, 0, NULL, NOW(), NOW()),
     (144, 'C-5-504', 15, 4, 0, NULL, NOW(), NOW()),
     (145, 'C-5-505', 15, 4, 0, NULL, NOW(), NOW()),
     (146, 'C-5-506', 15, 4, 0, NULL, NOW(), NOW()),
     (147, 'C-5-507', 15, 4, 0, NULL, NOW(), NOW()),
     (148, 'C-5-508', 15, 4, 0, NULL, NOW(), NOW()),
     (149, 'C-5-509', 15, 4, 0, NULL, NOW(), NOW()),
     (150, 'C-5-510', 15, 4, 0, NULL, NOW(), NOW()),
     (151, 'D-1-101', 16, 4, 0, NULL, NOW(), NOW()),
     (152, 'D-1-102', 16, 4, 0, NULL, NOW(), NOW()),
     (153, 'D-1-103', 16, 4, 0, NULL, NOW(), NOW()),
     (154, 'D-1-104', 16, 4, 0, NULL, NOW(), NOW()),
     (155, 'D-1-105', 16, 4, 0, NULL, NOW(), NOW()),
     (156, 'D-1-106', 16, 4, 0, NULL, NOW(), NOW()),
     (157, 'D-1-107', 16, 4, 0, NULL, NOW(), NOW()),
     (158, 'D-1-108', 16, 4, 0, NULL, NOW(), NOW()),
     (159, 'D-1-109', 16, 4, 0, NULL, NOW(), NOW()),
     (160, 'D-1-110', 16, 4, 0, NULL, NOW(), NOW()),
     (161, 'D-2-201', 17, 4, 0, NULL, NOW(), NOW()),
     (162, 'D-2-202', 17, 4, 0, NULL, NOW(), NOW()),
     (163, 'D-2-203', 17, 4, 0, NULL, NOW(), NOW()),
     (164, 'D-2-204', 17, 4, 0, NULL, NOW(), NOW()),
     (165, 'D-2-205', 17, 4, 0, NULL, NOW(), NOW()),
     (166, 'D-2-206', 17, 4, 0, NULL, NOW(), NOW()),
     (167, 'D-2-207', 17, 4, 0, NULL, NOW(), NOW()),
     (168, 'D-2-208', 17, 4, 0, NULL, NOW(), NOW()),
     (169, 'D-2-209', 17, 4, 0, NULL, NOW(), NOW()),
     (170, 'D-2-210', 17, 4, 0, NULL, NOW(), NOW()),
     (171, 'D-3-301', 18, 4, 0, NULL, NOW(), NOW()),
     (172, 'D-3-302', 18, 4, 0, NULL, NOW(), NOW()),
     (173, 'D-3-303', 18, 4, 0, NULL, NOW(), NOW()),
     (174, 'D-3-304', 18, 4, 0, NULL, NOW(), NOW()),
     (175, 'D-3-305', 18, 4, 0, NULL, NOW(), NOW()),
     (176, 'D-3-306', 18, 4, 0, NULL, NOW(), NOW()),
     (177, 'D-3-307', 18, 4, 0, NULL, NOW(), NOW()),
     (178, 'D-3-308', 18, 4, 0, NULL, NOW(), NOW()),
     (179, 'D-3-309', 18, 4, 0, NULL, NOW(), NOW()),
     (180, 'D-3-310', 18, 4, 0, NULL, NOW(), NOW()),
     (181, 'D-4-401', 19, 4, 0, NULL, NOW(), NOW()),
     (182, 'D-4-402', 19, 4, 0, NULL, NOW(), NOW()),
     (183, 'D-4-403', 19, 4, 0, NULL, NOW(), NOW()),
     (184, 'D-4-404', 19, 4, 0, NULL, NOW(), NOW()),
     (185, 'D-4-405', 19, 4, 0, NULL, NOW(), NOW()),
     (186, 'D-4-406', 19, 4, 0, NULL, NOW(), NOW()),
     (187, 'D-4-407', 19, 4, 0, NULL, NOW(), NOW()),
     (188, 'D-4-408', 19, 4, 0, NULL, NOW(), NOW()),
     (189, 'D-4-409', 19, 4, 0, NULL, NOW(), NOW()),
     (190, 'D-4-410', 19, 4, 0, NULL, NOW(), NOW()),
     (191, 'D-5-501', 20, 4, 0, NULL, NOW(), NOW()),
     (192, 'D-5-502', 20, 4, 0, NULL, NOW(), NOW()),
     (193, 'D-5-503', 20, 4, 0, NULL, NOW(), NOW()),
     (194, 'D-5-504', 20, 4, 0, NULL, NOW(), NOW()),
     (195, 'D-5-505', 20, 4, 0, NULL, NOW(), NOW()),
     (196, 'D-5-506', 20, 4, 0, NULL, NOW(), NOW()),
     (197, 'D-5-507', 20, 4, 0, NULL, NOW(), NOW()),
     (198, 'D-5-508', 20, 4, 0, NULL, NOW(), NOW()),
     (199, 'D-5-509', 20, 4, 0, NULL, NOW(), NOW()),
     (200, 'D-5-510', 20, 4, 0, NULL, NOW(), NOW());

-- ----------------------------
-- Table：床位信息表
-- Desc：该表主要用于存储床位信息。
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '床位id',
    `room_id`       int(11) NOT NULL COMMENT '病房ID',
    `bed_number`    int(11) NOT NULL COMMENT '床位编号, 1～4',
    `price`         decimal(10,2) NOT NULL COMMENT '单价',
    `status`        int(11) NOT NULL COMMENT '占用状态:0-空闲，1-占用, 2-维修，3-预定，4-已删除',
    `remark`        varchar(255) NULL DEFAULT NULL COMMENT '床位备注',
    `create_time`   datetime NOT NULL COMMENT '创建时间',
    `update_time`   datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '床位信息表';

-- ----------------------------
-- Records of bed （假设每个房间有 4 个床位）
-- ----------------------------
INSERT INTO `bed` (`id`, `room_id`, `bed_number`, `price`, `status`, `remark`, `create_time`, `update_time`)
VALUES (1, 1, 1, 100.00, 0, NULL, NOW(), NOW()),
       (2, 1, 2, 100.00, 0, NULL, NOW(), NOW()),
       (3, 1, 3, 100.00, 0, NULL, NOW(), NOW()),
       (4, 1, 4, 100.00, 0, NULL, NOW(), NOW()),
       (5, 2, 1, 100.00, 0, NULL, NOW(), NOW()),
       (6, 2, 2, 100.00, 0, NULL, NOW(), NOW()),
       (7, 2, 3, 100.00, 0, NULL, NOW(), NOW()),
       (8, 2, 4, 100.00, 0, NULL, NOW(), NOW()),
       (9, 3, 1, 100.00, 0, NULL, NOW(), NOW()),
       (10, 3, 2, 100.00, 0, NULL, NOW(), NOW()),
       (11, 3, 3, 100.00, 0, NULL, NOW(), NOW()),
       (12, 3, 4, 100.00, 0, NULL, NOW(), NOW()),
       (13, 4, 1, 100.00, 0, NULL, NOW(), NOW()),
       (14, 4, 2, 100.00, 0, NULL, NOW(), NOW()),
       (15, 4, 3, 100.00, 0, NULL, NOW(), NOW()),
       (16, 4, 4, 100.00, 0, NULL, NOW(), NOW()),
       (17, 5, 1, 100.00, 0, NULL, NOW(), NOW()),
       (18, 5, 2, 100.00, 0, NULL, NOW(), NOW()),
       (19, 5, 3, 100.00, 0, NULL, NOW(), NOW()),
       (20, 5, 4, 100.00, 0, NULL, NOW(), NOW()),
       (21, 6, 1, 100.00, 0, NULL, NOW(), NOW()),
       (22, 6, 2, 100.00, 0, NULL, NOW(), NOW()),
       (23, 6, 3, 100.00, 0, NULL, NOW(), NOW()),
       (24, 6, 4, 100.00, 0, NULL, NOW(), NOW()),
       (25, 7, 1, 100.00, 0, NULL, NOW(), NOW()),
       (26, 7, 2, 100.00, 0, NULL, NOW(), NOW()),
       (27, 7, 3, 100.00, 0, NULL, NOW(), NOW()),
       (28, 7, 4, 100.00, 0, NULL, NOW(), NOW()),
       (29, 8, 1, 100.00, 0, NULL, NOW(), NOW()),
       (30, 8, 2, 100.00, 0, NULL, NOW(), NOW()),
       (31, 8, 3, 100.00, 0, NULL, NOW(), NOW()),
       (32, 8, 4, 100.00, 0, NULL, NOW(), NOW()),
       (33, 9, 1, 100.00, 0, NULL, NOW(), NOW()),
       (34, 9, 2, 100.00, 0, NULL, NOW(), NOW()),
       (35, 9, 3, 100.00, 0, NULL, NOW(), NOW()),
       (36, 9, 4, 100.00, 0, NULL, NOW(), NOW()),
       (37, 10, 1, 100.00, 0, NULL, NOW(), NOW()),
       (38, 10, 2, 100.00, 0, NULL, NOW(), NOW()),
       (39, 10, 3, 100.00, 0, NULL, NOW(), NOW()),
       (40, 10, 4, 100.00, 0, NULL, NOW(), NOW()),
       (41, 11, 1, 100.00, 0, NULL, NOW(), NOW()),
       (42, 11, 2, 100.00, 0, NULL, NOW(), NOW()),
       (43, 11, 3, 100.00, 0, NULL, NOW(), NOW()),
       (44, 11, 4, 100.00, 0, NULL, NOW(), NOW()),
       (45, 12, 1, 100.00, 0, NULL, NOW(), NOW()),
       (46, 12, 2, 100.00, 0, NULL, NOW(), NOW()),
       (47, 12, 3, 100.00, 0, NULL, NOW(), NOW()),
       (48, 12, 4, 100.00, 0, NULL, NOW(), NOW()),
       (49, 13, 1, 100.00, 0, NULL, NOW(), NOW()),
       (50, 13, 2, 100.00, 0, NULL, NOW(), NOW()),
       (51, 13, 3, 100.00, 0, NULL, NOW(), NOW()),
       (52, 13, 4, 100.00, 0, NULL, NOW(), NOW()),
       (53, 14, 1, 100.00, 0, NULL, NOW(), NOW()),
       (54, 14, 2, 100.00, 0, NULL, NOW(), NOW()),
       (55, 14, 3, 100.00, 0, NULL, NOW(), NOW()),
       (56, 14, 4, 100.00, 0, NULL, NOW(), NOW()),
       (57, 15, 1, 100.00, 0, NULL, NOW(), NOW()),
       (58, 15, 2, 100.00, 0, NULL, NOW(), NOW()),
       (59, 15, 3, 100.00, 0, NULL, NOW(), NOW()),
       (60, 15, 4, 100.00, 0, NULL, NOW(), NOW()),
       (61, 16, 1, 100.00, 0, NULL, NOW(), NOW()),
       (62, 16, 2, 100.00, 0, NULL, NOW(), NOW()),
       (63, 16, 3, 100.00, 0, NULL, NOW(), NOW()),
       (64, 16, 4, 100.00, 0, NULL, NOW(), NOW()),
       (65, 17, 1, 100.00, 0, NULL, NOW(), NOW()),
       (66, 17, 2, 100.00, 0, NULL, NOW(), NOW()),
       (67, 17, 3, 100.00, 0, NULL, NOW(), NOW()),
       (68, 17, 4, 100.00, 0, NULL, NOW(), NOW()),
       (69, 18, 1, 100.00, 0, NULL, NOW(), NOW()),
       (70, 18, 2, 100.00, 0, NULL, NOW(), NOW()),
       (71, 18, 3, 100.00, 0, NULL, NOW(), NOW()),
       (72, 18, 4, 100.00, 0, NULL, NOW(), NOW()),
       (73, 19, 1, 100.00, 0, NULL, NOW(), NOW()),
       (74, 19, 2, 100.00, 0, NULL, NOW(), NOW()),
       (75, 19, 3, 100.00, 0, NULL, NOW(), NOW()),
       (76, 19, 4, 100.00, 0, NULL, NOW(), NOW()),
       (77, 20, 1, 100.00, 0, NULL, NOW(), NOW()),
       (78, 20, 2, 100.00, 0, NULL, NOW(), NOW()),
       (79, 20, 3, 100.00, 0, NULL, NOW(), NOW()),
       (80, 20, 4, 100.00, 0, NULL, NOW(), NOW());

-- ---------------------------------------------- 住院管理 ----------------------------------------------
-- ----------------------------
-- Table：住院信息表
-- Desc：该表用于记录患者的住院信息，医生可以通过患者的住院信息查询患者的病情，患者也可以通过住院信息查询自己的病情。
-- ----------------------------
DROP TABLE IF EXISTS `hospitalization_info`;
CREATE TABLE `hospitalization_info`  (
    `id` int(11)        NOT NULL AUTO_INCREMENT COMMENT '住院id号',
    `patient_id`        int(11) NOT NULL COMMENT '患者id',
    `patient_name`      varchar(50) NOT NULL COMMENT '患者姓名',
    `room_id`           int(11) NOT NULL COMMENT '病房ID',
    `bed_id`            int(11) NOT NULL COMMENT '床位号',
    `admission_date`    date NOT NULL COMMENT '入院日期',
    `discharge_date`    date NULL DEFAULT NULL COMMENT '出院日期',
    `total_cost`        decimal(10,2) NOT NULL COMMENT '总费用',
    `status`            int(11) NOT NULL DEFAULT 0 COMMENT '住院状态：0-住院中，1-已出院',
    `remark`            varchar(255) NULL DEFAULT NULL COMMENT '备注',
    `create_time`       datetime NOT NULL COMMENT '创建时间',
    `update_time`       datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '住院信息表';

-- ----------------------------
-- Table：病历信息表
-- Desc：该表用于记录患者的病历信息，医生可以通过患者的病历信息查询患者的病情，患者也可以通过病历信息查询自己的病情。
-- ----------------------------
DROP TABLE IF EXISTS `medical_record`;
CREATE TABLE `medical_record`  (
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '病历id',
    `record_date`       date NOT NULL COMMENT '病历日期',
    `patient_id`        int(11) NOT NULL COMMENT '患者id',
    `doctor_id`         int(11) NOT NULL COMMENT '医生id',
    `temperature`       float NOT NULL COMMENT '体温',
    `pulse`             int(11) NOT NULL COMMENT '脉搏',
    `blood_pressure`    int(11) NOT NULL COMMENT '血压',
    `remark`            varchar(255) NULL DEFAULT NULL COMMENT '备注',
    `appointment_date`  date NOT NULL COMMENT '预约时间',
    `create_time`       datetime NOT NULL COMMENT '创建时间',
    `update_time`       datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '病历信息表';

-- ----------------------------
-- Table：患者家属信息表
-- Desc：该表用于记录患者的家属信息，在患者住院期间，家属可以通过患者的住院信息查询患者的病情，医生也可以通过患者的住院信息查询患者的家属信息。
-- ----------------------------
DROP TABLE IF EXISTS `patient_relative`;
CREATE TABLE `patient_relative`  (
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `patient_id`    int(11) NOT NULL COMMENT '患者id',
    `name`          varchar(50) NOT NULL COMMENT '家属姓名',
    `relationship`  varchar(20) NOT NULL COMMENT '与患者的关系',
    `phone`         varchar(20) NOT NULL COMMENT '联系电话',
    `address`       varchar(255) NULL DEFAULT NULL COMMENT '联系地址',
    `create_time`   datetime NOT NULL COMMENT '创建时间',
    `update_time`   datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '患者家属信息表';

-- ---------------------------------------------- 药品管理 ----------------------------------------------
-- ----------------------------
-- Table：药品信息表
-- Desc：该表用于记录药品的信息，包括药品的名称、生产厂家、单价、单位、库存、备注等。
-- ----------------------------
DROP TABLE IF EXISTS `medication_info`;
CREATE TABLE `medication_info`  (
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '药品id',
    `medication_code`   varchar(50) NOT NULL COMMENT '药品编号',
    `medication_name`   varchar(50) NOT NULL COMMENT '药品名称',
    `manufacturer`      varchar(50) NOT NULL COMMENT '生产厂家',
    `price`             decimal(10,2) NOT NULL COMMENT '单价',
    `unit`              varchar(20) NOT NULL COMMENT '单位例如片、瓶、盒等',
    `stock`             int(11) NOT NULL COMMENT '库存',
    `remark`            varchar(255) NULL DEFAULT NULL COMMENT '关于药品的其他重要信息，例如用法用量、副作用等',
    `create_time`       datetime NOT NULL COMMENT '创建时间',
    `update_time`       datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '药品信息表';

-- ----------------------------
-- Records of medication_info
-- ----------------------------
INSERT INTO `medication_info` (`medication_code`, `medication_name`, `manufacturer`, `price`, `unit`, `stock`, `remark`, `create_time`, `update_time`)
VALUES
    ('M001', '阿莫西林', '制药公司A', 10.00, '瓶', 100, '用法用量：按医生建议服用', NOW(), NOW()),
    ('M002', '布洛芬', '制药公司B', 15.50, '盒', 200, '副作用：可能会引起头痛', NOW(), NOW()),
    ('M003', '头孢', '制药公司C', 8.75, '片', 150, '注意事项：不适用于儿童', NOW(), NOW()),
    ('M004', '布地奈德', '制药公司D', 25.00, '瓶', 50, '用法用量：每日两次，饭后服用', NOW(), NOW()),
    ('M005', '盐酸伪麻黄碱', '制药公司E', 12.60, '盒', 80, NULL, NOW(), NOW()),
    ('M006', '利巴韦林', '制药公司F', 9.99, '瓶', 120, NULL, NOW(), NOW()),
    ('M007', '阿莫洛尔', '制药公司G', 7.25, '片', 90, '副作用：可能会引起恶心', NOW(), NOW()),
    ('M008', '苯巴比妥', '制药公司H', 15.25, '瓶', 60, NULL, NOW(), NOW()),
    ('M009', '多巴胺', '制药公司I', 18.50, '瓶', 70, '用法用量：按医生建议服用', NOW(), NOW()),
    ('M010', '阿托伐他汀', '制药公司J', 20.00, '盒', 90, '副作用：可能会引起肌肉疼痛', NOW(), NOW()),
    ('M011', '伊布前列素', '制药公司K', 13.75, '片', 120, NULL, NOW(), NOW()),
    ('M012', '奥美拉唑', '制药公司L', 9.50, '瓶', 110, '用法用量：每日一次，空腹服用', NOW(), NOW()),
    ('M013', '头孢克洛', '制药公司M', 16.75, '片', 130, NULL, NOW(), NOW()),
    ('M014', '替诺福韦', '制药公司N', 21.00, '盒', 95, '副作用：可能会引起恶心和呕吐', NOW(), NOW()),
    ('M015', '甲磺酸倍他米松', '制药公司O', 14.25, '瓶', 75, NULL, NOW(), NOW()),
    ('M016', '非那雄胺', '制药公司P', 19.50, '盒', 85, '用法用量：每日一次，饭后服用', NOW(), NOW()),
    ('M017', '瑞托普利', '制药公司Q', 11.25, '片', 105, '副作用：可能会引起咳嗽', NOW(), NOW()),
    ('M018', '格列本脲', '制药公司R', 10.50, '瓶', 115, NULL, NOW(), NOW()),
    ('M019', '依普利特', '制药公司S', 12.75, '盒', 125, '用法用量：每日一次，餐后服用', NOW(), NOW()),
    ('M020', '阿普唑仑', '制药公司T', 8.00, '片', 70, '副作用：可能会引起嗜睡', NOW(), NOW()),
    ('M021', '阿司匹林', '制药公司A', 6.50, '片', 220, NULL, NOW(), NOW()),
    ('M022', '头孢拉定', '制药公司B', 14.00, '片', 180, '用法用量：每日三次，饭后服用', NOW(), NOW()),
    ('M023', '氨溴索', '制药公司C', 9.25, '片', 160, NULL, NOW(), NOW()),
    ('M024', '布比卡因', '制药公司D', 22.00, '瓶', 40, '副作用：可能会引起过敏', NOW(), NOW()),
    ('M025', '卡托普利', '制药公司E', 17.60, '盒', 95, NULL, NOW(), NOW()),
    ('M026', '普鲁卡因胺', '制药公司F', 11.99, '瓶', 130, NULL, NOW(), NOW()),
    ('M027', '托瑞米芬', '制药公司G', 14.50, '盒', 85, '用法用量：每日一次，睡前服用', NOW(), NOW()),
    ('M028', '葡萄糖', '制药公司H', 8.25, '瓶', 190, NULL, NOW(), NOW()),
    ('M029', '硝酸甘油', '制药公司I', 18.75, '瓶', 60, '副作用：可能会引起头晕', NOW(), NOW()),
    ('M030', '依诺肝素', '制药公司J', 16.00, '盒', 70, '注意事项：不适用于孕妇', NOW(), NOW()),
    ('M031', '曲美他嗪', '制药公司K', 10.75, '片', 140, NULL, NOW(), NOW()),
    ('M032', '利托考辛', '制药公司L', 15.25, '瓶', 80, NULL, NOW(), NOW()),
    ('M033', '多潘立酮', '制药公司M', 12.99, '瓶', 90, '用法用量：每餐前30分钟服用', NOW(), NOW()),
    ('M034', '特布他林', '制药公司N', 8.50, '盒', 120, '副作用：可能会引起心跳加速', NOW(), NOW()),
    ('M035', '布洛芬缓释片', '制药公司O', 13.25, '片', 100, NULL, NOW(), NOW()),
    ('M036', '奥硝唑', '制药公司P', 11.00, '瓶', 110, '注意事项：不适用于孕妇', NOW(), NOW()),
    ('M037', '普瑞巴林', '制药公司Q', 14.75, '盒', 70, '副作用：可能会引起嗜睡', NOW(), NOW()),
    ('M038', '吲哚美辛', '制药公司R', 9.75, '片', 180, NULL, NOW(), NOW()),
    ('M039', '酮康唑', '制药公司S', 15.00, '瓶', 55, '用法用量：每日一次，晚上服用', NOW(), NOW()),
    ('M040', '苯妥英钠', '制药公司T', 17.99, '瓶', 50, '副作用：可能会引起头痛和失眠', NOW(), NOW()),
    ('M041', '左卡尼汀', '制药公司A', 12.50, '盒', 95, NULL, NOW(), NOW()),
    ('M042', '达拉宾', '制药公司B', 14.75, '瓶', 80, NULL, NOW(), NOW()),
    ('M043', '替米沙坦', '制药公司C', 11.25, '片', 135, '用法用量：每日一次，饭后服用', NOW(), NOW()),
    ('M044', '多巴酚丁胺', '制药公司D', 19.00, '瓶', 65, '副作用：可能会引起恶心和呕吐', NOW(), NOW()),
    ('M045', '依普利嗪', '制药公司E', 14.60, '盒', 105, NULL, NOW(), NOW()),
    ('M046', '美托洛尔', '制药公司F', 10.99, '瓶', 125, NULL, NOW(), NOW()),
    ('M047', '非索非那定', '制药公司G', 16.25, '片', 70, '用法用量：每日一次，睡前服用', NOW(), NOW()),
    ('M048', '阿莫洛尔', '制药公司H', 9.25, '盒', 155, '副作用：可能会引起心悸', NOW(), NOW()),
    ('M049', '利巴韦林', '制药公司I', 13.75, '片', 145, NULL, NOW(), NOW()),
    ('M050', '多奈米特', '制药公司J', 7.60, '瓶', 190, '注意事项：不适用于孕妇', NOW(), NOW());

-- ----------------------------
-- Table：药品采购表
-- Desc：该表用于记录药品的采购信息，包括采购日期、采购总费用、采购明细等。
-- ----------------------------
DROP TABLE IF EXISTS `medication_purchase`;
CREATE TABLE `medication_purchase`  (
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '采购单id',
    `purchase_code`     varchar(50) NOT NULL COMMENT '采购单编号',
    `total_cost`        decimal(10,2) NOT NULL COMMENT '总费用',
    `purchase_date`     date NOT NULL COMMENT '采购日期',
    `remark`            varchar(255) NULL DEFAULT NULL COMMENT '备注',
    `create_time`       datetime NOT NULL COMMENT '创建时间',
    `update_time`       datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '药品采购表';

-- ----------------------------
-- Table：药品采购明细表
-- Desc：该表用于记录药品的采购明细信息，包括采购编号、药品编号、采购数量、费用等。
-- ----------------------------
DROP TABLE IF EXISTS `medication_purchase_details`;
CREATE TABLE `medication_purchase_details`  (
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '采购明细id',
    `purchase_id`       int(11) NOT NULL COMMENT '采购单id',
    `medication_id`     int(11) NOT NULL COMMENT '药品id',
    `quantity`          int(11) NOT NULL COMMENT '采购数量',
    `cost`              decimal(10,2) NOT NULL COMMENT '费用',
    `create_time`       datetime NOT NULL COMMENT '创建时间',
    `update_time`       datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '药品采购明细表';

-- ----------------------------
-- Table：药品入库作业表
-- Desc：该表用于记录药品的入库信息，包括采购编号、入库数量、入库日期等。
-- ----------------------------
DROP TABLE IF EXISTS `medication_stock`;
CREATE TABLE `medication_stock`  (
     `id`               int(11) NOT NULL AUTO_INCREMENT COMMENT '入库id',
     `purchase_id`      int(11) NOT NULL COMMENT '采购编号',
     `quantity`         int(11) NOT NULL COMMENT '入库数量',
     `stock_date`       date NOT NULL COMMENT '入库日期',
     `remark`           varchar(255) NULL DEFAULT NULL COMMENT '备注',
     `create_time`      datetime NOT NULL COMMENT '创建时间',
     `update_time`      datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '药品入库作业表';

-- ----------------------------
-- Table：药品财务审核表
-- Desc：该表用于记录药品的财务审核信息，包括采购编号、审核状态、审核日期、备注等。
-- ----------------------------
DROP TABLE IF EXISTS `medication_financial_audit`;
CREATE TABLE `medication_financial_audit`  (
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '审核id',
    `audit_code`    varchar(50) NOT NULL COMMENT '审核编号,与采购编号一致',
    `purchase_id`   int(11) NOT NULL COMMENT '采购编号',
    `audit_status`  int(11) NOT NULL DEFAULT 0 COMMENT '审核状态：0-待审核，1-已审核，2-审核不通过，-1-已删除',
    `audit_date`    date NULL DEFAULT NULL COMMENT '审核日期',
    `remark`        varchar(255) NULL DEFAULT NULL COMMENT '备注',
    `create_time`   datetime NOT NULL COMMENT '创建时间',
    `update_time`   datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '药品财务审核表';

-- ----------------------------
-- Table：医院财务表
-- Desc：该表用于记录医院的财务信息，包括财务日期、支出、收入、备注等。
-- ----------------------------
DROP TABLE IF EXISTS `financial_info`;
CREATE TABLE `financial_info`  (
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '财务id',
    `original_id`       int(11) NULL DEFAULT NULL COMMENT '原始id，例如住院信息表的id,处方信息表的id,药品采购表的id等',
    `category`          int(11) NOT NULL COMMENT '类别：0-住院，1-处方，2-药品采购',
    `type`              int(11) NOT NULL COMMENT '类型：0-支出，1-收入',
    `amount`            decimal(10,2) NOT NULL COMMENT '金额',
    `financial_date`     date NOT NULL COMMENT '财务日期',
    `remark`            varchar(255) NULL DEFAULT NULL COMMENT '备注',
    `create_time`       datetime NOT NULL COMMENT '创建时间',
    `update_time`       datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '医院财务表';

-- ---------------------------------------------- 处方管理 ----------------------------------------------
-- ----------------------------
-- Table：处方信息表
-- Desc：该表用于记录处方的信息，包括处方日期、医生姓名、总费用、支付状态、备注等。
-- ----------------------------
DROP TABLE IF EXISTS `prescription_info`;
CREATE TABLE `prescription_info`  (
    `id`                int(11) NOT NULL AUTO_INCREMENT COMMENT '处方id',
    `create_date`       date NOT NULL COMMENT '开方日期',
    `username`          varchar(50) NOT NULL COMMENT '患者姓名',
    `user_id`           int(11) NOT NULL COMMENT '用户id',
    `doctor_id`         int(11) NULL DEFAULT NULL COMMENT '医生id',
    `doctor_name`       varchar(50) NULL DEFAULT NULL COMMENT '医生姓名',
    `total_price`       decimal(10,2) NULL DEFAULT 0 COMMENT '总费用',
    `payment_status`    int(11) NOT NULL DEFAULT 0 COMMENT '支付状态：0-待支付,1-已支付',
    `remark`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处方备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic COMMENT = '处方信息表';

-- ----------------------------
-- Table：处方药品关联表
-- Desc：该表用于记录处方中的药品信息，包括药品编号、药品名称、生产厂家、总价、单位、数量等。
-- ----------------------------
DROP TABLE IF EXISTS `prescription_drug`;
CREATE TABLE `prescription_drug` (
    `id`                    int(11) NOT NULL AUTO_INCREMENT COMMENT '处方药品关联id',
    `prescription_id`       int(11) NOT NULL COMMENT '处方id',
    `medication_id`         int(11) NOT NULL COMMENT '药品id',
    `medication_name`       varchar(50) NOT NULL COMMENT '药品名称',
    `manufacturer`          varchar(50) NOT NULL COMMENT '生产厂家',
    `total_price`           decimal(10,2) NOT NULL COMMENT '该药品总价',
    `unit`                  varchar(10) NOT NULL COMMENT '单位',
    `quantity`              int(11) NOT NULL COMMENT '药品数量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic COMMENT = '处方药品关联表';

-- ---------------------------------------------- 附加功能 ----------------------------------------------
-- ----------------------------
-- Table：用户反馈表
-- Desc：该表用于记录用户的反馈信息，包括反馈主题、反馈人、反馈状态、反馈内容等。
-- ----------------------------
DROP TABLE IF EXISTS `user_feedback`;
CREATE TABLE `user_feedback`  (
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '反馈id',
    `user_id`       int(11) NOT NULL COMMENT '用户id',
    `subject`       varchar(255) NOT NULL COMMENT '反馈主题',
    `user_name`     varchar(50) NOT NULL COMMENT '反馈人',
    `status`        int(11) NOT NULL DEFAULT 0 COMMENT '反馈状态：0-待回复，1-已回复, -1-已删除',
    `feedback`      text NULL DEFAULT NULL COMMENT '反馈内容',
    `reply`         text NULL DEFAULT NULL COMMENT '回复内容',
    `reply_time`    datetime NULL DEFAULT NULL COMMENT '回复时间',
    `reply_user`    varchar(50) NULL DEFAULT NULL COMMENT '回复人',
    `reply_user_id` int(11) NULL DEFAULT NULL COMMENT '回复人id',
    `remark`        varchar(255) NULL DEFAULT NULL COMMENT '备注',
    `create_time`   datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic COMMENT = '用户反馈表';

-- ----------------------------
-- Table：公告表
-- Desc：该表用于记录公告信息，包括公告主题、发起人、公告内容等。
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
    `subject`       varchar(200) NOT NULL COMMENT '公告主题',
    `author`        varchar(50) NOT NULL COMMENT '发起人',
    `author_id`     int(11) NOT NULL COMMENT '发起人id',
    `content`       text NULL DEFAULT NULL COMMENT '公告内容',
    `status`        int(11) NOT NULL DEFAULT 0 COMMENT '公告状态：0-未发布，1-已发布, -1-已删除',
    `create_time`   datetime NOT NULL COMMENT '发布时间',
    `update_time`   datetime NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic COMMENT = '公告表';

SET FOREIGN_KEY_CHECKS = 1;