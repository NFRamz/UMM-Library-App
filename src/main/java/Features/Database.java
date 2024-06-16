package Features;

import books.Book;
import exception.custom.IllegalAdminAccess;

import java.sql.*;

public class Database {

    private static final String user_database = "jdbc:sqlite:src/main/java/.database/User_database";
    private static final String book_database = "jdbc:sqlite:src/main/java/.database/Book";

    private static Connection connect(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }
    public static void add_student(String nim, String pic, String name, String faculty, String major, String email) {

        String sql = "INSERT INTO mahasiswa_credentials (nim, pic, name,faculty, major) VALUES (?, ?)";

        try (Connection conn = connect(user_database);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nim);
            pstmt.setString(2, pic);
            pstmt.setString(3, name);
            pstmt.setString(4, faculty);
            pstmt.setString(5, major);
            pstmt.setString(6, email);

            pstmt.executeUpdate();
            System.out.println("data mahasiswa berhasil ditambahkan ke database.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data mahasiswa ke database: " + e.getMessage());
        }
    }

    public static boolean student_login_checker(String nim, String pic) throws IllegalAdminAccess {

        String sql = "SELECT * FROM mahasiswa_credentials WHERE NIM = ? AND PIC = ?";

        try (Connection conn = connect(user_database);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nim);
            pstmt.setString(2, pic);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // true jika ada baris hasil, false jika tidak

        } catch (SQLException e) {
            throw new IllegalAdminAccess("Err: SLS_method_database");
        }
    }

    public static String getEmailbyNIM(String nim) throws SQLException {
        String sql = "SELECT * FROM mahasiswa_credentials WHERE nim = ?";
        String email = null;

        try (Connection conn = connect(user_database);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nim);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving email for NIM " + nim + ": " + e.getMessage());
        }

        return email;
    }


    //Admin database Settings
    public static void add_admin(String username, String password) {

        String sql = "INSERT INTO admin_credentials (username, password) VALUES (?, ?)";

        try (Connection conn = connect(user_database);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            pstmt.executeUpdate();
            System.out.println("Data admin berhasil ditambahkan ke database.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data admin ke database: " + e.getMessage());
        }
    }

    public static boolean admin_login_checker(String username, String password)  {

        String sql = "SELECT * FROM admin_credentials WHERE username = ? AND password = ?";

        try (Connection conn = connect(user_database);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // true jika ada baris hasil, false jika tidak

        } catch (SQLException e) {
            System.out.println("Gagal memeriksa kredensial admin: " + e.getMessage());
            return false;
        }
    }

    //Book Database
    public static void add_book(String id, String title, String author, String category, int stock) {

        String sql = "INSERT INTO book_data (id, title, author, category, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection database = connect(book_database);
             PreparedStatement ps_add = database.prepareStatement(sql)) {

            ps_add.setString(1, id);
            ps_add.setString(2, title);
            ps_add.setString(3, author);
            ps_add.setString(4, category);
            ps_add.setInt(5, stock);

            ps_add.executeUpdate();
            System.out.println("data buku berhasil ditambahkan ke database.");

        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data mahasiswa ke database: " + e.getMessage());
        }
    }
    public static void book_display() {
        String sql = "SELECT * FROM book_data";

        try (Connection conn = connect(book_database);
             Statement stmt = conn.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            // Iterasi hasil query dan tampilkan informasi buku
            while (rs.next()) {
                String id       = rs.getString("id");
                String title    = rs.getString("title");
                String author   = rs.getString("author");
                String category = rs.getString("category");
                int stock       = rs.getInt("stock");

                Book.arr_bookList.add(new Book(id, title, author, category, stock));
            }

        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data buku: " + e.getMessage());
        }
    }

}
