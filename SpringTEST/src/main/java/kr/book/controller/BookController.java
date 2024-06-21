package kr.book.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.book.bshop.BookMapper;
import kr.book.model.BookVO;


@Controller  
public class BookController {

	@Autowired  
	private BookMapper mapper;
	
	// 도서 검색
	@RequestMapping("/bookList.do")
	public void bookList (Model model) {
		List<BookVO> list = mapper.bookList();
		System.out.println("수집한 데이터 : " + list);
		model.addAttribute("list", list);
	}
	
	// 도서 추가
	@RequestMapping("/bookInsert.do")
	public String bookInsert(BookVO vo) {
		mapper.bookInsert(vo);
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
}
