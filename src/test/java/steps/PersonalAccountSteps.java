package steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.PersonalAccountPage;
import pages.SearchCarSalesPage;

import java.util.concurrent.TimeUnit;

public class PersonalAccountSteps {
    private PersonalAccountPage personalAccountPage = new PersonalAccountPage();

    public PersonalAccountSteps addCarAdToFavourites() {
        personalAccountPage.goToAdCarsPage().
                addFirstAdCarToFavourites();
        return this;
    }

    public void verifyThatCarAdWereAddedToFavourites() {
        personalAccountPage.goToFavourites();
        Assert.assertEquals(
                personalAccountPage.getLastAddedCarAdTitleToFavourites(),
                personalAccountPage.getTitleForLastAddedFavourites()
        );
    }

    public void clearFavourites() {
        personalAccountPage.goToFavourites().
                pressOnRemoveBookmarksButtons();

    }
}
