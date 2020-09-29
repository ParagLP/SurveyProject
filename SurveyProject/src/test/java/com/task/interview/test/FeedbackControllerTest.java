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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.interview.feedback.Feedback;
import com.task.interview.feedback.FeedbackController;
import com.task.interview.feedback.FeedbackService;
import com.task.interview.survey.Survey;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FeedbackController.class)
public class FeedbackControllerTest {
	 public final static String CONTENT_TYPE = "application/json";
	
	@Autowired 
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
    private FeedbackService feedbackservice;
	
	
	
	@Test
	void testGetAllFeedbacksBySurveyId() throws Exception {

		List<Feedback> feedbacks = new ArrayList<Feedback>();
		feedbacks.add(new Feedback("I like the UI. I suggest it to the people!", 9, new Survey(1,"","",0)));
		feedbacks.add(new Feedback("I like the camera!", 7, new Survey(1,"","",0)));
		feedbacks.add(new Feedback("I liked the way it takes pics!", 10, new Survey(1,"","",0)));
		feedbacks.add(new Feedback("I did not like. Its expensive and it has a bad design!", 9, new Survey(1,"","",0)));
		feedbacks.add(new Feedback("Its not good or bad!", 8, new Survey(1,"","",0)));
				    
	    when(feedbackservice.getAllSurveys(1)).thenReturn(feedbacks);
	    mvc.perform(get("/listSurveyAnswers/1"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	            .andExpect(jsonPath("$[0].feedbackText", is("I like the UI. I suggest it to the people!")))
		        .andExpect(jsonPath("$[0].score", is(9)));
			    
	    verify(feedbackservice, times(1)).getAllSurveys(1);
	    verifyNoMoreInteractions(feedbackservice);
	
	
	}

	
	@Test
	void testaddFeedback() throws Exception {
		
		Feedback feedback = new Feedback("I like the UI. I suggest it to the people!", 9, new Survey(1,"","",0));
		ResultActions actions = mvc.perform(post("/submitSurvey/1")
				.contentType(CONTENT_TYPE)
				.content(objectMapper.writeValueAsString(feedback)));
		actions.andExpect(status().isOk());
	
	
		
	}
	
	




} 