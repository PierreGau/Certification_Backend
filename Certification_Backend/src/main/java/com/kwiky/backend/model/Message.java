package com.kwiky.backend.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "content", columnDefinition = "LONGTEXT")
	@Type(type = "text")
	private String content;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "postTime")
	private LocalDateTime postTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "editTime")
	private LocalDateTime editTime;

	@ManyToOne
	@NotNull
	@Cascade(CascadeType.DETACH)
	private User user;

	@ManyToOne
	@NotNull
	@Cascade(CascadeType.DETACH)
	private Canal canal;

	// Constructeurs
	public Message() {
	}

	public Message(@NotNull String content, @NotNull LocalDateTime postTime, LocalDateTime editTime, @NotNull User user,
			@NotNull Canal canal) {
		this.content = content;
		this.postTime = postTime;
		this.editTime = editTime;
		this.user = user;
		this.canal = canal;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}

	public LocalDateTime getEditTime() {
		return editTime;
	}

	public void setEditTime(LocalDateTime editTime) {
		this.editTime = editTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	// ToString
	@Override
	public String toString() {
		return String.format("Message [id=%s, content=%s, postTime=%s, editTime=%s, user=%s, canal=%s]", id, content,
				postTime, editTime, user, canal);
	}

}
