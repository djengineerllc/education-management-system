/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : ems

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2012-03-29 23:10:09
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(100) DEFAULT NULL,
  `publish_name` varchar(200) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `isbn_no` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO book VALUES ('1', '雅思1', '人民教育出版社1', 'JODARN', '6363221', '2012-03-27 23:58:40', '2012-03-27 23:59:26');
INSERT INTO book VALUES ('2', '托福', '人民教育出版社', 'LILI&&LUCY&&', '5555211', '2012-03-27 23:59:18', '2012-03-28 00:00:57');

-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_id` int(11) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `student_num` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO class VALUES ('4', '7', '雅思10-1班', '60', '2012-03-19 22:36:08', '2012-03-19 22:40:22');
INSERT INTO class VALUES ('5', '7', '计算机0901班', '80', '2012-03-19 22:38:01', null);
INSERT INTO class VALUES ('6', '3', '雅思1001班', '35', '2012-03-19 23:04:56', '2012-03-21 21:15:41');

-- ----------------------------
-- Table structure for `code_table`
-- ----------------------------
DROP TABLE IF EXISTS `code_table`;
CREATE TABLE `code_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_type` varchar(100) DEFAULT NULL,
  `code_key` varchar(100) DEFAULT NULL,
  `code_value` varchar(100) DEFAULT NULL,
  `code_name` varchar(200) DEFAULT NULL,
  `code_desc` varchar(200) DEFAULT NULL,
  `code_group` varchar(100) DEFAULT NULL,
  `ordinal` int(2) DEFAULT NULL,
  `can_modify_ind` char(1) DEFAULT '1',
  `status` char(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_table
-- ----------------------------
INSERT INTO code_table VALUES ('1', 'Sex', 'S1', '1', '男', null, null, '1', '1', '1');
INSERT INTO code_table VALUES ('2', 'Sex', 'S2', '2', '女', null, null, '2', '1', '1');
INSERT INTO code_table VALUES ('3', 'Indicator', 'S1', '1', '是', null, null, '1', '1', '1');
INSERT INTO code_table VALUES ('4', 'Indicator', 'S2', '2', '否', null, null, '2', '1', '1');
INSERT INTO code_table VALUES ('5', 'StudentStatus', 'S1', '1', '在读', null, null, '1', '1', '1');
INSERT INTO code_table VALUES ('6', 'StudentStatus', 'S2', '2', '退学', null, null, '2', '1', '1');
INSERT INTO code_table VALUES ('7', 'StudentStatus', 'S3', '3', '毕业', null, null, '3', '1', '1');
INSERT INTO code_table VALUES ('10', 'EducationLevel', 'S1', '1', '高二年纪学生', null, null, '1', '1', '1');
INSERT INTO code_table VALUES ('11', 'EducationLevel', 'S2', '2', '高中毕业或同等学历', null, null, '2', '1', '1');
INSERT INTO code_table VALUES ('12', 'EducationLevel', 'S3', '3', '本科', null, null, '3', '1', '1');
INSERT INTO code_table VALUES ('13', 'EducationLevel', 'S4', '4', '专科', null, null, '4', '1', '1');
INSERT INTO code_table VALUES ('14', 'GkType', 'S1', '1', '文史类', null, null, '1', '1', '1');
INSERT INTO code_table VALUES ('15', 'GkType', 'S2', '2', '理工类', null, null, '2', '1', '1');
INSERT INTO code_table VALUES ('16', 'GkType', 'S3', '3', '艺术类', null, null, '3', '1', '1');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_no` varchar(50) DEFAULT NULL,
  `course_name` varchar(100) DEFAULT NULL,
  `course_eng_name` varchar(100) DEFAULT NULL,
  `course_score` double DEFAULT NULL,
  `course_time` int(11) DEFAULT NULL,
  `course_type` varchar(50) DEFAULT NULL,
  `course_comment` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO course VALUES ('1', '1130', '综合英语2', 'Comprehensive English 2 ', '2', '18', null, null, '2012-03-26 23:02:04', '2012-03-26 23:05:37');
INSERT INTO course VALUES ('2', '5019', '创意训练:服装设计', 'Creative Thinking &Training and Coaching-Fishion Design', '0', '0', null, null, '2012-03-26 23:06:15', null);

