package reboot.spring.boot.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberVo {

    private Long id;
    private String name;
    private String loginId;
    private String email;
    private Boolean enabled;
    private LocalDate regDate;
    private LocalDate updateDate;
    private LocalDateTime recentDateTime;

    public MemberVo() {
        this.regDate = LocalDate.now();
        this.updateDate = LocalDate.now();
        this.recentDateTime = LocalDateTime.now();
        this.enabled = true;
    }

}
