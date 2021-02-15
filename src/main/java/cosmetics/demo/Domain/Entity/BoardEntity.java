package cosmetics.demo.Domain.Entity;

import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import cosmetics.demo.dto.BoardDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@SuperBuilder
@Table(name = "board")
public class BoardEntity extends TimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "views")
    private int viewcnt;

    @Column(name = "likes")
    private int like;

    @Column(name = "hot")
    private boolean hot;

    @OneToMany(mappedBy = "boardEntity")
    private List<CommentEntity> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Enumerated(EnumType.STRING)
    private Category category;

    //==연관 관계 편의 메서드==//
    public void setMember(MemberEntity member){
        if(this.memberEntity != null){          //기존 연관관계 있을시 제거
            this.memberEntity.getBoards().remove(this);
        }

        this.memberEntity = member;
        member.getBoards().add(this);
    }

    public void setCategory(Category category){
        this.category= category;
    }

    public void updateBoard(BoardDto boardDto){

        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.hot = boardDto.isHot();
        this.viewcnt = boardDto.getViewcnt();
        this.memberEntity = boardDto.toEntity().getMemberEntity();
        this.updateTime(boardDto.getCreatedDate(), boardDto.getModifiedDate());

    }
    public void updateLikes(int like){this.like = like;}

    public void updateView(int viewcnt){
        this.viewcnt = viewcnt;
    }
}
