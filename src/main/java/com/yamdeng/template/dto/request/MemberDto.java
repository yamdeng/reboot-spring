package com.yamdeng.template.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private Long id;

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    @NotNull
    private Integer age;

    private LocalDate create_date;
    private LocalDate update_date;
    
}
