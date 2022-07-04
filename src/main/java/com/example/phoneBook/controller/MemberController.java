package com.example.phoneBook.controller;

import com.example.phoneBook.Dto.factories.MemberDtoFactory;
import com.example.phoneBook.entity.Member;
import com.example.phoneBook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MemberDtoFactory memberDtoFactory;

    @GetMapping("/members")
    public String listMembers(Model model) {

        List<Member> members = memberService.getAllMembers();

        model.addAttribute("members", members.stream()
                .map(memberDtoFactory::makeMemberDto)
                .collect(Collectors.toList()));

        return "members";
    }

    @GetMapping("/members/new")
    public String createMember(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "create_member";
    }

    @PostMapping("/members")
    public String saveMember(@ModelAttribute("member") Member member) {
        memberService.saveMember(member);
        return "redirect:/members";
    }

    @GetMapping("/members/edit/{id}")
    public String editMemberForm(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", memberDtoFactory.makeMemberDto(member));
        return "edit_member";
    }

    @PostMapping("/members/{id}")
    public String updateMember(@PathVariable Long id,
                               @ModelAttribute("member") Member member,
                               Model model
    ) {
        Member existingMember = memberService.getMemberById(id);
        existingMember.setId(id);
        existingMember.setName(member.getName());
        existingMember.setSurname(member.getSurname());
        existingMember.setPhoneNumber(member.getPhoneNumber());
        memberService.updateMember(existingMember);
        return "redirect:/members";
    }

    @GetMapping("/members/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "redirect:/members";
    }
}
