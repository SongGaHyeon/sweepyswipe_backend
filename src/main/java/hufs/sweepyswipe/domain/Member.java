package hufs.sweepyswipe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;
}
