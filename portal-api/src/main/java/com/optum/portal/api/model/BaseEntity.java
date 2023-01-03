package com.optum.portal.api.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
	
	@Column(name = "created_date", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	@Column(name = "updated_date", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate  updatedDate;
	@Column(name = "created_by", nullable = true)
    private String createdBy;
	@Column(name = "updated_by", nullable = true)
    private String updatedBy;
	
	/**
	 * @return the createdDate
	 */
	public LocalDate  getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDate  createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the updatedDate
	 */
	public LocalDate  getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
