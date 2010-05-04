package net.sourceforge.fenixedu.dataTransferObject.administrativeOffice.dismissal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.fenixedu.dataTransferObject.student.IStudentCurricularPlanBean;
import net.sourceforge.fenixedu.domain.CurricularCourse;
import net.sourceforge.fenixedu.domain.Enrolment;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.Grade;
import net.sourceforge.fenixedu.domain.IEnrolment;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;
import net.sourceforge.fenixedu.domain.degreeStructure.CourseGroup;
import net.sourceforge.fenixedu.domain.degreeStructure.OptionalCurricularCourse;
import net.sourceforge.fenixedu.domain.student.Student;
import net.sourceforge.fenixedu.domain.studentCurriculum.CurriculumGroup;
import net.sourceforge.fenixedu.domain.studentCurriculum.ExternalEnrolment;

public class DismissalBean implements Serializable, IStudentCurricularPlanBean {

    static private final long serialVersionUID = 1L;

    private StudentCurricularPlan studentCurricularPlan;
    private ExecutionSemester executionSemester;
    private Collection<SelectedCurricularCourse> dismissals;
    private Collection<SelectedOptionalCurricularCourse> optionalDismissals;
    private CourseGroup courseGroup;
    private CurriculumGroup curriculumGroup;
    private Collection<SelectedEnrolment> enrolments;
    private Collection<SelectedExternalEnrolment> externalEnrolments;
    private DismissalType dismissalType;
    private Double credits;
    private Grade grade;

    public Collection<SelectedCurricularCourse> getDismissals() {
	return dismissals;
    }

    public void setDismissals(Collection<SelectedCurricularCourse> dismissals) {
	this.dismissals = dismissals;
    }

    public boolean hasAnyDismissals() {
	return getDismissals() != null && !getDismissals().isEmpty();
    }

    public boolean containsDismissal(CurricularCourse curricularCourse) {
	if (getDismissals() != null) {
	    for (SelectedCurricularCourse selectedCurricularCourse : getDismissals()) {
		if (selectedCurricularCourse.getCurricularCourse().equals(curricularCourse)) {
		    return true;
		}
	    }
	}
	return false;
    }

    public Collection<SelectedOptionalCurricularCourse> getOptionalDismissals() {
	return optionalDismissals;
    }

    public void setOptionalDismissals(Collection<SelectedOptionalCurricularCourse> optionalDismissals) {
	this.optionalDismissals = optionalDismissals;
    }

    public boolean hasAnyOptionalDismissals() {
	return getOptionalDismissals() != null && !getOptionalDismissals().isEmpty();
    }

    public boolean containsOptionalDismissal(CurricularCourse curricularCourse) {
	if (getOptionalDismissals() != null) {
	    for (SelectedOptionalCurricularCourse selectedCurricularCourse : getOptionalDismissals()) {
		if (selectedCurricularCourse.getCurricularCourse().equals(curricularCourse)) {
		    return true;
		}
	    }
	}
	return false;
    }

    public boolean containsDismissalOrOptionalDismissal(final CurricularCourse curricularCourse) {
	return containsDismissal(curricularCourse) || containsOptionalDismissal(curricularCourse);
    }

    public Collection<SelectedCurricularCourse> getAllDismissals() {
	final Collection<SelectedCurricularCourse> result = new ArrayList<SelectedCurricularCourse>();
	if (getDismissals() != null) {
	    result.addAll(getDismissals());
	}
	if (getOptionalDismissals() != null) {
	    result.addAll(getOptionalDismissals());
	}
	return result;
    }

    public Collection<SelectedEnrolment> getEnrolments() {
	return enrolments;
    }

    public void setEnrolments(Collection<SelectedEnrolment> enrolments) {
	this.enrolments = enrolments;
    }

    public CourseGroup getCourseGroup() {
	return this.courseGroup;
    }

    public void setCourseGroup(CourseGroup courseGroup) {
	this.courseGroup = courseGroup;
    }

    public CurriculumGroup getCurriculumGroup() {
	return this.curriculumGroup;
    }

    public void setCurriculumGroup(CurriculumGroup curriculumGroup) {
	this.curriculumGroup = curriculumGroup;
    }

    public StudentCurricularPlan getStudentCurricularPlan() {
	return this.studentCurricularPlan;
    }

    public void setStudentCurricularPlan(StudentCurricularPlan studentCurricularPlan) {
	this.studentCurricularPlan = studentCurricularPlan;
    }

    public ExecutionSemester getExecutionPeriod() {
	return this.executionSemester;
    }

    public void setExecutionPeriod(ExecutionSemester executionSemester) {
	this.executionSemester = executionSemester;
    }

    public DismissalType getDismissalType() {
	return dismissalType;
    }

    public void setDismissalType(DismissalType dismissalType) {
	this.dismissalType = dismissalType;
    }

    public Grade getGrade() {
	return grade;
    }

    public void setGrade(Grade grade) {
	this.grade = grade;
    }

    public Double getCredits() {
	return credits;
    }

    public void setCredits(Double credits) {
	this.credits = credits;
    }

    public Collection<SelectedExternalEnrolment> getExternalEnrolments() {
	return externalEnrolments;
    }

    public void setExternalEnrolments(Collection<SelectedExternalEnrolment> externalEnrolments) {
	this.externalEnrolments = externalEnrolments;
    }

    public Collection<IEnrolment> getSelectedEnrolments() {
	final Collection<IEnrolment> result = new ArrayList<IEnrolment>();

	if (getEnrolments() != null) {
	    for (final SelectedEnrolment selectedEnrolment : getEnrolments()) {
		if (selectedEnrolment.getSelected()) {
		    result.add(selectedEnrolment.getEnrolment());
		}
	    }
	}

	if (getExternalEnrolments() != null) {
	    for (final SelectedExternalEnrolment selectedEnrolment : getExternalEnrolments()) {
		if (selectedEnrolment.getSelected()) {
		    result.add(selectedEnrolment.getExternalEnrolment());
		}
	    }
	}

	return result;
    }

