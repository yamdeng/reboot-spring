package reboot.spring.boot.domain;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate create_date;
    private LocalDate update_date;

    public Member() {
        this.create_date = LocalDate.now();
        this.update_date = LocalDate.now();
    }

    @Builder
    public Member(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.create_date = LocalDate.now();
        this.update_date = LocalDate.now();
    }

}
