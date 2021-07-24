package reboot.spring.bean.vo;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Member {

    private Long id;
    private String email;
    private String password;
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
