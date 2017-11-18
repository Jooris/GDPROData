package com.agfa.gdpr.db;

import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmExtension;
import org.apache.olingo.odata2.jpa.processor.api.model.JPAEdmSchemaView;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.config.CacheIsolationType;

import com.agfa.gdpr.db.rc.RCCategory;

//import com.agfa.gdpr.db.rc.RCCategory;

@Entity
@Table(name = "GDPR_RECORD")
@NamedQueries({
	@NamedQuery(name = "Record.getAllRecords", query = "SELECT c FROM Record c"),
	@NamedQuery(name = "Record.getRecordById", query = "SELECT c FROM Record c where c.recordId = ?1")
})
@Cache(isolation=CacheIsolationType.ISOLATED)
public class Record implements JPAEdmExtension {

	public static final String QUERY_GETALLRECORDS = "Record.getAllRecords";
	public static final String QUERY_GETRECORDBYID = "Record.getRecordById";
	
	public static final String STATUS_DRAFT = "DRAFT";
	public static final String STATUS_SUBMITTED = "SUBMITTED";
	public static final String STATUS_PUBLISHED = "PUBLISHED";
	public static final String STATUS_OBSOLETE = "OBSOLETE";
	
	
	/* Customer ids are generated within a number range starting with 1 */
	@TableGenerator(name = "CustomerGenerator", table = "ESPM_ID_GENERATOR", pkColumnName = "GENERATOR_NAME", valueColumnName = "GENERATOR_VALUE", pkColumnValue = "Record", initialValue = 100000000, allocationSize = 100)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RecordGenerator")
	@Column(name = "RECORD_ID", length = 10)
	private String recordId;

	@Column(name = "RECORD_NUMBER")
	private String recordName;

	@Column(name = "PROCESS", length = 30)
	private String process;
	
	@Column(name = "ORGANISATION", length = 30)
	private String organisation;
	
	@Column(name = "PC", length = 30)
	private String pc;
	
	@Column(name = "COUNTRY", length = 30)
	private String country;
	
	@Column(name = "DATACATEGORY", length = 30)
	private String datacategory;
	
	@Column(name = "LEGALBASE", length = 30)
	private String legalbase;
	
	@Column(name = "CREATIONDATE")
	private Date creationdate;
	
	@Column(name = "SUBMITDATE")
	private Date submitdate;
	
	@Column(name = "OBSOLETEDAY")
	private Date obsoleteday;
	
	@Column(name = "REVIEWDATE")
	private Date reviewdate;

	@Column(name = "STATUS", length = 10)
	private String status;
	
	@OneToMany(mappedBy = "record", targetEntity = RecordText.class, fetch = FetchType.EAGER)
	private List<RecordText> texts;
	
	@OneToMany(mappedBy = "fk_record", targetEntity = Response.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Response> responses;
	
	
	@OneToMany(mappedBy = "fk_record", targetEntity = RCCategory.class, fetch = FetchType.EAGER)
	private List<RCCategory> rcCategories;
	
	
	/*
	@ManyToMany
	@JoinTable(
		      name="GDPR_RECORDCATEGORIESVIEW",
		      joinColumns=@JoinColumn(name="RECORD_ID", referencedColumnName="RECORD_ID"),
		      inverseJoinColumns=@JoinColumn(name="CATEGORY_CATEGORY_ID", referencedColumnName="CATEGORY_ID"))
	private List<Category> categories;
	*/
	
//	@ElementCollection
//	private List<Category> categories;
	
	
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDatacategory() {
		return datacategory;
	}

	public void setDatacategory(String datacategory) {
		this.datacategory = datacategory;
	}

	public String getLegalbase() {
		return legalbase;
	}

	public void setLegalbase(String legalbase) {
		this.legalbase = legalbase;
	}

	public Date getReviewdate() {
		return reviewdate;
	}

	public void setReviewdate(Date reviewdate) {
		this.reviewdate = reviewdate;
	}

	public List<RecordText> getTexts() {
		return texts;
	}

	public void setTexts(List<RecordText> texts) {
		this.texts = texts;
	}
	

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	/*
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	*/

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(Date submitdate) {
		this.submitdate = submitdate;
	}

	public Date getObsoleteday() {
		return obsoleteday;
	}

	public void setObsoleteday(Date obsoleteday) {
		this.obsoleteday = obsoleteday;
	}

	
	public List<RCCategory> getRcCategories() {
		return rcCategories;
	}

	public void setRcCategories(List<RCCategory> rcCategories) {
		this.rcCategories = rcCategories;
	}

	@Override
	public void extendWithOperation(JPAEdmSchemaView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendJPAEdmSchema(JPAEdmSchemaView view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream getJPAEdmMappingModelStream() {
		// TODO Auto-generated method stub
		return null;
	}
	 
	
	@PrePersist
	void onCreate() {
		this.setCreationdate(new Date(Calendar.getInstance().getTimeInMillis()));
		this.setStatus(STATUS_DRAFT);
	}
	/*
	@PreUpdate
	private void preUpdate(Record entity) {
	
			if(this.getStatus()!=STATUS_SUBMITTED){
				if(entity.getStatus()==STATUS_SUBMITTED){
					this.setSubmitdate(new Date(Calendar.getInstance().getTimeInMillis()));
				}
			}
			if(this.getStatus()!=STATUS_PUBLISHED){
				if(entity.getStatus()==STATUS_PUBLISHED){
					this.setReviewdate(new Date(Calendar.getInstance().getTimeInMillis()));
				}
			}
			if(this.getStatus()!=STATUS_OBSOLETE){
				if(entity.getStatus()==STATUS_OBSOLETE){
					this.setObsoleteday(new Date(Calendar.getInstance().getTimeInMillis()));
				}
			}
	
	}
*/
	

}