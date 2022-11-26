package pages;

import com.sun.xml.internal.ws.server.ServerRtException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Year;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SearchCarSalesPage extends BasePage {
    private WebDriverWait wait;

    @FindBy(xpath = "//form//div[contains(@class, 'css-3jcsdf e1srd0oj1')]//input[@placeholder='Марка']")
    private WebElement brandInput;

    @FindBy(xpath = "//form//div[contains(@class, 'css-3jcsdf e1srd0oj1')]//input[@placeholder='Модель']")
    private WebElement modelInput;

    @FindBy(xpath = "//div[contains(@class, 'css-109956f e1x0dvi10')]")
    private WebElement modelElement;

    @FindBy(css = ".css-15n80le.eiy4qr62")
    private WebElement unsoldCheckbox;

    @FindBy(xpath = "//button[text()='Год от']")
    private WebElement releaseFromButton;

    @FindBy(xpath = "//div[contains(@class, 'css-14hyps3 evnwjo70')]//input[contains(@class, 'css-1tvqm8a e1bmfvzq0')]")
    private WebElement mileageFromElement;

    @FindBy(xpath = "//div[contains(@class, 'css-uewl2b')]//input[contains(@class, 'css-1tvqm8a e1bmfvzq0')]")
    private WebElement keywordsSearchElement;

    @FindBy(css = ".ezmft1z0.css-1q5ta30.e104a11t0")
    private WebElement advancedSearchButton;

    @FindBy(css = ".css-tjza12.e1lm3vns0")
    private WebElement searchButton;

    @FindBy(css = ".css-c96isf.esqr6ni0")
    private WebElement goToCityPageLink;

    public SearchCarSalesPage() {
        super();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    public void selectBrand(String brandName) {
        brandInput.click();
        brandInput.sendKeys("Toyota");

        By brandElementBy = By.xpath("//div[contains(@class, 'css-109956f e1x0dvi10')]");
        wait.until(ExpectedConditions.elementToBeClickable(brandElementBy));
        WebElement brandElement = driver.findElement(brandElementBy);
        brandElement.click();
    }

    public void selectModel(String modelName) {
        modelInput.click();
        modelInput.sendKeys(modelName);

        By modelsElementBy = By.xpath("//div[contains(@class, 'css-109956f e1x0dvi10')]");
        wait.until(ExpectedConditions.elementToBeClickable(modelsElementBy));
        WebElement modelElement = driver.findElement(modelsElementBy);
        modelElement.click();
    }

    public void setUnsoldFilter() {
        unsoldCheckbox.click();
    }

    public void setReleaseYearFrom(Integer year) {
        releaseFromButton.click();
        List<WebElement> yearItems = driver.findElements(By.xpath("//div[contains(@class, 'css-xzwadh e1x0dvi10')]"));
        yearItems.get(Year.now().getValue() - year).click();
    }

    public void setAdvancedSearch() {
        advancedSearchButton.click();
    }

    public void setCarMileageFrom(String mileage) {
        mileageFromElement.sendKeys(mileage);
    }

    public void setKeyWordsSearch(String keywords) {
        keywordsSearchElement.sendKeys(keywords);
    }

    public void pressOnSearchButton() {
        searchButton.click();
    }

    public void goToSearchPage(Integer pageNumber) {
        WebElement pageLink;
        List<WebElement> searchResultPageLinks = driver.findElements(By.cssSelector(".css-19tk3lt.e15hqrm30"));

        Integer index = pageNumber;
        if (pageNumber != 1) {
            index = pageNumber - 2;
        }
        pageLink = searchResultPageLinks.get(index);

        if (pageLink.isEnabled()) {
            pageLink.click();
        }
    }

    public List<WebElement> getSoldCarsElementsFromSearchPage() {
        return driver.findElements(By.cssSelector("css-r91w5p"));
    }

    public List<Integer> getCarAdReleaseYearsFromSearchPage() {
        List<WebElement> searchResultElementTitles = driver.findElements(By.xpath("//div[contains(@class, 'css-17lk78h e3f4v4l2')]//span"));

        List<Integer> years = new ArrayList<>();
        for (WebElement searchElement : searchResultElementTitles) {
            String title = searchElement.getText();
            String releaseYearString = title.substring(title.length() - 4);
            years.add(Integer.parseInt(releaseYearString));
        }
        return years;
    }

    public List<String> getMileAgeForEveryCarAdFromSearchPage() {
        By carAdElementsBy = By.cssSelector(".css-xb5nz8.ewrty961");
        List<WebElement> carAdElements = driver.findElements(carAdElementsBy);

        List<String> mileages = new ArrayList<>();
        for (WebElement descriptionElement : carAdElements) {
            List<WebElement> carParameterElements = driver.findElements(By.xpath("//div[contains(@class, 'css-1fe6w6s e162wx9x0')]//span"));
            WebElement lastItem = carParameterElements.get(carParameterElements.size() - 1);
            mileages.add(lastItem.getText());
        }

        return mileages;
    }

    public void chooseCity(String city) {
        goToCityPageLink.click();

        WebElement cityLink = driver.findElement(By.xpath("//a[text()='" + city + "']"));
        cityLink.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public HashMap<String, Integer> getBrandsList() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(brandInput));
        brandInput.click();
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        HashMap<String, Integer> carAdNumber = new HashMap<>();
        List<WebElement> brandListTitles = new ArrayList<>();

        int brandDropdownListSizeInPixels = 5200;
        int step = 400;
        for (int pixelsY = 0; pixelsY <= brandDropdownListSizeInPixels; pixelsY += step) {
            jsExec.executeScript("document.getElementsByClassName('css-u25ii9 e154wmfa0')[0].scroll(0," + pixelsY + ")");
            Thread.sleep(3000);
            brandListTitles = driver.findElements(By.xpath("//div[contains(@class, 'css-u25ii9 e154wmfa0')]//div[contains(@class, 'css-1r0zrug e1uu17r80')]//div[text()]"));

            for (WebElement item : brandListTitles) {
                String title = item.getText();
                if (title.indexOf("(") > 0) {
                    String brand = title.substring(0, title.indexOf(' '));
                    String amountOfAd = title.substring(title.indexOf('(') + 1, title.indexOf(')'));
                    carAdNumber.put(brand, Integer.valueOf(amountOfAd));
                }
            }
        }
        return carAdNumber;
    }
}
