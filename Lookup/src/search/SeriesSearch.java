package search;

import media.Series;
import media.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeriesSearch {

    public void searchSeries(boolean includeNSFW, String type) {
        String query = "SELECT * FROM series WHERE 1=1";
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
            List<Media> seriesList = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_s");
                String name = resultSet.getString("name");
                boolean nsfw = resultSet.getBoolean("nsfw");
                int opinion = resultSet.getInt("opinion");
                String seriesType = resultSet.getString("type");

                // Create a Series object and add to list
                seriesList.add(new Series(id, name, seriesType, nsfw, opinion));
            }

            if (seriesList.isEmpty()) {
                System.out.println("No series found with the specified filters.");
                return;
            }

            // Use polymorphism to display details
            for (Media series : seriesList) {
                series.displayDetails();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
