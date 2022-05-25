package ua.kotsko.lab.repository;

import ua.kotsko.lab.entity.Tour;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepositoryImplementation<Tour, Long> {
    List<Tour> findByOrderById();
}
