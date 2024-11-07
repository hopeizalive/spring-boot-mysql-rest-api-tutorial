package com.example.easynotes.repository;

import com.example.easynotes.model.MemberClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberClassRepository extends JpaRepository<MemberClass, Long> {
}
