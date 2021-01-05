package cosmetics.demo.Domain.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "member")
@Builder
@AllArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;
}
