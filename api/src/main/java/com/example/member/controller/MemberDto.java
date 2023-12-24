package com.example.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto
{
    private Long memberId;

    private String memberName;

    public MemberDto(Long memberId, String memberName)
    {
        this.memberId = memberId;
        this.memberName = memberName;
    }
    public String toJson(){

        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            return objectMapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
