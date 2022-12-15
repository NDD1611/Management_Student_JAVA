package model;

import java.util.Date;

public class SinhVien {
	private int masv;
	private String tensv;
	private Tinh queQuan;
	private Date ngaySinh;
	private boolean gioiTinh;
	private float diem1, diem2, diem3;
	
	public SinhVien () {
		
	}

	public SinhVien(int masv, String tensv, Tinh queQuan, Date ngaySinh, boolean gioiTinh, float diem1, float diem2,
			float diem3) {
		this.masv = masv;
		this.tensv = tensv;
		this.queQuan = queQuan;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diem1 = diem1;
		this.diem2 = diem2;
		this.diem3 = diem3;
	}
	public int getMasv() {
		return masv;
	}

	public void setMasv(int masv) {
		this.masv = masv;
	}

	public String getTensv() {
		return tensv;
	}

	public void setTensv(String tensv) {
		this.tensv = tensv;
	}

	public Tinh getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(Tinh queQuan) {
		this.queQuan = queQuan;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public float getDiem1() {
		return diem1;
	}

	public void setDiem1(float diem1) {
		this.diem1 = diem1;
	}

	public float getDiem2() {
		return diem2;
	}

	public void setDiem2(float diem2) {
		this.diem2 = diem2;
	}

	public float getDiem3() {
		return diem3;
	}

	public void setDiem3(float diem3) {
		this.diem3 = diem3;
	}
	
	@Override
	public String toString() {
		return "SinhVien [masv=" + masv + ", tensv=" + tensv + ", queQuan=" + queQuan.toString() + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", diem1=" + diem1 + ", diem2=" + diem2 + ", diem3=" + diem3 + "]";
	}

}
