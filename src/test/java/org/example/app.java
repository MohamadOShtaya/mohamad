package org.example;

import Model.flashCard;
import Model.questions;
import Model.subject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.*;



public class app {

    public static void main( String[] args ) throws SQLException {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            System.out.println("Connected");
        }
        catch (Exception ex){
           System.out.println("Not connnect");
        }


//        for(int i=0;i<questionsList.size();i++){
//            entityManager.persist(questionsList.get(i));
//            entityManager.getTransaction().commit();
//        }


        //**************************open web browser
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Mohamad O Shtaya\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.assignguru.com/mcqs/?fbclid=IwAR1YYsyJZNdR3RBhbZn4A9XTZmgh6WTxWwarWJSHrxu5PwF3wgCFkHV9vOQ#gsc.tab=0");
        //******************************* get all subjects
        List<WebElement> listSubElm = driver.findElements(By.xpath("//*[@id=\"bd-sidebar\"]/ul/li/a"));
        // List<WebElement> listSubElm = subWebElm.findElements(By.("href"));//href


        List<subject> listOfsub = new ArrayList<>();
        int count = 0;

        int quick = 1;
        List<flashCard> listOfflash = new ArrayList<>();
        List<String> linkList = new ArrayList<>();
        int qcount = 0;

        List<questions> questionsList = new ArrayList<>();
        int questionCount = 0;

        for (WebElement obj : listSubElm) {

            count++;
            listOfsub.add(new subject(count, obj.getText(), null, obj.getAttribute("href")));
        }

        List<flashCard> flashList = new ArrayList<>();
        int flashCount=0;
        for (int i = 0; i < listOfsub.size(); i++) {

            driver.get(listOfsub.get(i).getUrl());
            // System.out.println(listOfsub.get(i).getUrl());
            List<WebElement> subjectelm = driver.findElements(By.xpath("/html/body/div/main/div[3]/div/div[3]/div/a"));
            for (WebElement obj : subjectelm) {
                flashCount++;
                flashList.add(new flashCard(flashCount, obj.getText(), null, obj.getAttribute("href")));
            }

            listOfsub.get(i).setFlashCards(flashList);

            //****************************************handle button next
            String str = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[2]/nav/a[2]")).getText();
            String[] arr = str.split(" ");
            int firstNumber = Integer.parseInt(arr[0]);
            int Qcount = firstNumber;
            int lastNumber = Integer.parseInt(arr[2]);
            //*****************************************


            while (quick <lastNumber) {
                List<WebElement> listQusElm = driver.findElements(By.xpath("/html/body/div/main/div[3]/div/div[3]/div/a"));
                WebElement btnNext = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[2]/nav/a[1]"));
                for (WebElement obj : listQusElm) {
                    qcount++;
                    linkList.add(obj.getAttribute("href"));
                    listOfflash.add(new flashCard(qcount, obj.getText(), null, obj.getAttribute("href")));
                }
                listOfsub.get(i).setFlashCards(listOfflash);

                btnNext.click();
                quick++;
                driver.getCurrentUrl();
                System.out.println(driver.getCurrentUrl());
                driver.get(driver.getCurrentUrl());
                System.out.println(listOfsub.get(i).toString());
                System.out.println("**********************************************************************************");
            }
        }





            //******************************************saving flashCard objects
            for (int j = 0; j < listOfsub.size(); j++) {
                driver.get(listOfsub.get(j).getUrl());
                for (int i = 0; i < listOfflash.size(); i++) {
                    driver.get(listOfflash.get(i).getUrl());
                    List<WebElement> questionElements = driver.findElements(By.xpath("/html/body/div/div/div[1]/div"));

                    for (WebElement e : questionElements) {
                        questionCount++;
                        questionsList.add(new questions(questionCount, e.getText()));
                    }
                    questionsList.remove(0);
                    questionsList.remove(1);
                    listOfflash.get(i).setqList(questionsList);
                    //Try catch
                    System.out.println(listOfflash.toString());

                }

            }





/*
        for (int i = 0; i < listOfflash.size(); i++) {
            driver.get(listOfflash.get(i).getUrl());
            List<WebElement> questionElements = driver.findElements(By.xpath("/html/body/div/div/div[1]/div"));

            for (WebElement e : questionElements) {
                questionCount++;
                questionsList.add(new questions(questionCount, e.getText()));
            }
            questionsList.remove(0);
            questionsList.remove(1);
            listOfflash.get(i).setqList(questionsList);
            //Try catch
            System.out.println(listOfflash.toString());
        }

 */





        }
    }






























// System.out.println(listOfflash.toString());
      /*  for (String str1 : linkList) {
            driver.get(str1);
            List<WebElement> questionElements = driver.findElements(By.xpath("/html/body/div/div/div[1]/div"));
            List<questions> questionsList=new ArrayList<>();
            try {

                System.out.println(questionElements.size());
            } catch (Exception e) {
                System.out.println("error");
            }
        }

       */










//        WebElement lastQuesWebElm = driver.findElement(By.xpath("/html/body/div/main/div[3]/div/div[3]"));
//        List<WebElement> lastListQusElm = lastQuesWebElm.findElements(By.tagName("a"));
//        for(WebElement obj: lastListQusElm  ){
//            qcount++;
//            listOfQus.add(new questions(qcount, obj.getText()));
//        }
//        //System.out.println(listOfQus.toString());
//        //******************************************get last sub questions
//        //System.out.println("---------------------------------------------");
//        String [] arrQes = new String[listOfQus.size()];
//        for(int i=0;i<listOfQus.size();i++){
//            //System.out.println(listOfQus.get(i).getName());
//            arrQes [i] = Arrays.toString(listOfQus.get(i).getName().split("\\R"));
//            //System.out.println();
//        }
//       String ss = Arrays.toString(arrQes);
//        //System.out.println(ss);
//*************************************
//*[@id="bd-docs-nav"]////*[@id="bd-sidebar"]/ul//






