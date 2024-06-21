package com.smhrd.myapp.entity;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.smhrd.myapp.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	public Member(MemberDTO vo) {
		id = vo.getId();
		userid = vo.getUserid();
		pw = vo.getPw();
		role = vo.getRole();
	}
	
	public Member(String userid, String pw, String role, PasswordEncoder encoder) {
		this.userid = userid;
		this.pw = encoder.encode(pw);
		this.role = role;
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String userid;  // not null
	@Column(nullable = false)
	private String pw;  // not null
	@Column(updatable = false, nullable = false)
	private String role;  // 역할, 권한 설정 때 사용 예정
						  // default값 'user'
}
