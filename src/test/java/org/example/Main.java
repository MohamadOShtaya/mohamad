package org.example;

import Model.quetions;
import Model.subject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.*;


public class Main {

    public static void main( String[] args )
    {
        //**************************open web browser
        System.setProperty("webdriver.gecko.driver","C:\\Users\\Mohamad O Shtaya\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.assignguru.com/mcqs/?fbclid=IwAR1YYsyJZNdR3RBhbZn4A9XTZmgh6WTxWwarWJSHrxu5PwF3wgCFkHV9vOQ#gsc.tab=0");
        //******************************* get all subjects
        WebElement sWElm = driver.findElement(By.xpath("//*[@id=\"bd-docs-nav\"]"));
        List<WebElement> listSub = sWElm.findElements(By.tagName("li"));
        System.out.println(listSub.size());
        List<subject> listOfSub =new ArrayList<>();
        int count =0;

        for(WebElement obj: listSub  ){
            count++;
          // System.out.println(obj.getAttribute("href").toString());
            listOfSub.add(new subject(count, obj.getText()));
       }
     //   System.out.println(listOfSub.toString());
        //***************************************************************************get start with one of subjects
        driver.findElement(By.linkText("Surgical Technology")).click();

        //****************************************
        WebElement qWElm = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[3]"));
        List<WebElement> listQus = qWElm.findElements(By.tagName("a"));

        System.out.println(listQus.size());
        List<quetions> listOfQues =new ArrayList<>();
        int qcount=0;
        for(WebElement obj: listQus  ){
            qcount++;
            listOfQues.add(new quetions(qcount, obj.getText()));
        }
       // System.out.println(listOfQues.toString());

        //****************************************handle button next

        String str = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[2]/nav/a[2]")).getText();
        //System.out.println(str);
        String[] arr = str.split(" ");
        String size = arr[2];
        int number = Integer.parseInt(size);;
        System.out.println(number);
        //******************************************saving questions objects

        for(int i=1;i<number;i++){
            WebElement test = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[2]/nav/a[1]"));
            List<WebElement> qq = test.findElements(By.tagName("href"));

            test.click();
          for(WebElement obj: qq  ){
                qcount++;
                listOfQues.add(new quetions(qcount, obj.getText()));
                System.out.println("==================");
            }


        }
        System.out.println(listOfQues.size());

    }

}
