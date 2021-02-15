package cosmetics.demo.Controller;

import cosmetics.demo.Domain.Entity.CommentEntity;
import cosmetics.demo.Service.CommentService;
import cosmetics.demo.dto.BoardDto;
import cosmetics.demo.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /* 게시글에 댓글 목록 */
    @GetMapping("/{no}/comment/list")
    public ResponseList list(@PathVariable("no") Long boardId, Pageable pageable){

        List<CommentDto> commentDtos = commentService.CommentsAll(boardId, pageable);

        return new ResponseList(commentDtos.size(), commentDtos);
    }
    @Data
    @AllArgsConstructor
    static class ResponseList<T>{
        private int commentCnt;
        private T data;
    }

    /* 댓글 쓰기 */
    @PostMapping("/comment/post")
    public CommentDto write(@RequestParam("memberId") Long memberId,
                            @RequestParam("boardId") Long boardId,
                            @RequestBody @Valid CommentDto commentDto) {
        CommentDto comment = commentService.addComment(memberId, boardId, commentDto);

        return comment;
    }

    /* 댓글 수정 */
    @PutMapping("/comment/post/edit/{no}")
    public CommentDto boardUpdate(@PathVariable("no") Long no, @RequestBody @Valid CommentDto commentDto) {
        CommentDto comment = commentService.updateComment(no, commentDto);
        return comment;
    }

    /* 댓글 삭제 */
    @DeleteMapping("/comment/post/{no}")
    public void delete(@PathVariable("no") Long no) {
        commentService.deleteComment(no);
    }


}
