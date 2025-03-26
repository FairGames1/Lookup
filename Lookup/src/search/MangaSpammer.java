package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class MangaSpammer {

    private static final String[] SAMPLE_NAMES = {
            "Attack on Titan", "Naruto", "One Piece", "Berserk", "Death Note",
            "Bleach", "Fullmetal Alchemist", "Tokyo Ghoul", "My Hero Academia", "Demon Slayer"
    };

    private static final String[] TYPES = { "Shonen", "Seinen", "Shojo", "Josei" };

    public void spamManga(int amount) {
        long startTime = System.currentTimeMillis();
        final int BATCH_SIZE = 50000; //< tutaj można zmienić liczbę na dowolną, zależne od podzespołów, ustawień etc.
        Random random = new Random();

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO manga (name, type, nsfw, opinion) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 1; i <= amount; i++) {
                String name = SAMPLE_NAMES[random.nextInt(SAMPLE_NAMES.length)] + " #" + i;
                String type = TYPES[random.nextInt(TYPES.length)];
                boolean nsfw = random.nextBoolean();
                int opinion = random.nextInt(11) - 5;

                statement.setString(1, name);
                statement.setString(2, type);
                statement.setBoolean(3, nsfw);
                statement.setInt(4, opinion);

                statement.addBatch();

                if (i % BATCH_SIZE == 0) {
                    statement.executeBatch();
                    connection.commit();
                    System.out.println("Inserted: " + i + " rows so far...");
                }
            }

            statement.executeBatch();
            connection.commit();
            long endTime = System.currentTimeMillis();
            System.out.println("Done in " + (endTime - startTime) / 1000.0 + " seconds.");
            System.out.println("Finished inserting " + amount + " manga entries.");

        } catch (Exception e) {
            System.err.println("Error while spamming manga: " + e.getMessage());
        }
    }
}
