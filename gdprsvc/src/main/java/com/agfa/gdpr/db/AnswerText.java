package com.agfa.gdpr.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "GDPR_ANSWERTEXT")
@NamedQueries({
	@NamedQuery(name = "AnswerText.getAllTexts", query = "SELECT c FROM AnswerText c"),
	
})

//@NamedQuery(name = "CategoryText.getAllTextByLanguage", query = "SELECT c FROM CategoryText c where language = ?language"),
public class AnswerText {

	public static final String QUERY_GETALLANSWERTEXTS = "AnswerText.getAllTexts";
	//public static final String QUERY_GETCATEGORYTEXTBYLANGUAGE = "CategoryText.getAllTextByLanguage";
	

	@Id
	@ManyToOne(optional=false)
	@JoinColumn(name="ANSWER_ID" , referencedColumnName="ANSWER_ID")
	private Answer answer;

	@Id
	@Column(name = "LANGUAGE")
	private String language;
	
	@Column(name = "ANSWERTEXT")
	private String answerText;

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	

	
	

	
	
}