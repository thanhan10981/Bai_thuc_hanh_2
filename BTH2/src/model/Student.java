package model;

public class Student {
    private String maSV;
    private String hoTen;
    private String ngaySinh; // yyyy-MM-dd
    private String nganh;
    private double diemTB;
    private String lop;

   

    public Student(String ma, String ten, String ns, String ng, double dtb, String lop) {
    	  this.maSV = ma;
          this.hoTen = ten;
          this.ngaySinh = ns;
          this.nganh = ng;
          this.diemTB = dtb;
          this.lop = lop;
	}

    // Getter Setter
    public String getMaSV() { return maSV; }
    public String getHoTen() { return hoTen; }
    public String getNgaySinh() { return ngaySinh; }
    public String getNganh() { return nganh; }
    public double getDiemTB() { return diemTB; }
    public String getLop() { return lop; }

    @Override
    public String toString() {
        return maSV + " | " + hoTen + " | " + ngaySinh + " | " + nganh + " | " + diemTB + " | " + lop;
    }
}
