package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;

    private final By nameInput = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    private final By surnameInput = By.xpath(".//input[contains(@placeholder, 'Фамили')]");
    private final By addressInput = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    private final By metroInput = By.xpath(".//input[contains(@placeholder, 'метро')]");
    private final By phoneNumberInput = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    private final By nextButton = By.xpath(".//div[contains(@class, 'Order_NextButton')]/button");


    private static final String METRO_PATTERN  = ".//div[contains(text(), '%s')]";

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setNameInput(String name){
        driver.findElement(nameInput).sendKeys(name);
    }
    public void setSurnameInput(String surname){
        driver.findElement(surnameInput).sendKeys(surname);
    }
    public void setAddressInput(String address){
        driver.findElement(addressInput).sendKeys(address);
    }
    public void setPhoneNumberInput(String number){
        driver.findElement(phoneNumberInput).sendKeys(number);
    }
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void setMetroInput(String metro){
        driver.findElement(metroInput).sendKeys(metro);

        String metroLocator = String.format(METRO_PATTERN, metro);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(metroLocator))).click();
    }

    public void fillOrderFormOnOrderPage(String name, String surname, String address, String metroStationName, String phoneNumber) {
        setNameInput(name);
        setSurnameInput(surname);
        setAddressInput(address);
        setMetroInput(metroStationName);
        setPhoneNumberInput(phoneNumber);
        clickNextButton();
    }

}

