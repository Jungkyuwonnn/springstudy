package com.smhrd.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// lombok 라이브러리란?
// --> getter, setter, 생성자 메소드를 annotation을 활용하여 자동으로 생성
// 개발을 편하게 할 수 있도록 도와주는 도구

//추가설명
// BoardVO 이 페이지는 데이터를 주고 받을 떄 사용하는 객체들인 VO(idx, title, writer, content, indate)를 만듦

@Data // getter, setter
@AllArgsConstructor // 모든 필드 값을 받는 생성자
@NoArgsConstructor // 기본생성자
public class BoardVO {
	// BoardVO : 게시글 하나를 표현할 수 있는 나만의 자료형 
 
	private int idx;
	private String title;
	private String writer;
	private String content;
	private Date indate;
	
	
}
