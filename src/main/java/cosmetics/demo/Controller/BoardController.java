package cosmetics.demo.Controller;

import cosmetics.demo.Domain.Entity.Category;
import cosmetics.demo.Service.BoardService;
import cosmetics.demo.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    /* 게시글 목록 */
    @GetMapping("/board/list")
    public ResponseList list(@RequestParam("category") Category category, Pageable page) {
        Page<BoardDto> boardDtos = boardService.Boardlist(category, page);

        return new ResponseList(boardDtos.getTotalElements(), boardDtos);
    }
    @Data
    @AllArgsConstructor
    static class ResponseList<T>{
        private Long boardCnt;
        private T data;
    }

    /* 게시글 상세 */
    @GetMapping("/board/{no}")
    public BoardDto detail(@PathVariable("no") Long no) {
        BoardDto boardDto = boardService.addViews(no);

        return boardDto;
    }

    /* 게시글 쓰기 */
    @PostMapping("/board/post")
    public BoardDto write(@RequestParam("memberId") Long memberId,
                          @RequestParam("category") Category category,
                          @RequestBody @Valid BoardRequest boardRequest) { //content, title
        BoardDto boardDto = new BoardDto();
        boardDto.setTitle(boardRequest.getTitle());
        boardDto.setContent(boardRequest.getContent());
        boardDto.setViewcnt(0);
        boardService.savePost(memberId, category, boardDto);

        return boardDto;
    }

    @Data
    @AllArgsConstructor
    static class BoardRequest{
        private String title;
        private String content;
    }


    /* 게시글 수정 */
    @PutMapping("/board/post/edit/{no}")
    public BoardDto boardUpdate(@PathVariable("no") Long boardId,
                                @RequestParam("memberId")Long memberId,
                                @RequestBody @Valid BoardDto boardDTO) {
        BoardDto boardDto = boardService.updatePost(boardId, memberId, boardDTO);
        return boardDto;
    }

    /* 게시글 삭제 */
    @DeleteMapping("/board/delete/{no}")
    public void delete(@RequestParam("memberId") Long memberId, @PathVariable("no") Long no) {
        boardService.deletePost(memberId, no);
    }

    /* 전체 검색 */
    @GetMapping("/board/search")
    public ResponseList search(@RequestParam(value="keyword") String keyword, Pageable pageable) {
        Page<BoardDto> boardDtos = boardService.searchPosts(keyword, pageable);

        return new ResponseList(boardDtos.getTotalElements(), boardDtos);
    }

    /* 카테고리에 따른 검색 */
    @GetMapping("/board/category/search")
    public ResponseList searchByCategory(@RequestParam(value = "keyword") String keyword,
                                         @RequestParam(value = "category") Category category,
                                         Pageable pageable){
        Page<BoardDto> boardDtos = boardService.searchPostsByCategory(keyword, category, pageable);

        return new ResponseList(boardDtos.getTotalElements(), boardDtos);
    }

    /* 좋아요 */
    @PostMapping("/{boardId}/likes")
    public void addLikes(@RequestParam("memberId")Long memberId,
                         @PathVariable("boardId") Long boardId){
        boardService.addLikes(memberId, boardId);
    }

    /* 좋아요 취소 */
    @DeleteMapping("/{boardId}/likes/{LikesId}")
    public void cancelLikes(@RequestParam("memberId") Long memberId,
                            @PathVariable("boardId") Long boardId,
                            @PathVariable("LikesId") Long LikesId){
        boardService.cancleLikes(memberId, boardId, LikesId);
    }


    @GetMapping("board/list/hot")
    public ResponseList hotBoardList(Pageable pageable){
        Page<BoardDto> boardDtos = boardService.listHotBoard(pageable);
        return new ResponseList(boardDtos.getTotalElements(), boardDtos);
    }

}

