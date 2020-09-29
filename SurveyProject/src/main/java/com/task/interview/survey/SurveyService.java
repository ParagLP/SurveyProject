package com.task.interview.survey;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.interview.feedback.Feedback;

@Service
public class SurveyService {
	
	
	@Autowired
	private SurveyRepository surveyRepository;
	public List<Survey> getAllSurveys(){
		
		List<Survey> feedbacks = new ArrayList<>();
		surveyRepository.findAll()
		.forEach(feedbacks::add);
		return feedbacks;
		
	}
	

	
	
	public Optional<Survey> getSurvey(int id) {
		return surveyRepository.findById(id);
	}
	
	public void addSurvey(Survey survey) {
		surveyRepository.save(survey);
	}
	
	
	public void updateSurvey(int id, Survey survey) {
		surveyRepository.save(survey);

	}

	
	
	

}
