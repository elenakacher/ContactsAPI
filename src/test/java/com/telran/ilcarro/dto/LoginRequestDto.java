package com.telran.ilcarro.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class LoginRequestDto {
    String email;
    String password;
}
