package net.sourceforge.fenixedu.webServices;

import java.util.List;

import net.sourceforge.fenixedu.domain.RootDomainObject;
import net.sourceforge.fenixedu.domain.messaging.Announcement;
import net.sourceforge.fenixedu.domain.messaging.AnnouncementBoard;
import net.sourceforge.fenixedu.domain.messaging.AnnouncementCategory;
import net.sourceforge.fenixedu.domain.messaging.AnnouncementCategoryType;
import net.sourceforge.fenixedu.domain.space.Campus;
import net.sourceforge.fenixedu.util.StringUtils;

import org.codehaus.xfire.MessageContext;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.utl.ist.fenix.tools.util.i18n.MultiLanguageString;

import com.sun.faces.util.Base64;

public class AnnouncementManagement implements IAnnouncementManagement {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";

    private static final String EVENT_ANNOUNCEMENT_BOARD = "EVENT_BOARD";
    private static final String NEWS_ANNOUNCEMENT_BOARD = "NEWS_BOARD";

    private static final int EVENT_BOARD_ID = 28367;
    private static final int NEWS_BOARD_ID = 28368;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

    private static final int MAX_SIZE_SUBJECT = 100;
    private static final int MAX_SIZE_BODY = 3000;
    private static final int MAX_SIZE_EXCERPT = 200;
    private static final int MAX_SIZE_KEYWORDS = 100;
    private static final int MAX_SIZE_AUTHOR_NAME = 100;
    private static final int MAX_SIZE_AUTHOR_EMAIL = 50;
    private static final int MAX_SIZE_PLACE = 100;
    private static final int MAX_SIZE_EDITOR_NOTES = 1000;
    private static final int MAX_SIZE_FILE = 4783818;

    public String addAnnouncement(String username, String password, String subject, String body, String excerpt, String keywords,
	    String authorName, String authorEmail, String eventBeginDate, String eventEndDate, String place,
	    String publicationBeginDate, String publicationEndDate, String[] categories, String campus, String announcementBoard,
	    String editorNotes, String fileName, String fileContents, Long fileSize, MessageContext context) {

	try {
	    validateField(subject, "Subject", MAX_SIZE_SUBJECT);
	    validateField(body, "Body", MAX_SIZE_BODY);
	    validateField(excerpt, "Excerpt", MAX_SIZE_EXCERPT);
	    validateField(keywords, "Keywords", MAX_SIZE_KEYWORDS);
	    validateField(authorName, "Author Name", MAX_SIZE_AUTHOR_NAME);
	    validateField(authorEmail, "Author Email", MAX_SIZE_AUTHOR_EMAIL);
	    validateField(place, "Place", MAX_SIZE_PLACE);
	    validateField(editorNotes, "Editor Notes", MAX_SIZE_EDITOR_NOTES);

	    AnnouncementBoard board = null;
	    if (announcementBoard.equals(EVENT_ANNOUNCEMENT_BOARD)) {
		board = (AnnouncementBoard) RootDomainObject.readDomainObjectByOID(AnnouncementBoard.class, EVENT_BOARD_ID);
	    } else if (announcementBoard.equals(NEWS_ANNOUNCEMENT_BOARD)) {
		board = (AnnouncementBoard) RootDomainObject.readDomainObjectByOID(AnnouncementBoard.class, NEWS_BOARD_ID);
	    } else {
		return FAILURE + " - " + "announcementBoard value must be EVENT_BOARD or NEWS_BOARD";
	    }

	    Campus selectedCampus = Announcement.getCampus(campus);
	    java.util.List<AnnouncementCategory> announcementCategories = readCategories(categories);
	    DateTime publicationBeginDateValue = dateTimeFormatter.parseDateTime(publicationBeginDate);
	    DateTime publicationEndDateValue = dateTimeFormatter.parseDateTime(publicationEndDate);
	    DateTime eventBeginDateValue = dateTimeFormatter.parseDateTime(eventBeginDate);
	    DateTime eventEndDateValue = dateTimeFormatter.parseDateTime(eventEndDate);

	    byte[] realFileContents = null;
	    if (!StringUtils.isEmpty(fileName) && fileSize > 0) {
		realFileContents = getFileContents(fileContents.getBytes());
	    }

	    validateField(fileContents, "File", MAX_SIZE_FILE);

	    Announcement.createAnnouncement(board, authorName, authorEmail, new MultiLanguageString(body), selectedCampus,
		    announcementCategories, place, publicationBeginDateValue, publicationEndDateValue, eventBeginDateValue,
		    eventEndDateValue, new MultiLanguageString(subject), new MultiLanguageString(excerpt), false, editorNotes,
		    fileName, realFileContents);

	    return SUCCESS;

	} catch (AnnouncementManagementException exception) {
	    return FAILURE + " - " + exception.getMessage();
	}
    }

    private void validateField(String value, String fieldName, int maxSizeSubject) throws AnnouncementManagementException {
	if (!StringUtils.isEmpty(value) && value.length() > maxSizeSubject) {
	    throw new AnnouncementManagementException(fieldName + " exceeds the limit with is " + maxSizeSubject);
	}
    }

    private List<AnnouncementCategory> readCategories(String[] categories) throws AnnouncementManagementException {
	java.util.List<AnnouncementCategory> categoriesList = new java.util.ArrayList<AnnouncementCategory>();

	for (String category : categories) {
	    AnnouncementCategoryType type = AnnouncementCategoryType.valueOf(category);
	    if (type == null) {
		throw new AnnouncementManagementException(String.format("Category type %s unknown", category));
	    }

	    categoriesList.add(AnnouncementCategoryType.getAnnouncementCategoryByType(type));
	}

	return categoriesList;
    }

    private byte[] getFileContents(byte[] byteContents) {
	return Base64.decode(byteContents);
    }

    private static class AnnouncementManagementException extends Exception {
	public AnnouncementManagementException(String message) {
	    super(message);
	}
    }
}
