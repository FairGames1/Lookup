package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AnimeSearch {

    public void searchAnime(boolean includeNSFW, String type) {
        String query = "SELECT * FROM anime WHERE 1=1";
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

            if (!resultSet.isBeforeFirst()) { // No results
                System.out.println("No anime found with the specified filters:");
                System.out.println("- NSFW included: " + (includeNSFW ? "Yes" : "No"));
                System.out.println("- Type: " + (type.isEmpty() ? "Any" : type));
                return;
            }

            // Display results
            System.out.println("Anime Results:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id_a");
                String name = resultSet.getString("name");
                boolean nsfw = resultSet.getBoolean("nsfw");
                int opinion = resultSet.getInt("opinion");
                String animeType = resultSet.getString("type");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Type: " + animeType);
                System.out.println("NSFW: " + nsfw);
                System.out.println("Opinion: " + opinion);
                System.out.println("-------------------");
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
