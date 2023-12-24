package com.example.member.domain;

import com.example.member.controller.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member
{
    @Id
    private Long memberId;

    private String memberName;

    protected Member() {

    }

    public Member(MemberDto memberDto)
    {
        this.memberId = memberDto.getMemberId();
        this.memberName = memberDto.getMemberName();
    }

}
