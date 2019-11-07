package model;

public enum EBulletType {
	
	TEST_BULLET("/resources/bullet-4.png");
	
	private final String addr;
	private EBulletType(String addr) {
		this.addr = addr;
	}

	public String getAddr() {
		return addr;
	}
}
