package com.task.interview.feedback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.task.interview.feedback.FeedbackRepository;
import org.springframework.beans.factory.annotation.Configurable;
@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	
	
	public List<Feedback> getAllSurveys(int surveyId){

		return feedbackRepository.findBySurveyId(surveyId);
		
	}
	
	public Optional<Feedback> getFeedback(int id) {
		
		return feedbackRepository.findById(id);
	}
	
	public void addFeedBack(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
	


	
	
	

}
