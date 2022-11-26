package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SearchCarSalesTest extends BaseTest {

    @BeforeTest
    public void searchCarSalesByFilters() {
        HashMap<String, String> filters = new HashMap<>();
        filters.put("brand", "Toyota");
        filters.put("model", "Harrier");
        filters.put("year_from", "2007");
        filters.put("mileage_from", "1");
        filters.put("keywords", "Гибрид");

        steps.searchCarAdsByFilters(filters);
    }

    @Test
    public void checkThatFirstResultPageHasNoSoldCars() {
        steps.goToSearchResultPage(1).
                verifyThatNoSoldCars();
    }

    @Test
    public void checkThatSecondResultPagePageHasNoSoldCars() {
        steps.goToSearchResultPage(2).
                verifyThatNoSoldCars();
    }

    @Test
    public void checkThatFirstResultPageHasNoAdWithReleaseYearLessThan() {
        steps.goToSearchResultPage(1).
                verifyThatReleaseYearMoreThan(2007);
    }

    @Test
    public void checkThatSecondResultPageHasNoAdWithReleaseYearLessThan() {
        steps.goToSearchResultPage(2).
                verifyThatReleaseYearMoreThan(2007);
    }

    @Test
    public void checkThatAllCarAdHasMileageOnFirstResultPage() {
        steps.goToSearchResultPage(1).
                verifyThatEveryCarAdHasMileage();
    }

    @Test
    public void checkThatAllCarAdHasMileageOnSecondResultPage() {
        steps.goToSearchResultPage(2).
                verifyThatEveryCarAdHasMileage();
    }
}
