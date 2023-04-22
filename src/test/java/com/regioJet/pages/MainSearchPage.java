package com.regioJet.pages;

import com.regioJet.utilities.BrowserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.regioJet.utilities.ConfigurationReader.getProperty;

public class MainSearchPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(MainSearchPage.class.getSimpleName());

    @FindBy(xpath = "(//input[@class='react-select__input'])[1]")
    public WebElement fromInputField;

    @FindBy(xpath = "(//input[@class='react-select__input'])[2]")
    public WebElement toInputField;

    @FindBy(xpath = "(//div[@data-id='departure-date']//span[@class='clickableDiv-input'])[1]")
    public WebElement departureDataField;

    @FindBy(xpath = "//td[contains(@aria-label, 'Monday')]")
    public List<WebElement> allMondaysInCalendar;

    @FindBy(xpath = "//button[@data-id='search-btn']")
    public WebElement searchButton;

    @FindBy(xpath = "//ul[@aria-label='Search results']/li//h2")
    public List<WebElement> departureAndArrivalForAllResults;

    @FindBy(xpath = "//ul[@aria-label='Search results']/li/div//span[contains(@aria-label, 'Journey length')]")
    public List<WebElement> journeyDurationsForAllResults;

    @FindBy(xpath = "//button[contains(@data-id, 'connection-card-price')]")
    public List<WebElement> pricesForAllResults;

    @FindBy(xpath = "//ul[@aria-label='Search results']/li//div[@role='button']/div/following-sibling::div/div/div/div/following-sibling::span")
    public List<WebElement> allConnections;

    @FindBy(xpath = "//ul[@aria-label='Journey information']//li[1]/div//div[4]/span[1]")
    public WebElement departCityCheck;

    @FindBy(xpath = "//ul[@aria-label='Journey information']//li[1]/div//div[10]")
    public WebElement arrivalCityCheck;

    @FindBy(xpath = "//button[.='ALLOW ALL']")
    public WebElement allowAllButton;

    public void chooseConnection() {
        fromInputField.click();
        fromInputField.sendKeys(getProperty("departureCity"), Keys.ENTER);
        toInputField.click();
        toInputField.sendKeys(getProperty("arrivalCity"), Keys.ENTER);
        selectAvailableMonday();
        searchButton.click();
    }

    public void selectAvailableMonday() {
        departureDataField.click();
        for (WebElement eachMonday : allMondaysInCalendar) {
            if (eachMonday.getAttribute("aria-disabled").equals("false")) {
                eachMonday.click();
                break;
            }
        }
    }

    public void printAdditionInfoTOConsole() {
        logger.info("Addition information about all journeys");
        for (int i = 0; i < departureAndArrivalForAllResults.size(); i++) {
            String fullAttributeValue = departureAndArrivalForAllResults.get(i).getAttribute("aria-label");
            String departureTime = fullAttributeValue.substring(11, 16);
            String arrivalTime = fullAttributeValue.substring(19);

            logger.info("Departure time: " + departureTime);
            logger.info("Arrival time: " + arrivalTime);

            logger.info("Number of stops: " + allConnections.get(i).getText());
            logger.info("The price of the journey: " + pricesForAllResults.get(i).getText());
        }
    }

    public void theSoonestArrivalTime() {
        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < departureAndArrivalForAllResults.size(); i++) {
            String fullAttributeValue = departureAndArrivalForAllResults.get(i).getAttribute("aria-label");
            String arrivalTime = fullAttributeValue.substring(19).replace(":", "");
            int arrivalTimeAsInt = Integer.parseInt(arrivalTime);

            if (arrivalTimeAsInt < minTime) {
                minTime = arrivalTimeAsInt;
            }
        }
        logger.info("The soonest arrival time to Brno: " + printTime(minTime));
    }

    public String printTime(int time) {
        String timeFormat = "" + time;
        if (timeFormat.length() < 4) {
            timeFormat = "0" + timeFormat;
        }
        timeFormat = timeFormat.substring(0, 2) + ":" + timeFormat.substring(2);
        return timeFormat;
    }

    public void theShortestDuration() {
        int minDuration = Integer.MAX_VALUE;
        for (WebElement eachDuration : journeyDurationsForAllResults) {
            String durationAsText = eachDuration.getText().substring(0, 5).replace(":", "");
            int durationAsNumber = Integer.parseInt(durationAsText);
            if (durationAsNumber < minDuration) {
                minDuration = durationAsNumber;
            }
        }
        logger.info("The shortest time spent with travelling: " + printTime(minDuration));
    }

    public void theLowestPrice() {
        double minPrice = Double.MAX_VALUE;
        for (WebElement priceForEachJourney : pricesForAllResults) {
            String priceAsString = priceForEachJourney.getText();
            double price = priceFormatter(priceAsString);
            if (price < minPrice) {
                minPrice = price;
            }
        }
        logger.info("The lowest price of the journey: " + printMoney(minPrice));
    }

    public String printMoney(double price) {
        return "Euro " + price;
    }

    public double priceFormatter(String price) {
        String digits = "";
        for (int i = 0; i < price.length(); i++) {
            if (Character.isDigit(price.charAt(i)) || (price.charAt(i) + "").equals(".")) {
                digits += price.charAt(i);
            }
        }
        return Double.parseDouble(digits);
    }

    public boolean isMatchWithInputCriteria() {
        boolean result = false;
        for (WebElement eachConnection : allConnections) {
            eachConnection.click();
            BrowserUtils.explicitWait(departCityCheck);
            if (departCityCheck.getText().contains("Ostrava") && arrivalCityCheck.getText().contains("Brno")) {
                result = true;
            } else {
                break;
            }
            eachConnection.click();
        }
        return result;
    }

    public boolean isConnectionDirect() {
        boolean result = false;
        for (WebElement eachConnection : allConnections) {
            eachConnection.click();
            BrowserUtils.explicitWait(departCityCheck);

            if (eachConnection.getText().contains("Direct")) {
                result = true;
            } else {
                result = false;
                break;
            }
            eachConnection.click();
        }
        return result;
    }

}
