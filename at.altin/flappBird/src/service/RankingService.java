package service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RankingService {
    private static final String RANKING_FILE = "ranking.csv";


    public static void saveRanking(String name, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RANKING_FILE, true))) {
            writer.write(name + "," + score);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
