package com.moneydonationpool.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Login_Session")
@JsonInclude(Include.NON_NULL)
public class LoginEntity {
	
	
	@Column(name = "user_id",nullable = false)
	private Integer userId;
	
	@Id
	@Column(name = "access_token",nullable = false,length = 2000)
	private String accessToken;

}
