package com.example.phoneBook.controller;

import com.example.phoneBook.Dto.factories.MemberDtoFactory;
import com.example.phoneBook.entity.Member;
import com.example.phoneBook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    public String saveMember(@Valid @ModelAttribute("member") Member member, BindingResult result) {
        if (result.hasErrors()) {
            return "create_member";
        }
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
                               @Valid @ModelAttribute("member") Member member,
                               BindingResult result,
                               Model model
    ) {

        if (result.hasErrors()) {
            return "edit_member";
        }

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
