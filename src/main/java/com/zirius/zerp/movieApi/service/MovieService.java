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
import org.springframework.web.client.RestTemplate;

import com.zirius.zerp.movieApi.entity.Movie;
import com.zirius.zerp.movieApi.entity.MovieType;
import com.zirius.zerp.movieApi.repo.MovieRepository;



@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepo;
	@Value("${application.ombd.key}")
	String omdbKey;
	
	public String getOmdbMovie(MovieType type, Date year, String title) {
		RestTemplate restTemplate = new RestTemplate();
		String url="http://www.omdbapi.com/?t="+title+"&apikey="+omdbKey;
		
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return  exchange.getBody();
	}

	public List<Movie> getLocalDBMovie(MovieType type,Date year, String title) {
		year=addOneDay(year);
		return (List<Movie>) movieRepo.findByTypeAndYearAndTitleContaining(type, year, title);
	}

	@Transactional
	public Movie saveMovie(Movie movie) {
		movie.setYear(addOneDay(movie.getYear()));
		return movieRepo.save(movie);
	}

	private Date addOneDay(Date date) {
		if(date !=null) {
			LocalDate ldate=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
			return Date.from(LocalDate.ofYearDay(ldate.getYear(),2).atStartOfDay(ZoneId.systemDefault()).toInstant());			
		}
		return date;
	}
}
