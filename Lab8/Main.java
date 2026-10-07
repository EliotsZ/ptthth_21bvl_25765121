package Lab8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:sqlite:nhanvien.db";

        try {
            // Kết nối đến database SQLite
            Connection conn = DriverManager.getConnection(url);

            System.out.println("Ket noi SQLite thanh cong!");

            // Tạo Statement
            Statement stmt = conn.createStatement();

            System.out.println("Tao Statement thanh cong!");

            // Tạo bảng NhanVien
            String sql_create_table = """
                    CREATE TABLE IF NOT EXISTS NhanVien (
                        id INTEGER PRIMARY KEY,
                        ten TEXT NOT NULL,
                        chuc_vu TEXT
                    )
                    """;

            stmt.executeUpdate(sql_create_table);

            System.out.println("Tao bang NhanVien thanh cong!");

            // Thêm nhân viên 1
            // String sql_insert1 = """
            //         INSERT INTO NhanVien (id, ten, chuc_vu)
            //         VALUES (1, 'Nguyen Van An', 'Nhan vien')
            //         """;

            // stmt.executeUpdate(sql_insert1);

            // // Thêm nhân viên 2
            // String sql_insert2 = """
            //         INSERT INTO NhanVien (id, ten, chuc_vu)
            //         VALUES (2, 'Tran Van Binh', 'Ke toan')
            //         """;

            // stmt.executeUpdate(sql_insert2);

            // // Thêm nhân viên 3
            // String sql_insert3 = """
            //         INSERT INTO NhanVien (id, ten, chuc_vu)
            //         VALUES (3, 'Le Van Cuong', 'Quan ly')
            //         """;

            // stmt.executeUpdate(sql_insert3);

            System.out.println("Them 3 nhan vien thanh cong!");

            // Truy vấn tất cả nhân viên
String sql_select = "SELECT id, ten, chuc_vu FROM NhanVien";

ResultSet rs = stmt.executeQuery(sql_select);

// Hiển thị dữ liệu
while (rs.next()) {
    int id = rs.getInt("id");
    String ten = rs.getString("ten");
    String chuc_vu = rs.getString("chuc_vu");

    System.out.println("ID: " + id);
    System.out.println("Ten: " + ten);
    System.out.println("Chuc vu: " + chuc_vu);
    System.out.println("--------------------");
}

            // Đóng tài nguyên
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
}