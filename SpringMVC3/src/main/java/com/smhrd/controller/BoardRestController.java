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
import com.smhrd.model.Artist;

// REST : Respresentational State Transfer
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
