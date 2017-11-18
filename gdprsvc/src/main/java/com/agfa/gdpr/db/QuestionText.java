package com.agfa.gdpr.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.config.CacheIsolationType;

@Entity
@Table(name = "GDPR_QUESTIONTEXT")
@NamedQueries({
	@NamedQuery(name = "QuestionText.getAllTexts", query = "SELECT c FROM QuestionText c"),
	@NamedQuery(name = "QuestionText.getText", query = "SELECT c FROM QuestionText c where c.question=?1 AND c.language=?2")
})

//@NamedQuery(name = "QuestionText.getAllTextByLanguage", query = "SELECT c FROM QuestionText c where language = ?language"),
@Cache(isolation=CacheIsolationType.ISOLATED)
public class QuestionText {

	public static final String QUERY_GETALLQUESTIONTEXTS = "QuestionText.getAllTexts";
	public static final String QUERY_GETTEXT = "QuestionText.getText";
	//public static final String QUERY_GETQUESTIONTEXTBYLANGUAGE = "QuestionText.getAllTextByLanguage";
	

	@Id
	@ManyToOne(optional=false)
	@JoinColumn(name="QUESTION_ID", referencedColumnName="QUESTION_ID")
	private Question question;

	@Id
	@Column(name = "LANGUAGE")
	private String language;
	
	@Column(name = "QUESTIONTEXT")
	private String questionText;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}


	

	
	
}