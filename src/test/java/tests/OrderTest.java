package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import pageobject.AboutOrder;
import pageobject.MainPage;
import pageobject.OrderPage;
import org.junit.Assert;


@RunWith(Parameterized.class)
public class OrderTest extends BaseUITest{
    public final String name;
    public final String surname;
    public final String address;
    public final String metroStationName;
    public final String phoneNumber;
    public final String date;
    public final String countDays;
    public final String colour;
    public final String comment;


    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getData() {
        return new Object[][] {
                {"Chrome", "Ким","Городон","улица Маяковского, д5","Парк культуры","88831232820", "22.11.2024","сутки","black","Быстрее"},
                {"Safari","Тёрстон", "Мур", "площадь Горького, д1", "Технопарк", "80513182328", "22.12.2024", "трое", "grey", "Спасибо!"}
        };
    }

    public OrderTest(String browser, String name, String surname, String address, String metroStationName, String phoneNumber, String date, String countDays, String colour, String comment) {
        super.browser = browser;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStationName = metroStationName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.countDays = countDays;
        this.colour = colour;
        this.comment = comment;
    }

    @Test
    public void checkOrderHeaderButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.cookieButtonClick();
        mainPage.clickHeaderOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderFormOnOrderPage(name, surname, address, metroStationName, phoneNumber);

        AboutOrder  aboutOrder = new AboutOrder(driver);
        aboutOrder.fillOrderFormOnAboutPage(date, countDays, colour, comment);

        Assert.assertTrue("Нет сообщения с информацией о заказе!",aboutOrder.orderPlacedMessageCheck().contains("Заказ оформлен"));

    }

    @Test
    public void checkOrderRoadMapButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.cookieButtonClick();
        mainPage.clickRoadMapOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderFormOnOrderPage(name, surname, address, metroStationName, phoneNumber);

        AboutOrder  aboutOrder = new AboutOrder(driver);
        aboutOrder.fillOrderFormOnAboutPage(date, countDays, colour, comment);

        Assert.assertTrue("Нет сообщения с информацией о заказе!",aboutOrder.orderPlacedMessageCheck().contains("Заказ оформлен"));

    }

}
