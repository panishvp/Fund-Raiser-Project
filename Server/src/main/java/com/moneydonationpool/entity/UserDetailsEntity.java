package com.moneydonationpool.entity;

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
@Table(name = "user_details")
@JsonInclude(Include.NON_NULL)
public class UserDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "email_id",nullable = false)
	private String emailId;
	
	@Column(name = "creation_date")
	private Timestamp creationDate;
	
	@Column(name = "modified_date")
	private Timestamp modifiedDate;
	
	@Column(name = "is_active",nullable = false)
	private boolean isActive;
	
	@Column(name = "user_type",nullable = false)
	private String userType;
	

}
