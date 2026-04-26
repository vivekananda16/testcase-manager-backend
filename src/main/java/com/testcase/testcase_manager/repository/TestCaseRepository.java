package com.testcase.testcase_manager.repository;
import com.testcase.testcase_manager.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    // Search by title (case-insensitive)
    List<TestCase> findByTitleContainingIgnoreCase(String title);

    // Filter by status
    List<TestCase> findByStatus(String status);

    // Filter by priority
    List<TestCase> findByPriority(String priority);
}
