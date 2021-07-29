package reboot.spring.boot.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "yamdeng")
public class YamdengProperties {

    private String name;
    private String email;
    private Integer age;

}
