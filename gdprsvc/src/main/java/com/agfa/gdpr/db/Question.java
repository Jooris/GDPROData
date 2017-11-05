package com.agfa.gdpr.db;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GDPR_QUESTION")
@NamedQueries({
	@NamedQuery(name = "Question.getAllQuestions", query = "SELECT q FROM Question q")
})
public class Question {

	public String getCustAtt() {
		return "Random Text"+getQuestionId();
	}

	public void setCustAtt(String custAtt) {
		this.custAtt = custAtt;
	}

	public static final String QUERY_GETALLQUESTIONS = "Question.getAllQuestions";
	
	/* Customer ids are generated within a number range starting with 1 */
	@TableGenerator(name = "QuestionGenerator", table = "ESPM_ID_GENERATOR", pkColumnName = "GENERATOR_NAME", valueColumnName = "GENERATOR_VALUE", pkColumnValue = "QUESTION", initialValue = 100000000, allocationSize = 100)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "QuestionGenerator")
	@Column(name = "QUESTION_ID", length = 10)
	private String questionId;

	@Column(name = "VALIDFROM")
	private Date validFrom;

	@Column(name = "VALIDTO")
	private Date validTo;
	
	@Column(name = "MULTIPLE")
	private boolean multiple;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="CATEGORY_ID", referencedColumnName="CATEGORY_ID")
	private Category fk_category;
	
	@OneToMany(mappedBy = "question", targetEntity = QuestionText.class, fetch = FetchType.EAGER)
	private List<QuestionText> texts;

	
	@OneToMany(mappedBy = "fk_question", targetEntity = Answer.class, fetch = FetchType.EAGER)
	private List<Answer> answers;
	
	@javax.persistence.Transient
	private String custAtt;
	 
	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
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

	public List<QuestionText> getTexts() {
		return texts;
	}

	public void setTexts(List<QuestionText> texts) {
		this.texts = texts;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public Category getFk_category() {
		return fk_category;
	}

	public void setFk_category(Category fk_category) {
		this.fk_category = fk_category;
	}
	
	
	
	
	
	

	

	
}