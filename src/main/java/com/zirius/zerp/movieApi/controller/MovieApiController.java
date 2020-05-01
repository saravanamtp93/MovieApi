package com.zirius.zerp.movieApi.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zirius.zerp.movieApi.entity.Movie;
import com.zirius.zerp.movieApi.service.MovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieApiController {
	@Autowired
	MovieService movieService;

	@GetMapping("/omdb")
	public String publicAPI(@RequestHeader String type, @RequestHeader LocalDate year,
			@RequestParam(name = "title") String title) {
		return movieService.getOmdbMovie(type, year, title);
	}

	@GetMapping("/localdb")
	public Movie localDB(@RequestHeader String type, @RequestHeader LocalDate year,
			@RequestParam(name = "title") String title) {
		return movieService.getLocalDBMovie(type, year, title);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createMovie(@RequestHeader Movie movie) {
		return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public Movie updateMovie(@RequestHeader Movie movie) {
		return movie;
	}
}
