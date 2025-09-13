package Main;

import java.util.*;
import DAO.StudentDao;
import model.Student;
public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            StudentDao dao = new StudentDao();

            while (true) {
                System.out.println("\n===== QUAN LY SINH VIEN =====");
                System.out.println("1. Them sinh vien");
                System.out.println("2. Sua sinh vien");
                System.out.println("3. Xoa sinh vien");
                System.out.println("4. Xem tat ca SV");
                System.out.println("5. Xem SV theo lop");
                System.out.println("6. Xem SV theo nganh");
                System.out.println("7. Xem SV theo diem giam dan");
                System.out.println("8. Xem SV sinh trong 1 thang");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                int chon = sc.nextInt(); sc.nextLine();

                switch (chon) {
                    case 1 -> {
            
                        System.out.print("Ma SV: ");
                        String ma = sc.nextLine().trim();

                        System.out.print("Ho ten: ");
                        String ten = sc.nextLine().trim();

                        System.out.print("Ngay sinh (yyyy-MM-dd): ");
                        String ns = sc.nextLine().trim();

                        System.out.print("Nganh (CNTT/KTPM): ");
                        String ng = sc.nextLine().trim().toUpperCase();

                        System.out.print("Diem TB: ");
                        String dtbStr = sc.nextLine().trim();

                        System.out.print("Lop: ");
                        String lop = sc.nextLine().trim();

                        // ===== VALIDATE =====
                        if (ma.isEmpty() || ten.isEmpty() || ns.isEmpty() || ng.isEmpty() || dtbStr.isEmpty() || lop.isEmpty()) {
                            System.out.println("⚠️ Không được bỏ trống bất kỳ trường nào!");
                            return;
                        }

                        double dtb;
                        try {
                            dtb = Double.parseDouble(dtbStr);
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ Điểm trung bình phải là số!");
                            return;
                        }

                        if (!ng.equals("CNTT") && !ng.equals("KTPM")) {
                            System.out.println("⚠️ Ngành không hợp lệ! Chỉ được CNTT hoặc KTPM.");
                            return;
                        }

                        if (dtb < 0.0 || dtb > 10.0) {
                            System.out.println("⚠️ Điểm trung bình phải nằm trong [0.0, 10.0]");
                            return;
                        }

                        if (!ma.matches("45510(5|9)\\d{4}")) {
                            System.out.println("⚠️ Mã sinh viên không hợp lệ!");
                            return;
                        }

                        dao.addStudent(new Student(ma, ten, ns, ng, dtb, lop));
                        System.out.println("==> Đã thêm!");
                    }

                    case 2 -> {
                        System.out.print("Nhap ma SV can sua: "); String ma = sc.nextLine();
                        System.out.print("Ho ten: "); String ten = sc.nextLine();
                        System.out.print("Ngay sinh (yyyy-MM-dd): "); String ns = sc.nextLine();
                        System.out.print("Nganh: "); String ng = sc.nextLine();
                        System.out.print("Diem TB: "); double dtb = sc.nextDouble(); sc.nextLine();
                        System.out.print("Lop: "); String lop = sc.nextLine();
                        dao.updateStudent(new Student(ma, ten, ns, ng, dtb, lop));
                        System.out.println("==> Da cap nhat!");
                    }
                    case 3 -> {
                        System.out.print("Nhap ma SV can xoa: "); String ma = sc.nextLine();
                        dao.deleteStudent(ma);
                        System.out.println("==> Da xoa!");
                    }
                    case 4 -> dao.getAllStudents().forEach(System.out::println);
                    case 5 -> {
                        System.out.print("Nhap lop: "); String lop = sc.nextLine();
                        dao.getByClass(lop).forEach(System.out::println);
                    }
                    case 6 -> {
                        System.out.print("Nhap nganh: "); String ng = sc.nextLine();
                        dao.getByNganh(ng).forEach(System.out::println);
                    }
                    case 7 -> dao.getSortedByDiem().forEach(System.out::println);
                    case 8 -> {
                        System.out.print("Nhap thang: "); int thang = sc.nextInt();
                        dao.getByMonth(thang).forEach(System.out::println);
                    }
                    case 0 -> System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
