#-------------------------------------------
# Table structure for execution_degree
#-------------------------------------------
DROP TABLE IF EXISTS EXECUTION_DEGREE;
CREATE TABLE EXECUTION_DEGREE (
  ID_INTERNAL int(11) NOT NULL default '0',
  ACADEMIC_YEAR int(11) NOT NULL,
  KEY_DEGREE_CURRICULAR_PLAN int(11) not null,
  KEY_DEGREE int(11),
  ANO_LECTIVO varchar(100),
  PRIMARY KEY  (ID_INTERNAL),
  UNIQUE KEY U1 (ACADEMIC_YEAR,KEY_DEGREE_CURRICULAR_PLAN)
) TYPE=InnoDB;
   
#------------------------------------------
# Table structure for country
#------------------------------------------
drop table if exists COUNTRY;
create table COUNTRY (
   ID_INTERNAL integer(11) not null auto_increment,
   NAME varchar(50) not null,
   NATIONALITY varchar(50) not null,
   CODE varchar(10) not null,
   primary key (ID_INTERNAL),
   unique U1 (NAME),
   unique U2 (NATIONALITY),
   unique U3 (CODE))
   type=InnoDB;

#------------------------------------------
# Table structure for execution_course
#------------------------------------------
DROP TABLE IF EXISTS EXECUTION_COURSE;
CREATE TABLE EXECUTION_COURSE (
  ID_INTERNAL int(11) NOT NULL auto_increment,
  NAME varchar(100) default NULL,
  CODE varchar(50) NOT NULL default '',
  KEY_EXECUTION_DEGREE int(11),
  KEY_TEACHER_IN_CHARGE int(11) NOT NULL default '0',
  PROGRAM text,
  THEORETICAL_HOURS double default NULL,
  PRATICAL_HOURS double default NULL,
  THEO_PRAT_HOURS double default NULL,
  LAB_HOURS double default NULL,
  SEMESTER int(11) default '2',
  KEY_EXECUTION_PERIOD int(11) unsigned NOT NULL,
  PRIMARY KEY  (ID_INTERNAL),
  UNIQUE KEY U1 (ID_INTERNAL, CODE, KEY_EXECUTION_PERIOD)
) TYPE=InnoDB;

#---------------------------------------------------------------
# Table structure for curricular_course_execution_course
#---------------------------------------------------------------
drop table if exists CURRICULAR_COURSE_EXECUTION_COURSE;
create table CURRICULAR_COURSE_EXECUTION_COURSE (
   INTERNAL_CODE integer(11) not null auto_increment,
   KEY_CURRICULAR_COURSE integer(11) not null,
   KEY_EXECUTION_COURSE integer(11) not null,
   primary key (INTERNAL_CODE),
   unique U1 (KEY_CURRICULAR_COURSE, KEY_EXECUTION_COURSE))
   type=InnoDB;


