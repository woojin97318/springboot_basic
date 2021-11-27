package com.care.root.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.root.dto.SearchMovieDto;
import com.care.root.service.MovieSerachService;

@RestController
@RequestMapping("search")
@CrossOrigin
public class MovieSearchRestController {
	@Autowired MovieSerachService ms;

	@GetMapping("getMovies")
	public List<SearchMovieDto> getMovies() {
		return ms.getMovies();
	}
	/*
	@GetMapping("movieinfo/{movieId}")
	public Map<String, Object> movieInfo(@PathVariable String movieId) {
		Map<String, Object> map = new HashMap();
		map.put("result", "success : " + movieId);
		return map;
	}*/
	@GetMapping("movieinfo/{movieId}")
	public SearchMovieDto movieInfo(@PathVariable String movieId) {
		return ms.getMovieInfo(movieId);
	}
	@GetMapping("getCount/{movieId}")
	public Integer getCount(@PathVariable Integer movieId) {
		return ms.getCount(movieId);
	}
	@PutMapping("subCount")
	public void subCount(@RequestBody Map map) {
		ms.subCount(map);
	}
}
