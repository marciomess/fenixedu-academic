package ServidorAplicacao.Servico.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import DataBeans.InfoStudent;
import Dominio.DisciplinaExecucao;
import Dominio.Frequenta;
import Dominio.IDisciplinaExecucao;
import Dominio.IFrequenta;
import Dominio.IStudent;
import Dominio.ITurnoAluno;
import ServidorAplicacao.IServico;
import ServidorAplicacao.Servico.exceptions.FenixServiceException;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.IDisciplinaExecucaoPersistente;
import ServidorPersistente.IFrequentaPersistente;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.ITurnoAlunoPersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;

/**
 * Describe class <code>WriteStudentAttendingCourses</code> here.
 *
 * @author <a href="mailto:tdi-dev@tagus.ist.utl.pt">tdi-dev:Edgar Gon�alves</a>
 * @version 1.0
 */
public class WriteStudentAttendingCourses implements IServico {

	private static WriteStudentAttendingCourses _servico =
		new WriteStudentAttendingCourses();

	/**
	 * The actor of this class.
	 **/

	private WriteStudentAttendingCourses() {

	}

	/**
	 * Returns Service Name
	 */
	public String getNome() {
		return "WriteStudentAttendingCourses";
	}

	/**
	 * Returns the _servico.
	 * @return WriteStudentAttendingCourses
	 */
	public static WriteStudentAttendingCourses getService() {
		return _servico;
	}

	/**
	 * Describe <code>run</code> method here.
	 *
	 * @param infoStudent an <code>InfoStudent</code>,
	 * @param infoExecutionCourses a <code>List</code> with the wanted executionCourse.idInternal's of the ATTEND table.
	 * @return a <code>Boolean</code> to indicate if all went fine.
	 * @exception FenixServiceException if an error occurs.
	 */
	public Boolean run(InfoStudent infoStudent, List infoExecutionCourses)
		throws FenixServiceException {

		if (infoExecutionCourses == null || infoStudent == null) {
			return new Boolean(false);
		}

		try {

			ISuportePersistente sp = SuportePersistenteOJB.getInstance();

			//Reads the student from the database
			IStudent student =
				sp.getIPersistentStudent().readByNumero(
					infoStudent.getNumber(),
					infoStudent.getDegreeType());

			IFrequentaPersistente attendsDAO = sp.getIFrequentaPersistente();

			//Read every course the student attends to:
			List attends = attendsDAO.readByStudentNumber(student.getNumber());

			List attendingCourses = getExecutionCoursesFromAttends(attends);

			IDisciplinaExecucaoPersistente executionCourseDAO =
				sp.getIDisciplinaExecucaoPersistente();

			//Gets the database objects for the wanted courses
			List wantedAttendingCourses = new ArrayList();
			Iterator i = infoExecutionCourses.iterator();
			while (i.hasNext()) {
				Integer executionCourseId = new Integer((String) i.next());
				IDisciplinaExecucao executionCourse =
					new DisciplinaExecucao(executionCourseId);
				executionCourse =
					(IDisciplinaExecucao) executionCourseDAO.readByOId(
						executionCourse,
						false);

				if (executionCourse == null) {
					System.out.println(
						"Execution course with ID="
							+ executionCourseId
							+ " does not exist in the database!");
					throw new FenixServiceException();
				} else {
					wantedAttendingCourses.add(executionCourse);

				}
			}

			//Delete all courses the student is currently attendin to that he/she doesn't want to:
			//attendings to remove : 
			List attendsToRemove =
				(List) CollectionUtils.subtract(
					attendingCourses,
					wantedAttendingCourses);
			List attendingCoursesToAdd =
				(List) CollectionUtils.subtract(
					wantedAttendingCourses,
					attendingCourses);
			if (attendsToRemove != null && !attendsToRemove.isEmpty()) {
				deleteAttends(attendsToRemove, student);
			}

			//Add new courses (without duplicates)
			i = attendingCoursesToAdd.iterator();
			while (i.hasNext()) {

				IDisciplinaExecucao executionCourse =
					(IDisciplinaExecucao) i.next();

				IFrequenta attendsEntry =
					attendsDAO.readByAlunoAndDisciplinaExecucao(
						student,
						executionCourse);
				if (attendsEntry == null) {
					attendsEntry = new Frequenta();
					attendsDAO.simpleLockWrite(attendsEntry);
					attendsEntry.setAluno(student);
					attendsEntry.setDisciplinaExecucao(executionCourse);
				}
			}
		} catch (ExcepcaoPersistencia e) {
			throw new FenixServiceException(e);
		}
		return new Boolean(true);

	}

	/**
	 * @param attends
	 * @return
	 */
	private List getExecutionCoursesFromAttends(List attends) {
		List executionCourses = new ArrayList();
		Iterator iter = attends.iterator();
		while (iter.hasNext()) {
			IFrequenta attend = (IFrequenta) iter.next();
			executionCourses.add(attend.getDisciplinaExecucao());
		}
		return executionCourses;
	}

	private void deleteAttends(List attendingCoursesToRemove, IStudent student)
		throws FenixServiceException {
		Iterator iterator = attendingCoursesToRemove.iterator();
		try {
			ISuportePersistente sp = SuportePersistenteOJB.getInstance();
			IFrequentaPersistente persistentAttends =
				sp.getIFrequentaPersistente();
			ITurnoAlunoPersistente persistentShiftStudent =
				sp.getITurnoAlunoPersistente();
			while (iterator.hasNext()) {
				IDisciplinaExecucao executionCourse =
					(IDisciplinaExecucao) iterator.next();
				IFrequenta attends =
					persistentAttends.readByAlunoAndDisciplinaExecucao(
						student,
						executionCourse);
				if (attends != null && attends.getEnrolment() == null) {
					//NOTE: attends that are linked to enrollments are not deleted
					List shiftAttendsToDelete =
						persistentShiftStudent.readByStudentAndExecutionCourse(
							student,
							executionCourse);
					if (shiftAttendsToDelete != null) {
						Iterator iter = shiftAttendsToDelete.iterator();
						while (iter.hasNext()) {
							persistentShiftStudent.delete(
								(ITurnoAluno) iter.next());
						}
					}
					persistentAttends.delete(attends);
				}

			}
		} catch (ExcepcaoPersistencia e) {
			throw new FenixServiceException(e);
		}
	}

}
