package com.smhrd.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.smhrd.model.Artist;
import com.smhrd.model.BoardVO;
import com.smhrd.model.SearchCriteria;

// mybatis f/w 사용할 때 interface 형식을 사용

public interface BoardMapper {
	// 전체 게시글을 조회 메소드
	public List<BoardVO> boardList();

	// 게시글 등록 메소드
	public void register(BoardVO vo);

	// 게시글 한 개를 조회하는 메소드 --> 자동완성으로 생성
	@Select("SELECT * FROM BOARD WHERE IDX = #{idx}")
	public BoardVO boardContent(int idx);
	
	// DELETE FROM TABLE WHERE IDX = #{idx}
	@Delete("DELETE FROM BOARD WHERE IDX = #{idx}")
	public BoardVO boardDelete(int idx);

	public List<BoardVO> search(SearchCriteria criteria);

	public List<BoardVO> autocomplete(SearchCriteria criteria);

	
	
	
}
