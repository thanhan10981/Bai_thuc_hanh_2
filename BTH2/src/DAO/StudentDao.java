package DAO;
import DB.connect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;
public class StudentDao {
	private Connection con = null;
    private PreparedStatement ps = null;

    public void addStudent(Student s) throws Exception {
    	if (existsStudent(s.getMaSV())) {
            System.out.println("⚠️ Mã SV " + s.getMaSV() + " đã tồn tại, không thể thêm!");
            return;
        }
    	con = connect.getConnection();
        String sql = "INSERT INTO students VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, s.getMaSV());
	     ps.setNString(2, s.getHoTen());
	     ps.setString(3, s.getNgaySinh());
	     ps.setString(4, s.getNganh());
	     ps.setDouble(5, s.getDiemTB());
	     ps.setString(6, s.getLop());
        ps.executeUpdate();
    }
    public boolean existsStudent(String maSV) throws Exception {
        con = connect.getConnection();
        String sql = "SELECT COUNT(*) FROM Students WHERE maSV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSV);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }

    public void updateStudent(Student s) throws Exception {
        con = connect.getConnection();
        String sql = "UPDATE students SET hoTen=?, ngaySinh=?, nganh=?, diemTB=?, lop=? WHERE maSV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, s.getHoTen());
        ps.setString(2, s.getNgaySinh());
        ps.setString(3, s.getNganh());
        ps.setDouble(4, s.getDiemTB());
        ps.setString(5, s.getLop());
        ps.setString(6, s.getMaSV());
        ps.executeUpdate();
    }


    public void deleteStudent(String maSV) throws Exception {
        String sql = "DELETE FROM students WHERE maSV=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maSV);
        ps.executeUpdate();
    }

    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");
        while (rs.next()) {
            list.add(new Student(
                    rs.getString("maSV"),
                    rs.getString("hoTen"),
                    rs.getString("ngaySinh"),
                    rs.getString("nganh"),
                    rs.getDouble("diemTB"),
                    rs.getString("lop")));
        }
        return list;
    }

    public List<Student> getByClass(String lop) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE lop=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, lop);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Student(
                    rs.getString("maSV"),
                    rs.getString("hoTen"),
                    rs.getString("ngaySinh"),
                    rs.getString("nganh"),
                    rs.getDouble("diemTB"),
                    rs.getString("lop")));
        }
        return list;
    }

    public List<Student> getByNganh(String nganh) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE nganh=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nganh);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Student(
                    rs.getString("maSV"),
                    rs.getString("hoTen"),
                    rs.getString("ngaySinh"),
                    rs.getString("nganh"),
                    rs.getDouble("diemTB"),
                    rs.getString("lop")));
        }
        return list;
    }

    public List<Student> getByMonth(int month) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE MONTH(ngaySinh)=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, month);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Student(
                    rs.getString("maSV"),
                    rs.getString("hoTen"),
                    rs.getString("ngaySinh"),
                    rs.getString("nganh"),
                    rs.getDouble("diemTB"),
                    rs.getString("lop")));
        }
        return list;
    }

    public List<Student> getSortedByDiem() throws Exception {
        List<Student> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students ORDER BY diemTB DESC");
        while (rs.next()) {
            list.add(new Student(
                    rs.getString("maSV"),
                    rs.getString("hoTen"),
                    rs.getString("ngaySinh"),
                    rs.getString("nganh"),
                    rs.getDouble("diemTB"),
                    rs.getString("lop")));
        }
        return list;
    }


}

