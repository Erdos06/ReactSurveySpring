package com.react.survey.repositories.survey;

import com.react.survey.entities.survey.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
}
