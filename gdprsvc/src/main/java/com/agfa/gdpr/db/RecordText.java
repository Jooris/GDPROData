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
@Table(name = "GDPR_RECORDTEXT")
@NamedQueries({
	@NamedQuery(name = "RecordText.getAllTexts", query = "SELECT c FROM RecordText c"),
	
})
@Cache(isolation=CacheIsolationType.ISOLATED)
//@NamedQuery(name = "CategoryText.getAllTextByLanguage", query = "SELECT c FROM CategoryText c where language = ?language"),
public class RecordText {

	public static final String QUERY_GETALLCATEGORYTEXTS = "RecordText.getAllTexts";
	//public static final String QUERY_GETCATEGORYTEXTBYLANGUAGE = "CategoryText.getAllTextByLanguage";
	

	@Id
	@ManyToOne(optional=false)
	@JoinColumn(name="RECORD_ID", referencedColumnName="RECORD_ID")
	private Record record;

	@Id
	@Column(name = "LANGUAGE")
	private String language;
	
	@Column(name = "PROCESSNAME")
	private String processName;

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	
	
	

	
	
}