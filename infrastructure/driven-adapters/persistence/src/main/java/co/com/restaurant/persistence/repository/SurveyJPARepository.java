package co.com.restaurant.persistence.repository;

import co.com.restaurant.persistence.entity.SurveyEntity;
import co.com.restaurant.model.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyJPARepository extends CrudRepository<SurveyEntity, Long> {

    public Optional<SurveyEntity> findFirstByOrderById();
}
