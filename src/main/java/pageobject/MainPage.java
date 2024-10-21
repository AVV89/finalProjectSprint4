package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pageobject.Constants.MAIN_PAGE_URL;

public class MainPage {
    protected WebDriver driver;

    public By cookieButton = By.id("rcc-confirm-button");
    private static final String FAQ_QUESTION_PATTERN  = ".//div[contains(@id, 'accordion__heading') and contains(text(), '%s')]";
    private static final String FAQ_ANSWER_PATTERN  = ".//div[contains(@id, 'accordion__panel')]/p[contains(text(), '%s')]/parent::div";
    public void cookieButtonClick(){
        driver.findElement(cookieButton).click();
    }
    public By headerOrderButton = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[contains(@class, 'Button')]");
    public By roadMapOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button");

    private void scrollToElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public MainPage openMainPage(){
        driver.get(MAIN_PAGE_URL);
        return this;
    }

    public void clickFAQQuestion(String questionMessage){
        String questionLocator = String.format(FAQ_QUESTION_PATTERN, questionMessage);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(questionLocator)));

        WebElement questionElement = driver.findElement(By.xpath(questionLocator));
        scrollToElement(questionElement);
        questionElement.click();
    }

    public boolean FAQQAnswerIsHidden(String answerMessage){
        String questionLocator = String.format(FAQ_ANSWER_PATTERN, answerMessage);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(questionLocator)));
        WebElement questionElement = driver.findElement(By.xpath(questionLocator));
        String hiddenAttribute = questionElement.getAttribute("hidden");
        return hiddenAttribute != null;
    }

    public void clickHeaderOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(headerOrderButton)).click();
    }

    public void clickRoadMapOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(roadMapOrderButton)).click();
    }


}
