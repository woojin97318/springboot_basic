package com.care.root.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.care.root.dto.MovieBookDto;
import com.care.root.dto.SearchMovieDto;
import com.care.root.movieURL.movieURL;

@Service
public class SearchService implements movieURL {
	RestTemplate movieSearch = new RestTemplate();

	/*
	 * public ArrayList<SearchMovieDto> getMovieView() { String test =
	 * movieSearch.getForObject( movieViewUrl + "/getMovies", String.class);
	 * System.out.println("view 결과 확인 : " + test); ArrayList<SearchMovieDto> list =
	 * new ArrayList<SearchMovieDto>(); String movieURL =
	 * "http://localhost:10000/search/movieinfo/"; for (int i = 1; i < 6; i++) {
	 * SearchMovieDto dto = new SearchMovieDto(); dto.setMovieId(i + "");
	 * dto.setMovieTitle("화성을 지켜라" + i); dto.setMovieDate("2200-05-1" + i); Link
	 * link = Link.of(movieURL + i); dto.add(link.withRel(dto.getMovieTitle()));//
	 * 경로이름 list.add(dto); } return list; }
	 */
	public SearchMovieDto[] getMovieView() {
		return movieSearch.getForObject(movieViewUrl + "/getMovies", SearchMovieDto[].class);
	}

	public String booking(MovieBookDto dto) {
		boolean bool = bookingChk(dto.getMovieId(), dto.getBookCount());
		String message;
		if (bool) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH시mm분");
			dto.setBookDate(format.format(cal.getTime()));
			int re = movieSearch.postForObject(movieBookUrl + "save/", dto, Integer.class);
			System.out.println("결과(1성공, 0실패) : " + re);
			
			message = "<script>alert('예매 성공')</script>";
		} else {
			message = "<script>alert('매진');location.href='" + movieUrl + "'</script>";
		}
		return message;
	}

	private boolean bookingChk(String movieId, String bookCount) {
		int count = movieSearch.getForObject(movieViewUrl + "getCount/" + movieId, Integer.class);
		if (count < Integer.parseInt(bookCount)) {
			return false;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("movieId", movieId);
			map.put("count", bookCount);
			movieSearch.put(movieViewUrl + "subCount", map);
			return true;
		}
	}

}
