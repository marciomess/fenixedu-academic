package net.sourceforge.fenixedu.renderers;

import java.util.Collection;
import java.util.List;

import net.sourceforge.fenixedu.domain.DomainObject;
import net.sourceforge.fenixedu.renderers.components.HtmlComponent;
import net.sourceforge.fenixedu.renderers.components.HtmlText;
import net.sourceforge.fenixedu.renderers.layouts.Layout;
import net.sourceforge.fenixedu.renderers.layouts.TabularLayout;
import net.sourceforge.fenixedu.renderers.model.MetaObject;
import net.sourceforge.fenixedu.renderers.model.MetaSlot;
import net.sourceforge.fenixedu.renderers.schemas.Schema;
import net.sourceforge.fenixedu.renderers.utils.RenderKit;
import net.sourceforge.fenixedu.util.MultiLanguageString;

public class TabularNonNullValuesRenderer extends OutputRenderer{

	private static int numberOfColumns = 2;
	
	private String label;
	private String schema;
	private String columnClasses;
	
	public String getColumnClasses() {
		return columnClasses;
	}

	public void setColumnClasses(String columnClasses) {
		this.columnClasses = columnClasses;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		DomainObject objectToRender = (DomainObject) object;
		return new TabularNonNullValuesLayout(objectToRender);
	}
	
	private class TabularNonNullValuesLayout extends TabularLayout {

		private DomainObject object;
		private MetaObject metaObject;
		private List<MetaSlot> slots;
		private int indexSkipped=0;
		
		public TabularNonNullValuesLayout(DomainObject object) {
			this.object = object;
			this.metaObject = getContext().getMetaObject();
			this.slots = metaObject.getSlots();
		}
		
		private Schema translateSchema(String name) {
			return RenderKit.getInstance().findSchema(name);
		}
		
		@Override
        protected boolean isHeader(int rowIndex, int columnIndex) {
            return columnIndex == 0;
        }
		
		@Override
		protected HtmlComponent getComponent(int rowIndex, int columnIndex) {
			if(!renderRowIndex(rowIndex+indexSkipped)) {
				indexSkipped++;
				return getComponent(rowIndex, columnIndex);
			}
			else {
				return (columnIndex==0) ? new HtmlText(addLabel(slots.get(rowIndex+indexSkipped).getLabel())) : renderSlot(this.metaObject.getSlots().get(rowIndex+indexSkipped));
			}
			
			
		}

		@Override
		protected HtmlComponent getHeaderComponent(int columnIndex) {
			return new HtmlText();
		}

		@Override
		protected int getNumberOfColumns() {
			return numberOfColumns;
		}

		@Override
		protected int getNumberOfRows() {
			int numberOfRows=0;
			for(MetaSlot slot : metaObject.getSlots()) {
				if(isValidObject(slot.getObject())) {
					numberOfRows++;
				}
			}
			return numberOfRows;
		}
		
		private boolean renderRowIndex(int rowIndex) {
			return isValidObject(this.metaObject.getSlots().get(rowIndex).getObject());
		}
		
		private String addLabel(String name) {
			return (getLabel()==null) ? name + ":" : name + getLabel();
		}
		
		private boolean isValidObject(Object object) {
			return !(object==null || (object instanceof String && ((String)object).length()==0)
			|| (object instanceof Collection && ((Collection)object).size()==0) 
			|| (object instanceof MultiLanguageString && ((MultiLanguageString)object).getAllContents().size()==0)
			);
		}
		
	}

}
