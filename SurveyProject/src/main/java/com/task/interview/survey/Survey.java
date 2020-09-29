package com.task.interview.survey;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.task.interview.feedback.Feedback;
@Entity
public class Survey {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public int getId() {
		return id;
	}

	private String topic;
	private String surveyQuestion;
	private float npmScore = 0 ;
	
	public float getNpmScore() {
		return npmScore;
	}

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "survey_id")
	private List<Feedback> feedbacks = new ArrayList<>();

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Survey(int id, String topic, String surveyQuestion, int npmScore) {
		super();
		this.id = id;
		this.topic = topic;
		this.surveyQuestion = surveyQuestion;
		this.npmScore = npmScore;
	}
	
	public Survey() {
		// TODO Auto-generated constructor stub
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getSurveyQuestion() {
		return surveyQuestion;
	}
	public void setSurveyQuestion(String surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	public void setNpmScore(float toplam) {
		this.npmScore = toplam;
	}

}
