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
@Table(name = "GDPR_CATEGORYTEXT")
@NamedQueries({
	@NamedQuery(name = "CategoryText.getAllTexts", query = "SELECT c FROM CategoryText c"),
	
})

//@NamedQuery(name = "CategoryText.getAllTextByLanguage", query = "SELECT c FROM CategoryText c where language = ?language"),
@Cache(isolation=CacheIsolationType.ISOLATED)
public class CategoryText {

	public static final String QUERY_GETALLCATEGORYTEXTS = "CategoryText.getAllTexts";
	//public static final String QUERY_GETCATEGORYTEXTBYLANGUAGE = "CategoryText.getAllTextByLanguage";
	

	@Id
	@ManyToOne(optional=false)
	@JoinColumn(name="CATEGORY_ID",referencedColumnName="CATEGORY_ID")
	private Category category;

	@Id
	@Column(name = "LANGUAGE")
	private String language;
	
	@Column(name = "CATEGORYNAME")
	private String categoryName;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

	
	
}