-- ----------------------------
-- Table structure for `ems_menu`
-- ----------------------------
DROP TABLE IF EXISTS `ems_menu`;
CREATE TABLE `ems_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `leaf` bit(1) DEFAULT NULL,
  `module_id` varchar(255) DEFAULT NULL,
  `module_name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ems_menu
-- ----------------------------
INSERT INTO ems_menu VALUES ('247', '系统管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('248', '班级管理', '', 'bjgl', '班级管理', '247');
INSERT INTO ems_menu VALUES ('249', '学期管理', '', 'xqgl', '学期管理', '247');
INSERT INTO ems_menu VALUES ('250', '课程管理', '', 'kcgl', '课程管理', '247');
INSERT INTO ems_menu VALUES ('251', '年级管理', '', 'njgl', '年级管理', '247');
INSERT INTO ems_menu VALUES ('252', '管理员管理', '', 'glygl', '管理员管理', '247');
INSERT INTO ems_menu VALUES ('253', '专业管理', '', 'zygl', '专业管理', '247');
INSERT INTO ems_menu VALUES ('254', '物资管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('255', '教材管理', '', 'jcgl', '教材管理', '254');
INSERT INTO ems_menu VALUES ('256', '教室管理', '', 'jsgl', '教室管理', '254');
INSERT INTO ems_menu VALUES ('257', '权限管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('258', '菜单管理', '', 'cdgl', '菜单管理', '257');
INSERT INTO ems_menu VALUES ('259', '用户角色管理', '', 'yhjsgl', '用户角色管理', '257');
INSERT INTO ems_menu VALUES ('260', '角色管理', '', 'jsgl', '角色管理', '257');
INSERT INTO ems_menu VALUES ('261', '用户管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('262', '辅导员管理', '', 'fdygl', '辅导员管理', '261');
INSERT INTO ems_menu VALUES ('263', '学生管理', '', 'xsgl', '学生管理', '261');
INSERT INTO ems_menu VALUES ('264', '教师管理', '', 'jsgl', '教师管理', '261');
INSERT INTO ems_menu VALUES ('265', '报名管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('266', '手工导入', '', 'bmsgdr', '手工导入', '265');
INSERT INTO ems_menu VALUES ('267', '批量导入', '', 'bmpldr', '批量导入', '265');
INSERT INTO ems_menu VALUES ('268', '报名查询', '', 'bmcx', '报名查询', '265');
INSERT INTO ems_menu VALUES ('269', '开课管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('270', '必须课管理', '', 'bxkgl', '必须课管理', '269');
INSERT INTO ems_menu VALUES ('271', '选须课管理', '', 'xxkgl', '选须课管理', '269');
INSERT INTO ems_menu VALUES ('272', '排课管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('273', '自动排课', '', 'zdpk', '自动排课', '272');
INSERT INTO ems_menu VALUES ('274', '手工排课', '', 'sgpk', '手工排课', '272');
INSERT INTO ems_menu VALUES ('275', '我的选修课', '', null, null, null);
INSERT INTO ems_menu VALUES ('276', '选修课管理', '', 'xxkgl', '选修课管理', '275');
INSERT INTO ems_menu VALUES ('277', '成绩管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('278', '批量导入', '', 'cjpldr', '批量导入', '277');
INSERT INTO ems_menu VALUES ('279', '手工导入', '', 'cjsgdr', '手工导入', '277');
INSERT INTO ems_menu VALUES ('280', '必修课成绩', '', 'bxkcj', '必修课成绩', '277');
INSERT INTO ems_menu VALUES ('281', '选修课成绩', '', 'xxkcj', '选修课成绩', '277');
INSERT INTO ems_menu VALUES ('282', '考勤管理', '', null, null, null);
INSERT INTO ems_menu VALUES ('283', '考勤统计', '', 'kqtj', '考勤统计', '282');
INSERT INTO ems_menu VALUES ('284', '批量导入', '', 'kqpldr', '批量导入', '282');
INSERT INTO ems_menu VALUES ('285', '手工导入', '', 'kqsgdr', '手工导入', '282');
INSERT INTO ems_menu VALUES ('286', '我的课表', '', null, null, null);
INSERT INTO ems_menu VALUES ('287', '查询', '', 'wdkbcx', '查询', '286');
INSERT INTO ems_menu VALUES ('288', '个人信息', '', null, null, null);
INSERT INTO ems_menu VALUES ('289', '密码修改', '', 'mmxg', '密码修改', '288');
INSERT INTO ems_menu VALUES ('290', '打印证明', '', null, null, null);
INSERT INTO ems_menu VALUES ('291', '成绩证明', '', 'cjzm', '成绩证明', '290');
INSERT INTO ems_menu VALUES ('292', '在学证明', '', 'zxzm', '在学证明', '290');
INSERT INTO ems_menu VALUES ('293', '就读证明', '', 'jdzm', '就读证明', '290');
INSERT INTO ems_menu VALUES ('294', '毕业证明', '', 'byzm', '毕业证明', '290');
INSERT INTO ems_menu VALUES ('295', '预毕业证明', '', 'ybyzm', '预毕业证明', '290');
INSERT INTO ems_menu VALUES ('296', '实例', '', null, null, null);
INSERT INTO ems_menu VALUES ('297', '测试节点1', '', null, null, '296');
INSERT INTO ems_menu VALUES ('298', '实例-表格', '', 'ems.biz.samples.xgridsample.XGridSample', 'XGrid实例', '296');
INSERT INTO ems_menu VALUES ('299', '实例-表单', '', 'ems.biz.samples.formsample.FormSample', 'Form实例', '296');
INSERT INTO ems_menu VALUES ('300', '实例-对话框', '', 'ems.biz.samples.dialogsample.DialogSample', 'Dialog实例', '296');
INSERT INTO ems_menu VALUES ('301', 'CRUD实例', '', 'ems.biz.samples.crud.User', 'CRUD实例', '296');
INSERT INTO ems_menu VALUES ('302', '测试节点1.1', '', 'ems.biz.samples.dialogsample.DialogSample', '测试节点1.1Title', '297');
INSERT INTO ems_menu VALUES ('303', '测试节点1.2', '', 'ems.biz.samples.dialogsample.DialogSample', '测试节点1.2Title', '297');

-- ----------------------------
-- Table structure for `ems_role`
-- ----------------------------
DROP TABLE IF EXISTS `ems_role`;
CREATE TABLE `ems_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_cd` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ems_role
-- ----------------------------
INSERT INTO ems_role VALUES ('1', 'R0001', '系统管理员');
INSERT INTO ems_role VALUES ('2', 'R0002', '教师');
INSERT INTO ems_role VALUES ('3', 'R0003', '辅导员');
INSERT INTO ems_role VALUES ('4', 'R0004', '学生');
INSERT INTO ems_role VALUES ('5', 'R0005', '财务');
INSERT INTO ems_role VALUES ('6', 'R0006', '教务处');

-- ----------------------------
-- Table structure for `ems_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `ems_role_menu`;
CREATE TABLE `ems_role_menu` (
  `fk_role_id` int(11) NOT NULL,
  `fk_menu_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_role_id`,`fk_menu_id`),
  KEY `FKFE566914EF2F3213` (`fk_role_id`),
  KEY `FKFE566914CCAF0733` (`fk_menu_id`),
  CONSTRAINT `FKFE566914CCAF0733` FOREIGN KEY (`fk_menu_id`) REFERENCES `ems_menu` (`id`),
  CONSTRAINT `FKFE566914EF2F3213` FOREIGN KEY (`fk_role_id`) REFERENCES `ems_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ems_role_menu
-- ----------------------------
INSERT INTO ems_role_menu VALUES ('1', '247');
INSERT INTO ems_role_menu VALUES ('1', '252');
INSERT INTO ems_role_menu VALUES ('1', '257');
INSERT INTO ems_role_menu VALUES ('1', '258');
INSERT INTO ems_role_menu VALUES ('1', '259');
INSERT INTO ems_role_menu VALUES ('1', '260');
INSERT INTO ems_role_menu VALUES ('1', '261');
INSERT INTO ems_role_menu VALUES ('1', '262');
INSERT INTO ems_role_menu VALUES ('1', '263');
INSERT INTO ems_role_menu VALUES ('1', '264');
INSERT INTO ems_role_menu VALUES ('1', '296');
INSERT INTO ems_role_menu VALUES ('1', '297');
INSERT INTO ems_role_menu VALUES ('1', '298');
INSERT INTO ems_role_menu VALUES ('1', '299');
INSERT INTO ems_role_menu VALUES ('1', '300');
INSERT INTO ems_role_menu VALUES ('1', '301');
INSERT INTO ems_role_menu VALUES ('1', '302');
INSERT INTO ems_role_menu VALUES ('1', '303');
INSERT INTO ems_role_menu VALUES ('2', '267');
INSERT INTO ems_role_menu VALUES ('2', '276');
INSERT INTO ems_role_menu VALUES ('2', '277');
INSERT INTO ems_role_menu VALUES ('2', '278');
INSERT INTO ems_role_menu VALUES ('2', '280');
INSERT INTO ems_role_menu VALUES ('2', '281');
INSERT INTO ems_role_menu VALUES ('2', '282');
INSERT INTO ems_role_menu VALUES ('2', '283');
INSERT INTO ems_role_menu VALUES ('2', '284');
INSERT INTO ems_role_menu VALUES ('2', '286');
INSERT INTO ems_role_menu VALUES ('2', '287');
INSERT INTO ems_role_menu VALUES ('3', '261');
INSERT INTO ems_role_menu VALUES ('3', '263');
INSERT INTO ems_role_menu VALUES ('3', '265');
INSERT INTO ems_role_menu VALUES ('3', '267');
INSERT INTO ems_role_menu VALUES ('3', '277');
INSERT INTO ems_role_menu VALUES ('3', '278');
INSERT INTO ems_role_menu VALUES ('3', '280');
INSERT INTO ems_role_menu VALUES ('3', '281');
INSERT INTO ems_role_menu VALUES ('3', '282');
INSERT INTO ems_role_menu VALUES ('3', '283');
INSERT INTO ems_role_menu VALUES ('3', '284');
INSERT INTO ems_role_menu VALUES ('3', '287');
INSERT INTO ems_role_menu VALUES ('3', '290');
INSERT INTO ems_role_menu VALUES ('3', '291');
INSERT INTO ems_role_menu VALUES ('3', '292');
INSERT INTO ems_role_menu VALUES ('3', '293');
INSERT INTO ems_role_menu VALUES ('3', '294');
INSERT INTO ems_role_menu VALUES ('3', '295');
INSERT INTO ems_role_menu VALUES ('4', '261');
INSERT INTO ems_role_menu VALUES ('4', '263');
INSERT INTO ems_role_menu VALUES ('4', '265');
INSERT INTO ems_role_menu VALUES ('4', '276');
INSERT INTO ems_role_menu VALUES ('4', '277');
INSERT INTO ems_role_menu VALUES ('4', '280');
INSERT INTO ems_role_menu VALUES ('4', '281');
INSERT INTO ems_role_menu VALUES ('4', '282');
INSERT INTO ems_role_menu VALUES ('4', '283');
INSERT INTO ems_role_menu VALUES ('4', '286');
INSERT INTO ems_role_menu VALUES ('4', '287');
INSERT INTO ems_role_menu VALUES ('5', '265');
INSERT INTO ems_role_menu VALUES ('5', '287');
INSERT INTO ems_role_menu VALUES ('6', '247');
INSERT INTO ems_role_menu VALUES ('6', '248');
INSERT INTO ems_role_menu VALUES ('6', '249');
INSERT INTO ems_role_menu VALUES ('6', '250');
INSERT INTO ems_role_menu VALUES ('6', '251');
INSERT INTO ems_role_menu VALUES ('6', '253');
INSERT INTO ems_role_menu VALUES ('6', '254');
INSERT INTO ems_role_menu VALUES ('6', '255');
INSERT INTO ems_role_menu VALUES ('6', '256');
INSERT INTO ems_role_menu VALUES ('6', '265');
INSERT INTO ems_role_menu VALUES ('6', '267');
INSERT INTO ems_role_menu VALUES ('6', '272');
INSERT INTO ems_role_menu VALUES ('6', '273');
INSERT INTO ems_role_menu VALUES ('6', '274');
INSERT INTO ems_role_menu VALUES ('6', '277');
INSERT INTO ems_role_menu VALUES ('6', '278');
INSERT INTO ems_role_menu VALUES ('6', '280');
INSERT INTO ems_role_menu VALUES ('6', '281');
INSERT INTO ems_role_menu VALUES ('6', '282');
INSERT INTO ems_role_menu VALUES ('6', '283');
INSERT INTO ems_role_menu VALUES ('6', '284');
INSERT INTO ems_role_menu VALUES ('6', '286');
INSERT INTO ems_role_menu VALUES ('6', '287');
INSERT INTO ems_role_menu VALUES ('6', '290');
INSERT INTO ems_role_menu VALUES ('6', '291');
INSERT INTO ems_role_menu VALUES ('6', '292');
INSERT INTO ems_role_menu VALUES ('6', '293');
INSERT INTO ems_role_menu VALUES ('6', '294');
INSERT INTO ems_role_menu VALUES ('6', '295');

-- ----------------------------
-- Table structure for `ems_user`
-- ----------------------------
DROP TABLE IF EXISTS `ems_user`;
CREATE TABLE `ems_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) DEFAULT NULL,
  `user_Name` varchar(20) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `passwordMail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ems_user
-- ----------------------------
INSERT INTO ems_user VALUES ('1', 'admin', '系统管理员', 'admin', null);
INSERT INTO ems_user VALUES ('2', 'teacher', '教师1', 'teacher', null);
INSERT INTO ems_user VALUES ('3', 'fdy', '辅导员1', 'fdy', null);
INSERT INTO ems_user VALUES ('4', 'student', '学生1', 'student', null);
INSERT INTO ems_user VALUES ('5', 'caiwu', '财务1', 'caiwu', null);
INSERT INTO ems_user VALUES ('6', 'jwc', '教务处1', 'jwc', null);

-- ----------------------------
-- Table structure for `ems_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `ems_user_role`;
CREATE TABLE `ems_user_role` (
  `fk_user_id` int(11) NOT NULL,
  `fk_role_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_user_id`,`fk_role_id`),
  KEY `FKFE8E35F6EF2F3213` (`fk_role_id`),
  KEY `FKFE8E35F699AC74B3` (`fk_user_id`),
  CONSTRAINT `FKFE8E35F699AC74B3` FOREIGN KEY (`fk_user_id`) REFERENCES `ems_user` (`id`),
  CONSTRAINT `FKFE8E35F6EF2F3213` FOREIGN KEY (`fk_role_id`) REFERENCES `ems_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of ems_user_role
-- ----------------------------
INSERT INTO ems_user_role VALUES ('1', '1');
INSERT INTO ems_user_role VALUES ('2', '2');
INSERT INTO ems_user_role VALUES ('3', '3');
INSERT INTO ems_user_role VALUES ('4', '4');
INSERT INTO ems_user_role VALUES ('5', '5');
INSERT INTO ems_user_role VALUES ('6', '6');

-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO grade VALUES ('3', '2004级', '2012-03-18 00:00:00', null);
INSERT INTO grade VALUES ('4', '2005级', '2012-03-18 00:00:00', null);
INSERT INTO grade VALUES ('7', '2009级', '2012-03-18 00:00:00', '2012-03-18 00:00:00');
INSERT INTO grade VALUES ('8', '2010级', '2012-03-18 00:00:00', '2012-03-18 00:00:00');

-- ----------------------------
-- Table structure for `profess`
-- ----------------------------
DROP TABLE IF EXISTS `profess`;
CREATE TABLE `profess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `profess_name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 103424 kB';

-- ----------------------------
-- Records of profess
-- ----------------------------
INSERT INTO profess VALUES ('1', '2', '商务管理', '2012-03-26 00:02:25', '2012-03-26 00:04:39');
INSERT INTO profess VALUES ('2', '1', '计算机网络2', '2012-03-26 00:08:46', '2012-03-26 00:10:53');
INSERT INTO profess VALUES ('3', '1', '商务会计', '2012-03-26 00:09:45', null);

-- ----------------------------
-- Table structure for `project`
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `project_comment` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO project VALUES ('1', '预本硕连读项目', null, '2012-03-21 23:20:02', null);
INSERT INTO project VALUES ('2', '法国兰斯管理学院本硕连读项目', null, '2012-03-21 23:36:59', null);
INSERT INTO project VALUES ('4', '英国南安普顿大学艺术设计本科项目', null, '2012-03-21 23:39:32', '2012-03-21 23:39:42');

-- ----------------------------
-- Table structure for `project_old`
-- ----------------------------
DROP TABLE IF EXISTS `project_old`;
CREATE TABLE `project_old` (
  `id` int(11) NOT NULL,
  `project_name` varchar(100) DEFAULT NULL,
  `study_country` varchar(200) DEFAULT NULL,
  `study_school` varchar(100) DEFAULT NULL,
  `education_system` varchar(100) DEFAULT NULL,
  `zsdx` varchar(50) DEFAULT NULL,
  `cgtj` varchar(100) DEFAULT NULL,
  `professional` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='InnoDB free: 106496 kB';

-- ----------------------------
-- Records of project_old
-- ----------------------------
INSERT INTO project_old VALUES ('100', '预本硕连读项目', '英国,荷兰,澳大利亚,美国', '有30多所高校可供选择', '3年国内+ 2年国外', '高中毕业生', '通过国内全部课程，且雅思达到6.0', '计算机网络|国际理财|国际贸易与商务|商务会计|物流管理(非制造业)|酒店管理');
INSERT INTO project_old VALUES ('101', '法国兰斯管理学院本硕连读项目', '法国', '法国兰斯 管理学院（ESB）', '3年国内+2年或2年国外', '高中毕业生', '通过国内全部课程，通过法方面试，且雅思达到6.0或法语TEF达到550', '管理学');
INSERT INTO project_old VALUES ('102', '中日本科项目', '日本', '日本静冈产业大学', '2年国内+2年国外', '高中毕业生', '通过国内全部课程，并且通过日语二级', '预科');
INSERT INTO project_old VALUES ('103', '英国伦敦大学商务管理本科项目', '英国', '英国伦敦大学', '2年国内+2年国外', '高中毕业生', '通过国内全部课程，通过英方面试，且雅思达到6.5', '商务管理');
INSERT INTO project_old VALUES ('104', '英国南安普顿大学艺术设计本科项目', '英国', '英国南安普顿大学', '2年国内+2年国外', '高中毕业生', '通过国内全部课程，通过英方面试', '视觉艺术设计专业|纺织时装及纤维专业');
INSERT INTO project_old VALUES ('105', '英国南安普顿大学教育硕士项目', '英国', '英国南安普顿大学', '1年国内+1年国外', '本科毕业生', '通过国内全部课程通过英方面试', '教育机构管理与领导|教育实践与革新|计算机辅助教学');
INSERT INTO project_old VALUES ('106', '美国布里诺大学工商管理硕士项目', '美国', '美国布里诺大学', '0.5年国内+1年国外（或1.5年国外）', '大专毕业生或本科毕业生', '通过国内全部课程，大学平均成绩75分以上或GPA2.5以上', '工商管理');
INSERT INTO project_old VALUES ('107', '硕士预科项目', '各英语国家', '英语授课的相关院校', '1年国内+1年或2年国外', '本科毕业生', '达到申请院校的雅思要求', '预科');
INSERT INTO project_old VALUES ('108', '美国本科预科', '美国', '有12所美国大学可供选择', '1年国内+4年国外', '高中毕业生', '通过国内全部课程，且雅思达到6.0', '预科');
INSERT INTO project_old VALUES ('109', '本科预科项目', '各英语国家', '英语授课的相关院校', '1年国内+3年或4年国外', '高中毕业生', '达到申请院校的雅思要求', '预科');

-- ----------------------------
-- Table structure for `room`
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_name` varchar(100) DEFAULT NULL,
  `term_id` int(11) DEFAULT NULL,
  `room_size` varchar(20) DEFAULT NULL,
  `room_status` varchar(20) DEFAULT NULL,
  `room_comment` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO room VALUES ('1', '北教楼501', '4', '80', '正常', null, '2012-03-27 22:56:14', null);
INSERT INTO room VALUES ('2', '北教楼503', '4', '100', '正常', '无', '2012-03-27 23:00:59', null);
INSERT INTO room VALUES ('4', '多媒体202', '3', '100', '正常', '无', '2012-03-27 23:14:57', null);
INSERT INTO room VALUES ('5', '多媒体505', '3', '100', '正常', '无', '2012-03-27 23:22:03', null);
INSERT INTO room VALUES ('6', '多媒体606', '3', '100', '备用', '备用', '2012-03-27 23:34:51', null);

-- ----------------------------
-- Table structure for `term`
-- ----------------------------
DROP TABLE IF EXISTS `term`;
CREATE TABLE `term` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term_name` varchar(50) DEFAULT NULL,
  `is_current_term` char(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of term
-- ----------------------------
INSERT INTO term VALUES ('1', '2006-2007第一学期', '0', '2012-03-20 00:03:33', '2012-03-20 00:11:13');
INSERT INTO term VALUES ('2', '2007-2007第二学期', '0', '2012-03-27 22:50:56', null);
INSERT INTO term VALUES ('3', '2011-2012第一学期', '0', '2012-03-27 22:51:27', null);
INSERT INTO term VALUES ('4', '2011-2012第二学期', '1', '2012-03-27 22:52:02', null);
