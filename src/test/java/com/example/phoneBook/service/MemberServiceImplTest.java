package com.example.phoneBook.service;

import com.example.phoneBook.entity.Member;
import com.example.phoneBook.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;
    private MemberServiceImpl memberService;

    @BeforeEach
    void setUp() {
        memberService = new MemberServiceImpl(memberRepository);
    }

    @Test
    void canGetAllMembers() {

        memberService.getAllMembers();

        verify(memberRepository).findAll();
    }

    @Test
    void canSaveMember() {
        Member member = Member.builder()
                .id(777L)
                .name("Albert")
                .surname("Hugo")
                .phoneNumber("+368868889")
                .build();

        memberService.saveMember(member);

        ArgumentCaptor<Member> memberArgumentCaptor =
                ArgumentCaptor.forClass(Member.class);

        verify(memberRepository).save(memberArgumentCaptor.capture());

        Member capturedMember = memberArgumentCaptor.getValue();

        assertThat(capturedMember).isEqualTo(member);
    }

    @Test
    @Disabled
    void updateMember() {
    }

    @Test
    @Disabled
    void getMemberById() {
    }

    @Test
    @Disabled
    void deleteMemberById() {
    }
}