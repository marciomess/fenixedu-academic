ALTER TABLE ENROLMENT CHANGE KEY_CURRICULAR_COURSE_SCOPE KEY_CURRICULAR_COURSE int(11) not null;
 
ALTER TABLE DEGREE_CURRICULAR_PLAN ADD ENROLLMENT_STRATEGY_CLASS_NAME VARCHAR(255) AFTER DESCRIPTION_EN;
UPDATE DEGREE_CURRICULAR_PLAN SET ENROLLMENT_STRATEGY_CLASS_NAME = 'class ServidorAplicacao.strategy.enrolment.strategys.student.EnrolmentStrategyLEEC' WHERE ID_INTERNAL = 48;
 
ALTER TABLE CURRICULAR_COURSE ADD MAX_INCREMENT_NAC int(11) DEFAULT '2';
ALTER TABLE CURRICULAR_COURSE ADD MIN_INCREMENT_NAC int(11) DEFAULT '1';
ALTER TABLE CURRICULAR_COURSE ADD ENROLLMENT_WEIGTH int(11) DEFAULT '1';
ALTER TABLE CURRICULAR_COURSE ADD ECTS_CREDITS double;
 
UPDATE CURRICULAR_COURSE_SCOPE SET CREDITS = 0 WHERE CREDITS IS NULL;
UPDATE CURRICULAR_COURSE_SCOPE SET ECTS_CREDITS = 0 WHERE ECTS_CREDITS IS NULL;
UPDATE CURRICULAR_COURSE_SCOPE SET THEORETICAL_HOURS = 0 WHERE THEORETICAL_HOURS IS NULL;
UPDATE CURRICULAR_COURSE_SCOPE SET PRATICAL_HOURS = 0 WHERE PRATICAL_HOURS IS NULL;
UPDATE CURRICULAR_COURSE_SCOPE SET LAB_HOURS = 0 WHERE LAB_HOURS IS NULL;
UPDATE CURRICULAR_COURSE_SCOPE SET THEO_PRAT_HOURS = 0 WHERE THEO_PRAT_HOURS IS NULL;

