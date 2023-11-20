package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PWD = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Film> findFilmById(int filmId) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT film.id AS film_id, film.title, film.description, film.release_year, film.rating, "
					+ "GROUP_CONCAT(CONCAT(actor.first_name, ' ', actor.last_name) SEPARATOR ', ') AS actors, "
					+ "language.id AS language_id, language.name AS language_name "
					+ "FROM film "
					+ "LEFT JOIN film_actor ON film.id = film_actor.film_id "
					+ "LEFT JOIN actor ON film_actor.actor_id = actor.id "
					+ "LEFT JOIN language ON film.language_id = language.id "
					+ "WHERE film.title LIKE ? OR film.description LIKE ? "
					+ "OR film.rating LIKE ? OR film.release_year LIKE ? "
					+ "GROUP BY film.id, film.title, film.description, film.release_year, film.rating, language.id, language.name";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + filmId + "%");
			stmt.setString(2, "%" + filmId + "%");
			stmt.setString(3, "%" + filmId + "%");
			stmt.setString(4, "%" + filmId + "%");

			ResultSet filmResult = stmt.executeQuery();

			if (!filmResult.next()) {
				System.out.println("We're sorry (sort of), but the film you are looking for does not exist."
						+ " Please try again.");
			} else {
                StringBuilder actors = new StringBuilder();

				do {
					actors.append(filmResult.getString("actors")).append(", ");
					System.out.println("Title: " + filmResult.getString("title") +
                            "\nDescription: " + filmResult.getString("description") +
                            "\nRating: " + filmResult.getString("rating") +
                            "\nRelease Year: " + filmResult.getShort("release_year") +
                            "\nLanguage: " + filmResult.getString("language_name") +
                            "\nActors: " + actors.toString() + "\n");
				} while (filmResult.next());

			conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}


	public Film findFilmByKeyword(String keyword) throws SQLException {
		Film filmSetNumber2 = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT film.id AS film_id, film.title, film.description, film.release_year, film.rating, "
						+ "GROUP_CONCAT(CONCAT(actor.first_name, ' ', actor.last_name) SEPARATOR ', ') AS actors, "
						+ "language.id AS language_id, language.name AS language_name "
						+ "FROM film "
						+ "LEFT JOIN film_actor ON film.id = film_actor.film_id "
						+ "LEFT JOIN actor ON film_actor.actor_id = actor.id "
						+ "LEFT JOIN language ON film.language_id = language.id "
						+ "WHERE film.title LIKE ? OR film.description LIKE ? "
						+ "OR film.rating LIKE ? OR film.release_year LIKE ? "
						+ "GROUP BY film.id, film.title, film.description, film.release_year, film.rating, language.id, language.name";
			
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + keyword + "%");
			preparedStatement.setString(2, "%" + keyword + "%");
			preparedStatement.setString(3, "%" + keyword + "%");
			preparedStatement.setString(4, "%" + keyword + "%");

			ResultSet filmResult = preparedStatement.executeQuery();

			if (!filmResult.next()) {
				System.out.println("We're sorry (sort of), but the film you are looking for does not exist."
						+ " Please try again.");
			} else {
                StringBuilder actors = new StringBuilder();

				do {
					actors.append(filmResult.getString("actors")).append(", ");
					System.out.println("Title: " + filmResult.getString("title") +
                            "\nDescription: " + filmResult.getString("description") +
                            "\nRating: " + filmResult.getString("rating") +
                            "\nRelease Year: " + filmResult.getShort("release_year") +
                            "\nLanguage: " + filmResult.getString("language_name") +
                            "\nActors: " + actors.toString() + "\n");
				} while (filmResult.next());

			}

			filmResult.close();
			preparedStatement.close();
			conn.close();

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return filmSetNumber2;
	}
	
	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, USER, PWD);
		
		String sql = "SELECT * FROM actor WHERE id = ?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		
		ResultSet actorResult = stmt.executeQuery();
		
		if (actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
		}
		System.out.println(actorResult);
		conn.close();
		return actor;
	}

	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PWD);

			String sql = "SELECT actor.* FROM actor JOIN film_actor ON actor.id = film_actor.film_id WHERE film_actor.actor_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor actor = new Actor(id, firstName, lastName);
				actors.add(actor);
				System.out.println(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

}
