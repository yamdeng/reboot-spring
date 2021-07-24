package reboot.spring.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirstVo {

    private String name;
    private String loginId;
    private String password;
    private boolean enabled;

}
