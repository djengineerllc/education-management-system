-- MySQL dump 10.10
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_book`
--

DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id` int(11) NOT NULL auto_increment,
  `book_name` varchar(100) default NULL,
  `publish_name` varchar(200) default NULL,
  `author` varchar(100) default NULL,
  `isbn_no` varchar(50) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tb_book`
--


/*!40000 ALTER TABLE `tb_book` DISABLE KEYS */;
LOCK TABLES `tb_book` WRITE;
INSERT INTO `tb_book` VALUES (1,'雅思1','人民教育出版社1','JODARN','6363221','2012-03-27 23:58:40','2012-03-27 23:59:26'),(2,'托福','人民教育出版社','LILI&&LUCY&&','5555211','2012-03-27 23:59:18','2012-03-28 00:00:57');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_book` ENABLE KEYS */;

--
-- Table structure for table `tb_class`
--

DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class` (
  `id` int(11) NOT NULL auto_increment,
  `grade_id` int(11) default NULL,
  `class_name` varchar(20) default NULL,
  `student_num` int(11) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `is_graduate` varchar(5) default NULL COMMENT '是否已经毕业(0:未毕业,1:已毕业);已毕业的班级不需要参与排课',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_class`
--


/*!40000 ALTER TABLE `tb_class` DISABLE KEYS */;
LOCK TABLES `tb_class` WRITE;
INSERT INTO `tb_class` VALUES (4,7,'雅思10-1班',60,'2012-03-19 22:36:08','2012-03-19 22:40:22',NULL),(5,7,'计算机0901班',80,'2012-03-19 22:38:01',NULL,NULL),(6,3,'雅思1001班',35,'2012-03-19 23:04:56','2012-03-21 21:15:41',NULL),(7,8,'1',1,'2012-04-06 22:41:24','2012-04-06 22:41:24',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_class` ENABLE KEYS */;

--
-- Table structure for table `tb_code_table`
--

DROP TABLE IF EXISTS `tb_code_table`;
CREATE TABLE `tb_code_table` (
  `id` int(11) NOT NULL auto_increment,
  `code_type` varchar(100) default NULL,
  `code_key` varchar(100) default NULL,
  `code_value` varchar(100) default NULL,
  `code_name` varchar(200) default NULL,
  `code_desc` varchar(200) default NULL,
  `code_group` varchar(100) default NULL,
  `ordinal` int(2) default NULL,
  `can_modify_ind` char(1) default '1',
  `status` char(1) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_code_table`
--


/*!40000 ALTER TABLE `tb_code_table` DISABLE KEYS */;
LOCK TABLES `tb_code_table` WRITE;
INSERT INTO `tb_code_table` VALUES (1,'Sex','S1','1','男',NULL,NULL,1,'1','1'),(2,'Sex','S2','2','女',NULL,NULL,2,'1','1'),(3,'Indicator','S1','1','是',NULL,NULL,1,'1','1'),(4,'Indicator','S2','2','否',NULL,NULL,2,'1','1'),(5,'StudentStatus','S1','1','在读',NULL,NULL,1,'1','1'),(6,'StudentStatus','S2','2','退学',NULL,NULL,2,'1','1'),(7,'StudentStatus','S3','3','毕业',NULL,NULL,3,'1','1'),(10,'EducationLevel','S1','1','高二年纪学生',NULL,NULL,1,'1','1'),(11,'EducationLevel','S2','2','高中毕业或同等学历',NULL,NULL,2,'1','1'),(12,'EducationLevel','S3','3','本科',NULL,NULL,3,'1','1'),(13,'EducationLevel','S4','4','专科',NULL,NULL,4,'1','1'),(14,'GkType','S1','1','文史类',NULL,NULL,1,'1','1'),(15,'GkType','S2','2','理工类',NULL,NULL,2,'1','1'),(16,'GkType','S3','3','艺术类',NULL,NULL,3,'1','1'),(17,'WeekOeInd','S1','1','单',NULL,NULL,1,'1','1'),(18,'WeekOeInd','S2','2','双',NULL,NULL,2,'1','1'),(19,'AchApprResult','S1','1','已通过',NULL,NULL,1,'1','1'),(20,'AchApprResult','S2','2','未通过',NULL,NULL,2,'1','1'),(21,'Week','S1','1','周一',NULL,NULL,1,'1','1'),(22,'Week','S2','2','周二',NULL,NULL,2,'1','1'),(23,'Week','S3','3','周三',NULL,NULL,3,'1','1'),(24,'Week','S4','4','周四',NULL,NULL,4,'1','1'),(25,'Week','S5','5','周五',NULL,NULL,5,'1','1'),(26,'Week','S6','6','周六',NULL,NULL,6,'1','1'),(27,'Week','S7','7','周日',NULL,NULL,7,'1','1'),(28,'Lesson','S12','12','12节',NULL,NULL,1,'1','1'),(29,'Lesson','S34','34','34节',NULL,NULL,2,'1','1'),(30,'Lesson','S56','56','56节',NULL,NULL,3,'1','1'),(31,'Lesson','S78','78','78节',NULL,NULL,4,'1','1'),(32,'Lesson','S90','90','90节',NULL,NULL,5,'1','1'),(33,'SysParams','defaultPassword','123456','默认密码',NULL,NULL,1,'1','1'),(34,'roomStatus','S1','1','使用',NULL,NULL,1,'1','1'),(35,'roomStatus','S2','2','未使用',NULL,NULL,2,'1','1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_code_table` ENABLE KEYS */;

--
-- Table structure for table `tb_course`
--

DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `id` int(11) NOT NULL auto_increment,
  `course_no` varchar(50) default NULL,
  `course_name` varchar(100) default NULL,
  `course_eng_name` varchar(100) default NULL,
  `course_score` double default NULL,
  `course_time` int(11) default NULL,
  `course_type` varchar(50) default NULL,
  `course_comment` varchar(200) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_course`
--


/*!40000 ALTER TABLE `tb_course` DISABLE KEYS */;
LOCK TABLES `tb_course` WRITE;
INSERT INTO `tb_course` VALUES (1,'1130','综合英语2','Comprehensive English 2 ',2,18,NULL,NULL,'2012-03-26 23:02:04','2012-03-26 23:05:37'),(2,'5019','创意训练:服装设计','Creative Thinking &Training and Coaching-Fishion Design',0,0,NULL,NULL,'2012-03-26 23:06:15',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_course` ENABLE KEYS */;

--
-- Table structure for table `tb_grade`
--

DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade` (
  `id` int(11) NOT NULL auto_increment,
  `grade_name` varchar(11) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_grade`
--


/*!40000 ALTER TABLE `tb_grade` DISABLE KEYS */;
LOCK TABLES `tb_grade` WRITE;
INSERT INTO `tb_grade` VALUES (3,'2004级','2012-03-18 00:00:00',NULL),(4,'2005级','2012-03-18 00:00:00',NULL),(7,'2009级','2012-03-18 00:00:00','2012-03-18 00:00:00'),(8,'2010级','2012-04-06 21:16:25','2012-04-06 21:16:25');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_grade` ENABLE KEYS */;

--
-- Table structure for table `tb_profess`
--

DROP TABLE IF EXISTS `tb_profess`;
CREATE TABLE `tb_profess` (
  `id` int(11) NOT NULL auto_increment,
  `project_id` int(11) default NULL,
  `profess_name` varchar(100) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 103424 kB';

--
-- Dumping data for table `tb_profess`
--


/*!40000 ALTER TABLE `tb_profess` DISABLE KEYS */;
LOCK TABLES `tb_profess` WRITE;
INSERT INTO `tb_profess` VALUES (1,2,'商务管理','2012-03-26 00:02:25','2012-03-26 00:04:39'),(2,1,'计算机网络2','2012-03-26 00:08:46','2012-03-26 00:10:53'),(3,1,'商务会计','2012-03-26 00:09:45',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_profess` ENABLE KEYS */;

--
-- Table structure for table `tb_project`
--

DROP TABLE IF EXISTS `tb_project`;
CREATE TABLE `tb_project` (
  `id` int(11) NOT NULL auto_increment,
  `project_name` varchar(50) default NULL,
  `project_comment` text,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tb_project`
--


/*!40000 ALTER TABLE `tb_project` DISABLE KEYS */;
LOCK TABLES `tb_project` WRITE;
INSERT INTO `tb_project` VALUES (1,'预本硕连读项目',NULL,'2012-03-21 23:20:02',NULL),(2,'法国兰斯管理学院本硕连读项目',NULL,'2012-03-21 23:36:59',NULL),(4,'英国南安普顿大学艺术设计本科项目',NULL,'2012-03-21 23:39:32','2012-03-21 23:39:42');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_project` ENABLE KEYS */;

--
-- Table structure for table `tb_project_old`
--

DROP TABLE IF EXISTS `tb_project_old`;
CREATE TABLE `tb_project_old` (
  `id` int(11) NOT NULL,
  `project_name` varchar(100) default NULL,
  `study_country` varchar(200) default NULL,
  `study_school` varchar(100) default NULL,
  `education_system` varchar(100) default NULL,
  `zsdx` varchar(50) default NULL,
  `cgtj` varchar(100) default NULL,
  `professional` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk COMMENT='InnoDB free: 106496 kB';

--
-- Dumping data for table `tb_project_old`
--


/*!40000 ALTER TABLE `tb_project_old` DISABLE KEYS */;
LOCK TABLES `tb_project_old` WRITE;
INSERT INTO `tb_project_old` VALUES (100,'预本硕连读项目','英国,荷兰,澳大利亚,美国','有30多所高校可供选择','3年国内+ 2年国外','高中毕业生','通过国内全部课程，且雅思达到6.0','计算机网络|国际理财|国际贸易与商务|商务会计|物流管理(非制造业)|酒店管理'),(101,'法国兰斯管理学院本硕连读项目','法国','法国兰斯 管理学院（ESB）','3年国内+2年或2年国外','高中毕业生','通过国内全部课程，通过法方面试，且雅思达到6.0或法语TEF达到550','管理学'),(102,'中日本科项目','日本','日本静冈产业大学','2年国内+2年国外','高中毕业生','通过国内全部课程，并且通过日语二级','预科'),(103,'英国伦敦大学商务管理本科项目','英国','英国伦敦大学','2年国内+2年国外','高中毕业生','通过国内全部课程，通过英方面试，且雅思达到6.5','商务管理'),(104,'英国南安普顿大学艺术设计本科项目','英国','英国南安普顿大学','2年国内+2年国外','高中毕业生','通过国内全部课程，通过英方面试','视觉艺术设计专业|纺织时装及纤维专业'),(105,'英国南安普顿大学教育硕士项目','英国','英国南安普顿大学','1年国内+1年国外','本科毕业生','通过国内全部课程通过英方面试','教育机构管理与领导|教育实践与革新|计算机辅助教学'),(106,'美国布里诺大学工商管理硕士项目','美国','美国布里诺大学','0.5年国内+1年国外（或1.5年国外）','大专毕业生或本科毕业生','通过国内全部课程，大学平均成绩75分以上或GPA2.5以上','工商管理'),(107,'硕士预科项目','各英语国家','英语授课的相关院校','1年国内+1年或2年国外','本科毕业生','达到申请院校的雅思要求','预科'),(108,'美国本科预科','美国','有12所美国大学可供选择','1年国内+4年国外','高中毕业生','通过国内全部课程，且雅思达到6.0','预科'),(109,'本科预科项目','各英语国家','英语授课的相关院校','1年国内+3年或4年国外','高中毕业生','达到申请院校的雅思要求','预科');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_project_old` ENABLE KEYS */;

--
-- Table structure for table `tb_room`
--

DROP TABLE IF EXISTS `tb_room`;
CREATE TABLE `tb_room` (
  `id` int(11) NOT NULL auto_increment,
  `term_id` int(11) default NULL,
  `room_name` varchar(100) default NULL,
  `room_size` varchar(20) default NULL,
  `room_status` varchar(20) default NULL,
  `room_comment` varchar(200) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tb_room`
--


/*!40000 ALTER TABLE `tb_room` DISABLE KEYS */;
LOCK TABLES `tb_room` WRITE;
INSERT INTO `tb_room` VALUES (1,4,'北教楼501','80','1',NULL,'2012-03-27 22:56:14',NULL),(2,4,'北教楼503','100','1','无','2012-03-27 23:00:59',NULL),(4,3,'多媒体202','100','1','无','2012-03-27 23:14:57',NULL),(5,3,'多媒体505','100','1','无','2012-03-27 23:22:03',NULL),(6,3,'多媒体606','100','2','备用','2012-04-07 22:47:07','2012-04-07 22:47:07'),(7,4,'南教楼505','55','2','无','2012-04-07 22:44:17','2012-04-07 22:44:17');
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_room` ENABLE KEYS */;

--
-- Table structure for table `tb_syllabus`
--

DROP TABLE IF EXISTS `tb_syllabus`;
CREATE TABLE `tb_syllabus` (
  `id` int(11) NOT NULL auto_increment,
  `term_id` int(11) default NULL,
  `class_id` int(11) default NULL,
  `lesson` char(2) default NULL,
  `oe_ind` char(1) default NULL,
  `week` char(1) default NULL,
  `course_no` varchar(50) default NULL,
  `teacher_id` int(11) default NULL,
  `room_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_syllabus`
--


/*!40000 ALTER TABLE `tb_syllabus` DISABLE KEYS */;
LOCK TABLES `tb_syllabus` WRITE;
INSERT INTO `tb_syllabus` VALUES (74,4,4,'12','1','1','5019',2,6),(75,4,5,'12','1','1','1130',1,5),(76,4,4,'34','1','1','1130',2,5);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_syllabus` ENABLE KEYS */;

--
-- Table structure for table `tb_term`
--

DROP TABLE IF EXISTS `tb_term`;
CREATE TABLE `tb_term` (
  `id` int(11) NOT NULL auto_increment,
  `term_name` varchar(50) default NULL,
  `is_current_term` char(1) default NULL,
  `syllabus_status` char(1) default '2',
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_term`
--


/*!40000 ALTER TABLE `tb_term` DISABLE KEYS */;
LOCK TABLES `tb_term` WRITE;
INSERT INTO `tb_term` VALUES (1,'2010-2010第一学期','2','2','2012-03-20 00:03:33','2012-03-20 00:11:13'),(2,'2010-2010第二学期','2','2','2012-03-27 22:50:56',NULL),(3,'2011-2012第一学期','2','2','2012-03-27 22:51:27',NULL),(4,'2011-2012第二学期','1','1','2012-03-27 22:52:02',NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tb_term` ENABLE KEYS */;

--
-- Table structure for table `ts_menu_info`
--

DROP TABLE IF EXISTS `ts_menu_info`;
CREATE TABLE `ts_menu_info` (
  `id` int(11) NOT NULL auto_increment,
  `text` varchar(255) default NULL,
  `leaf` char(1) default NULL,
  `module_id` varchar(255) default NULL,
  `module_name` varchar(255) default NULL,
  `parent_id` int(11) default '-1',
  `ordinal` int(2) default '0',
  `status` char(1) default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ts_menu_info`
--


/*!40000 ALTER TABLE `ts_menu_info` DISABLE KEYS */;
LOCK TABLES `ts_menu_info` WRITE;
INSERT INTO `ts_menu_info` VALUES (1,'实例','2',NULL,NULL,-1,1,'1'),(2,'基础信息管理','2',NULL,NULL,-1,2,'1'),(3,'排课管理','2',NULL,NULL,-1,3,'1'),(4,'打印证明','2',NULL,NULL,-1,4,'1'),(5,'实例-表格','1','ems.biz.samples.xgridsample.XGridSample',NULL,1,1,'1'),(6,'实例-表单','1','ems.biz.samples.formsample.FormSample',NULL,1,2,'1'),(7,'实例-对话框','1','ems.biz.samples.dialogsample.DialogSample',NULL,1,3,'1'),(8,'CRUD实例','1','ems.biz.samples.crud.User',NULL,1,4,'1'),(9,'用户管理','1','ems.biz.basicInfo.userManager.User',NULL,2,1,'1'),(10,'年级管理','1','ems.biz.basicInfo.gradeManager.Grade',NULL,2,2,'1'),(11,'学期管理','1','ems.biz.basicInfo.termManager.Term',NULL,2,4,'1'),(12,'班级管理','1','ems.biz.basicInfo.classManager.Class',NULL,2,3,'1'),(13,'项目管理','1','ems.biz.basicInfo.projectManager.Project',NULL,2,5,'1'),(14,'专业管理','1','ems.biz.basicInfo.professManager.Profess',NULL,2,6,'1'),(15,'课程管理','1','ems.biz.basicInfo.courseManager.Course',NULL,2,7,'1'),(16,'教室管理','1','ems.biz.basicInfo.roomManager.Room',NULL,2,8,'1'),(17,'教材管理','1','ems.biz.basicInfo.bookManager.Book',NULL,2,9,'1'),(18,'成绩证明','1','ems.biz.certificate.transcript.Transcript',NULL,4,1,'1'),(19,'在学证明','1','ems.biz.certificate.studentship.Studentship',NULL,4,2,'1'),(20,'预毕业证明','1','ems.biz.certificate.freshgraduate.FreshGraduate',NULL,4,3,'1'),(21,'毕业证明','1','ems.biz.certificate.graduate.Graduate',NULL,4,4,'1'),(22,'就读证明','1','ems.biz.certificate.study.Study',NULL,4,5,'1'),(23,'排课(一览表)','1','ems.biz.syllabus.syllabusplan.SyllabusPlan',NULL,3,1,'1'),(24,'教师分表','1','ems.biz.syllabus.syllabusbyteacher.SyllabusByTeacher',NULL,3,2,'1'),(25,'班级分表','1','ems.biz.syllabus.syllabusbyclass.SyllabusByClass',NULL,3,3,'1'),(26,'课程分表','1','ems.biz.syllabus.syllabusbycourse.SyllabusByCourse',NULL,3,4,'1');
UNLOCK TABLES;
/*!40000 ALTER TABLE `ts_menu_info` ENABLE KEYS */;

--
-- Table structure for table `ts_role_info`
--

DROP TABLE IF EXISTS `ts_role_info`;
CREATE TABLE `ts_role_info` (
  `id` int(11) NOT NULL auto_increment,
  `role_cd` varchar(255) default NULL,
  `role_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ts_role_info`
--


/*!40000 ALTER TABLE `ts_role_info` DISABLE KEYS */;
LOCK TABLES `ts_role_info` WRITE;
INSERT INTO `ts_role_info` VALUES (1,'admin','系统管理员'),(2,'teacher','教师'),(3,'counselor','辅导员'),(4,'student','学生'),(5,'financial','财务'),(6,'senate','教务处');
UNLOCK TABLES;
/*!40000 ALTER TABLE `ts_role_info` ENABLE KEYS */;

--
-- Table structure for table `ts_role_menu_rel`
--

DROP TABLE IF EXISTS `ts_role_menu_rel`;
CREATE TABLE `ts_role_menu_rel` (
  `id` int(11) NOT NULL auto_increment,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ts_role_menu_rel`
--


/*!40000 ALTER TABLE `ts_role_menu_rel` DISABLE KEYS */;
LOCK TABLES `ts_role_menu_rel` WRITE;
INSERT INTO `ts_role_menu_rel` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(26,1,26);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ts_role_menu_rel` ENABLE KEYS */;

--
-- Table structure for table `ts_user_info`
--

DROP TABLE IF EXISTS `ts_user_info`;
CREATE TABLE `ts_user_info` (
  `id` int(11) NOT NULL auto_increment,
  `login_name` varchar(20) default NULL,
  `user_name` varchar(20) default NULL,
  `password` char(32) default NULL,
  `email` varchar(255) default NULL,
  `contact` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ts_user_info`
--


/*!40000 ALTER TABLE `ts_user_info` DISABLE KEYS */;
LOCK TABLES `ts_user_info` WRITE;
INSERT INTO `ts_user_info` VALUES (1,'admin','系统管理员','202cb962ac59075b964b07152d234b70',NULL,NULL),(2,'teacher','教师1','202cb962ac59075b964b07152d234b70',NULL,NULL),(3,'fdy','辅导员1','202cb962ac59075b964b07152d234b70','',''),(4,'student','学生1','202cb962ac59075b964b07152d234b70',NULL,NULL),(5,'caiwu','财务1','202cb962ac59075b964b07152d234b70',NULL,NULL),(6,'jwc','教务处1','202cb962ac59075b964b07152d234b70',NULL,NULL);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ts_user_info` ENABLE KEYS */;

--
-- Table structure for table `ts_user_role_rel`
--

DROP TABLE IF EXISTS `ts_user_role_rel`;
CREATE TABLE `ts_user_role_rel` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ts_user_role_rel`
--


/*!40000 ALTER TABLE `ts_user_role_rel` DISABLE KEYS */;
LOCK TABLES `ts_user_role_rel` WRITE;
INSERT INTO `ts_user_role_rel` VALUES (1,1,1),(2,3,3),(3,4,4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `ts_user_role_rel` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

