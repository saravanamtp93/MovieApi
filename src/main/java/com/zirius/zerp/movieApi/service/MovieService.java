package com.zirius.zerp.movieApi.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.zirius.zerp.movieApi.entity.Movie;
import com.zirius.zerp.movieApi.entity.MovieType;
import com.zirius.zerp.movieApi.exception.OmdbAccessException;
import com.zirius.zerp.movieApi.repo.MovieRepository;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepo;
	@Value("${application.ombd.key}")
	String omdbKey;

	public String getOmdbMovie(MovieType type, Date year, String title) {
		String response = null;
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://www.omdbapi.com/?t=" + title + "&apikey=" + omdbKey;
		if (omdbKey.isBlank()) {
			throw new OmdbAccessException("Requires valid KEY to Access permission for public API OMDB ");
		}

		try {
			ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
			response = exchange.getBody();
		} catch (RestClientException ex) {
			throw new OmdbAccessException("Exception occured while calling OMDB API:" + ex.getLocalizedMessage());

		}
		return response;
	}

	public List<Movie> getLocalDBMovie(MovieType type, Date year, String title) {
		year = setFixedDate(year);
		return (List<Movie>) movieRepo.findByTypeAndYearAndTitleContaining(type, year, title);
	}

	@Transactional
	public Movie saveMovie(Movie movie) {
		movie.setYear(setFixedDate(movie.getYear()));
		return movieRepo.save(movie);
	}

	private Date setFixedDate(Date date) {
		if (date != null) {
			LocalDate ldate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
			return Date.from(LocalDate.ofYearDay(ldate.getYear(), 2).atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		return date;
	}
}
