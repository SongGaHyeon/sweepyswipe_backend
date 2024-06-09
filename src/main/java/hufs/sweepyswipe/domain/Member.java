package hufs.sweepyswipe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;

    private DayOfWeek dayOfWeek;
    private LocalTime time;
    private boolean enabled;
}
