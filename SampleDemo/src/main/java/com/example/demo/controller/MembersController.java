package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MembersController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<MemberDto> memberFindAll( ){
        List<MemberDto> result = memberService.findAll();
        System.out.println(result);
        return result;
    }

    @PostMapping("/member")
    public MemberDto memberInsert(@RequestBody MemberDto memberDto){
        return memberService.save(memberDto);
    }
}
