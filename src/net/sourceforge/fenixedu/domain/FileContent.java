package net.sourceforge.fenixedu.domain;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.fenixedu.domain.accessControl.Group;
import net.sourceforge.fenixedu.domain.contents.Attachment;

public class FileContent extends FileContent_Base {

    protected FileContent() {
	super();

	setAttachment(null);
    }

    protected FileContent(Attachment attachment) {
	super();

	setAttachment(attachment);
    }

    public FileContent(Attachment attachment, String filename, String displayName, String mimeType, String checksum,
	    String checksumAlgorithm, Integer size, String externalStorageIdentification, Group permittedGroup) {
	this(attachment);
	init(filename, processDisplayName(displayName), mimeType, checksum, checksumAlgorithm, size,
		externalStorageIdentification, permittedGroup);
    }

    public FileContent(String filename, String displayName, String mimeType, String checksum, String checksumAlgorithm,
	    Integer size, String externalStorageIdentification, Group permittedGroup) {
	init(filename, processDisplayName(displayName), mimeType, checksum, checksumAlgorithm, size,
		externalStorageIdentification, permittedGroup);
    }

    @Override
    public void delete() {
	Attachment attachment = getAttachment();
	if (attachment != null) {
	    setAttachment(null);
	    attachment.delete();
	}
	removeRootDomainObject();
	super.deleteDomainObject();
    }

    public static FileContent readByOID(Integer idInternal) {
	return (FileContent) RootDomainObject.getInstance().readFileByOID(idInternal);
    }

    public static List<FileContent> readAllFileItems() {
	List<FileContent> fileItems = new ArrayList<FileContent>();

	for (File file : RootDomainObject.getInstance().getFiles()) {
	    if (file instanceof FileContent) {
		fileItems.add((FileContent) file);
	    }
	}

	return fileItems;
    }

    public Site getSite() {
	return getAttachment().getSite();
    }

    private String processDisplayName(String name) {
	return name.replace('\\', '-').replace('/', '-');
    }

    @Override
    public void setDisplayName(String displayName) {
	super.setDisplayName(processDisplayName(displayName));
    }
}