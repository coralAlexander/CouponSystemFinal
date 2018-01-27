package main;

import models.Company;

public class Test {
   
	String corpName ;
    Company comp ;
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public void setComp(Company comp) {
		this.comp = comp;
	}

	@Override
	public String toString() {
		return "Test [corpName=" + corpName + ", comp=" + comp + "]";
	}	
}
