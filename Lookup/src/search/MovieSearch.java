package search;

import media.Movie;
import media.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieSearch {

    public void searchMovies(boolean includeNSFW, String type) {
        String query = "SELECT * FROM movies WHERE 1=1";
        if (!includeNSFW) {
            query += " AND nsfw = 0";
        }
        if (!type.isEmpty()) {
            query += " AND type = ?";
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (!type.isEmpty()) {
                statement.setString(1, type);
            }

            ResultSet resultSet = statement.executeQuery();
            List<Media> movieList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_m");
                String name = resultSet.getString("name");
                boolean nsfw = resultSet.getBoolean("nsfw");
                int opinion = resultSet.getInt("opinion");
                String movieType = resultSet.getString("type");

                // Create a Movie object and add to list
                movieList.add(new Movie(id, name, movieType, nsfw, opinion));
            }

            if (movieList.isEmpty()) {
                System.out.println("No movies found with the specified filters.");
                return;
            }

            // Use polymorphism to display details
            for (Media movie : movieList) {
                movie.displayDetails();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
