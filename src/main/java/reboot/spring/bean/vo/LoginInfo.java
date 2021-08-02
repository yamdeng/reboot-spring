package reboot.spring.bean.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginInfo {

    private String loginId;
    private String name;
    private String loginToken;

}
