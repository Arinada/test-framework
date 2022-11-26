package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PersonalAccountPage extends BasePage{
    private String titleForLastAddedFavourites;

    @FindBy(css = ".css-311zh0.eq0jfbs0")
    private WebElement linkToCarSalesPage;

    @FindBy(xpath = "//div[contains(@class, 'css-cvu2h3 ecjvn7j0')]//div[contains(@class, 'css-1rozdag')]")
    private WebElement firstCarAdFavouritesButton;

    @FindBy(xpath = "//div[contains(@class, 'css-1ovyjlx e173iafn0')]//a[@title='Избранное']")
    private WebElement linkToFavouritesPage;

    @FindBy(xpath = "//div[contains(@class, 'css-17lk78h e3f4v4l2')]//span")
    private WebElement firstCarAdTitleElement;

    @FindBy(css = ".bulletinLink.bull-item__self-link.auto-shy")
    private WebElement lastAddedCarAdTitleToFavourites;

    @FindBy(css = ".removeBookmark")
    private List<WebElement> removeBookmarkLinks;

    public PersonalAccountPage goToAdCarsPage() {
        linkToCarSalesPage.click();
        return this;
    }

    public PersonalAccountPage addFirstAdCarToFavourites() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        setTitleForLastAddedFavourites(firstCarAdTitleElement.getText());
        firstCarAdFavouritesButton.click();
        return this;
    }

    private void setTitleForLastAddedFavourites(String title) {
        titleForLastAddedFavourites = title;
    }

    public PersonalAccountPage goToFavourites() {
        linkToFavouritesPage.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return this;
    }

    public String getTitleForLastAddedFavourites(){
        return titleForLastAddedFavourites;
    }

    public String getLastAddedCarAdTitleToFavourites() {
        return lastAddedCarAdTitleToFavourites.getText();
    }

    public PersonalAccountPage pressOnRemoveBookmarksButtons() {
        for(WebElement link: removeBookmarkLinks) {
            link.click();
        }
        return this;
    }
}
