package com.smhrd.db;

import java.util.List;

import com.smhrd.model.Artist;

// @Mapper --> String 버전이 업그레이드 되면서 생략 가능해졌음
public interface ArtistMapper {

	public List<Artist> artist();

	
	
	
}
