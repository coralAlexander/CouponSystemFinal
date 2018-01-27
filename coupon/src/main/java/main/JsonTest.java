package main;

import com.google.gson.Gson;

import models.Company;

public class JsonTest {

	public static void main(String[] args) {
		Company comp = new Company();
		Test test = new Test();
		test.setCorpName("Intel");
		comp.setId(1);
		comp.setName("Vasia");
		comp.setPassword("1233dfg");
		comp.setEmail("vasia@gmail.com");
		test.setComp(comp);
		Gson gson = new Gson();
        System.out.println(gson.toJson(test).toString());
        
	}

}
