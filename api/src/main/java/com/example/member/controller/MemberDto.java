package com.example.member.controller;

public class MemberDto
{
    private Long memberId;

    private String memberName;

    public MemberDto(Long memberId, String memberName)
    {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
    }
}
