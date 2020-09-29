package com.task.interview.survey;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.task.interview.feedback.*;
@Component

public interface SurveyRepository extends CrudRepository<Survey, Integer> {
	
	
}
