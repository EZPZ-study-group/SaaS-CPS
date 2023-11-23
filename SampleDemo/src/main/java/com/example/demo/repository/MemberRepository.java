package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
