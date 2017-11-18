package com.agfa.gdpr.odata.web.extentions;

import java.util.Calendar;
import java.util.Date;
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

import com.agfa.gdpr.db.Answer;
import com.agfa.gdpr.db.AnswerText;
import com.agfa.gdpr.db.Question;
import com.agfa.gdpr.db.QuestionText;
import com.agfa.gdpr.db.Record;
import com.agfa.gdpr.db.Response;
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
public class RecordsProcessor {

	/**
	 * Function Import implementation for getting customer by email address
	 * 
	 * @param emailAddress
	 *            email address of the customer
	 * @return customer entity.
	 * @throws ODataException
	 */
	@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "GetSmallRecords", entitySet = "Records", returnType = @ReturnType(type = Type.ENTITY, isCollection = true))
	public List<Record> getCustomerByEmailAddress(
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
	
	
	@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "ActualQuestions", entitySet = "Questions", returnType = @ReturnType(type = Type.ENTITY, isCollection = true))
	public List<Question> getActualQuestions() throws ODataException {
		EntityManagerFactory emf = Utility.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		List<Question> custList = null;
		List<Question> result = new LinkedList<Question>();
		try {

			Query query = em.createNamedQuery(Question.QUERY_GETALLQUESTIONS);
		

			try {

				custList = query.getResultList();
				Date now = Calendar.getInstance().getTime();
				for(Question q : custList) {
					if (q.getValidFrom()== null || q.getValidFrom().before(now)) {
						if (q.getValidTo()== null || q.getValidTo().after(now)) {
							result.add(q);
						}
					}
				}
				
				return result;

			} catch (NoResultException e) {
				throw new ODataApplicationException("No Questions",
						Locale.ENGLISH, HttpStatusCodes.BAD_REQUEST, e);
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "ActualAnswers", entitySet = "Answers", returnType = @ReturnType(type = Type.ENTITY, isCollection = true))
	public List<Answer> getActualAnswers(
			@EdmFunctionImportParameter(name = "questionID") String questionID
			) throws ODataException {
		EntityManagerFactory emf = Utility.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		List<Answer> custList = null;
		List<Answer> result = new LinkedList<Answer>();
		try {

			Query query = em.createNamedQuery(Answer.QUERY_GETANSWERSFORQUESTION).setParameter(1, questionID);
		

			try {

				custList = query.getResultList();
				Date now = Calendar.getInstance().getTime();
				for(Answer a : custList) {
					if (a.getValidFrom()== null || a.getValidFrom().before(now)) {
						if (a.getValidTo()== null || a.getValidTo().after(now)) {
							result.add(a);
						}
					}
				}
				
				return result;

			} catch (NoResultException e) {
				throw new ODataApplicationException("No Questions",
						Locale.ENGLISH, HttpStatusCodes.BAD_REQUEST, e);
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "AddResponse", entitySet = "Responses", returnType = @ReturnType(type = Type.ENTITY, isCollection = false))
	public Response AddResponse(
			@EdmFunctionImportParameter(name = "recordID") String recordID,
			@EdmFunctionImportParameter(name = "answerID") String answerID,
			@EdmFunctionImportParameter(name = "text") String text
			) throws ODataException {
		EntityManagerFactory emf = Utility.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		try {
				Response response = new Response();
				Answer answer = (Answer)em.createNamedQuery(Answer.QUERY_GETANSWERBYID).setParameter(1, answerID).getSingleResult();
		    	Record record = (Record)em.createNamedQuery(Record.QUERY_GETRECORDBYID).setParameter(1, recordID).getSingleResult();
		    	
		    	response.setFk_answer(answer);
		    	response.setFk_record(record);
		    	response.setText(text);
		    	
		    	 em.getTransaction().begin();
		    	    if (!em.contains(response)) {
		    	        // persist object - add to entity manager
		    	        em.persist(response);
		    	        // flush em - save to DB
		    	        em.flush();
		    	    }
		    	    // commit transaction at all
		    	    em.getTransaction().commit();
		    	    
		    	return response;

		} finally {
			em.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "setAnswerText", entitySet = "AnswerTexts", returnType = @ReturnType(type = Type.ENTITY, isCollection = false))
	public AnswerText setAnswerText(
			@EdmFunctionImportParameter(name = "language") String language,
			@EdmFunctionImportParameter(name = "answerID") String answerID,
			@EdmFunctionImportParameter(name = "text") String text
			) throws ODataException {
		EntityManagerFactory emf = Utility.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		try {
				AnswerText answerText;
				Answer answer = (Answer)em.createNamedQuery(Answer.QUERY_GETANSWERBYID).setParameter(1, answerID).getSingleResult();
		    	
				answerText = (AnswerText)em.createNamedQuery(AnswerText.QUERY_GETTEXT).setParameter(1, answer).setParameter(2, language).getSingleResult();
				
				if(answerText!=null){
					answerText.setAnswerText(text);
				}
				else{
					answerText = new AnswerText();
					
					answerText.setAnswer(answer);
					answerText.setAnswerText(text);
					answerText.setLanguage(language);
				}
				
		    	
		    	
		    	 em.getTransaction().begin();
		    	    if (!em.contains(answerText)) {
		    	        // persist object - add to entity manager
		    	        em.persist(answerText);
		    	        // flush em - save to DB
		    	        em.flush();
		    	    }
		    	    // commit transaction at all
		    	    em.getTransaction().commit();
		    	    
		    	return answerText;

		} finally {
			em.close();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@EdmFunctionImport(name = "setQuestionText", entitySet = "QuestionTexts", returnType = @ReturnType(type = Type.ENTITY, isCollection = false))
	public QuestionText setQuestionText(
			@EdmFunctionImportParameter(name = "language") String language,
			@EdmFunctionImportParameter(name = "questionID") String questionID,
			@EdmFunctionImportParameter(name = "text") String text
			) throws ODataException {
		EntityManagerFactory emf = Utility.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		try {
				QuestionText questionText;
				Question question = (Question)em.createNamedQuery(Question.QUERY_GETQUESTIONBYID).setParameter(1, questionID).getSingleResult();
		    	
				questionText = (QuestionText)em.createNamedQuery(QuestionText.QUERY_GETTEXT).setParameter(1, question).setParameter(2, language).getSingleResult();
				
				if(questionText!=null){
					questionText.setQuestionText(text);
				}
				else{
					questionText = new QuestionText();
					
					questionText.setQuestion(question);
					questionText.setQuestionText(text);
					questionText.setLanguage(language);
				}
				
		    	
		    	
		    	 em.getTransaction().begin();
		    	    if (!em.contains(questionText)) {
		    	        // persist object - add to entity manager
		    	        em.persist(questionText);
		    	        // flush em - save to DB
		    	        em.flush();
		    	    }
		    	    // commit transaction at all
		    	    em.getTransaction().commit();
		    	    
		    	return questionText;

		} finally {
			em.close();
		}
	}
	
}
