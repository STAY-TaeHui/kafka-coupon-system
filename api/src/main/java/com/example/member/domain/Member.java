package com.example.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member
{
    @Id
    private Long memberId;

    private String memberName;

    public Member() {

    }

    public Member(Long memberId, String memberName)
    {
        this.memberId = memberId;
        this.memberName = memberName;
    }

}
