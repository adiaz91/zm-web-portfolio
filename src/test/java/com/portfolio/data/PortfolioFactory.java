package com.portfolio.data;

import com.portfolio.entities.Portfolio;

public class PortfolioFactory {

    public static Portfolio sample1(){
        Portfolio portfolio=new Portfolio();
        portfolio.setId(1L);
        portfolio.setEmail("mail@mail.com");
        portfolio.setAddress("abc123");
        portfolio.setExperienceSummary("summary");
        return portfolio;
    }
    public static Portfolio sample2(){
        Portfolio portfolio=new Portfolio();
        portfolio.setId(2L);
        portfolio.setEmail("mail2@mail.com");
        portfolio.setAddress("abc123-2");
        portfolio.setExperienceSummary("summary-2");
        return portfolio;
    }
    
     public static Portfolio sample3(){

         Portfolio portfolio=new Portfolio();
         portfolio.setId(3L);
         portfolio.setImageUrl("http://expamle.com/images");
         portfolio.setTwitterUserName("peterpan");
         portfolio.setTitle("Senior Java Developer");
         portfolio.setAddress("Av 45 96");
         portfolio.setEmail("updated@example.com");
         portfolio.setExperience("sample experience");
         portfolio.setExperienceSummary("sample summary");
         portfolio.setName("Peter");
         portfolio.setLastName("Pan");
         portfolio.setPhone("+2 366 998 555");
         portfolio.setZipCode("9775");

         return portfolio;
     }
    
}
