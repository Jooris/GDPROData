package com.agfa.gdpr.db;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GDPR_CATEGORY")
@NamedQueries({
	@NamedQuery(name = "Category.getAllCategories", query = "SELECT c FROM Category c")
})
public class Category {

	public static final String QUERY_GETALLCATEGORIES = "Category.getAllCategories";
	
	/* Customer ids are generated within a number range starting with 1 */
	@TableGenerator(name = "CategoryGenerator", 
			table = "ESPM_ID_GENERATOR", 
			pkColumnName = "GENERATOR_NAME", 
			valueColumnName = "GENERATOR_VALUE", 
			pkColumnValue = "CATEGORY", 
			initialValue = 100000000, allocationSize = 100)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CategoryGenerator")
	@Column(name = "CATEGORY_ID", length = 10)
	private String categoryId;

	@Column(name = "CATEGORY_NUMBER", unique = true)
	private String categoryName;
	
	@OneToMany(mappedBy = "fk_category", targetEntity = Question.class, fetch = FetchType.EAGER)
	private List<Question> questions;
	
	private List<Question> validQuestions;
	
	@OneToMany(mappedBy = "category", targetEntity = CategoryText.class, fetch = FetchType.EAGER)
	private List<CategoryText> texts;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<CategoryText> getTexts() {
		return texts;
	}

	public void setTexts(List<CategoryText> texts) {
		this.texts = texts;
	}

	public List<Question> getValidQuestions() {
		return getQuestions();
	}

	public void setValidQuestions(List<Question> validQuestions) {
		this.validQuestions =validQuestions;
	}
	
	
	
	

	
	
}