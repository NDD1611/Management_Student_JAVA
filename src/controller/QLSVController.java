package controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.JDBCUtil;
import model.SinhVien;
import model.Tinh;
import view.qlsvView;

public class QLSVController implements Action{
	public qlsvView view;
	
	public QLSVController(qlsvView view) {
		this.view = view;
	}
	
	public SinhVien KhoiTaoSinhVien() {
		try {
			int masv = Integer.valueOf(this.view.masv.getText());
			String tensv = this.view.hoten.getText();
			String quequan = this.view.quequan1.getSelectedItem().toString();
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date ngaysinh = df.parse(this.view.ngaysinh.getText());
			
			String chongioitinh = "";
	        for (Enumeration<AbstractButton> buttons = this.view.btn_gioitinh.getElements(); buttons.hasMoreElements();) {
	            AbstractButton button = buttons.nextElement();

	            if (button.isSelected()) {
	            	chongioitinh = button.getActionCommand();
	            }
	        }
			boolean gioitinh = true;
			if(chongioitinh == "Nữ") {
				gioitinh = false;
			}
			float diem1 = Float.valueOf(this.view.diem1.getText());
			float diem2 = Float.valueOf(this.view.diem2.getText());
			float diem3 = Float.valueOf(this.view.diem3.getText());
			Tinh tinh = new Tinh(1, quequan);
			SinhVien sv = new SinhVien(masv, tensv, tinh, ngaysinh, gioitinh, diem1, diem2, diem3);
			return sv;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new SinhVien();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		
		switch (cm) {
		case "Clear":
			this.view.xoaForm();
			this.view.masv.setEditable(true);
			break;
			
		case "Exit":
			System.exit(0);
			break;
			
		case "Lưu":
			try {
				SinhVien sv = this.KhoiTaoSinhVien();
				if(!JDBCUtil.ktSinhVienCoTonTaiDB(sv.getMasv()+"")) {//neu sv khong ton tai thi insert
					// insert giao dien
					this.view.insertSinhVienView(sv);
					// insert database
					JDBCUtil.insertSinhVienDB(sv);
				} else {
					JOptionPane.showMessageDialog(view, "Mã Sinh Viên Này Đã Tồn Tại. Vui Lòng Nhập Mã Sinh Viên Khác");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
			
		case "Thêm":
			this.view.hienThiThongTinVaoTextFieldView(this.view.table);
			break;
			
		case "Xóa":
			System.out.println("Xóa");
			int isRow = this.view.table.getSelectedRow();
			String masv = this.view.table.getValueAt(isRow, 0)+"";
			JDBCUtil.deleteSinhVienDB(masv);
			this.view.removeRowTableView(isRow);
			break;
			
		case "Cập Nhật":
			try {
				this.view.masv.setEditable(true);
				SinhVien sv = this.KhoiTaoSinhVien();
				//update giao dien
				this.view.updateSinhVienView(sv);	
				//update Database
				JDBCUtil.updateSinhVienDB(sv);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			break;
			
		case "Tìm":
			String masv1 = this.view.textField.getText();
			if(masv1.equals("")) {
				JOptionPane.showMessageDialog(view, "Bạn vui lòng nhập mã sinh viên");
				return;
			} else {
				try {
					SinhVien sv = JDBCUtil.timMotSinhVien(masv1);
					System.out.println(sv.toString()); // nếu thông tin sv sai thi sẽ tạo ra lỗi và bắt lỗi để thông báo là sinh viên không tồn tại
					this.view.deleteAllTable();
					SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
					String ngaysinh= DateFor.format(sv.getNgaySinh());
				    Object[] row = { sv.getMasv()+"", 
				    		sv.getTensv(), 
				    		sv.getQueQuan().getTenTinh(), 
				    		sv.isGioiTinh() ? "Nam" : "Nữ",
				    		ngaysinh, 
				    		sv.getDiem1(), 
				    		sv.getDiem2(), 
				    		sv.getDiem3()};

				    DefaultTableModel model = (DefaultTableModel) this.view.table.getModel();
				    model.addRow(row);
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(view, "Sinh viên không tồn tại");
				}
			}
			break;
			
		case "Tất cả sinh viên":
			this.view.deleteAllTable();
			this.view.hienThiDssvVaoTableView();			
			break;
		case "Lọc":
			this.view.deleteAllTable();
			this.view.hienThiDssvFilterVaoTableView();
			break;
		}
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
