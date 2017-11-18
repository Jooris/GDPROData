package com.agfa.gdpr.db;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.config.CacheIsolationType;

//declare
@Entity
@Table(name = "GDPR_ANSWER" )
@NamedQueries({
	@NamedQuery(name = "Answer.getAllAnswers", query = "SELECT c FROM Answer c"),
	@NamedQuery(name = "Answer.getAnswersForQuestion", query = "SELECT c FROM Answer c where c.fk_question.questionId = ?1"),
	@NamedQuery(name = "Answer.getAnswerById", query = "SELECT c FROM Answer c where c.answerId = ?1")
})
@Cache(isolation=CacheIsolationType.ISOLATED)
public class Answer {

	public int getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}

	public static final String QUERY_GETALLANSWERS = "Answer.getAllAnswers";
	public static final String QUERY_GETANSWERSFORQUESTION = "Answer.getAnswersForQuestion";
	public static final String QUERY_GETANSWERBYID = "Answer.getAnswerById";
	
	public static final String TYPE_SUGGESTEDVALUE = "SV";
	public static final String TYPE_FREETEXT = "FT";
	
	/* Customer ids are generated within a number range starting with 1 */
	@TableGenerator(name = "AnswerGenerator", 
			table = "ESPM_ID_GENERATOR", 
			pkColumnName = "GENERATOR_NAME", 
			valueColumnName = "GENERATOR_VALUE", 
			pkColumnValue = "ANSWER", 
			initialValue = 100000000, allocationSize = 100)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AnswerGenerator")
	@Column(name = "ANSWER_ID", length = 10)
	private String answerId;

	
	@Column(name = "VALIDFROM")
	private Date validFrom;

	@Column(name = "VALIDTO")
	private Date validTo;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "SORTINDEX")
	private int sortIndex;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="QUESTION_ID", referencedColumnName="QUESTION_ID")
	private Question fk_question;
	
	@OneToMany(mappedBy = "fk_answer", targetEntity = Response.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Response> responses;
	
	@OneToMany(mappedBy = "answer", targetEntity = AnswerText.class, fetch = FetchType.EAGER)
	private List<AnswerText> texts;

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	

	public Question getFk_question() {
		return fk_question;
	}

	public void setFk_question(Question fk_question) {
		this.fk_question = fk_question;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public List<AnswerText> getTexts() {
		return texts;
	}

	public void setTexts(List<AnswerText> texts) {
		this.texts = texts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	
	

	
	
}