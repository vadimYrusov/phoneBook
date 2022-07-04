package com.example.phoneBook.Dto.factories;

import com.example.phoneBook.Dto.MemberDto;
import com.example.phoneBook.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberDtoFactory {

    public MemberDto makeMemberDto(Member member) {

        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .surname(member.getSurname())
                .phoneNumber(member.getPhoneNumber())
                .build();
    }
}
