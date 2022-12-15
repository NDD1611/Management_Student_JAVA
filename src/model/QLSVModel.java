package model;

import java.util.ArrayList;

public class QLSVModel {
	private ArrayList<SinhVien> dssv;
	public QLSVModel() {
		this.dssv = new ArrayList<SinhVien>();
	}
	public QLSVModel(ArrayList<SinhVien> dssv) {
		this.dssv = dssv;
	}
	public ArrayList<SinhVien> getDssv() {
		return dssv;
	}
	public void setDssv(ArrayList<SinhVien> dssv) {
		this.dssv = dssv;
	}
	
	public void insert(SinhVien sv) {
		this.dssv.add(sv);
	}
	
	public void delete(SinhVien sv) {
		this.dssv.remove(sv);
	}

	public void edit(SinhVien sv) {
		this.dssv.add(sv);
	}

	public void update(SinhVien svCu, SinhVien svMoi) {
		this.dssv.remove(svCu);
		this.dssv.add(svMoi);
	}
	@Override
	public String toString() {
		return "QLSVModel [dssv=" + dssv + "]";
	}
	
}
