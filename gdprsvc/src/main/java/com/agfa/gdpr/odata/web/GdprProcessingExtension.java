package com.agfa.gdpr.odata.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.provider.ComplexType;
import org.apache.olingo.odata2.api.edm.provider.Property;
import org.apache.olingo.odata2.api.edm.provider.Schema;
import org.apache.olingo.odata2.api.edm.provider.SimpleProperty;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmExtension;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmSchemaView;

import com.agfa.gdpr.odata.web.extentions.RecordsProcessor;


/**
 * 
 * Class for registering function imports.
 * 
 */
public class GdprProcessingExtension implements JPAEdmExtension {

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Register function imports.
	 */
	Locale locale;

	@Override
	public void extendWithOperation(JPAEdmSchemaView view) {
		view.registerOperations(RecordsProcessor.class, null);
		//view.registerOperations(SalesOrderProcessor.class, null);
		//view.registerOperations(CustomerReviewProcessor.class, null);

	}

	   @Override
	    public void extendJPAEdmSchema(final JPAEdmSchemaView view) {
	      Schema edmSchema = view.getEdmSchema();
	   //   edmSchema.getComplexTypes().add(getComplexType());
	    }
	   

	    private ComplexType getComplexType() {
	      ComplexType complexType = new ComplexType();

	      List<Property> properties = new ArrayList<Property>();
	      SimpleProperty property = new SimpleProperty();

	      property.setName("Text");
	      property.setType(EdmSimpleTypeKind.Single);
	      properties.add(property);

	    

	      complexType.setName("Question");
	      complexType.setProperties(properties);

	      return complexType;

	    }
	
	@Override
	public InputStream getJPAEdmMappingModelStream(){
		return null;
	}
	
	
	
	
	
	

}
