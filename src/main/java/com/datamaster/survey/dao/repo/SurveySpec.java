package com.datamaster.survey.dao.repo;

import com.datamaster.survey.dao.model.Survey;
import com.datamaster.survey.web.request.SurveyFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

public class SurveySpec {

    public static Specification<Survey> where(SurveyFilter filter) {

        return (survey, cq, cb) -> {

            Path<String> namePath = survey.get("name");
            Path<LocalDateTime> startPath = survey.get("start");
            Path<LocalDateTime> endPath = survey.get("end");
            Path<Boolean> activePath = survey.get("active");

            List<Predicate> predicates = new ArrayList<>();
            ofNullable(filter.getName()).ifPresent(f -> predicates.add(cb.equal(namePath, f)));
            ofNullable(filter.getDate()).ifPresent(f -> {
                predicates.add(cb.lessThanOrEqualTo(startPath, f));
                predicates.add(cb.greaterThan(endPath, f));
            });
            ofNullable(filter.getActive()).ifPresent(f -> predicates.add(cb.equal(activePath, f)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
