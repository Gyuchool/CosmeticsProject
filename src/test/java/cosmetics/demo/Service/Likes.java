package cosmetics.demo.Service;

import cosmetics.demo.Domain.Entity.BoardEntity;
import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import cosmetics.demo.Domain.Repository.LikesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class Likes {

    @Autowired
    LikesRepository likesRepository;
    @Autowired
    BoardService boardService;

    @Test
    public void 좋아요증가() throws Exception {
        //given

        //when

        //then
    }
}
