package com.smhrd.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

	private Long id;
	private String userid;
	private String pw;
	private String role;
	
}
