package com.task.interview.feedback;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.task.interview.survey.Survey;

@Entity
public class Feedback {

	
	
	public Feedback() {
		
	}
	
	public Feedback(String feedbackText, int score, Survey survey) {
		super();
		this.feedbackText = feedbackText;
		this.score = score;
		this.survey = survey;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String feedbackText;
	
	private int score;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Survey survey;
	


	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfeedbackText() {
		return feedbackText;
	}
	public void setfeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	
	
}
