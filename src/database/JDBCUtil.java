package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.DatabaseMetaData;

import model.SinhVien;
import model.Tinh;

public class JDBCUtil {
	public static  Connection getConnection() {
		Connection c = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			String url = "jdbc:mySQL://localhost:3306/qlsv";
			String username = "root";
			String password = "";
			
			c = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if( c != null) {
				c.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printInfo(Connection c) {
			try {
				if(c != null) {
					DatabaseMetaData mt = (DatabaseMetaData) c.getMetaData();
					System.out.println(mt.getDatabaseProductName());
					System.out.println(mt.getDatabaseProductVersion());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void createTable(Connection c) {
		try {
			Statement sttm = c.createStatement();
			String sql = "CREATE TABLE Infomation (masv integer not null, ten varchar(255), "
					+ "quequan varchar(255), gioitinh boolean, ngaysinh date, diem1 float, diem2 float,  diem3 float)";
			sttm.executeUpdate(sql);
			System.out.println("create table successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static ArrayList<SinhVien> getAllSinhVienDB() {
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		Connection c = JDBCUtil.getConnection();
		try {
			String sql = "select * from infomation";
			PreparedStatement sta = c.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();      
	         while(rs.next()){
	             //Display values
	        	 int masv = rs.getInt("masv");
	        	 String ten = rs.getString("ten");
	        	 Tinh tinh = new Tinh(1, rs.getString("quequan"));
	        	 boolean gioitinh = rs.getBoolean("gioitinh");
	        	 Date ngaysinh;
	        	 if(rs.getDate("ngaysinh") != null) {
	        		 ngaysinh = new Date(rs.getDate("ngaysinh").getTime());
	        	 } else {
	        		 ngaysinh = null;
	        	 }
	        	 float diem1 = rs.getFloat("diem1");
	        	 float diem2 = rs.getFloat("diem2");
	        	 float diem3 = rs.getFloat("diem3");
	        	 
	        	 SinhVien sv = new SinhVien(masv, ten, tinh, ngaysinh, gioitinh, diem1, diem2 , diem3);
	        	 list.add(sv);
	          }
	         JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public static void insertSinhVienDB(SinhVien sv) {
		try {
			if(JDBCUtil.ktSinhVienCoTonTaiDB(sv.getMasv()+"")) {
				JOptionPane.showMessageDialog(null, "Sinh Vien Da Ton Tai");
				return;
			}
			Connection c = JDBCUtil.getConnection();
			SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
			String ngaysinh = DateFor.format(sv.getNgaySinh());
			@SuppressWarnings("deprecation")
			java.util.Date myDate = new java.util.Date(ngaysinh);
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			System.out.println(ngaysinh);
			PreparedStatement sta = c.prepareStatement(
					"INSERT INTO infomation ( masv, ten, quequan, gioitinh, ngaysinh, diem1, diem2, diem3 ) " +
					" values (?, ?, ?, ?, ?, ?, ?, ? )");
			sta.setInt(1, sv.getMasv());
			sta.setString(2, sv.getTensv());
			sta.setString(3, sv.getQueQuan().getTenTinh());
			sta.setBoolean(4, sv.isGioiTinh());
			sta.setDate(5, sqlDate);
			sta.setFloat(6, sv.getDiem1());
			sta.setFloat(7, sv.getDiem2());
			sta.setFloat(8, sv.getDiem3());
			sta.executeUpdate();
			JOptionPane.showMessageDialog(null, "Bạn Đã Thêm Thành Công Một Sinh Viên");
			JDBCUtil.closeConnection(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void deleteSinhVienDB(String masv) {
		try {
			Connection c = JDBCUtil.getConnection();
			Statement sta = c.createStatement();
			String sql = "delete from infomation where masv="+ masv;
			sta.executeUpdate(sql);
	        JDBCUtil.closeConnection(c);

			JOptionPane.showMessageDialog(null, "Bạn Đã Xóa Thành Công Một Sinh Viên");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateSinhVienDB(SinhVien sv) {
		try {
			Connection c = JDBCUtil.getConnection();

			SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
			String ngaysinh = DateFor.format(sv.getNgaySinh());
			@SuppressWarnings("deprecation")
			java.util.Date myDate = new java.util.Date(ngaysinh);
			java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
			
			PreparedStatement sta = c.prepareStatement("update infomation "
					+ "set ten=?, quequan=?, gioitinh=?, ngaysinh=?, diem1=?, diem2=?, diem3=? "
					+ "where masv=?");

			sta.setInt(8, sv.getMasv());
			sta.setString(1, sv.getTensv());
			sta.setString(2, sv.getQueQuan().getTenTinh());
			sta.setBoolean(3, sv.isGioiTinh());
			sta.setDate(4, sqlDate);
			sta.setFloat(5, sv.getDiem1());
			sta.setFloat(6, sv.getDiem2());
			sta.setFloat(7, sv.getDiem3());
			
			sta.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Bạn Đã Cập Nhật Thành Công Một Sinh Viên");
			
	        JDBCUtil.closeConnection(c);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean ktSinhVienCoTonTaiDB(String masv) {
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "select masv from infomation where masv="+masv;
			PreparedStatement sta = c.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();  
			int result = -1;
			while(rs.next()) {
				result = rs.getInt("masv");
			}
			JDBCUtil.closeConnection(c);
			if(result != -1) return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static SinhVien timMotSinhVien(String masv) {
		try {
			Connection c = JDBCUtil.getConnection();
			String sql = "select * from infomation where masv="+masv;
			PreparedStatement sta = c.prepareStatement(sql);
			ResultSet rs = sta.executeQuery();  
			while(rs.next()) {
	        	 int masv1 = rs.getInt("masv");
	        	 String ten = rs.getString("ten");
	        	 Tinh tinh = new Tinh(1, rs.getString("quequan"));
	        	 boolean gioitinh = rs.getBoolean("gioitinh");
	        	 Date ngaysinh;
	        	 if(rs.getDate("ngaysinh") != null) {
	        		 ngaysinh = new Date(rs.getDate("ngaysinh").getTime());
	        	 } else {
	        		 ngaysinh = null;
	        	 }
	        	 float diem1 = rs.getFloat("diem1");
	        	 float diem2 = rs.getFloat("diem2");
	        	 float diem3 = rs.getFloat("diem3");
	        	 
	        	 SinhVien sv = new SinhVien(masv1, ten, tinh, ngaysinh, gioitinh, diem1, diem2 , diem3);
	        	 return sv;
			}
			JDBCUtil.closeConnection(c);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return new SinhVien();
	}
	public static ArrayList<SinhVien> filter(String tinh, String gioitinh) {
		try {
			String sql;
			int gender=1;
			Connection c = JDBCUtil.getConnection();
			if(gioitinh == "Nữ") {
				gender=0;
			}
			if(tinh == "Chọn Quê Quán") {
				sql = "select * from infomation where gioitinh=" + gender;
			} else {
				sql = "select * from infomation where gioitinh=" + gender +" and quequan="+"'"+tinh+"'";
			}
			Statement sta = c.createStatement();
			ResultSet rs = sta.executeQuery(sql);     
			ArrayList<SinhVien> list = new ArrayList<SinhVien>();
	         while(rs.next()){
	             //Display values
	        	 int masv = rs.getInt("masv");
	        	 String ten = rs.getString("ten");
	        	 Tinh tinh1 = new Tinh(1, rs.getString("quequan"));
	        	 boolean gioitinh1 = rs.getBoolean("gioitinh");
	        	 Date ngaysinh;
	        	 if(rs.getDate("ngaysinh") != null) {
	        		 ngaysinh = new Date(rs.getDate("ngaysinh").getTime());
	        	 } else {
	        		 ngaysinh = null;
	        	 }
	        	 float diem1 = rs.getFloat("diem1");
	        	 float diem2 = rs.getFloat("diem2");
	        	 float diem3 = rs.getFloat("diem3");
	        	 
	        	 SinhVien sv = new SinhVien(masv, ten, tinh1, ngaysinh, gioitinh1, diem1, diem2 , diem3);
	        	 list.add(sv);
	          }
	         JDBCUtil.closeConnection(c);
	         return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC");
			e.printStackTrace();
		}
		return new ArrayList<SinhVien>();
	}
}
