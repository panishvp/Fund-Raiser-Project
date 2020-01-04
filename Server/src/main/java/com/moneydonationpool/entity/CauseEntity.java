package com.moneydonationpool.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cause")
@JsonInclude(Include.NON_NULL)
public class CauseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cause_id")
	private Integer causeId;
	
	@Column(name = "category_id",nullable = false)
	private Integer categoryId;

	@Column(name = "cause_target_amt",nullable = false)
	private Integer causeTargetAmt;
	
	@Column(name = "creation_date",nullable = false)
	private Timestamp creationDate;
	
	@Column(name = "is_active",nullable = false)
	private Boolean isActive;
	
	@Column(name = "cause_expiration_date",nullable = false)
	private Date causeExpirationDate;
	
	@Column(name = "description",length=5000)
	private String description;
	
	@Column(name = "collected_amt",nullable = false)
	private Integer collectedAmt;
	
	@Column(name = "cause_title",nullable = false,length=500)
	private String causeTitle;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "accountNumber",nullable = false,length=20)
	private String bankAccountNumber;
	
	@Column(name = "BIC",nullable = false,length=20)
	private String bic;

	@Column(name = "image_name",nullable = false)
	private String imageName;
}
