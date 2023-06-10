package com.example.ex06.crawling;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class Crawling {
    @Value("${chrome.driver}")
    private String driverPath;
    public String web_driver_id = "webdriver.chrome.driver";

    private WebDriver driver;

    private int page = 1;
//    private String url = "https://www.jobkorea.co.kr/Search/?stext=spring%20boot&tabType=recruit&Page_No=" + page;
    private String url = "https://map.kakao.com/?q=동인천역";


    public List<String> doProcess() {
        System.setProperty(web_driver_id, driverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");   //원격 연결 허용
        options.addArguments("--disable-popup-blocking");       //팝업안띄움
        options.addArguments("headless");                       //브라우저 안띄움
        options.addArguments("--disable-gpu");         //gpu 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음


        driver = new ChromeDriver(options);    //드라이버 객체 생성

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<String> resultList = getResultList();

        driver.close();
        driver.quit();  //브라우저를 닫는 메소드

        return resultList;
    }

    private void printResult() {
        driver.get(url);

        List<WebElement> elements = driver.findElements(By.cssSelector(".suggest .suggest_list_target>.ms_item .emph_keyword"));

        for (WebElement element : elements) {
            System.out.println("=================");
            System.out.println(element.getText());
        }

    }

    private List<String> getResultList() {
        List<String> resultList = new ArrayList<>();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> elements = null;

        while(true){
//            url = "https://www.jobkorea.co.kr/Search/?stext=spring%20boot&tabType=recruit&Page_No="+page;

            driver.get(url);

            try {
                webDriverWait.until(
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector(".list-default .title"))
                        //  selector로 가져오는 요소가 있을 때까지 대기한다.
                        //  만약 존재하지 않는다면 위에서 설정한 10초까지 기다리고 예외가 발생한다.
                );
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            elements = driver.findElements(By.cssSelector(".suggest .suggest_list_target .ms_item .emph_keyword"));

            for(WebElement element : elements){
                resultList.add(element.getText());
            }
//            page++;
        }

        System.out.println(resultList);
        return resultList;
    }

}
