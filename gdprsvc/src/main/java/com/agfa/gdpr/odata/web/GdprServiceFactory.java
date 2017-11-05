package com.agfa.gdpr.odata.web;

import java.util.Locale;

import javax.persistence.EntityManagerFactory;

import org.apache.olingo.odata2.core.exception.ODataRuntimeException;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;

/**
 * Odata JPA Processor implementation class. This is required for the
 * configuration of OData via the Olingo Framework.
 * <p>
 * For more information regarding the Olingo framework configuration steps,
 * refer to the documentation: https://olingo.apache.org/doc/odata4/index.html
 */
public class GdprServiceFactory extends ODataJPAServiceFactory {

	/**
	 * The package that contains all the model classes.
	 */
	private static final String PERSISTENCE_UNIT_NAME = "com.sap.gdpr.model";

	@Override
	public ODataJPAContext initializeODataJPAContext()
			throws ODataJPARuntimeException {
		ODataJPAContext oDataJPAContext = this.getODataJPAContext();
		
		Locale locale = Locale.ENGLISH;
		
		try {
			String acceptlang = oDataJPAContext.getODataContext().getRequestHeader("Accept-Language"); //: nl-BE,nl-NL;q=0.8,nl;q=0.6,en-US;q=0.4,en;q=0.2")
			
			if(acceptlang!=null){
				String localeStr = acceptlang.split(",")[0];
				String[] l = localeStr.split("_");
			    switch(l.length){
			        case 2: locale = new Locale(l[0], l[1]); break;
			        case 3: locale = new Locale(l[0], l[1], l[2]); break;
			        default: locale = new Locale(l[0]); break;
			    }
				
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EntityManagerFactory emf;
		
		try {
			emf = JpaEntityManagerFactory.getEntityManagerFactory();
			oDataJPAContext.setEntityManagerFactory(emf);
			oDataJPAContext.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
			GdprProcessingExtension ext = new GdprProcessingExtension();
			ext.setLocale(locale);
			oDataJPAContext.setJPAEdmExtension(ext);
			oDataJPAContext.setJPAEdmMappingModel("GdprEdmMapping.xml");
			//emf.getProperties().put("locale", locale);
			return oDataJPAContext;
		} catch (Exception e) {
			throw new ODataRuntimeException(e);
		}

	}
}
