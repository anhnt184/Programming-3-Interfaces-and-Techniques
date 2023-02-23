import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieAnalytics2 {
    private List<Movie> movies;

    public MovieAnalytics2() {
        movies = List.of();
    }

    public void populateWithData(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            movies = br.lines()
                    .map(line -> {
                        String[] fields = line.split(";");
                        String title = fields[0];
                        int releaseYear = Integer.parseInt(fields[1]);
                        int duration = Integer.parseInt(fields[2]);
                        String genre = fields[3];
                        double score = Double.parseDouble(fields[4]);
                        String director = fields[5];
                        return new Movie(title, releaseYear, duration, genre, score, director);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printCountByDirector(int n) {
        Map<String, Long> countByDirector = movies.stream()
                .collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));

        countByDirector.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .forEach(entry -> System.out.printf("%s: %d movies%n", entry.getKey(), entry.getValue()));
        // System.out.println();
    }

    public void printAverageDurationByGenre() {
        Map<String, Double> averageDurationByGenre = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingInt(Movie::getDuration)));

        averageDurationByGenre.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue()
                .thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue()));
        // System.out.println();
    }

    public void printAverageScoreByGenre() {
        Map<String, Double> averageScoreByGenre = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre, Collectors.averagingDouble(Movie::getScore)));

        averageScoreByGenre.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry -> System.out.printf("%s: %.2f%n", entry.getKey(), entry.getValue()));
        // System.out.println();
    }
}
