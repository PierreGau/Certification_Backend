package com.kwiky.backend.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Message 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Column(name="content",columnDefinition = "LONGTEXT")
	@Type(type = "text")
	private String content;
	
	@NotNull
	@Column(name="postTime")
	private LocalDateTime postTime;
	
	@Column(name="editTime")
	private LocalDateTime editTime;
	
	@ManyToOne
	@NotNull
	private User user;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@NotNull
	private Canal canal;
}
