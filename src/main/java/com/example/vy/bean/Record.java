package com.example.vy.bean;




public class Record {
	private String Rid;
	private String Aid;
	private String Gname;
    private String Buytime;
	
	
	public String getBuytime() {
		// TODO Auto-generated method stub
		return this.Buytime;
	}


	public void setBuytime(String p) {
		this.Buytime=p;
	}

	
	public String getGname() {
		// TODO Auto-generated method stub
		return this.Gname;
	}


	public void setGname(String p) {
		this.Gname=p;
	}

	public String getRid() {
		// TODO Auto-generated method stub
		return this.Rid;
	}


	public void setRid(String p) {
		this.Rid=p;
	}

	public String getAid() {
		// TODO Auto-generated method stub
		return this.Aid;
	}


	public void setAid(String p) {
		this.Aid=p;
	}
}
