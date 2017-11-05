package com.agfa.gdpr.db.rc;

import java.sql.Date;
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
import com.agfa.gdpr.db.Question;
import com.agfa.gdpr.db.Record;

@Entity
@Table(name = "GDPR_RC_QUES_VIEW")
@NamedQueries({

})
public class RCQuestion {

	@Id
	@Column(name = "RCQID")
	private String rcid;
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="RCID", referencedColumnName = "RCID")
	private RCCategory fk_rcCategory;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="QUESTION_ID", referencedColumnName = "QUESTION_ID")
	private Question fk_question;
	
	@Column(name = "VALIDFROM")
	private Date validFrom;

	@Column(name = "VALIDTO")
	private Date validTo;
	
	@Column(name = "MULTIPLE")
	private boolean multiple;

	@OneToMany(mappedBy = "fk_rcQuestion", targetEntity = RCAnswer.class, fetch = FetchType.EAGER)
	private List<RCAnswer> rcAnswers;

	public String getRcid() {
		return rcid;
	}


	public void setRcid(String rcid) {
		this.rcid = rcid;
	}


	


	public RCCategory getFk_rcCategory() {
		return fk_rcCategory;
	}


	public void setFk_rcCategory(RCCategory fk_rcCategory) {
		this.fk_rcCategory = fk_rcCategory;
	}


	public Question getFk_question() {
		return fk_question;
	}


	public void setFk_question(Question fk_question) {
		this.fk_question = fk_question;
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


	public boolean isMultiple() {
		return multiple;
	}


	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}


	public List<RCAnswer> getRcAnswers() {
		return rcAnswers;
	}


	public void setRcAnswers(List<RCAnswer> rcAnswers) {
		this.rcAnswers = rcAnswers;
	}
	
	
	

	
	
	
	
}