    public Student getStudent() {
	return getStudentCurricularPlan().getRegistration().getStudent();
    }

    public static class SelectedCurricularCourse implements Serializable {

	static private final long serialVersionUID = 1L;

	private Boolean selected = Boolean.FALSE;

	private CurricularCourse curricularCourse;
	private CurriculumGroup curriculumGroup;
	private StudentCurricularPlan studentCurricularPlan;

	public SelectedCurricularCourse(CurricularCourse curricularCourse, StudentCurricularPlan studentCurricularPlan) {
	    setCurricularCourse(curricularCourse);
	    setStudentCurricularPlan(studentCurricularPlan);
	}

	public CurricularCourse getCurricularCourse() {
	    return this.curricularCourse;
	}

	public void setCurricularCourse(CurricularCourse curricularCourse) {
	    this.curricularCourse = curricularCourse;
	}

	public Boolean getSelected() {
	    return selected;
	}

	public void setSelected(Boolean selected) {
	    this.selected = selected;
	}

	public CurriculumGroup getCurriculumGroup() {
	    return this.curriculumGroup;
	}

	public void setCurriculumGroup(CurriculumGroup curriculumGroup) {
	    this.curriculumGroup = curriculumGroup;
	}

	public StudentCurricularPlan getStudentCurricularPlan() {
	    return this.studentCurricularPlan;
	}

	public void setStudentCurricularPlan(StudentCurricularPlan studentCurricularPlan) {
	    this.studentCurricularPlan = studentCurricularPlan;
	}

	public String getKey() {
	    StringBuilder stringBuilder = new StringBuilder();
	    if (this.getCurricularCourse() != null) {
		stringBuilder.append(this.getCurricularCourse().getClass().getName()).append(":").append(
			this.getCurricularCourse().getIdInternal());
	    }
	    stringBuilder.append(",");
	    if (this.getCurriculumGroup() != null) {
		stringBuilder.append(this.getCurriculumGroup().getClass().getName()).append(":").append(
			this.getCurriculumGroup().getIdInternal());
	    }
	    stringBuilder.append(",");
	    if (this.getStudentCurricularPlan() != null) {
		stringBuilder.append(this.getStudentCurricularPlan().getClass().getName()).append(":").append(
			this.getStudentCurricularPlan().getIdInternal());
	    }
	    return stringBuilder.toString();
	}

	public boolean isOptional() {
	    return false;
	}
    }

    public static class SelectedOptionalCurricularCourse extends SelectedCurricularCourse {

	static private final long serialVersionUID = 1L;

	private Double credits;

	public SelectedOptionalCurricularCourse(final OptionalCurricularCourse curricularCourse,
		final StudentCurricularPlan studentCurricularPlan) {
	    super(curricularCourse, studentCurricularPlan);
	}

	@Override
	public OptionalCurricularCourse getCurricularCourse() {
	    return (OptionalCurricularCourse) super.getCurricularCourse();
	}

	public Double getCredits() {
	    return credits;
	}

	public void setCredits(Double credits) {
	    this.credits = credits;
	}

	@Override
	public boolean isOptional() {
	    return true;
	}
    }

    public static class SelectedEnrolment implements Serializable {

	static private final long serialVersionUID = 1L;

	private Boolean selected = Boolean.FALSE;

	private Enrolment enrolment;

	public SelectedEnrolment(Enrolment enrolment) {
	    setEnrolment(enrolment);
	}

	public Enrolment getEnrolment() {
	    return this.enrolment;
	}

	public void setEnrolment(Enrolment enrolment) {
	    this.enrolment = enrolment;
	}

	public Boolean getSelected() {
	    return selected;
	}

	public void setSelected(Boolean selected) {
	    this.selected = selected;
	}

	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof SelectedEnrolment)) {
		return false;
	    }
	    return equals((SelectedEnrolment) obj);
	}

	public boolean equals(final SelectedEnrolment other) {
	    return getEnrolment() == other.getEnrolment();
	}

	@Override
	public int hashCode() {
	    return getEnrolment().hashCode();
	}
    }

    public static class SelectedExternalEnrolment implements Serializable {

	static private final long serialVersionUID = 1L;

	private Boolean selected = Boolean.FALSE;

	private ExternalEnrolment externalEnrolment;

	public SelectedExternalEnrolment(ExternalEnrolment externalEnrolment) {
	    setExternalEnrolment(externalEnrolment);
	}

	public ExternalEnrolment getExternalEnrolment() {
	    return this.externalEnrolment;
	}

	public void setExternalEnrolment(ExternalEnrolment externalEnrolment) {
	    this.externalEnrolment = externalEnrolment;
	}

	public Boolean getSelected() {
	    return selected;
	}

	public void setSelected(Boolean selected) {
	    this.selected = selected;
	}

	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof SelectedExternalEnrolment)) {
		return false;
	    }
	    return equals((SelectedExternalEnrolment) obj);
	}

	public boolean equals(final SelectedExternalEnrolment other) {
	    return getExternalEnrolment() == other.getExternalEnrolment();
	}

	@Override
	public int hashCode() {
	    return getExternalEnrolment().hashCode();
	}
    }

    public static enum DismissalType {
	CURRICULAR_COURSE_CREDITS, CURRICULUM_GROUP_CREDITS, NO_COURSE_GROUP_CURRICULUM_GROUP_CREDITS;

	public String getName() {
	    return name();
	}
    }

}
