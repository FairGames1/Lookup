package search;

import media.Manga;
import media.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MangaSearch {

    public void searchManga(boolean includeNSFW, String type) {
        String query = "SELECT * FROM manga WHERE 1=1";
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
            List<Media> mangaList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_b");
                String name = resultSet.getString("name");
                boolean nsfw = resultSet.getBoolean("nsfw");
                int opinion = resultSet.getInt("opinion");
                String mangaType = resultSet.getString("type");

                // Create a Manga object and add to list
                mangaList.add(new Manga(id, name, mangaType, nsfw, opinion));
            }

            if (mangaList.isEmpty()) {
                System.out.println("No manga found with the specified filters.");
                return;
            }

            // Use polymorphism to display details
            for (Media manga : mangaList) {
                manga.displayDetails();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
