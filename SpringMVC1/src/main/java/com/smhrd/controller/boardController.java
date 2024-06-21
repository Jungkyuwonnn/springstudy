package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// import com.smhrd.db.BoardDAO;
import com.smhrd.db.BoardMapper;
import com.smhrd.model.BoardVO;

// Spring F/W 흐름
// (1) Dispatcher Servlet(Front Controller) 요청을 받는다
// (2) Handler Mapping 에게 요청값(==요청 url)을 보낸다.
// (3) Handler Mapping 요청값과 일치하는 Controller를 Spring Container에서 찾는다. 
// (4) Handler Adapter가 HM이 찾은 Controller를 실행 

// 추가설명
// 웹브라우저의 요청을 전담하여 처리하는 컨트롤러 

// 해당하는 클래스 파일이 Spring Container(SC) 안에 자동으로 생성될 수 있게 만들고, Controller임을 명시하는 어노테이션
@Controller  // 기능이 정의되어 있음
public class boardController {

	// Autowired 
	// : 자동으로 연결, Spring Container에서 mapper를 구현한 구현체를 자동으로 연결해줌.  
	@Autowired  // ********별표 백만개개개개개******
	private BoardMapper mapper;
	
	// 글 삭제기능
	// @RequestMapping("url")
	// : 클라이언트의 요청(url)에 맞는 클래스나 메소드를 연결함
	// : url에 일치하는 요청을 처리함. 
	@RequestMapping("/boardDelete/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		// 1. mapper 사용하기
		mapper.boardDelete(idx);
		// 2. 경로 이동하기
		return "redirect:/";
	}
	
	//////// 추가정보 //////////
	// GET vs POST
	// Client에서 정보를 요청할 떄 사용
	// GET
	// - 서버로부터 데이터를 요청할 때 사용
	// POST	 
	// - 정보를 생성/업데이트하기 위해 서버에 데이터를 보낼 떄 사용
	// DB로 비유하자면  GET은 SELECT에 가깝고, POST는 CREATE에 가깝다.
	
	
	
	// 글 상세조회 기능
	// 경로상에 데이터를 직접적으로 보내주는 경우에 사용할 수 있는 기능
	// /경로/{받아줄 데이터 이름}
	// @PathVariable("받아줄 데이터 이름") 자료형 변수명
	// Model 필요할 시 , Model model 불러줌
	// Model model 파라미터는 컨트롤러와 뷰 사이에서 데이터를 전달하는 데 사용
	// **왜 idx를 하는 이유???? 해당하는 게시글을 조회하기 위해서는 숫자 형태인 idx를 가져옴
	@GetMapping("/boardContent/{idx}")
	public String boardContent(@PathVariable("idx") int idx, Model model) {
		System.out.println("수집한 데이터>> " + idx);
		// 1. mapper 사용해서 게시글 한 개를 조회하기
		// mapper에서 boardContent라는 게시글 한 개 조회하는 기능 만듦
		// result 변수에 담아주기 
		// BoardVO는 뭐임 ? --> BoardVO.java에 field 명을 지정해뒀으니까
		// BoardVO 타입의 객체를 반환 
		// 반환 객체 타입 변수 = mapper에 있는 기능 
		BoardVO result = mapper.boardContent(idx);
		
		// 2. 조회한 결과를 model 담아주기
		// board라는 이름으로 model에 담음
		model.addAttribute("board", result);
		// 3. Content.jsp 이동하기 --> view
		return "content";
	}
	
	// 글쓰기 기능을 하는 메소드 생성
	// 매개변수가 다름 --> 메소드 오버로딩
	@PostMapping("/register") // --> post 전송방식 일 때만 메소드 실행
	public String register(BoardVO vo) {
		// 1. 요청데이터 수집
		// --> Spring이 알아서 진행해줌
		// 메소드의 매개변수로 수집하고 싶은 자료형을 지정해주기만 하면 된다. 
		// *** vo 필드명 == 보내주는 name 값 == table column(DB)명 ***
			// list : 게시글 전체를 말함 , BoardVO : 게시글을 구성하는 요소들(작성자, 제목, 게시글 내용)을 하나로 묶은 거 
		System.out.println(vo);
		// 주소값 안나오고 안에 저장되어 있는 데이터 출력
		// --> @Data >> getter, setter, toString method override
		// toString method ?
		// >> 객체의 주소값을 출력하는 대신에 안족에 있는 데이터 출력을 도와주는 Object의 메소드
		
		// 2. mapper 사용 (dao가 mapper임)
		mapper.register(vo); // 수집한 데이터 가져와야 함. mapper 쪽에 vo
		
		// 3. 경로이동
		return "redirect:/";
		// '/'라는 경로로 이동하겠다. 
		// --> board 메소드 실행 --> boardList 조회 --> 결과값 model --> board로 이동
	}
	
	
	// 글쓰기 페이지로 이동하는 메소드 생성
	@GetMapping("/register") // --> get 전송방식 일 때만 메소드 실행
	public String register() {
		return "register";
	}
	

	
	// Spring F/W(FrameWork)는 url mapping 값을 메소드 단위로 설정함
	// @RequestMapping () : 요청하는 데이터("/")에 url mapping 값을 잡아주는 방법 
	@RequestMapping("/") // "/" 요청이 들어왔을 때 board 메소드를 실행
	public String board(Model model) {
		// Model을 가져오는 방법
		// --> 메소드의 매개변수로 해당하는 객체를 작성
		// --> SC가 자동으로 객체를 채워줌 
		// 2. DAO 사용해서 결과값 받아오기
		List<BoardVO> list = mapper.boardList();  // 게시글 보기 , ArrayList 형태로 반환됨 
		// 3. 받아온 결과값 model 영역에 담아주기
		// model이란 ? >> request의 경량화된 버전 
		model.addAttribute("list", list);
			// VO에서 불러온 데이터를 list이고, "list"라 이름을 부르겠다.
			// addAttribute (--> 속성 중 하나)로 board (---> View ) 로 list를 보냄
		
		// 4. board.jsp로 forward 방식으로 이동하기 
		return "board";
		// board --> 논리적인 주소값
		// WEB-INF/views/board.jsp --> 물리적인 주소값
		// 논리적인 주소값을 물리적인 주소 값으로 자동으로 변환해주는 View Resolver 객체가 이미 존재. 
		// ViewResolver의 역할은 ? (확인해보고 싶다면 servlet-context.xml 봐보기)
		// WEB-INF/views/ + 리턴한 값 + .jsp 형식으로 자동으로 변환되어 실행됨. 
	}
	
	
	
	
	
	
}
