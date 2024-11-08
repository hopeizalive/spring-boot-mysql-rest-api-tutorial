package com.example.easynotes.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.example.easynotes.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.easynotes.model.Feedback;
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // GET: Fetch all feedback entries with pagination
    @GetMapping
    public Page<Feedback> getAllFeedback(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("feedbackID").ascending());
        return feedbackRepository.findAll(pageable);
    }
}
