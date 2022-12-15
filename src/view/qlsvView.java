package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.QLSVModel;
import model.SinhVien;
import model.Tinh;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.QLSVController;
import database.JDBCUtil;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class qlsvView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public QLSVModel model;
	public JTextField textField;
	public JTable table;
	public JTextField masv;
	public JTextField hoten;
	public JTextField ngaysinh;
	public JTextField diem1;
	public JTextField diem2;
	public JTextField diem3;
	public ButtonGroup btn_gioitinh;
	public JComboBox<String> quequan1;
	private JRadioButton radioNam;
	private JRadioButton radioNu;
	public JComboBox<String> quequan;
	public JComboBox gender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					qlsvView frame = new qlsvView();
					Tinh.getDsTinh();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public qlsvView() {
		this.model = new QLSVModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 728);
		setLocationRelativeTo(null);
		
		Action ac = new QLSVController(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu MenuBar = new JMenu("File");
		MenuBar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(MenuBar);
		
		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		MenuBar.add(menuOpen);
		
		JMenuItem menuClose = new JMenuItem("Close");
		menuClose.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		MenuBar.add(menuClose);
		
		JSeparator separator_1 = new JSeparator();
		MenuBar.add(separator_1);
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(ac);
		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		MenuBar.add(menuExit);
		
		JMenu About = new JMenu("About");
		About.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(About);
		
		Box verticalBox = Box.createVerticalBox();
		menuBar.add(verticalBox);
		verticalBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quê Quán:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(362, 90, 82, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Sinh Viên:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(352, 10, 112, 22);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(478, 10, 231, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		quequan = new JComboBox<String>();
		quequan.setFont(new Font("Tahoma", Font.PLAIN, 10));
		quequan.addItem("Chọn Quê Quán");
		ArrayList<Tinh> listTinh = Tinh.getDsTinh();
		for(Tinh tinh : listTinh ) {
			quequan.addItem(tinh.getTenTinh());
		}
		quequan.setBounds(444, 89, 129, 26);
		contentPane.add(quequan);
		
		JButton btnNewButton = new JButton("T\u00ECm");
		btnNewButton.addActionListener(ac);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(736, 10, 99, 42);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 77, 825, 2);
		contentPane.add(separator);
		
		JLabel lblDanhSchSinh = new JLabel("Danh Sách Sinh Viên:");
		lblDanhSchSinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDanhSchSinh.setBounds(10, 89, 176, 22);
		contentPane.add(lblDanhSchSinh);
		
		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Sinh Vi\u00EAn", "H\u1ECD V\u00E0 T\u00EAn", "Qu\u00EA Qu\u00E1n", "Gi\u1EDBi T\u00EDnh", "Ng\u00E0y Sinh", "\u0110i\u1EC3m 1", "\u0110i\u1EC3m 2", "\u0110i\u1EC3m 3"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 135, 825, 159);
		contentPane.add(scrollPane);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 304, 825, 2);
		contentPane.add(separator_2);
		
		JLabel lblNewLabel_2 = new JLabel("Thông Tin Sinh Viên:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 319, 170, 18);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mã Sinh Viên:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 372, 94, 19);
		contentPane.add(lblNewLabel_3);
		
		masv = new JTextField();
		masv.setBounds(125, 371, 153, 26);
		contentPane.add(masv);
		masv.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Họ Và Tên:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(8, 430, 76, 19);
		contentPane.add(lblNewLabel_4);
		
		hoten = new JTextField();
		hoten.setBounds(125, 430, 153, 26);
		contentPane.add(hoten);
		hoten.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Quê Quán:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 497, 74, 19);
		contentPane.add(lblNewLabel_5);
		
		quequan1 = new JComboBox<String>();
		quequan1.addItem("Chọn Quê Quán");
		quequan1.setBounds(125, 497, 153, 24);
		for(Tinh tinh : listTinh ) {
			quequan1.addItem(tinh.getTenTinh());
		}
		contentPane.add(quequan1);
		
		JLabel lblNewLabel_6 = new JLabel("Ngày Sinh:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(10, 561, 74, 19);
		contentPane.add(lblNewLabel_6);
		
		ngaysinh = new JTextField();
		ngaysinh.setBounds(125, 561, 153, 26);
		contentPane.add(ngaysinh);
		ngaysinh.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Giới Tính:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_7.setBounds(352, 372, 65, 19);
		contentPane.add(lblNewLabel_7);
		
		radioNam = new JRadioButton("Nam");
		radioNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioNam.setBounds(484, 368, 57, 27);
		contentPane.add(radioNam);
		
		radioNu = new JRadioButton("Nữ");
		radioNu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioNu.setBounds(567, 368, 45, 27);
		contentPane.add(radioNu);

		btn_gioitinh = new ButtonGroup();
		btn_gioitinh.add(radioNam);
		btn_gioitinh.add(radioNu);
		
		JLabel lblNewLabel_8 = new JLabel("Điểm Môn 1:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(352, 430, 86, 19);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_8_1 = new JLabel("Điểm Môn 2:");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1.setBounds(352, 498, 86, 19);
		contentPane.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_8_2 = new JLabel("Điểm Môn 3:");
		lblNewLabel_8_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_2.setBounds(352, 561, 86, 19);
		contentPane.add(lblNewLabel_8_2);
		
		diem1 = new JTextField();
		diem1.setColumns(10);
		diem1.setBounds(484, 430, 153, 26);
		contentPane.add(diem1);
		
		diem2 = new JTextField();
		diem2.setColumns(10);
		diem2.setBounds(484, 497, 153, 24);
		contentPane.add(diem2);
		
		diem3 = new JTextField();
		diem3.setColumns(10);
		diem3.setBounds(484, 561, 153, 26);
		contentPane.add(diem3);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 597, 825, 2);
		contentPane.add(separator_3);
		
		JButton btn_them = new JButton("Thêm");
		btn_them.addActionListener(ac);
		btn_them.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_them.setBounds(10, 612, 94, 27);
		contentPane.add(btn_them);
		
		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.addActionListener(ac);
		btn_xoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_xoa.setBounds(125, 612, 86, 27);
		contentPane.add(btn_xoa);
		
		JButton btn_capnhat = new JButton("Cập Nhật");
		btn_capnhat.addActionListener(ac);
		btn_capnhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_capnhat.setBounds(243, 612, 95, 27);
		contentPane.add(btn_capnhat);
		
		JButton btn_luu = new JButton("Lưu");
		btn_luu.addActionListener(ac);
		btn_luu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_luu.setBounds(379, 612, 86, 27);
		contentPane.add(btn_luu);
		
		JButton btn_huy = new JButton("Clear");
		btn_huy.addActionListener(ac);
		btn_huy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_huy.setBounds(491, 612, 99, 27);
		contentPane.add(btn_huy);
		
		JButton btnNewButton_2 = new JButton("Tất cả sinh viên");
		btnNewButton_2.addActionListener(ac);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(199, 90, 139, 26);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Lọc");
		btnNewButton_1.addActionListener(ac);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(755, 87, 55, 27);
		contentPane.add(btnNewButton_1);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(350, 80, 2, 40);
		contentPane.add(separator_4);
		
		JSeparator separator_4_1 = new JSeparator();
		separator_4_1.setBounds(10, 121, 825, 9);
		contentPane.add(separator_4_1);
		
		JLabel lblNewLabel_9 = new JLabel("Giới Tính:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(583, 90, 65, 19);
		contentPane.add(lblNewLabel_9);
		
		gender = new JComboBox();
		gender.addItem("Nam");
		gender.addItem("Nữ");
		gender.setBounds(657, 92, 65, 23);
		contentPane.add(gender);
	
		
		
		this.hienThiDssvVaoTableView();		
	}

	public void xoaForm() {
		this.masv.setText("");
		this.hoten.setText("");
		this.ngaysinh.setText("");
		this.diem1.setText("");
		this.diem2.setText("");
		this.diem3.setText("");
		this.quequan1.setSelectedItem("Chọn Quê Quán");
	}

	public void insertSinhVienView(SinhVien sv) {
		// neu sv khong ton tai thi them sv
		if(!JDBCUtil.ktSinhVienCoTonTaiDB(sv.getMasv()+"")) {
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

		    DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		    model.addRow(row);
		}
	}
	
	public void updateSinhVienView(SinhVien sv) {
		int countRow = this.table.getModel().getRowCount();
		for(int i = 0; i < countRow; i++) {
			String masvTable = this.table.getModel().getValueAt(i, 0)+"";
			if(masvTable.equals(sv.getMasv()+"")) {

				SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
				String ngaysinh= DateFor.format(sv.getNgaySinh());
				this.table.setValueAt(sv.getTensv(), i, 1);
				this.table.setValueAt(sv.getQueQuan().getTenTinh(), i, 2);
				this.table.setValueAt(sv.isGioiTinh() ? "Nam":"Nữ", i, 3);
				this.table.setValueAt(ngaysinh, i, 4);
				this.table.setValueAt(sv.getDiem1(), i, 5);
				this.table.setValueAt(sv.getDiem2(), i, 6);
				this.table.setValueAt(sv.getDiem3(), i, 7);
			}
		}
	}
	
	public void hienThiThongTinVaoTextFieldView(JTable table) {
		int isRow = table.getSelectedRow();
		if(isRow != -1) {
			String masv = table.getValueAt(isRow, 0).toString();
			String hoten = table.getValueAt(isRow, 1).toString();
			String quequan = table.getValueAt(isRow, 2).toString();
			String gioitinh = table.getValueAt(isRow, 3).toString();
			String ngaysinh = table.getValueAt(isRow, 4).toString();
			String diem1 = table.getValueAt(isRow, 5).toString();
			String diem2 = table.getValueAt(isRow, 6).toString();
			String diem3 = table.getValueAt(isRow, 7).toString();
			
			this.masv.setText(masv);
			this.masv.setEditable(false);
			this.hoten.setText(hoten);
			this.quequan1.setSelectedItem(quequan);
			
			if(gioitinh == "Nam") {
				this.radioNam.setSelected(true);
			} else if(gioitinh == "Nữ") {
				this.radioNu.setSelected(true);
			}
			
			this.ngaysinh.setText(ngaysinh);
			this.diem1.setText(diem1);
			this.diem2.setText(diem2);
			this.diem3.setText(diem3);
		}
	}
	
	public void hienThiDssvVaoTableView() {
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		list = JDBCUtil.getAllSinhVienDB();
		for(SinhVien sv : list) {
			String born = null;
			if(sv.getNgaySinh() != null) {
				SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
				born = DateFor.format(sv.getNgaySinh());
			}
		    Object[] row = { sv.getMasv()+"", 
		    		sv.getTensv(), 
		    		sv.getQueQuan().getTenTinh(), 
		    		sv.isGioiTinh() ? "Nam" : "Nữ",
		    		born, 
		    		sv.getDiem1(), 
		    		sv.getDiem2(), 
		    		sv.getDiem3()};

		    DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		    model.addRow(row);
		}
	}
	

	public void hienThiDssvFilterVaoTableView() {
		String tinh = this.quequan.getSelectedItem().toString();
		String gioitinh = this.gender.getSelectedItem().toString();
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		list = JDBCUtil.filter(tinh, gioitinh);
		if(list.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
			this.quequan.setSelectedItem("Chọn Quê Quán");
			return;
		}
		for(SinhVien sv : list) {
			String born = null;
			if(sv.getNgaySinh() != null) {
				SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
				born = DateFor.format(sv.getNgaySinh());
			}
		    Object[] row = { sv.getMasv()+"", 
		    		sv.getTensv(), 
		    		sv.getQueQuan().getTenTinh(), 
		    		sv.isGioiTinh() ? "Nam" : "Nữ",
		    		born, 
		    		sv.getDiem1(), 
		    		sv.getDiem2(), 
		    		sv.getDiem3()};

		    DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		    model.addRow(row);
		}
		this.quequan.setSelectedItem("Chọn Quê Quán");
	}
	
	public void removeRowTableView(int isRow) {
		System.out.println("remove row: "+ isRow);
	    DefaultTableModel model = (DefaultTableModel) this.table.getModel();
	    model.removeRow(isRow);
	}
	
	public void deleteAllTable() {
		int countRow = this.table.getRowCount();
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		for(int i = (countRow-1); i >= 0; i--) {
		    model.removeRow(i);
		}
	}
}
