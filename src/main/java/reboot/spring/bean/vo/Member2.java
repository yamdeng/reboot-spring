package reboot.spring.bean.vo;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Member2 {

    private Long id;

    @NotEmpty(message = "!!! not empty allow !!!!")
    @NotBlank(message = "!!! not blank allow !!!!")
    @Email
    private String email;

    @NotEmpty(message = "!!! not empty allow !!!!")
    private String password;

    @NotNull
    private String name;

    private LocalDateTime regDate;

    public Member2() {

    }

    public Member2(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.regDate = LocalDateTime.now();
    }

    public Member2(String email, String password, String name, LocalDateTime localDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.regDate = localDateTime;
    }


}
