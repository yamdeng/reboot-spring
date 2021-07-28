package reboot.spring.boot.domain;


import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDate create_date;
    private LocalDate update_date;

    public Board(String title) {
        this.title = title;
        this.create_date = LocalDate.now();
        this.update_date = LocalDate.now();
    }

}
