package ServidorApresentacao.Action.degreeAdministrativeOffice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author Fernanda Quit�rio 05/Fev/2004
 *  
 */
public class ViewStudentCurriculumDispatchAction extends DispatchAction {

    public ActionForward prepareView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        getExecutionDegreeId(request);
        
        if(request.getParameter("degreeCurricularPlanID") != null){
            Integer degreeCurricularPlanID = new Integer(request.getParameter("degreeCurricularPlanID"));
            request.setAttribute("degreeCurricularPlanID", degreeCurricularPlanID);
        }
        
        return mapping.findForward("prepareViewStudentCurriculumChooseStudent");
    }

    private void getExecutionDegreeId(HttpServletRequest request) {
        String executionDegreeId = request.getParameter("executionDegreeId");
        if (executionDegreeId == null) {
            executionDegreeId = (String) request.getAttribute("executionDegreeId");
        }
        request.setAttribute("executionDegreeId", executionDegreeId);
    }
}