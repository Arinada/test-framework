package steps;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.SearchCarSalesPage;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchCarSalesSteps {
    private SearchCarSalesPage searchCarSalesPage = new SearchCarSalesPage();

    public void searchCarAdsByFilters(HashMap<String, String> filters) {
        searchCarSalesPage.selectBrand(filters.get("brand"));
        searchCarSalesPage.selectModel(filters.get("model"));
        searchCarSalesPage.setUnsoldFilter();
        searchCarSalesPage.setReleaseYearFrom(Integer.parseInt(filters.get("year_from")));
        searchCarSalesPage.setAdvancedSearch();
        searchCarSalesPage.setCarMileageFrom(filters.get("mileage_from"));
        searchCarSalesPage.setKeyWordsSearch(filters.get("keywords"));
        searchCarSalesPage.pressOnSearchButton();
    }

    public void verifyThatNoSoldCars() {
        List<WebElement> soldCarElements = searchCarSalesPage.getSoldCarsElementsFromSearchPage();
        Assert.assertEquals(soldCarElements.size(), 0);
    }

    public void verifyThatReleaseYearMoreThan(Integer yearFrom) {
        assertThat(searchCarSalesPage.getCarAdReleaseYearsFromSearchPage(), everyItem(greaterThanOrEqualTo(yearFrom)));
    }

    public void verifyThatEveryCarAdHasMileage() {
        assertThat(searchCarSalesPage.getMileAgeForEveryCarAdFromSearchPage(), everyItem(containsString("тыс. км")));
    }

    public SearchCarSalesSteps goToSearchResultPage(Integer pageNumber) {
        searchCarSalesPage.goToSearchPage(pageNumber);
        return this;
    }
}
