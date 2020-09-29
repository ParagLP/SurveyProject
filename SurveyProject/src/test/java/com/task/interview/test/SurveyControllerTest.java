package com.task.interview.test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.interview.feedback.Feedback;
import com.task.interview.survey.Survey;
import com.task.interview.survey.SurveyController;
import com.task.interview.survey.SurveyService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SurveyController.class)
public class SurveyControllerTest {
	 public final static String CONTENT_TYPE = "application/json";
	
	@Autowired 
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
    private SurveyService surveyservice;
	
	
	@Test
	void testAddSurvey() throws Exception {
		
		Survey survey = new Survey(1,"Grovee","What was the reason of your point",0);
		ResultActions actions = mvc.perform(post("/createSurvey")
				.contentType(CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(survey)));
		actions.andExpect(status().isOk());
		
	}

	@Test
	void testGetAllsurveys() throws Exception {
		

		
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		feedbacks.add(new Feedback("I like the UI. I suggest it to the people!", 9, new Survey(1,"","",0)));
		feedbacks.add(new Feedback("I like the camera!", 7, new Survey(1,"","",0)));
	
		Survey survey1 = new Survey(1,"Apple","What is the most important reason for your score?",0);
		survey1.setFeedbacks(feedbacks);

		
		List<Feedback> feedbacks2 = new ArrayList<Feedback>();
		feedbacks2.add(new Feedback("I liked the way it takes pics!", 10, new Survey(2,"","",0)));
		feedbacks2.add(new Feedback("I did not like. Its expensive and it has a bad design!", 9, new Survey(2,"","",0)));
		feedbacks2.add(new Feedback("Its not good or bad!", 8, new Survey(2,"","",0)));

		Survey survey2 = new Survey(2,"Samsung","What is the thing you liked most?",0);
		survey2.setFeedbacks(feedbacks2);

		
	
	    List<Survey> surveys = Arrays.asList(
	            survey1,survey2);
	    
	    when(surveyservice.getAllSurveys()).thenReturn(surveys);
	    mvc.perform(get("/listSurveyTopics"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(jsonPath("$[0].id", is(1)))
	    	    .andExpect(jsonPath("$[0].topic", is("Apple")))
	    	    .andExpect(jsonPath("$[0].npmScore", is(50.0)))
	    	    .andExpect(jsonPath("$[1].id", is(2)))
	    	    .andExpect(jsonPath("$[1].topic", is("Samsung")))
	    	    .andExpect(jsonPath("$[1].npmScore", is(66.0)));
	    
	    verify(surveyservice, times(1)).getAllSurveys();
	    verifyNoMoreInteractions(surveyservice);
	
		
	}
	
	




} 