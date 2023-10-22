package ranking;

import objects.RankingEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class RankingTable extends JFrame {
    public RankingTable() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Ranking Table");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Rank");
        tableModel.addColumn("Player Name");
        tableModel.addColumn("Score");

        java.util.List<RankingEntry> rankingEntries = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("ranking.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String playerName = data[0];
                    int score = Integer.parseInt(data[1]);
                    rankingEntries.add(new RankingEntry(playerName, score));
                }
            }
            reader.close();

            rankingEntries.sort(Comparator.comparingInt(RankingEntry::getScore).reversed());

            for (int i = 0; i < rankingEntries.size(); i++) {
                RankingEntry entry = rankingEntries.get(i);
                tableModel.addRow(new Object[]{i + 1, entry.getPlayerName(), entry.getScore()});
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JTable rankingTable = new JTable(tableModel);
        rankingTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(rankingTable);
        add(scrollPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RankingTable::new);
    }
}
