package reboot.spring.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class MemberVo {

    private Long id;

    @NotEmpty
    private String email;

    private String password;

    @NotNull
    private String name;

//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime regDate;

//    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate updateDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime regTime;

    public MemberVo() {

    }

    public MemberVo(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.regDate = LocalDateTime.now();
    }

    public MemberVo(String email, String password, String name, LocalDateTime localDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.regDate = localDateTime;
    }


}
