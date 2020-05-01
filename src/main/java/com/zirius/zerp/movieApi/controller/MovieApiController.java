package com.zirius.zerp.movieApi.controller;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zirius.zerp.movieApi.entity.Movie;
import com.zirius.zerp.movieApi.entity.MovieType;
import com.zirius.zerp.movieApi.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieApiController {
	@Autowired
	MovieService movieService;

	@GetMapping("/omdb")
	public String publicAPI(@RequestHeader MovieType type, @RequestHeader @DateTimeFormat(pattern = "yyyy") Date year,
			@RequestParam(name = "title") String title) {
		return movieService.getOmdbMovie(type, year, title);
	}

	@GetMapping("/localdb")
	public List<Movie> localDB(@RequestHeader MovieType type, @RequestHeader("year") @DateTimeFormat(pattern="yyyy") Date year,
			@RequestParam(name = "title") String title) {
	
		return movieService.getLocalDBMovie(type, year, title);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
		movie.setId(null);
		movieService.saveMovie(movie);
		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public Movie updateMovie(@RequestBody Movie movie) {
		movieService.saveMovie(movie);
		return movie;
	}
}
