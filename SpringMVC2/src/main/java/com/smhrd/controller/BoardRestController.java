package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.db.BoardMapper;
import com.smhrd.model.BoardVO;
import com.smhrd.model.SearchCriteria;

// REST : Respresentational State Transfer
// REST의 경우, 클라이언트가 특정 URL에 접속하면 웹페이지를 그리는 것이 아니라 특정 정보 또는 특정 처리 결과를 텍스트 형태로 반환한다.
// boardController는 특정 정보를 표시, BoardRestController는 화면에 특정 정보를 표시
@RestController // @Controller + @ResponseBody 기능 합친 것과 같음
public class BoardRestController {

	// 비동기 통신으로 요청이 들어왔을 떄 처리해주는 역할
	@Autowired
	private BoardMapper mapper;
	
	// 자동완성 기능
	@RequestMapping("/autocomplete")
	public List<BoardVO> autocomplete(SearchCriteria criteria){
		// 리턴타입이나 매개변수의 자료형은 자유롭게 설정
		// ---> 내가 handling하기 (꺼내오기) 편한 자료형으로 지정하는 것이 좋다. 
		
		// 추가설명
		// autocomplete 요청이 들어오면 SearchCriteria에서 객체를 받아온 후 autocomplete가 실행되고 이때 받아온 데이터가 List<BoardVO> 형태로 반환
		// mapper.autocomplete(criteria) 
		// -> MyBatis Mapper를 통해 데이터베이스에서 검색 작업을 수행하는 메서드 호출을 나타냄. 
		//  Mapper를 통해 데이터베이스에 액세스하고, autocomplete() 메서드에 SearchCriteria 객체를 전달하여 데이터베이스에서 검색을 수행합니다. 그리고 그 결과를 List<BoardVO> 형태로 반환
		// --> 이러한 데이터를 result에 담는다고 지정함 
		List<BoardVO> result = mapper.autocomplete(criteria);
		
		return result;
	}
	
	
	// 비동기통신으로 검색하는 기능
	@RequestMapping("/search")
	// ==> 즉, 비동기 통신에 사용하는 annotation , return 해주는 데이터가 화면에 출력이 됨. ==> 여기서는 list 
	public List<BoardVO> search(SearchCriteria criteria) {
		System.out.println("수집 데이터 >>" + criteria);
		List<BoardVO> list = mapper.search(criteria); // 검색했을 때 게시글이 나와야 하므로 list
		return list; // return 된 값이 화면에 출력
		// list : 레퍼런스 변수 즉, 주소값이 담겨 있음
		// 현재 list에는 주소값이 담겨져있다! 우리가 비동기통신으로 보내줘야하는 데이터는 json 구조다!!!
		// 주소값 --변환--> json
		// 변환을 Spring에서 자동으로 진행 (단, 변환용 라이브러리가 필요함)
	}

	
}
