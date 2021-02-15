package cosmetics.demo.dto;

import cosmetics.demo.Domain.Entity.BoardEntity;
import cosmetics.demo.Domain.Entity.CommentEntity;
import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import lombok.*;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String name;
    private String password;
    private String nickname;

    public MemberDto(MemberEntity memberEntity){
        this.id = memberEntity.getId();
        this.name = memberEntity.getName();
        this.password = memberEntity.getPassword();
        this.nickname = memberEntity.getNickname();
//        this.comments = memberEntity.getComments().stream()
//                .map( commentEntity -> new CommentDto(commentEntity))
//                .collect(Collectors.toList());
//        this.boards = memberEntity.getBoards().stream()
//                .map(boardEntity -> new BoardDto(boardEntity))
//                .collect(Collectors.toList());
    }

}
