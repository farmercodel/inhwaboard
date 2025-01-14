package com.study.board.Service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    // @Autowired는 BoardRepository boardRepository = new BoardRepository() 이렇게 작성할 필요 없이 주입 해줌.
    // Dependency Injection (의존성 주입) 해주는 어노테이션임!
    // BoardRepository 인터페이스의 구현체를 BoardService 클래스의 boardRepository 필드에 자동으로 주입
    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; // 프로젝트 경로를 projectPath에 담고, 경로 지정
        UUID uuid = UUID.randomUUID(); // 랜덤 user-id 생성
        String fileName = uuid + "_" + file.getOriginalFilename(); // 저장된 파일 이름 생성
        File saveFile = new File(projectPath, fileName); // 파일 경로 지정

        file.transferTo(saveFile);

        // 데이터베이스에 파일 경로, 이름 저장
        board.setFilename(fileName); // 파일 이름
        board.setFilepath("/files/" + fileName); // 파일 경로와 이름

        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {
        // findAll()은 board라는 테이블이 담긴 리스트를 리턴해줌.
        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 처리
    public Board boardView(Integer id) {
        // findById 메소드는 Optional<Board> 타입의 값을 반환하는데,
        // Optional은 값이 존재할 수도 있고, 존재하지 않을 수도 있음을 나타내는 객체
        // 따라서 이를 안전하게 처리하기 위해 get() 메소드를 활용한다.
        // get() 메소드는 Optional 안에 값이 존재할 경우 그 값을 반환하지만, 값이 존재하지 않을 경우 NoSuchElementException을 발생시키기 때문이다.
        return boardRepository.findById(id).get();
    }

    public void update(Board board) {
        boardRepository.save(board);
    }

    // 특정 게시글 삭제
    public void boardDelete(@RequestParam Integer id) {
        boardRepository.deleteById(id);
    }
}
