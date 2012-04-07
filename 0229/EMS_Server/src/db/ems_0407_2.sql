/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : ems

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2012-04-07 00:54:37
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tb_book`
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
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
-- Records of tb_book
-- ----------------------------
INSERT INTO tb_book VALUES ('1', '雅思1', '人民教育出版社1', 'JODARN', '6363221', '2012-03-27 23:58:40', '2012-03-27 23:59:26');
INSERT INTO tb_book VALUES ('2', '托福', '人民教育出版社', 'LILI&&LUCY&&', '5555211', '2012-03-27 23:59:18', '2012-03-28 00:00:57');

-- ----------------------------
-- Table structure for `tb_class`
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_id` int(11) DEFAULT NULL,
  `class_name` varchar(20) DEFAULT NULL,
  `student_num` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_graduate` varchar(5) DEFAULT NULL COMMENT '是否已经毕业(0:未毕业,1:已毕业);已毕业的班级不需要参与排课',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_class
-- ----------------------------
INSERT INTO tb_class VALUES ('4', '7', '雅思10-1班', '60', '2012-03-19 22:36:08', '2012-03-19 22:40:22', null);
INSERT INTO tb_class VALUES ('5', '7', '计算机0901班', '80', '2012-03-19 22:38:01', null, null);
INSERT INTO tb_class VALUES ('6', '3', '雅思1001班', '35', '2012-03-19 23:04:56', '2012-03-21 21:15:41', null);
INSERT INTO tb_class VALUES ('7', '8', '1', '1', '2012-04-06 22:41:24', '2012-04-06 22:41:24', null);

-- ----------------------------
-- Table structure for `tb_code_table`
-- ----------------------------
DROP TABLE IF EXISTS `tb_code_table`;
CREATE TABLE `tb_code_table` (
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_code_table
-- ----------------------------
INSERT INTO tb_code_table VALUES ('1', 'Sex', 'S1', '1', '男', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('2', 'Sex', 'S2', '2', '女', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('3', 'Indicator', 'S1', '1', '是', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('4', 'Indicator', 'S2', '2', '否', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('5', 'StudentStatus', 'S1', '1', '在读', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('6', 'StudentStatus', 'S2', '2', '退学', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('7', 'StudentStatus', 'S3', '3', '毕业', null, null, '3', '1', '1');
INSERT INTO tb_code_table VALUES ('10', 'EducationLevel', 'S1', '1', '高二年纪学生', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('11', 'EducationLevel', 'S2', '2', '高中毕业或同等学历', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('12', 'EducationLevel', 'S3', '3', '本科', null, null, '3', '1', '1');
INSERT INTO tb_code_table VALUES ('13', 'EducationLevel', 'S4', '4', '专科', null, null, '4', '1', '1');
INSERT INTO tb_code_table VALUES ('14', 'GkType', 'S1', '1', '文史类', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('15', 'GkType', 'S2', '2', '理工类', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('16', 'GkType', 'S3', '3', '艺术类', null, null, '3', '1', '1');
INSERT INTO tb_code_table VALUES ('17', 'WeekOeInd', 'S1', '1', '单', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('18', 'WeekOeInd', 'S2', '2', '双', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('19', 'AchApprResult', 'S1', '1', '已通过', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('20', 'AchApprResult', 'S2', '2', '未通过', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('21', 'Week', 'S1', '1', '周一', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('22', 'Week', 'S2', '2', '周二', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('23', 'Week', 'S3', '3', '周三', null, null, '3', '1', '1');
INSERT INTO tb_code_table VALUES ('24', 'Week', 'S4', '4', '周四', null, null, '4', '1', '1');
INSERT INTO tb_code_table VALUES ('25', 'Week', 'S5', '5', '周五', null, null, '5', '1', '1');
INSERT INTO tb_code_table VALUES ('26', 'Week', 'S6', '6', '周六', null, null, '6', '1', '1');
INSERT INTO tb_code_table VALUES ('27', 'Week', 'S7', '7', '周日', null, null, '7', '1', '1');
INSERT INTO tb_code_table VALUES ('28', 'Lesson', 'S12', '12', '12节', null, null, '1', '1', '1');
INSERT INTO tb_code_table VALUES ('29', 'Lesson', 'S34', '34', '34节', null, null, '2', '1', '1');
INSERT INTO tb_code_table VALUES ('30', 'Lesson', 'S56', '56', '56节', null, null, '3', '1', '1');
INSERT INTO tb_code_table VALUES ('31', 'Lesson', 'S78', '78', '78节', null, null, '4', '1', '1');
INSERT INTO tb_code_table VALUES ('32', 'Lesson', 'S90', '90', '90节', null, null, '5', '1', '1');
INSERT INTO tb_code_table VALUES ('33', 'SysParams', 'defaultPassword', '123456', '默认密码', null, null, '1', '1', '1');

-- ----------------------------
-- Table structure for `tb_course`
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
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
-- Records of tb_course
-- ----------------------------
INSERT INTO tb_course VALUES ('1', '1130', '综合英语2', 'Comprehensive English 2 ', '2', '18', null, null, '2012-03-26 23:02:04', '2012-03-26 23:05:37');
INSERT INTO tb_course VALUES ('2', '5019', '创意训练:服装设计', 'Creative Thinking &Training and Coaching-Fishion Design', '0', '0', null, null, '2012-03-26 23:06:15', null);

-- ----------------------------
-- Table structure for `tb_grade`
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------
INSERT INTO tb_grade VALUES ('3', '2004级', '2012-03-18 00:00:00', null);
INSERT INTO tb_grade VALUES ('4', '2005级', '2012-03-18 00:00:00', null);
INSERT INTO tb_grade VALUES ('7', '2009级', '2012-03-18 00:00:00', '2012-03-18 00:00:00');
INSERT INTO tb_grade VALUES ('8', '2010级', '2012-04-06 21:16:25', '2012-04-06 21:16:25');

-- ----------------------------
-- Table structure for `tb_profess`
-- ----------------------------
DROP TABLE IF EXISTS `tb_profess`;
CREATE TABLE `tb_profess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `profess_name` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 103424 kB';

