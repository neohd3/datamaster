package com.datamaster.survey;

import com.datamaster.survey.dao.model.Question;
import com.datamaster.survey.dao.model.Survey;
import com.datamaster.survey.dao.repo.SurveyRepository;
import com.datamaster.survey.service.survey.SurveyService;
import com.datamaster.survey.web.request.SurveyFilter;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class, SurveyApplication.class})
@ActiveProfiles("test")
public class ApplicationTest {

	@ClassRule
	public static PostgreSQLContainer postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(
			"postgres:10.3")
			.withDatabaseName("test")
			.withUsername("user")
			.withPassword("pass").withStartupTimeout(Duration.ofSeconds(600));

	@Autowired
	private SurveyRepository surveyRepository;
	@Autowired
	private SurveyService surveyService;

	@Test
	@Transactional
	public void contextLoads() {

		Survey survey = new Survey();
		survey.setName("test");
		survey.setStart(LocalDateTime.now());
		survey.setEnd(LocalDateTime.now().plusDays(1));
		survey.setActive(true);

		List<Question> questions = new ArrayList<>();
		Question q = new Question();
		q.setSurvey(survey);
		q.setText("test1");
		q.setOrder(1);

		q = new Question();
		q.setSurvey(survey);
		q.setText("test2");
		q.setOrder(2);

		survey.setQuestions(questions);

		surveyRepository.save(survey);

		SurveyFilter f = new SurveyFilter();
		f.setActive(false);
		f.setDate(LocalDateTime.now());
		f.setName("test1");

		Assert.assertNotNull(surveyService.findAll(f, PageRequest.of(0, 2, Sort.by("name"))));
	}
}