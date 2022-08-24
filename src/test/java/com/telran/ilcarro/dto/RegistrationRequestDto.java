package com.telran.ilcarro.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

public class RegistrationRequestDto {

    String first_name;
    String second_name;

}