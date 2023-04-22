package com.regioJet.tests;

import com.regioJet.pages.MainSearchPage;
import com.regioJet.utilities.ConfigurationReader;
import com.regioJet.utilities.Driver;
import org.junit.jupiter.api.*;

public abstract class TestBase {

    MainSearchPage mainSearchPage = new MainSearchPage();

    @BeforeEach
    public void setupMethod() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        mainSearchPage.allowAllButton.click();
        mainSearchPage.chooseConnection();
    }

    @AfterEach
    public void tearDown() {
        Driver.closeDriver();
    }

    @DisplayName("Additional information about connections")
    @Test
    @Order(0)
    public void additionalInformation() {
        mainSearchPage.printAdditionInfoTOConsole();
    }

}
