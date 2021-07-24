package reboot.spring.bean.vo;

import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Member {

    private Long id;

    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotNull
    private String name;

    private LocalDateTime regDate;

    public Member() {

    }

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.regDate = LocalDateTime.now();
    }

    public Member(String email, String password, String name, LocalDateTime localDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.regDate = localDateTime;
    }


}
