package kr.book.bshop;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.book.model.BookVO;

@Mapper
public interface BookMapper {

	// 전체 도서 조회
	public List<BookVO> bookList();

	// 도서 등록 메소드
	public void bookInsert(BookVO vo);

	// SELECT * FROM BOARD WHERE IDX = #{idx}
//	@Select("SELECT * FROM BOARD WHERE IDX = #{idx}")
//	public BookVO boardContent(int idx);
	
	
	// DELETE FROM TABLE WHERE IDX = #{idx}
	// 해당하는 IDX만 삭제하도록 
//	@Delete("DELETE FROM BOARD WHERE IDX = #{idx}")
//	public void boardDelete(int idx);

	
	
}
