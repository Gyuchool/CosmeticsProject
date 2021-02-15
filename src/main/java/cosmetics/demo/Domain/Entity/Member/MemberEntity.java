package cosmetics.demo.Domain.Entity.Member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cosmetics.demo.Domain.Entity.BoardEntity;
import cosmetics.demo.Domain.Entity.CommentEntity;
import cosmetics.demo.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor
public class MemberEntity {

        @Id
        @GeneratedValue
        @Column(name = "member_id")
        private Long id;
        private String name;
        private String password;
        private String nickname;

        @OneToMany(mappedBy = "memberEntity" )
        private List<CommentEntity> comments = new ArrayList<>();

        @OneToMany(mappedBy = "memberEntity")
        private List<BoardEntity> boards = new ArrayList<>();

        @Enumerated(EnumType.STRING)
        private SkinStatus skin_status;

        @Enumerated(EnumType.STRING)
        private SensitiveStatus sensitive_status;
        //피부고민 엔티티 나눠야하나?
        private boolean blackhead;
        private boolean oily;
        private boolean keratin;
        private boolean pimple;
        private boolean dry;
        private boolean glow;
        private boolean flexibility;
        private boolean skintone;
        private boolean wrinkle;
        //개인특성
        private String Sleeping_Hours;
        private String Wash_Temperature;
        private String Wash_Num;
        private String Stress;
        private String Collyrium;
        private String Food;

    }

/*
Name: '' 로그인id
PW: '' 로그인password
PWT: '' 비번확인
EMAIL: '' 이멜(보류)
NickNAME: '' 닉네임
list:"지성" 피부타입
api 명세
blackhead, oily, keratin, pimple, dry, glow, flexibility, skintone, wrinkle 여기까지가 boolean으로
Sleeping_Hours
    Wash_Temperature
    Wash_Num
    Stress
    Collyrium
    Food 이게 그냥 스트링 값으로 보낼듯
 */
