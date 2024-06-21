package com.smhrd.myapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.myapp.entity.Member;

// 추가 설명
// Repository?
// --> 데이터베이스 테이블에 접근하는 메서드들(ex findAll, save 등)을 사용하기 위한 인터페이스

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


	// 기본 CRUD 세팅 끝남. 
	Member findByUseridAndPw(String userid, String pw);

	Member findByUserid(String username);
}
