package cosmetics.demo.Service;

import cosmetics.demo.Domain.Entity.CommentEntity;
import cosmetics.demo.Domain.Repository.CommentRepository;
import cosmetics.demo.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    //==댓글 추가==//
    public Long addComment(CommentDto commentDto){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDto.getId());
        commentEntity.setContent(commentDto.getContent());
        commentRepository.save(commentEntity);
        return commentEntity.getId();
    }

    //==댓글 삭제==//
    public void deleteComment(CommentDto commentDto){
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setId(commentDto.getId());
        commentRepository.delete(commentEntity);
    }

}
