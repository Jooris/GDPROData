package com.agfa.gdpr.odata.web.extentions;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport.ReturnType;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImport.ReturnType.Type;
import org.apache.olingo.odata2.api.annotation.edm.EdmFunctionImportParameter;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.exception.ODataException;

import com.agfa.gdpr.db.Record;
import com.agfa.gdpr.odata.web.Utility;



/**
 * 
 * This is a custom Apache Olingo Function Import. For more reference
 * information regarding a Function Import, refer to the official Olingo
 * documentation:
 * <p>
 * https://olingo.apache.org/doc/odata2/tutorials/jpafunctionimport.html
 * <p>
 * http://olingo.apache.org/doc/odata2/
 * <p>
 * This class is used to define custom OData functions for {@link Customer}
 * entity.
 * 
 * 
 */
public class TextProcessor {

	/**
	 * Function Import implementation for getting customer by email address
	 * 
	 * @param emailAddress
	 *            email address of the customer
	 * @return customer entity.
	 * @throws ODataException
	 */
	@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "Text", entitySet = "Category", returnType = @ReturnType(type = Type.ENTITY, isCollection = false))
	public List<Record> getCategoryText(
			@EdmFunctionImportParameter(name = "EmailAddress") String emailAddress) throws ODataException {
		EntityManagerFactory emf = Utility.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		List<Record> custList = null;
		List<Record> result = new LinkedList<Record>();
		try {

			Query query = em.createNamedQuery(Record.QUERY_GETALLRECORDS);
		

			try {

				custList = query.getResultList();
				for(Record r : custList) {
					if (r.getProcess()!= null && r.getProcess().length() <5) {
						result.add(r);
					}
				}
				
				return result;

			} catch (NoResultException e) {
				throw new ODataApplicationException("No matching customer with Email Address:" + emailAddress,
						Locale.ENGLISH, HttpStatusCodes.BAD_REQUEST, e);
			}
		} finally {
			em.close();
		}
	}
}
