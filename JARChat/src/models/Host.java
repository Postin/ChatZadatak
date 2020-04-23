package models;

public class Host {
	
	private String alias;
	private String address;
	
	public Host() {
		super();
	}
	
	public Host(String alias, String address) {
		this.alias = alias;
		this.address = address;
	}
	
	public Host(Host h) {
		this.alias = h.alias;
		this.address = h.address;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