-- ----------------------------
-- Records of tb_profess
-- ----------------------------
INSERT INTO tb_profess VALUES ('1', '2', '商务管理', '2012-03-26 00:02:25', '2012-03-26 00:04:39');
INSERT INTO tb_profess VALUES ('2', '1', '计算机网络2', '2012-03-26 00:08:46', '2012-03-26 00:10:53');
INSERT INTO tb_profess VALUES ('3', '1', '商务会计', '2012-03-26 00:09:45', null);

-- ----------------------------
-- Table structure for `tb_project`
-- ----------------------------
DROP TABLE IF EXISTS `tb_project`;
CREATE TABLE `tb_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `project_comment` text,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_project
-- ----------------------------
INSERT INTO tb_project VALUES ('1', '预本硕连读项目', null, '2012-03-21 23:20:02', null);
INSERT INTO tb_project VALUES ('2', '法国兰斯管理学院本硕连读项目', null, '2012-03-21 23:36:59', null);
INSERT INTO tb_project VALUES ('4', '英国南安普顿大学艺术设计本科项目', null, '2012-03-21 23:39:32', '2012-03-21 23:39:42');

-- ----------------------------
-- Table structure for `tb_project_old`
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_old`;
CREATE TABLE `tb_project_old` (
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
-- Records of tb_project_old
-- ----------------------------
INSERT INTO tb_project_old VALUES ('100', '预本硕连读项目', '英国,荷兰,澳大利亚,美国', '有30多所高校可供选择', '3年国内+ 2年国外', '高中毕业生', '通过国内全部课程，且雅思达到6.0', '计算机网络|国际理财|国际贸易与商务|商务会计|物流管理(非制造业)|酒店管理');
INSERT INTO tb_project_old VALUES ('101', '法国兰斯管理学院本硕连读项目', '法国', '法国兰斯 管理学院（ESB）', '3年国内+2年或2年国外', '高中毕业生', '通过国内全部课程，通过法方面试，且雅思达到6.0或法语TEF达到550', '管理学');
INSERT INTO tb_project_old VALUES ('102', '中日本科项目', '日本', '日本静冈产业大学', '2年国内+2年国外', '高中毕业生', '通过国内全部课程，并且通过日语二级', '预科');
INSERT INTO tb_project_old VALUES ('103', '英国伦敦大学商务管理本科项目', '英国', '英国伦敦大学', '2年国内+2年国外', '高中毕业生', '通过国内全部课程，通过英方面试，且雅思达到6.5', '商务管理');
INSERT INTO tb_project_old VALUES ('104', '英国南安普顿大学艺术设计本科项目', '英国', '英国南安普顿大学', '2年国内+2年国外', '高中毕业生', '通过国内全部课程，通过英方面试', '视觉艺术设计专业|纺织时装及纤维专业');
INSERT INTO tb_project_old VALUES ('105', '英国南安普顿大学教育硕士项目', '英国', '英国南安普顿大学', '1年国内+1年国外', '本科毕业生', '通过国内全部课程通过英方面试', '教育机构管理与领导|教育实践与革新|计算机辅助教学');
INSERT INTO tb_project_old VALUES ('106', '美国布里诺大学工商管理硕士项目', '美国', '美国布里诺大学', '0.5年国内+1年国外（或1.5年国外）', '大专毕业生或本科毕业生', '通过国内全部课程，大学平均成绩75分以上或GPA2.5以上', '工商管理');
INSERT INTO tb_project_old VALUES ('107', '硕士预科项目', '各英语国家', '英语授课的相关院校', '1年国内+1年或2年国外', '本科毕业生', '达到申请院校的雅思要求', '预科');
INSERT INTO tb_project_old VALUES ('108', '美国本科预科', '美国', '有12所美国大学可供选择', '1年国内+4年国外', '高中毕业生', '通过国内全部课程，且雅思达到6.0', '预科');
INSERT INTO tb_project_old VALUES ('109', '本科预科项目', '各英语国家', '英语授课的相关院校', '1年国内+3年或4年国外', '高中毕业生', '达到申请院校的雅思要求', '预科');

-- ----------------------------
-- Table structure for `tb_room`
-- ----------------------------
DROP TABLE IF EXISTS `tb_room`;
CREATE TABLE `tb_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term_id` int(11) DEFAULT NULL,
  `room_name` varchar(100) DEFAULT NULL,
  `room_size` varchar(20) DEFAULT NULL,
  `room_status` varchar(20) DEFAULT NULL,
  `room_comment` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_room
-- ----------------------------
INSERT INTO tb_room VALUES ('1', '4', '北教楼501', '80', '正常', null, '2012-03-27 22:56:14', null);
INSERT INTO tb_room VALUES ('2', '4', '北教楼503', '100', '正常', '无', '2012-03-27 23:00:59', null);
INSERT INTO tb_room VALUES ('4', '3', '多媒体202', '100', '正常', '无', '2012-03-27 23:14:57', null);
INSERT INTO tb_room VALUES ('5', '3', '多媒体505', '100', '正常', '无', '2012-03-27 23:22:03', null);
INSERT INTO tb_room VALUES ('6', '3', '多媒体606', '100', '备用', '备用', '2012-03-27 23:34:51', null);

-- ----------------------------
-- Table structure for `tb_syllabus`
-- ----------------------------
DROP TABLE IF EXISTS `tb_syllabus`;
CREATE TABLE `tb_syllabus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term_id` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `lesson` char(2) DEFAULT NULL,
  `oe_ind` char(1) DEFAULT NULL,
  `week` char(1) DEFAULT NULL,
  `course_no` varchar(50) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_syllabus
-- ----------------------------
INSERT INTO tb_syllabus VALUES ('71', '4', '4', '12', '1', '1', '5019', '2', '6');
INSERT INTO tb_syllabus VALUES ('72', '4', '5', '12', '1', '1', '1130', '1', '5');
INSERT INTO tb_syllabus VALUES ('73', '4', '4', '34', '1', '1', '1130', '2', '5');

-- ----------------------------
-- Table structure for `tb_term`
-- ----------------------------
DROP TABLE IF EXISTS `tb_term`;
CREATE TABLE `tb_term` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term_name` varchar(50) DEFAULT NULL,
  `is_current_term` char(1) DEFAULT NULL,
  `syllabus_status` char(1) DEFAULT '2',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_term
-- ----------------------------
INSERT INTO tb_term VALUES ('1', '2010-2010第一学期', '2', '2', '2012-03-20 00:03:33', '2012-03-20 00:11:13');
INSERT INTO tb_term VALUES ('2', '2010-2010第二学期', '2', '2', '2012-03-27 22:50:56', null);
INSERT INTO tb_term VALUES ('3', '2011-2012第一学期', '2', '2', '2012-03-27 22:51:27', null);
INSERT INTO tb_term VALUES ('4', '2011-2012第二学期', '1', '1', '2012-03-27 22:52:02', null);

-- ----------------------------
-- Table structure for `ts_menu_info`
-- ----------------------------
DROP TABLE IF EXISTS `ts_menu_info`;
CREATE TABLE `ts_menu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  `leaf` char(1) CHARACTER SET gbk DEFAULT NULL,
  `module_id` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  `module_name` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  `parent_id` int(11) DEFAULT '-1',
  `ordinal` int(2) DEFAULT '0',
  `status` char(1) CHARACTER SET gbk DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ts_menu_info
-- ----------------------------
INSERT INTO ts_menu_info VALUES ('1', '实例', '2', null, null, '-1', '1', '1');
INSERT INTO ts_menu_info VALUES ('2', '基础信息管理', '2', null, null, '-1', '2', '1');
INSERT INTO ts_menu_info VALUES ('3', '排课管理', '2', null, null, '-1', '3', '1');
INSERT INTO ts_menu_info VALUES ('4', '打印证明', '2', null, null, '-1', '4', '1');
INSERT INTO ts_menu_info VALUES ('5', '实例-表格', '1', 'ems.biz.samples.xgridsample.XGridSample', null, '1', '1', '1');
INSERT INTO ts_menu_info VALUES ('6', '实例-表单', '1', 'ems.biz.samples.formsample.FormSample', null, '1', '2', '1');
INSERT INTO ts_menu_info VALUES ('7', '实例-对话框', '1', 'ems.biz.samples.dialogsample.DialogSample', null, '1', '3', '1');
INSERT INTO ts_menu_info VALUES ('8', 'CRUD实例', '1', 'ems.biz.samples.crud.User', null, '1', '4', '1');
INSERT INTO ts_menu_info VALUES ('9', '用户管理', '1', 'ems.biz.basicInfo.userManager.User', null, '2', '1', '1');
INSERT INTO ts_menu_info VALUES ('10', '年级管理', '1', 'ems.biz.basicInfo.gradeManager.Grade', null, '2', '2', '1');
INSERT INTO ts_menu_info VALUES ('11', '学期管理', '1', 'ems.biz.basicInfo.termManager.Term', null, '2', '4', '1');
INSERT INTO ts_menu_info VALUES ('12', '班级管理', '1', 'ems.biz.basicInfo.classManager.Class', null, '2', '3', '1');
INSERT INTO ts_menu_info VALUES ('13', '项目管理', '1', 'ems.biz.basicInfo.projectManager.Project', null, '2', '5', '1');
INSERT INTO ts_menu_info VALUES ('14', '专业管理', '1', 'ems.biz.basicInfo.professManager.Profess', null, '2', '6', '1');
INSERT INTO ts_menu_info VALUES ('15', '课程管理', '1', 'ems.biz.basicInfo.courseManager.Course', null, '2', '7', '1');
INSERT INTO ts_menu_info VALUES ('16', '教室管理', '1', 'ems.biz.basicInfo.roomManager.Room', null, '2', '8', '1');
INSERT INTO ts_menu_info VALUES ('17', '教材管理', '1', 'ems.biz.basicInfo.bookManager.Book', null, '2', '9', '1');
INSERT INTO ts_menu_info VALUES ('18', '成绩证明', '1', 'ems.biz.certificate.transcript.Transcript', null, '4', '1', '1');
INSERT INTO ts_menu_info VALUES ('19', '在学证明', '1', 'ems.biz.certificate.studentship.Studentship', null, '4', '2', '1');
INSERT INTO ts_menu_info VALUES ('20', '预毕业证明', '1', 'ems.biz.certificate.freshgraduate.FreshGraduate', null, '4', '3', '1');
INSERT INTO ts_menu_info VALUES ('21', '毕业证明', '1', 'ems.biz.certificate.graduate.Graduate', null, '4', '4', '1');
INSERT INTO ts_menu_info VALUES ('22', '就读证明', '1', 'ems.biz.certificate.study.Study', null, '4', '5', '1');
INSERT INTO ts_menu_info VALUES ('23', '排课(一览表)', '1', 'ems.biz.syllabus.syllabusplan.SyllabusPlan', null, '3', '1', '1');
INSERT INTO ts_menu_info VALUES ('24', '教师分表', '1', 'ems.biz.syllabus.syllabusbyteacher.SyllabusByTeacher', null, '3', '2', '1');
INSERT INTO ts_menu_info VALUES ('25', '班级分表', '1', 'ems.biz.syllabus.syllabusbyclass.SyllabusByClass', null, '3', '3', '1');
INSERT INTO ts_menu_info VALUES ('26', '课程分表', '1', 'ems.biz.syllabus.syllabusbycourse.SyllabusByCourse', null, '3', '4', '1');

-- ----------------------------
-- Table structure for `ts_role_info`
-- ----------------------------
DROP TABLE IF EXISTS `ts_role_info`;
CREATE TABLE `ts_role_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_cd` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ts_role_info
-- ----------------------------
INSERT INTO ts_role_info VALUES ('1', 'admin', '系统管理员');
INSERT INTO ts_role_info VALUES ('2', 'teacher', '教师');
INSERT INTO ts_role_info VALUES ('3', 'counselor', '辅导员');
INSERT INTO ts_role_info VALUES ('4', 'student', '学生');
INSERT INTO ts_role_info VALUES ('5', 'financial', '财务');
INSERT INTO ts_role_info VALUES ('6', 'senate', '教务处');

-- ----------------------------
-- Table structure for `ts_role_menu_rel`
-- ----------------------------
DROP TABLE IF EXISTS `ts_role_menu_rel`;
CREATE TABLE `ts_role_menu_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ts_role_menu_rel
-- ----------------------------
INSERT INTO ts_role_menu_rel VALUES ('1', '1', '1');
INSERT INTO ts_role_menu_rel VALUES ('2', '1', '2');
INSERT INTO ts_role_menu_rel VALUES ('3', '1', '3');
INSERT INTO ts_role_menu_rel VALUES ('4', '1', '4');
INSERT INTO ts_role_menu_rel VALUES ('5', '1', '5');
INSERT INTO ts_role_menu_rel VALUES ('6', '1', '6');
INSERT INTO ts_role_menu_rel VALUES ('7', '1', '7');
INSERT INTO ts_role_menu_rel VALUES ('8', '1', '8');
INSERT INTO ts_role_menu_rel VALUES ('9', '1', '9');
INSERT INTO ts_role_menu_rel VALUES ('10', '1', '10');
INSERT INTO ts_role_menu_rel VALUES ('11', '1', '11');
INSERT INTO ts_role_menu_rel VALUES ('12', '1', '12');
INSERT INTO ts_role_menu_rel VALUES ('13', '1', '13');
INSERT INTO ts_role_menu_rel VALUES ('14', '1', '14');
INSERT INTO ts_role_menu_rel VALUES ('15', '1', '15');
INSERT INTO ts_role_menu_rel VALUES ('16', '1', '16');
INSERT INTO ts_role_menu_rel VALUES ('17', '1', '17');
INSERT INTO ts_role_menu_rel VALUES ('18', '1', '18');
INSERT INTO ts_role_menu_rel VALUES ('19', '1', '19');
INSERT INTO ts_role_menu_rel VALUES ('20', '1', '20');
INSERT INTO ts_role_menu_rel VALUES ('21', '1', '21');
INSERT INTO ts_role_menu_rel VALUES ('22', '1', '22');
INSERT INTO ts_role_menu_rel VALUES ('23', '1', '23');
INSERT INTO ts_role_menu_rel VALUES ('24', '1', '24');
INSERT INTO ts_role_menu_rel VALUES ('25', '1', '25');
INSERT INTO ts_role_menu_rel VALUES ('26', '1', '26');

-- ----------------------------
-- Table structure for `ts_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `ts_user_info`;
CREATE TABLE `ts_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) CHARACTER SET gbk DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET gbk DEFAULT NULL,
  `password` char(32) CHARACTER SET gbk DEFAULT NULL,
  `email` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET gbk DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ts_user_info
-- ----------------------------
INSERT INTO ts_user_info VALUES ('1', 'admin', '系统管理员', '202cb962ac59075b964b07152d234b70', null, null);
INSERT INTO ts_user_info VALUES ('2', 'teacher', '教师1', '202cb962ac59075b964b07152d234b70', null, null);
INSERT INTO ts_user_info VALUES ('3', 'fdy', '辅导员1', '202cb962ac59075b964b07152d234b70', '', '');
INSERT INTO ts_user_info VALUES ('4', 'student', '学生1', '202cb962ac59075b964b07152d234b70', null, null);
INSERT INTO ts_user_info VALUES ('5', 'caiwu', '财务1', '202cb962ac59075b964b07152d234b70', null, null);
INSERT INTO ts_user_info VALUES ('6', 'jwc', '教务处1', '202cb962ac59075b964b07152d234b70', null, null);

-- ----------------------------
-- Table structure for `ts_user_role_rel`
-- ----------------------------
DROP TABLE IF EXISTS `ts_user_role_rel`;
CREATE TABLE `ts_user_role_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ts_user_role_rel
-- ----------------------------
INSERT INTO ts_user_role_rel VALUES ('1', '1', '1');
INSERT INTO ts_user_role_rel VALUES ('2', '3', '3');
INSERT INTO ts_user_role_rel VALUES ('3', '4', '4');
