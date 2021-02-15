package cosmetics.demo.Domain.Entity;

import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class LikesFunction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_Id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    private MemberEntity memberEntity;

    @Builder
    public LikesFunction(BoardEntity boardEntity, MemberEntity memberEntity){
        this.boardEntity = boardEntity;
        this.memberEntity = memberEntity;
    }

}
