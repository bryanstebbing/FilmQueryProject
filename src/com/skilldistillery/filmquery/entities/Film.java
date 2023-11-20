package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int filmId;
	private String title;
	private String description;
	private short releaseYear;
	private int languageId;
	private String langName;
	private String rating;
	private List<Actor> actors;

	public Film(String title, String desc, short releaseYear, int langId, String rating, String langName, List<Actor> actors) {
		this.title = title;
		this.description = desc;
		this.releaseYear = releaseYear;
		this.languageId = langId;
		this.langName = langName;
		this.rating = rating;
		this.actors = actors;
	}

	public Film() {
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}



	@Override
	public int hashCode() {
		return Objects.hash(actors, description, filmId, langName, languageId, rating, releaseYear, title);
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", description=" + description + ", releaseYear="
				+ releaseYear + ", languageId=" + languageId + ", langName=" + langName + ", rating=" + rating
				+ ", actors=" + actors + "]";
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(actors, other.actors) && Objects.equals(description, other.description)
				&& filmId == other.filmId && Objects.equals(langName, other.langName) && languageId == other.languageId
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& Objects.equals(title, other.title);
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}
}
