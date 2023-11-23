package com.example.demo.dto;

import com.example.demo.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private Long memberId;
    private String memberName;
    private String idInsert;
    private LocalDateTime dtInsert;
    private String idUpdate;
    private LocalDateTime dtUpdate;

    public MemberDto(Member entity){
        this.memberId = entity.getMemberId();
        this.memberName = entity.getMemberName();
        this.idInsert = entity.getIdInsert();
        this.dtInsert = entity.getDtInsert();
        this.idUpdate = entity.getIdUpdate();
        this.dtUpdate = entity.getDtUpdate();
    }
}
