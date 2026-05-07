import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class Task4 {

    private DataSource dataSource;

    public List<ReportEntry> fetchMonthlyReport(String accountId,
                                                int month,
                                                int year)
                                                throws SQLException {

        // Connection conn = dataSource.getConnection();

    	// FIX: Commented above line & use try-with-resources to ensure Connection is closed.
        try (Connection conn = dataSource.getConnection();

             // PreparedStatement ps = conn.prepareStatement(
             //     "SELECT * FROM report_entries " +
             //     "WHERE account_id = ? AND MONTH(entry_date) = ? " +
             //     "AND YEAR(entry_date) = ?"
             // );

        		// FIX:Commented above logic & include PreparedStatement inside try-with-resources
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM report_entries " +
                     "WHERE account_id = ? AND MONTH(entry_date) = ? " +
                     "AND YEAR(entry_date) = ?"
             )) {

            ps.setString(1, accountId);
            ps.setInt(2, month);
            ps.setInt(3, year);

            // ResultSet rs = ps.executeQuery();

         // FIX: Commented above line and include ResultSet in try-with-resources
            try (ResultSet rs = ps.executeQuery()) {

                List<ReportEntry> entries = new ArrayList<>();

                while (rs.next()) {

                    entries.add(mapRow(rs));
                }

                return entries;
            }
        }
    }

    private ReportEntry mapRow(ResultSet rs) {

        // Existing mapping logic
        return null;
    }
}