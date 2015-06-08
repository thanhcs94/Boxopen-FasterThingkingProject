package db;

public class Question {

	int id;
	String linkanh, linkanh1, linkanh2, dapan;
	int causo;
	int vang;
	int kt1, kt2;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public Question(int id, String linkanh , String linkanh1, String linkanh2, int causo,
			int vang, int kt1, int kt2, String dapan) {
		super();
		this.id = id;
		this.linkanh = linkanh;
		this.linkanh1 = linkanh1;
		this.linkanh2 = linkanh2;
		this.causo = causo;
		this.vang = vang;
		this.kt1 = kt1;
		this.kt2 = kt2;
		this.dapan = dapan;
	}

	
	public String getLinkanh() {
		return linkanh;
	}

	public void setLinkanh(String linkanh) {
		this.linkanh = linkanh;
	}

	public String getDapan() {
		return dapan;
	}

	public void setDapan(String dapan) {
		this.dapan = dapan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLinkanh1() {
		return linkanh1;
	}

	public void setLinkanh1(String linkanh1) {
		this.linkanh1 = linkanh1;
	}

	public String getLinkanh2() {
		return linkanh2;
	}

	public void setLinkanh2(String linkanh2) {
		this.linkanh2 = linkanh2;
	}

	public int getCauso() {
		return causo;
	}

	public void setCauso(int causo) {
		this.causo = causo;
	}

	public int getVang() {
		return vang;
	}

	public void setVang(int vang) {
		this.vang = vang;
	}

	public int getKt1() {
		return kt1;
	}

	public void setKt1(int kt1) {
		this.kt1 = kt1;
	}

	public int getKt2() {
		return kt2;
	}

	public void setKt2(int kt2) {
		this.kt2 = kt2;
	}

	@Override
	public String toString() {
		return "CauHoi [id=" + id + ", dapan=" + dapan + "]";
	}
	
	
	
	
	
	
}
