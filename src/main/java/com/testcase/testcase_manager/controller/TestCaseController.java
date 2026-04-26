package com.testcase.testcase_manager.controller;

import com.testcase.testcase_manager.model.TestCase;
import com.testcase.testcase_manager.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/testcases")
@CrossOrigin(origins = "*")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    // GET all test cases
    @GetMapping
    public List<TestCase> getAllTestCases() {
        return testCaseService.getAllTestCases();
    }

    // GET single test case by ID
    @GetMapping("/{id}")
    public ResponseEntity<TestCase> getTestCaseById(@PathVariable Long id) {
        return testCaseService.getTestCaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - create new test case
    @PostMapping
    public TestCase createTestCase(@RequestBody TestCase testCase) {
        return testCaseService.createTestCase(testCase);
    }

    // PUT - update existing test case
    @PutMapping("/{id}")
    public ResponseEntity<TestCase> updateTestCase(
            @PathVariable Long id,
            @RequestBody TestCase testCase) {
        try {
            return ResponseEntity.ok(testCaseService.updateTestCase(id, testCase));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - delete test case
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestCase(@PathVariable Long id) {
        testCaseService.deleteTestCase(id);
        return ResponseEntity.noContent().build();
    }

    // GET - search by title
    @GetMapping("/search")
    public List<TestCase> searchByTitle(@RequestParam String title) {
        return testCaseService.searchByTitle(title);
    }

    // GET - filter by status
    @GetMapping("/status/{status}")
    public List<TestCase> filterByStatus(@PathVariable String status) {
        return testCaseService.filterByStatus(status);
    }

    // GET - filter by priority
    @GetMapping("/priority/{priority}")
    public List<TestCase> filterByPriority(@PathVariable String priority) {
        return testCaseService.filterByPriority(priority);
    }
}
