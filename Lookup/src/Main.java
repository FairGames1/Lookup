import search.AnimeSearch;
import search.MangaSearch;
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

        // input Validation
        while (!mediaType.equals("anime") && !mediaType.equals("manga") &&
                !mediaType.equals("series") && !mediaType.equals("movies")) {
            System.out.print("Invalid input. Please enter 'anime', 'manga', 'series', or 'movies': ");
            mediaType = scanner.nextLine().trim().toLowerCase();
        }

        // check for nsfw
        boolean includeNSFW = getValidatedYesNo(scanner, "Include NSFW content? (y/n): ");

        // choose type
        System.out.print("Enter the type to filter by (leave blank for all): ");
        String type = scanner.nextLine().trim();

        // Seargiej
        switch (mediaType) {
            case "anime" -> new AnimeSearch().searchAnime(includeNSFW, type);
            case "manga" -> new MangaSearch().searchManga(includeNSFW, type);
            case "series" -> new SeriesSearch().searchSeries(includeNSFW, type);
            case "movies" -> new MovieSearch().searchMovies(includeNSFW, type);
        }
    }

    private static boolean getValidatedYesNo(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
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
