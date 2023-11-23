package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberDto;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<MemberDto> findAll(){
        return memberRepository.findAll().stream().map(MemberDto::new).collect(Collectors.toList());
    }

    public MemberDto save(MemberDto memberDto){
        Member member = Member.builder().memberName(memberDto.getMemberName())
                              .idInsert(memberDto.getIdInsert())
                              .idUpdate(memberDto.getIdUpdate())
                              .build();

        return new MemberDto(memberRepository.save(member));
    }
}
