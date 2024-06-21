package com.smhrd.myapp.entity;

import com.smhrd.myapp.vo.MemberVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 추가설명
// Entity : 데이터베이스의 테이블을 나타내는 클래스 
// 			JPA(Java Persistence API)를 사용하여 데이터베이스에 저장되고 관리됨

// Entity annotation이 안나오면 JPA 라이브러리를 안가져온 거다!!
@Entity  // DB에 있는 Table을 표현하는 자료형, JPA가 해당 클래스를 엔티티로 인식함
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {

	public MemberEntity(MemberVO vo) {
		this.id = vo.getId();
		this.pw = vo.getPw();
		name = vo.getName();
		tel = vo.getTel();
		// this 써도 되고 안 써도 됨. 
	}
	
	
	// JPA 쓰려면 필수 !!!
	@Id  // pk, idx가 테이블의 주키(primary key) 역할을 함
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment
	private Long idx; 
	
	// 유니크, 길이, null 허용 등등
	@Column(unique = true)  // 해당 컬럼에 대한 설정값
	private String id;
	
	@Column(length = 200)
	private String pw;
	
	@Column(nullable = false)
	private String name;
	
	private String tel;
	
}
