package cosmetics.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cosmetics.demo.Domain.Entity.BoardEntity;
import cosmetics.demo.Domain.Entity.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {

    private Long id;

    private MemberDto memberDto ;
    private List<CommentDto> commentDtos;

    private Category category;
    private String title;

    private String content;
    private int viewcnt;

    private int like;
    private boolean hot;

    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime modifiedDate;

    public BoardEntity toEntity(){
        BoardEntity boardEntity = BoardEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .category(category)
                .viewcnt(viewcnt)
                .like(like)
                .hot(hot)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .build();
        return boardEntity;
    }

    public BoardDto(BoardEntity boardEntity){
        this.memberDto = new MemberDto(boardEntity.getMemberEntity());
        this.id = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.viewcnt = boardEntity.getViewcnt();
        this.like = boardEntity.getLike();
        this.setHot(boardEntity.isHot());
        this.createdDate = boardEntity.getCreatedDate();
        this.modifiedDate = boardEntity.getModifiedDate();
        this.category = boardEntity.getCategory();
        this.commentDtos = boardEntity.getComments().stream()
                .map( commentEntity -> new CommentDto(commentEntity))
                .collect(Collectors.toList());
    }
}
