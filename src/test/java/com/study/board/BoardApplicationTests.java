package com.study.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.board.Service.BoardService;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private BoardService boardService;
	@Test
	void testJpa() {
		// JUnit - 여기 활용하면 좋음

		// assertTrue("제목1", boardService.boardView(1).getTitle());

		// for (int i = 1; i < 100; i++){
		// 	String subject = String.format("제목%d", i);
		// 	String content = String.format("내용 -> %d", i);
		// 	boardService.();
		// }
	}

}
