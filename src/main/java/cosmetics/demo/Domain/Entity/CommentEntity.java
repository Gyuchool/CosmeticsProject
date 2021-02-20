package cosmetics.demo.Domain.Entity;

import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import cosmetics.demo.dto.BoardDto;
import cosmetics.demo.dto.CommentDto;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SuperBuilder
@Table(name = "comment")
public class CommentEntity extends TimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    /* 연곤 관계 편의메서드 */
    public void setMember(MemberEntity member){

        this.memberEntity = member;
        member.getComments().add(this);
    }

    public void setBoard (BoardEntity board){
        this.boardEntity = board;
        board.getComments().add(this);
    }

    /* 정적 팩토리 */
    public static CommentEntity createComment(BoardEntity board, MemberEntity member, CommentDto commentDto){

        CommentEntity comment = commentDto.toEntity();
        comment.updateTime(LocalDateTime.now(), LocalDateTime.now());
        comment.setMember(member);
        comment.setBoard(board);

        return comment;
    }
}
