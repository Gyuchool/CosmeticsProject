package cosmetics.demo.api;

import cosmetics.demo.Service.CommentService;
import cosmetics.demo.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final CommentService commentService;

    @PostMapping("/api/comment/post")
    public CreateCommentResponse post(@RequestBody @Valid CommentDto commentDto){
        Long id = commentService.addComment(commentDto);

        return new CreateCommentResponse(id);
    }
    @Data
    @AllArgsConstructor
    static class CreateCommentResponse{
        private Long id;
    }
}
