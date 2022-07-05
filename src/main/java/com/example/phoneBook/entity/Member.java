package com.example.phoneBook.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "User's name cannot be empty.")
    private String name;

    @NotEmpty(message = "User's surname cannot be empty.")
    private String surname;

    @NotEmpty(message = "User's phone number cannot be empty.")
    private String phoneNumber;

    public Member(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}
