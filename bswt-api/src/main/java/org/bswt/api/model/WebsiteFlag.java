package org.bswt.api.model;

public class WebsiteFlag 
{
    private String key;
    
    private boolean flag;

    public WebsiteFlag() {}
    
    public WebsiteFlag(String key) {
    	this.key = key;
    	this.flag = false;
    }
    
    public WebsiteFlag(String key, boolean flag) {
    	this.key = key;
    	this.flag = flag;
    }
    
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
