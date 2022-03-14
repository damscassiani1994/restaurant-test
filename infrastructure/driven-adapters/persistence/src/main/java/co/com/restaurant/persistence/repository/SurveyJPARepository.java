package co.com.restaurant.persistence.repository;

import co.com.restaurant.persistence.entity.SurveyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyJPARepository extends CrudRepository<SurveyEntity, Long> {
}
