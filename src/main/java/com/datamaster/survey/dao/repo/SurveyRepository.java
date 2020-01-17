package com.datamaster.survey.dao.repo;

import com.datamaster.survey.dao.model.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

    @EntityGraph(attributePaths = {"questions"})
    Page<Survey> findAll(Specification<Survey> spec, Pageable page);
}
