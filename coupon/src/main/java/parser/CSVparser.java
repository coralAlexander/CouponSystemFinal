package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import models.Company;

public class CSVparser {

    
    public static void main(String[] args) throws IOException {
        String filePath = "c:\\CSVexample\\csvExample.txt";
        List<Company> companies = ParseProductCsv(filePath);
       //List<Product> products = ParseProductCsv(filePath);
        for( Company company :  companies ) {
        	System.out.println(company);
        }
       
    }
 
    private static List<Company> ParseProductCsv(String filePath) throws IOException {
        
        List<Company> companies = new ArrayList<Company>();
        
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitedText.length; i++) {
                
                if (IsColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + ","+ splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            Company company = new Company();
            int companyId=Integer.parseInt(columnList.get(0));
            company.setId(companyId);
            company.setName(columnList.get(1)) ;
            company.setPassword(columnList.get(2)); 
            company.setEmail(columnList.get(3));
            companies.add(company);
        }
        return companies;
    }

    
    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}