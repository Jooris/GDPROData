package com.agfa.gdpr.db.rc;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.agfa.gdpr.db.Category;
import com.agfa.gdpr.db.Record;

@Entity
@Table(name = "GDPR_RC_CAT_VIEW")
@NamedQueries({

})
public class RCCategory {

	@Id
	@Column(name = "RCID")
	private String rcid;
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="RECORD_ID", referencedColumnName = "RECORD_ID")
	private Record fk_record;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
	private Category fk_category;

	@Column(name = "CATEGORY_NUMBER", unique = true)
	private String categoryName;
	
	
	@OneToMany(mappedBy = "fk_rcCategory", targetEntity = RCQuestion.class, fetch = FetchType.EAGER)
	private List<RCQuestion> rcQuestions;
	

	public Record getFk_record() {
		return fk_record;
	}

	public void setFk_record(Record fk_record) {
		this.fk_record = fk_record;
	}


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getFk_category() {
		return fk_category;
	}

	public void setFk_category(Category fk_category) {
		this.fk_category = fk_category;
	}

	public String getRcid() {
		return rcid;
	}

	public void setRcid(String rcid) {
		this.rcid = rcid;
	}

	public List<RCQuestion> getRcQuestions() {
		return rcQuestions;
	}

	public void setRcQuestions(List<RCQuestion> rcQuestions) {
		this.rcQuestions = rcQuestions;
	}
	
	
	
	
}