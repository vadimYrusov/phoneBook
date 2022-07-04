package com.example.phoneBook.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;
}
