select concat('UPDATE CURRICULAR_COURSE SET CREDITS = ', ccs.CREDITS, ', THEORETICAL_HOURS = ', ccs.THEORETICAL_HOURS, ', PRATICAL_HOURS = ', ccs.PRATICAL_HOURS, ', THEO_PRAT_HOURS = ', ccs.THEO_PRAT_HOURS, ', LAB_HOURS = ', ccs.LAB_HOURS, ', ECTS_CREDITS = ', ccs.ECTS_CREDITS, ' WHERE ID_INTERNAL = ', cc.ID_INTERNAL, ';') AS "QQ" from CURRICULAR_COURSE_SCOPE ccs inner join CURRICULAR_COURSE cc on ccs.KEY_CURRICULAR_COURSE = cc.ID_INTERNAL;

