package search;

import media.Manga;
import media.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MangaSearch {

    public void searchManga(boolean includeNSFW, String type, Integer limit) {
        String query = "SELECT * FROM manga WHERE 1=1";
        if (!includeNSFW) {
            query += " AND nsfw = 0";
        }
        if (!type.isEmpty()) {
            query += " AND type = ?";
        }
        if (limit != null && limit > 0) {
            query += " LIMIT " + limit;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            int paramIndex = 1;
            if (!type.isEmpty()) {
                statement.setString(paramIndex++, type);
            }

            ResultSet resultSet = statement.executeQuery();
            List<Media> mangaList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_b");
                String name = resultSet.getString("name");
                boolean nsfw = resultSet.getBoolean("nsfw");
                int opinion = resultSet.getInt("opinion");
                String mangaType = resultSet.getString("type");

                mangaList.add(new Manga(id, name, mangaType, nsfw, opinion));
            }

            if (mangaList.isEmpty()) {
                System.out.println("No manga found with the specified filters.");
                return;
            }

            for (Media manga : mangaList) {
                manga.displayDetails();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
