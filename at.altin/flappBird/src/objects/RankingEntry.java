package objects;

public final class RankingEntry {
    private final String playerName;
    private final int score;

    public RankingEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
