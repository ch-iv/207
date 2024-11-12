package us.jonathans.DataAccess;

import us.jonathans.Leaderboard;
import us.jonathans.UseCase.GetLeaderboard.GetLeaderboardRepositoryInterface;
import us.jonathans.UseCase.PostLeaderboard.PostLeaderboardRepositoryInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeaderboardRepository implements GetLeaderboardRepositoryInterface, PostLeaderboardRepositoryInterface {
    private File file = new File("database.txt");
    public LeaderboardRepository() throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write("Mina,30,100");
        writer.close();
    }

    @Override
    public Leaderboard getLeaderboard() {
        Leaderboard leaderboard = new Leaderboard();
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] result = data.split(",");
            leaderboard.add(result[0], Integer.parseInt(result[1]), Integer.parseInt(result[2]));
        }
        reader.close();
        return leaderboard;
    }

    @Override
    public void postLeaderboard(Leaderboard leaderboard) {
        // Richard
    }
}
