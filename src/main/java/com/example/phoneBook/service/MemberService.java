package com.example.phoneBook.service;

import com.example.phoneBook.entity.Member;

import java.util.List;

public interface MemberService {

    List<Member> getAllMembers();

    Member saveMember(Member member);

    Member updateMember(Member member);

    Member getMemberById(Long id);

    void deleteMemberById(Long id);
}
