package com.example.mdbvectorsearch.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("embedded_movies")
public class Movie {
	
	@Id
	private String _id;
	private String title;
	private int year;
	private int runtime;
	private Date released;
	private String poster;
	private String plot;
	private String fullplot;
	private String lastupdated;
	private String type;
	private List<String> directors;
	private Imdb imdb;
	private List<String> cast;
	private List<String> countries;
	private List<String> genres;
	private Tomatoes tomatoes;
	private int num_mflix_comments;
	private String plot_embeddings;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public Date getReleased() {
		return released;
	}

	public void setReleased(Date released) {
		this.released = released;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getFullplot() {
		return fullplot;
	}

	public void setFullplot(String fullplot) {
		this.fullplot = fullplot;
	}

	public String getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(String lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public Imdb getImdb() {
		return imdb;
	}

	public void setImdb(Imdb imdb) {
		this.imdb = imdb;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public Tomatoes getTomatoes() {
		return tomatoes;
	}

	public void setTomatoes(Tomatoes tomatoes) {
		this.tomatoes = tomatoes;
	}

	public int getNum_mflix_comments() {
		return num_mflix_comments;
	}

	public void setNum_mflix_comments(int num_mflix_comments) {
		this.num_mflix_comments = num_mflix_comments;
	}

	public String getPlot_embeddings() {
		return plot_embeddings;
	}

	public void setPlot_embeddings(String plot_embeddings) {
		this.plot_embeddings = plot_embeddings;
	}

	public static class Imdb {
		private double rating;
		private int votes;
		private int id;
		public double getRating() {
			return rating;
		}
		public void setRating(double rating) {
			this.rating = rating;
		}
		public int getVotes() {
			return votes;
		}
		public void setVotes(int votes) {
			this.votes = votes;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		// Getters and setters for Imdb fields
	}

	public static class Tomatoes {
		private Viewer viewer;
		private Date lastUpdated;
		
		// Getters and setters for Tomatoes fields
		
		public Viewer getViewer() {
			return viewer;
		}

		public void setViewer(Viewer viewer) {
			this.viewer = viewer;
		}

		public Date getLastUpdated() {
			return lastUpdated;
		}

		public void setLastUpdated(Date lastUpdated) {
			this.lastUpdated = lastUpdated;
		}

		public static class Viewer {
			public double getRating() {
				return rating;
			}
			public void setRating(double rating) {
				this.rating = rating;
			}
			public int getNumReviews() {
				return numReviews;
			}
			public void setNumReviews(int numReviews) {
				this.numReviews = numReviews;
			}
			private double rating;
			private int numReviews; 
			
			// Getters and setters for Viewer fields
			}
		}
		
		// Getters and setters for Movie fields
}

