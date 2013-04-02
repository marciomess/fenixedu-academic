/*
 * Created on 10/Dec/2003
 */

package net.sourceforge.fenixedu.presentationTier.Action.grant.qualification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.applicationTier.IUserView;
import net.sourceforge.fenixedu.dataTransferObject.person.InfoSiteQualifications;
import net.sourceforge.fenixedu.presentationTier.Action.base.FenixDispatchAction;
import net.sourceforge.fenixedu.presentationTier.Action.resourceAllocationManager.utils.ServiceUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.fenixWebFramework.security.UserView;
import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;
import pt.ist.fenixWebFramework.struts.annotations.Tile;

/**
 * @author Barbosa
 * @author Pica
 */

@Mapping(module = "facultyAdmOffice", path = "/manageGrantQualification",
        input = "/manageGrantQualification.do?page=0&method=prepareManageGrantQualificationForm", attribute = "voidForm",
        formBean = "voidForm", scope = "request", parameter = "method")
@Forwards(value = { @Forward(name = "manage-grant-qualification",
        path = "/facultyAdmOffice/grant/qualification/manageGrantQualification.jsp", tileProperties = @Tile(
                title = "private.teachingstaffandresearcher.managementscholarship.scholarshipsearch")) })
public class ManageGrantQualificationAction extends FenixDispatchAction {

    public ActionForward prepareManageGrantQualificationForm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Integer idInternal = null;
        Integer idInternalPerson = null;
        Integer grantOwnerNumber = null;
        String username = null;

        if (verifyParameterInRequest(request, "idInternal")) {
            idInternal = new Integer(request.getParameter("idInternal"));
        } else if (verifyParameterInRequest(request, "idGrantOwner")) {
            idInternal = new Integer(request.getParameter("idGrantOwner"));
        }
        if (verifyParameterInRequest(request, "idPerson")) {
            idInternalPerson = new Integer(request.getParameter("idPerson"));
        }
        if (verifyParameterInRequest(request, "grantOwnerNumber")) {
            grantOwnerNumber = new Integer(request.getParameter("grantOwnerNumber"));
        }
        username = request.getParameter("username");

        Object[] args = { username };
        IUserView userView = UserView.getUser();
        InfoSiteQualifications infoSiteQualifications =
                (InfoSiteQualifications) ServiceUtils.executeService("ReadQualifications", args);

        if (infoSiteQualifications != null) {
            if (infoSiteQualifications.getInfoPerson() != null) {
                request.setAttribute("grantOwnerName", infoSiteQualifications.getInfoPerson().getNome());
            }
            if (infoSiteQualifications.getInfoQualifications() != null
                    && !infoSiteQualifications.getInfoQualifications().isEmpty()) {
                request.setAttribute("infoQualificationList", infoSiteQualifications.getInfoQualifications());
            }
        }
        request.setAttribute("grantOwnerNumber", grantOwnerNumber);
        request.setAttribute("idGrantOwner", idInternal);
        request.setAttribute("idPerson", idInternalPerson);
        request.setAttribute("username", username);
        return mapping.findForward("manage-grant-qualification");
    }
}