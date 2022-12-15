package model;

import java.util.ArrayList;

public class Tinh {
	private int maTinh;
	private String tenTinh;
	
	public Tinh(int maTinh, String tenTinh) {
		this.maTinh = maTinh;
		this.tenTinh = tenTinh;
	}

	@Override
	public String toString() {
		return "Tinh [maTinh=" + maTinh + ", tenTinh=" + tenTinh + "]";
	}

	public int getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(int maTinh) {
		this.maTinh = maTinh;
	}

	public String getTenTinh() {
		return tenTinh;
	}

	public void setTenTinh(String tenTinh) {
		this.tenTinh = tenTinh;
	}
	
	public static ArrayList<Tinh> getDsTinh() {
		String[] arrTinh = {"An Giang",
							"Bà Rịa – Vũng Tàu",
							"Bạc Liêu",
							"Bắc Giang",
							"Bắc Kạn",
							"Bắc Ninh",
							"Bến Tre",
							"Bình Dương",
							"Bình Định",
							"Bình Phước",
							"Bình Thuận",
							"Cà Mau",
							"Cao Bằng",
							"Cần Thơ",
							"Đà Nẵng",
							"Đắk Lắk",
							"Đắk Nông",
							"Điện Biên",
							"Đồng Nai",
							"Đồng Tháp",
							"Gia Lai",
							"Hà Giang",
							"Hà Nam",
							"Hà Nội",
							"Hà Tĩnh",
							"Hải Dương",
							"Hải Phòng",
							"Hậu Giang",
							"Hòa Bình",
							"Hưng Yên",
							"Khánh Hòa",
							"Kiên Giang",
							"Kon Tum",
							"Lai Châu",
							"Lạng Sơn",
							"Lào Cai",
							"Lâm Đồng",
							"Long An",
							"Nam Định",
							"Nghệ An",
							"Ninh Bình",
							"Ninh Thuận",
							"Phú Thọ",
							"Phú Yên",
							"Quảng Bình",
							"Quảng Nam",
							"Quảng Ngãi",
							"Quảng Ninh",
							"Quảng Trị",
							"Sóc Trăng",
							"Sơn La",
							"Tây Ninh",
							"Thái Bình",
							"Thái Nguyên",
							"Thanh Hóa",
							"Thành phố Hồ Chí Minh",
							"Thừa Thiên Huế",
							"Tiền Giang",
							"Trà Vinh",
							"Tuyên Quang",
							"Vĩnh Long",
							"Vĩnh Phúc",
							"Yên Bái"};
		ArrayList<Tinh> listTinh = new ArrayList();
		for(int i=0; i < arrTinh.length; i++) {
			Tinh t = new Tinh(i, arrTinh[i]);
			listTinh.add(t);
		}
		return listTinh;
		
	}
}
