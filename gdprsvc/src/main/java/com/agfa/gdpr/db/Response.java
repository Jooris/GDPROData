package com.agfa.gdpr.db; 

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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.config.CacheIsolationType;

@Entity
@Table(name = "GDPR_RESPONSE")
@NamedQueries({
	@NamedQuery(name = "Response.getAllResponses", query = "SELECT c FROM Response c")
})
@Cache(isolation=CacheIsolationType.ISOLATED)
public class Response {
	
	
	@TableGenerator(name = "ResponseGenerator", 
			table = "ESPM_ID_GENERATOR", 
			pkColumnName = "GENERATOR_NAME", 
			valueColumnName = "GENERATOR_VALUE", 
			pkColumnValue = "REPONSE", 
			initialValue = 100000000, allocationSize = 100)
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ResponseGenerator")
	@Column(name = "RESPONSE_ID", length = 10)
	private String responseid;
	
	
	@ManyToOne()
	@JoinColumn(name ="RECORD_ID")
	private Record fk_record;

	
	@ManyToOne()
	@JoinColumn(name ="ANSWER_ID")
	private Answer fk_answer;

	
	@Column(name = "Text",nullable= true )
	private String text;


	

	public Record getFk_record() {
		return fk_record;
	}

	public void setFk_record(Record fk_record) {
		this.fk_record = fk_record;
	}

	public Answer getFk_answer() {
		return fk_answer;
	}

	public void setFk_answer(Answer fk_answer) {
		this.fk_answer = fk_answer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getResponseid() {
		return responseid;
	}

	public void setResponseid(String responseid) {
		this.responseid = responseid;
	}
	
	
	
}
