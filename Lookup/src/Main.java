import search.AnimeSearch;
import search.MangaSearch;
import search.MangaSpammer;
import search.SeriesSearch;
import search.MovieSearch;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Media Search Application!");
        Scanner scanner = new Scanner(System.in);

        // Choose media type
        System.out.print("What would you like to search for? (anime/manga/series/movies): ");
        String mediaType = scanner.nextLine().trim().toLowerCase();

        // Input Validation
        while (!mediaType.equals("anime") && !mediaType.equals("manga") &&
                !mediaType.equals("series") && !mediaType.equals("movies")) {
            System.out.print("Invalid input. Please enter 'anime', 'manga', 'series', or 'movies': ");
            mediaType = scanner.nextLine().trim().toLowerCase();
        }

        // Optional spam for manga
        if (mediaType.equals("manga")) {
            System.out.print("Would you like to auto-fill the manga database with fake data? (y/n): ");
            if (getValidatedYesNo(scanner, "")) {
                System.out.print("How many entries do you want to insert?: ");
                String amountInput = scanner.nextLine().trim();
                int amount = 0;

                try {
                    amount = Integer.parseInt(amountInput);
                    if (amount > 0) {
                        new MangaSpammer().spamManga(amount);
                    } else {
                        System.out.println("Amount must be greater than zero. Skipping spam.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Skipping spam.");
                }
            }
        }

        // check for nsfw
        boolean includeNSFW = getValidatedYesNo(scanner, "Include NSFW content? (y/n): ");

        // choose type
        System.out.print("Enter the type to filter by (leave blank for all): ");
        String type = scanner.nextLine().trim();

        // limit
        System.out.print("How many results would you like to see? (leave blank for all): ");
        String limitInput = scanner.nextLine().trim();
        Integer limit = null;
        if (!limitInput.isEmpty()) {
            try {
                limit = Integer.parseInt(limitInput);
                if (limit <= 0) {
                    System.out.println("Invalid number. Showing all results instead.");
                    limit = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Showing all results instead.");
            }
        }

        // Search
        switch (mediaType) {
            case "anime" -> new AnimeSearch().searchAnime(includeNSFW, type); // You can extend these later
            case "manga" -> new MangaSearch().searchManga(includeNSFW, type, limit);
            case "series" -> new SeriesSearch().searchSeries(includeNSFW, type);
            case "movies" -> new MovieSearch().searchMovies(includeNSFW, type);
        }
    }

    private static boolean getValidatedYesNo(Scanner scanner, String prompt) {
        while (true) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
    }
}
