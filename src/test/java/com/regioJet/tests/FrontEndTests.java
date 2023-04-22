package com.regioJet.tests;

import com.regioJet.pages.MainSearchPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class FrontEndTests extends TestBase {
    MainSearchPage mainSearchPage = new MainSearchPage();

    @DisplayName("Additional information about connections")
    @Test
    @Order(0)
    public void additionalInformation() {
        mainSearchPage.printAdditionInfoTOConsole();
    }

    @DisplayName("The soonest arrival time to Brno starting from midnight")
    @Test
    @Order(1)
    public void theSoonestArrivalTime() {
        mainSearchPage.theSoonestArrivalTime();
        Assertions.assertTrue(mainSearchPage.isMatchWithInputCriteria(), "Place of departure and arrival don't meet input criteria");
        Assertions.assertTrue(mainSearchPage.isConnectionDirect(), "This is an indirect connection");
    }

    @DisplayName("The shortest time spent with travelling - sitting in the train")
    @Test
    @Order(2)
    public void theShortestTimeSpentWithTravelling() {
        mainSearchPage.theShortestDuration();
        Assertions.assertTrue(mainSearchPage.isMatchWithInputCriteria(), "Place of departure and arrival don't meet input criteria");
        Assertions.assertTrue(mainSearchPage.isConnectionDirect(), "This is an indirect connection");
    }

    @DisplayName("The lowest price of the journey")
    @Test
    @Order(3)
    public void theLowestPriceOfTheJourney() {
        mainSearchPage.theLowestPrice();
        Assertions.assertTrue(mainSearchPage.isMatchWithInputCriteria(), "Place of departure and arrival don't meet input criteria");
        Assertions.assertTrue(mainSearchPage.isConnectionDirect(), "This is an indirect connection");
    }
}
