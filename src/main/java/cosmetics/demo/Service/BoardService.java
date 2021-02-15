package cosmetics.demo.Service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final LikesRepository likesRepository;

    // 카테고리별 게시글 목록 //
    public Page<BoardDto> Boardlist(Category category, Pageable pageable) {
        Page<BoardEntity> entityPage = boardRepository.findBoardEntitiesByCategory(category, pageable);
        //page<entity> -> page<Dto>
        return entityPage.map(BoardDto::new);

    }

    // 게시글 좋아요 //
    @Transactional
    public void addLikes(Long memberId, Long boardId){

        MemberEntity memberEntity = memberRepository.findOne(memberId);
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        likesRepository.findByMemberEntityAndBoardEntity(memberEntity, boardEntity).ifPresent(none ->{throw new RuntimeException();});
        int count = likesRepository.countByBoardEntity(boardEntity);
        boardEntity.updateLikes(count + 1);
        likesRepository.save(LikesFunction.builder()
                .boardEntity(boardEntity)
                .memberEntity(memberEntity)
                .build());
    }

    // 게시글 좋아요 취소 //
    public void cancleLikes(Long memberId, Long boardId, Long LikesId){
        MemberEntity memberEntity = memberRepository.findOne(memberId);
        BoardEntity boardEntity = boardRepository.findById(boardId).get();

        likesRepository.findByMemberEntityAndBoardEntity(memberEntity, boardEntity).orElseThrow(() -> new RuntimeException());
        int count = likesRepository.countByBoardEntity(boardEntity);
        boardEntity.updateLikes(count - 1);
        likesRepository.deleteById(LikesId);
    }

    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        return new BoardDto(boardEntity);
    }

    @Transactional
    public Long savePost(Long memberId, Category category, BoardDto boardDto) {
        MemberEntity findMember = memberRepository.findOne(memberId);

        BoardEntity board = BoardEntity.builder()
                .id(boardDto.getId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .viewcnt(boardDto.getViewcnt())
                .build();

        board.setCategory(category);
        board.setMember(findMember);

        return boardRepository.save(board).getId();
    }
    //<수정>//
    @Transactional
    public BoardDto addViews(Long id){
        BoardEntity boardEntity = boardRepository.findById(id).get();
        boardEntity.updateView(boardEntity.getViewcnt()+1);

        return new BoardDto(boardEntity);
    }

    @Transactional
    public void deletePost(Long memberId, Long boardId) {
        boardRepository.deleteById(boardId);
        MemberEntity findMember = memberRepository.findOne(memberId);
        List<BoardEntity> findBoards = boardRepository.findByMemberEntityId(memberId);
        findMember.setBoards(findBoards);
    }

    //==게시글 찾기==//
    public Page<BoardDto> searchPosts(String keyword, Pageable pageable) {
        Page<BoardEntity> byTitleContaining = boardRepository.findByTitleContaining(keyword, pageable);
        if (byTitleContaining.isEmpty()) return null;

        Page<BoardDto> boardDtos = byTitleContaining.map(BoardDto::new);

        return boardDtos;
    }

    //==게시글 업뎃==//
    @Transactional
    public BoardDto updatePost(Long id, Long memberId, BoardDto board){
        MemberEntity findMember = memberRepository.findOne(memberId);
        BoardEntity boardEntity = boardRepository.findById(id).get();

        List<BoardEntity> boards = findMember.getBoards();

        if(boards.contains(boardEntity)){
            boardEntity.updateBoard(board);
        }

        return new BoardDto(boardEntity);

    }

    public Page<BoardDto> searchPostsByCategory(String keyword, Category category, Pageable pageable) {
        Page<BoardEntity> content = boardRepository.findBoardEntitiesByCategoryAndTitleContaining(keyword, category, pageable);

        Page<BoardDto> collect = content.map(BoardDto::new);

        return collect;
    }
}