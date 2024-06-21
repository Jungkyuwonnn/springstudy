package com.smhrd.db;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.smhrd.model.BoardVO;

// mybatis f/w 사용할 때 interface 형식을 사용
// 인터페이스(interface)란 다른 클래스를 작성할 때 기본이 되는 틀을 제공하면서, 
// 다른 클래스 사이의 중간 매개 역할까지 담당하는 일종의 추상 클래스를 의미
// 선언방식
// 접근제어자 interface 인터페이스명
// 코드 필요없음 왜? spring이 다 하는데?
// -> 그럼 중괄호도 필요 없겠네?
// --> 추상메소드
// class -> interface 로 변경

// 추가설명
// Service 파트 
// 데이터베이스에 접근하는 DAO를 이용해서 결과값을 받아 옵니다.

public interface BoardMapper {

	// 전체 게시글을 조회 메소드
	public List<BoardVO> boardList();
	// BoardMapper mapper = new SqlSessionFactoryBean();
	// sql session 빌려오기, 사용하기, 반납하기, 결과값 반환하기
	// mapper.boardList();
	// 모두 SqlSessionFactoryBean의 역할 !!!!!!
	// 즉, Spring에서 알아서 해줌. 
	
	/////////////추가설명/////////////////
	//  접근제어자 메소드 반환 타입 메소드명
	// List<BoardVO>
	// List는 자바의 컬렉션 프레임워크에 속하는 인터페이스 중 하나로, 객체들의 집합을 다루는데 사용
	// <BoardVO>는 제네릭 타입을 명시하고 있으며, BoardVO 타입의 객체들만을 List에 담겠다는 것을 의미
	// 종합
	// public List<BoardVO> boardList();는 BoardVO 타입의 객체들을 담은 리스트를 반환하는 공개 메소드이며, 이 메소드는 추가적인 매개변수 없이 호출
	// 그 결과를 List<BoardVO> 형태로 반환

	// 게시글 등록 메소드
	public void register(BoardVO vo);

	// 게시글 한 개를 조회하는 메소드 --> 자동완성으로 생성
	// mybatis를 사용하는 방법
	// 1. 기존 DAO 방식 --> jsp/servlet 사용했던 방식
	// 2. interface 방식 --> spring f/w 사용하는 방식
	// 3. annotation 방식 --> 비교적 간단한 sql 구분일 때 사용 권장
	
	// 추가설명
	// 공개적으로 접근 가능하며, BoardVO 타입의 객체를 매개변수로 받아서 어떠한 값을 반환하지 않는 메소드를 선언
	// 이 메소드의 목적은 주로 받은 BoardVO 객체에 담긴 정보를 데이터베이스 등에 등록하는 작업을 수행하는 것입니다.
	
	
	// 추가설명
	// 이 두 개는 왜 xml 파일에 없고 여기에 있음?
	// 어노테이션 기반 DAO 방식을 적용함
	// DAO 메소드에 SQL 쿼리를 작성하는 어노테이션 방식
	// --> @Select, @Insert, @ Update, @Delete 등의 어노테이션을 사용하여 메서드에 직접 SQL 쿼리를 작성함
	// --> SQL 쿼리와 자바 코드를 가까이 두어 코드의 가독성을 높임 
	
	// SELECT * FROM BOARD WHERE IDX = #{idx}
	@Select("SELECT * FROM BOARD WHERE IDX = #{idx}")
	public BoardVO boardContent(int idx);
	
	
	// DELETE FROM TABLE WHERE IDX = #{idx}
	// 해당하는 IDX만 삭제하도록 
	@Delete("DELETE FROM BOARD WHERE IDX = #{idx}")
	public void boardDelete(int idx);
	
}
