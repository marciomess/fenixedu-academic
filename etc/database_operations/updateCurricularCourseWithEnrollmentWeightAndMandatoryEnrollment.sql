update CURRICULAR_COURSE set CURRICULAR_COURSE.ENROLLMENT_WEIGTH = 0 and CURRICULAR_COURSE.MANDATORY_ENROLLMENT = 0 where CURRICULAR_COURSE.ENROLLMENT_WEIGTH is null and CURRICULAR_COURSE.MANDATORY_ENROLLMENT is null;