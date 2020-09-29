package com.task.interview.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.task.interview.feedback.Feedback;
import com.task.interview.survey.*;

@RestController
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	ObjectMapper mapper;
	
	
	@RequestMapping(value = "/listSurveyTopics", method=RequestMethod.GET)
	public List<HashMap> getAllsurveys(){
		
		List<HashMap> surveys = new ArrayList<>();

			
	    for(Survey survey : surveyService.getAllSurveys()) {
			List<Integer> detractors = new ArrayList<>();
			List<Integer> passives = new ArrayList<>();
			List<Integer> promoters = new ArrayList<>();
			HashMap<String, Comparable> surveyTemp = new HashMap<String, Comparable>();
			for(Feedback feedback : survey.getFeedbacks()) {
		
			if(feedback.getScore() <= 6){
				detractors.add(feedback.getScore());
			}
			if(feedback.getScore() == 7 || feedback.getScore() ==8) {
				passives.add(feedback.getScore());
			}
			if(feedback.getScore() == 9 || feedback.getScore() == 10) {
				promoters.add(feedback.getScore());
			}
				
			}
	
			int pay = promoters.size() - detractors.size();
			int payda = promoters.size() + passives.size() + detractors.size();
			float sonuc = (float) pay/payda;
			float toplam = sonuc*100;
			survey.setNpmScore((int)toplam);
			surveyTemp.put("id", survey.getId());
			surveyTemp.put("topic", survey.getTopic());
			surveyTemp.put("npmScore", survey.getNpmScore());
			surveys.add(surveyTemp);

		
			
		}

		return surveys;
		
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/createSurvey")
	public void addSurvey(@RequestBody Survey survey){
		survey.setNpmScore(0);
		surveyService.addSurvey(survey);
	}
	

}
