package com.task.interview.feedback;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.interview.survey.*;
@RestController
public class FeedbackController {
	
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping(value = "/listSurveyAnswers/{id}", method = RequestMethod.GET)
	public List<Feedback> getAllFeedbacksBySurveyId(@PathVariable int id){
		return feedbackService.getAllSurveys(id);
		
	}

	
	
	@RequestMapping(method=RequestMethod.POST, value="/submitSurvey/{surveyId}")
	public void addFeedback(@RequestBody Feedback feedback, @PathVariable int surveyId){
		feedback.setSurvey(new Survey(surveyId,"","",0));
		feedbackService.addFeedBack(feedback);
		
	}
	

	
}
