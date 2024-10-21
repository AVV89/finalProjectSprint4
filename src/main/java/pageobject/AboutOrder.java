package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AboutOrder {
    protected WebDriver driver;

    private final By deliveryDateInput = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат')]");
    private final By deliveryDateDataPicker = By.xpath(".//div[contains(@class, 'datepicker__day--selected')]");
    private final By termInput = By.xpath(".//div[contains(text(), 'Срок аренды')]");
    private final By commentInput = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[contains(text(), 'Заказать')]");
    private final By confirmOrderButton = By.xpath(".//button[contains(text(),'Да')]");
    private final By orderPlaced = By.xpath(".//div[contains(@class, 'Order_Modal') and contains(text(), 'Заказ оформлен')]");

    private static final String COLOR_CHECKBOX_PATTERN  = ".//input[@id='%s']";
    private static final String TERM_DAYS_PATTERN = ".//div[contains(@class, 'Dropdown-option') and contains(text(), %s)]";


    public AboutOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDeliveryDateJS(String date){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(deliveryDateInput));
        driver.findElement(deliveryDateInput).sendKeys(date);
        driver.findElement(deliveryDateDataPicker).click();
    }

    public void selectTerm(String countDays){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(termInput)).click();
        String daysLocator = String.format(TERM_DAYS_PATTERN, countDays);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(daysLocator))).click();
    }

    public void selectColour(String colour){
        String colourLocator = String.format(COLOR_CHECKBOX_PATTERN, colour);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(colourLocator))).click();
    }

    public void inputComment(String comment){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(commentInput)).sendKeys(comment);
    }

    public void clickOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    public void clickConfirmOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }

    public String orderPlacedMessageCheck(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPlaced));
        return driver.findElement(orderPlaced).getText();
    }

    public void fillOrderFormOnAboutPage(String date, String countDays, String colour, String comment) {
        selectDeliveryDateJS(date);
        selectTerm(countDays);
        selectColour(colour);
        inputComment(comment);
        clickOrderButton();
        clickConfirmOrderButton();
    }

}
