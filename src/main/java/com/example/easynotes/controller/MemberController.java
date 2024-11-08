package com.example.easynotes.controller;
import com.example.easynotes.model.*;

import com.example.easynotes.repository.ClassesRepository;
import com.example.easynotes.repository.MemberClassRepository;
import com.example.easynotes.repository.MemberRepository;
import com.example.easynotes.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;
@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private MemberClassRepository memberClassRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;


    // GET all members
    @GetMapping
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/mapClass/{memberId}/{classId}")
    public HashMap getClassesById(@PathVariable Long memberId, @PathVariable Long classId) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Classes> classesData = classesRepository.findById(classId);
        if (classesData.isPresent() && member.isPresent()) {
            MemberClass memberClass = new MemberClass();
            memberClass.setMember(member.get());
            memberClass.setClasses(classesData.get());
            MemberClass memberClasses = memberClassRepository.save(memberClass);
            HashMap hashMap = new HashMap();
            hashMap.put("memberID", memberClasses.getMember().getMemberId());
            hashMap.put("classID", memberClasses.getClasses().getClassID());
            hashMap.put("mappingId", memberClasses.getId());
            return hashMap;
        }
        return null;
    }

    @GetMapping("/{memberId}")
    public Optional<Member> getMemberById(@PathVariable Long memberId) {
        return memberRepository.findById(memberId);
    }


    // POST create a new member
    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    // DELETE a member by ID, including associated MemberClass entries
    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberRepository.deleteById(memberId);
    }
    // Endpoint to add feedback for a specific member

    @PostMapping("/{memberId}/feedback")
    public Feedback addFeedback(@PathVariable Long memberId, @RequestBody Feedback feedback) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        feedback.setMember(member); // Link feedback to the member
        return feedbackRepository.save(feedback);
    }

    // Endpoint to retrieve feedback for a specific member
    @GetMapping("/{memberId}/feedback")
    public List<Feedback> getMemberFeedback(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return member.getFeedback(); // Assumes getFeedback() returns List<Feedback>
    }

    // POST: Assign a member to a class
// POST: Assign a member to a class


}
