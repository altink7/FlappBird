package ranking;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RankingTable extends JFrame {
    public RankingTable() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Ranking Table");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a table model with columns: Rank, Player Name, Score
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Rank");
        tableModel.addColumn("Player Name");
        tableModel.addColumn("Score");

        try {
            // Read ranking data from a CSV file (e.g., ranking.csv)
            BufferedReader reader = new BufferedReader(new FileReader("ranking.csv"));
            String line;
            int rank = 1;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(new Object[]{rank, data[0], data[1]});
                rank++;
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create a JTable with the table model
        JTable rankingTable = new JTable(tableModel);
        rankingTable.setAutoCreateRowSorter(true); // Enable sorting

        // Add the table to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        add(scrollPane);

        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RankingTable::new);
    }
}
