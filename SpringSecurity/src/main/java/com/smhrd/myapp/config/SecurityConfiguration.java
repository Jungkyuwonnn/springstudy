package com.smhrd.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

// 스프링 시큐리티 설정 공간
// 보통 둘이 같이 씀
// @EnableWebSecurity : 웹 보안 활성화 및 다양한 보안 기능 추가
// 

// 해당하는 파일이 환경설정 파일임을 나타냄
// --> 해당 클래스 안쪽에서 @Bean(객체)들을 하나 이상 정의할 수 있게 해줌
@Configuration  
// 해당 클래스 파일이 "Spring Security"용 환경설정 파일임을 나타냄
@EnableWebSecurity
public class SecurityConfiguration {

	// Bean (객체)
	// : Spring Container에 적재될 수 있는 형태 (Bean만 가능함!!)
	// 언제 많이 사용해요?
	// 1. 기능들을 사용자 정의 형태로 만들어야 할 때 많이 사용
	// 2. 모든 클래스 파일들이 공유해야 할 때 
	
	// deprecated --> 더 이상 지원하지 않을 문법들. 안 쓰는 게 좋음
	// SpringSecurity에서 매개변수로서 권장하는 문법
	// --> lambda식 (==익명함수 : 해당하는 영역에서만 사용하고, 버릴 함수)
	// 어나니머스 메소드
	// *lambda식 문법
	// () -> 실행할 구문
	
	
	// 권한을 부여하는 3가지 메소드
	// (1) permitAll : 전부 허용
	// (2) hasAnyRole : 권한 확인 후 해당 권한을 가진 사람에게만 허용
	// (3) authenticated : 인증받은 사용자에게만 허용
	
	
	// 클래스 안에 또 다른 클래스 설계가 가능함 --> inner class
	// 거름망
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// 해당하는 메소드가 동작하면서 SFC 생성할 거고, Bean으로써 등록
		
		// :: (lambda식에서 제공해주는 method reference 연산자)
		// 객체 :: 메소드명 , 객체에 있는 메소드를 참조하겠다라는 의미
		// 1. CORS(Cross Origin Resource Sharing) : 동일출처정책 비활성화 하겠다.
		http.cors(AbstractHttpConfigurer::disable)
		// 2. CSRF(Cross Site Request Forgery) 비활성화 함
			.csrf(AbstractHttpConfigurer::disable)
			//// 1~2기본옵션이라고 생각하셈(비활성화)
		// 3. 우리가 인가를 내려줄 페이지를 지정하는 메소드
			// forward로 모든 페이지 다 엶
			// DispatcherType -> jakarta type으로 설정
			// permitAll() : 요청 허용
			.authorizeHttpRequests((request) -> 
				request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/images/**","/join", "/join-process").permitAll()  // String type, /images/에 있는 모든 이미지를 업로드 함.
				.requestMatchers("/admin").hasAnyRole("admin")
				.requestMatchers("/user").hasAnyRole("user")
				.anyRequest().authenticated())  // 인증받은 대상에 대해서만 허용(저 한 줄이 다 인증이 되어야 한다는 의미)
		// 4. 사용자 지정 페이지로 인증 받을 수 있도록 메소드 설정
			.formLogin((login)->login.loginPage("/")  // 사용자 정의 로그인 페이지
					// @PostMapping
					.loginProcessingUrl("/login-process")  
					.usernameParameter("userid")
					.passwordParameter("pw")
					// build된 user == userid, pw
					// redirect:/dashboard
					.defaultSuccessUrl("/dashboard") // 로그인 성공 후 이동 페이지
					.permitAll());

		return http.build();
		
	}
	
	// 암호화를 도와주는 PasswordEncoder 하나 추가
	// --> DB에 있는 데이터를 가져오려고 했더니, 암호화하는 Encoder~~ error 발생함
	@Bean
	PasswordEncoder passwordEncoder() {
		// 이미 다 만들어진 암호화 알고리즘 사용하겠다 
		return new BCryptPasswordEncoder();
		
	}
	
	
	
	
	
}
