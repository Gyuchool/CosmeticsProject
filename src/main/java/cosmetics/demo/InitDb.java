package cosmetics.demo;

import cosmetics.demo.Domain.Entity.BoardEntity;
import cosmetics.demo.Domain.Entity.Category;
import cosmetics.demo.Domain.Entity.LikesFunction;
import cosmetics.demo.Domain.Entity.Member.MemberEntity;
import cosmetics.demo.Domain.Repository.BoardRepository;
import cosmetics.demo.Domain.Repository.LikesRepository;
import cosmetics.demo.Domain.Repository.MemberRepository;
import cosmetics.demo.dto.BoardDto;
import cosmetics.demo.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    void setInitService(){
        initService.BoardInit1();
        initService.BoardInit2();
        initService.BoardInit3();
    }

    @Transactional
    @Component
    @RequiredArgsConstructor
    static class InitService{

        private final BoardRepository boardRepository;
        private final MemberRepository memberRepository;
        private final LikesRepository likesRepository;

        public void BoardInit1(){


            MemberEntity member1 = new MemberEntity();
            member1.setName("James");
            member1.setNickname("Cosco");
            member1.setPassword("123123");
            member1.setBlackhead(true);
            memberRepository.save(member1);

            BoardDto boardDto = new BoardDto();
            boardDto.setContent("인잇디비");
            boardDto.setHot(false);

            boardDto.setViewcnt(1);
            boardDto.setCreatedDate(LocalDateTime.now());
            boardDto.setTitle("제목은?");
            boardDto.setMemberDto(new MemberDto(member1));
            BoardEntity boardEntity = boardDto.toEntity();

            boardEntity.setMember(member1);
            boardEntity.setCategory(Category.DRY);
            boardRepository.save(boardEntity);

            LikesFunction like = LikesFunction.builder()
                    .memberEntity(member1)
                    .boardEntity(boardEntity)
                    .build();
            likesRepository.save(like);

        }

        public void BoardInit2(){

            MemberEntity member2 = new MemberEntity();
            member2.setName("Mike");
            member2.setNickname("medics");
            member2.setPassword("123");
            member2.setOily(true);
            memberRepository.save(member2);


            BoardDto boardDto2 = new BoardDto();
            boardDto2.setContent("인잇디비 vol.2");
            boardDto2.setViewcnt(2);
            boardDto2.setHot(false);
            boardDto2.setCreatedDate(LocalDateTime.now());
            boardDto2.setTitle("제목짓기2");
            boardDto2.setMemberDto(new MemberDto(member2));

            BoardEntity boardEntity2 = boardDto2.toEntity();
            boardEntity2.setCategory(Category.OILY);

            boardEntity2.setMember(member2);
            boardRepository.save(boardEntity2);

            LikesFunction like = LikesFunction.builder()
                    .memberEntity(member2)
                    .boardEntity(boardEntity2)
                    .build();
            likesRepository.save(like);

        }

        public void BoardInit3(){

            MemberEntity member2 = new MemberEntity();
            member2.setName("Mike");
            member2.setNickname("medics");
            member2.setPassword("123");
            member2.setOily(true);
            memberRepository.save(member2);

            BoardDto boardDto2 = new BoardDto();
            boardDto2.setContent("인잇디비 vol.2");
            boardDto2.setViewcnt(2);
            boardDto2.setHot(false);
            boardDto2.setCreatedDate(LocalDateTime.now());
            boardDto2.setTitle("제목짓기2");
            boardDto2.setMemberDto(new MemberDto(member2));

            BoardEntity boardEntity2 = boardDto2.toEntity();
            boardEntity2.setCategory(Category.OILY);

            boardEntity2.setMember(member2);
            boardRepository.save(boardEntity2);

            LikesFunction like = LikesFunction.builder()
                    .memberEntity(member2)
                    .boardEntity(boardEntity2)
                    .build();
            likesRepository.save(like);

        }
    }


}
