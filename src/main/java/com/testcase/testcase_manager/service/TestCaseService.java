package com.testcase.testcase_manager.service;


import com.testcase.testcase_manager.model.TestCase;
import com.testcase.testcase_manager.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    // Get all test cases
    public List<TestCase> getAllTestCases() {
        return testCaseRepository.findAll();
    }

    // Get single test case by ID
    public Optional<TestCase> getTestCaseById(Long id) {
        return testCaseRepository.findById(id);
    }

    // Create new test case
    public TestCase createTestCase(TestCase testCase) {
        return testCaseRepository.save(testCase);
    }

    // Update existing test case
    public TestCase updateTestCase(Long id, TestCase updatedTestCase) {
        return testCaseRepository.findById(id).map(existing -> {
            existing.setTitle(updatedTestCase.getTitle());
            existing.setDescription(updatedTestCase.getDescription());
            existing.setStatus(updatedTestCase.getStatus());
            existing.setPriority(updatedTestCase.getPriority());
            return testCaseRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("TestCase not found with id: " + id));
    }

    // Delete test case
    public void deleteTestCase(Long id) {
        testCaseRepository.deleteById(id);
    }

    // Search by title
    public List<TestCase> searchByTitle(String title) {
        return testCaseRepository.findByTitleContainingIgnoreCase(title);
    }

    // Filter by status
    public List<TestCase> filterByStatus(String status) {
        return testCaseRepository.findByStatus(status);
    }

    // Filter by priority
    public List<TestCase> filterByPriority(String priority) {
        return testCaseRepository.findByPriority(priority);
    }
}
