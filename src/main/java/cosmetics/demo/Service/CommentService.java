package cosmetics.demo.Service;

import cosmetics.demo.Domain.Entity.BoardEntity;
import cosmetics.demo.Domain.Entity.CommentEntity;
import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import cosmetics.demo.Domain.Repository.BoardRepository;
import cosmetics.demo.Domain.Repository.CommentRepository;
import cosmetics.demo.Domain.Repository.MemberRepository;
import cosmetics.demo.dto.BoardDto;
import cosmetics.demo.dto.CommentDto;
import cosmetics.demo.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    //==댓글 조회==//
    public List<CommentDto> CommentsAll(Long boardId, Pageable pageable){
        List<CommentEntity> comments = commentRepository.findCommentEntityByBoardEntity_Id(boardId, pageable).getContent();

        List<CommentDto> commentsDto = new ArrayList<>();

        comments.stream().forEach(commentEntity -> commentsDto.add(new CommentDto(commentEntity)));

        return commentsDto;
    }

    //==댓글 추가==//
    @Transactional
    public CommentDto addComment(Long memberId, Long boardId, CommentDto commentDto){
        MemberEntity findMember = memberRepository.findOne(memberId);
        Optional<BoardEntity> optional = boardRepository.findById(boardId);
        BoardEntity findBoard = optional.get();

        //연관 짓기
        CommentEntity comment = CommentEntity.createComment(findBoard, findMember, commentDto);
        commentRepository.save(comment);

        return new CommentDto(comment);
    }

    //==댓글 삭제==//
    @Transactional
    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    //==댓글 수정==//
    @Transactional
    public CommentDto updateComment(Long id, CommentDto commentDto){
        Optional<CommentEntity> comment = commentRepository.findById(id);
        CommentEntity commentEntity = comment.get();

        CommentEntity.builder()
                .id(commentDto.getId())
                .boardEntity(commentDto.toEntity().getBoardEntity())
                .memberEntity(commentDto.toEntity().getMemberEntity())
                .content(commentDto.getContent())
                .modifiedDate(commentDto.getModifiedDate())
                .build();

        return new CommentDto(commentEntity);
    }

}
