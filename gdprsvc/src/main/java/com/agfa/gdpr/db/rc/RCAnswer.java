package com.agfa.gdpr.db.rc;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.agfa.gdpr.db.Answer;
import com.agfa.gdpr.db.Question;
import com.agfa.gdpr.db.Response;

@Entity
@Table(name = "GDPR_RC_ANSW_VIEW" )
@NamedQueries({
})
public class RCAnswer {


	public static final String TYPE_SUGGESTEDVALUE = "SV";
	public static final String TYPE_FREETEXT = "FT";
	
	@Id
	@Column(name = "RCQAID")
	private String rcqaid;
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="RCQID", referencedColumnName = "RCQID")
	private RCQuestion fk_rcQuestion;
	
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="ANSWER_ID", referencedColumnName = "ANSWER_ID")
	private Answer fk_answer;
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="RESPONSE_ID", referencedColumnName = "RESPONSE_ID")
	private Response fk_response;

	
	@Column(name = "VALIDFROM")
	private Date validFrom;

	@Column(name = "VALIDTO")
	private Date validTo;
	
	@Column(name = "TYPE")
	private String type;

	public String getRcqaid() {
		return rcqaid;
	}

	public void setRcqaid(String rcqaid) {
		this.rcqaid = rcqaid;
	}

	public RCQuestion getFk_rcQuestion() {
		return fk_rcQuestion;
	}

	public void setFk_rcQuestion(RCQuestion fk_rcQuestion) {
		this.fk_rcQuestion = fk_rcQuestion;
	}

	public Answer getFk_answer() {
		return fk_answer;
	}

	public void setFk_answer(Answer fk_answer) {
		this.fk_answer = fk_answer;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Response getFk_response() {
		return fk_response;
	}

	public void setFk_response(Response fk_response) {
		this.fk_response = fk_response;
	}
	
	
	
	
	

	
	
}