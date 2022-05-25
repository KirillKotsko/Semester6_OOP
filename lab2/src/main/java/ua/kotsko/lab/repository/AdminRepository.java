package ua.kotsko.lab.repository;

import ua.kotsko.lab.entity.Admin;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepositoryImplementation<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
