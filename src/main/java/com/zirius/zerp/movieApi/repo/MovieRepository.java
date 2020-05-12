package com.zirius.zerp.movieApi.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zirius.zerp.movieApi.entity.Movie;
import com.zirius.zerp.movieApi.entity.MovieType;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	public List<Movie> findByTypeAndYearAndTitleContaining(MovieType type, Date year, String title);
}
