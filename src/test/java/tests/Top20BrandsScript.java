package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import steps.Top20BrandsScriptSteps;

public class Top20BrandsScript extends BaseTest {
    private Top20BrandsScriptSteps top20BrandsScriptSteps = new Top20BrandsScriptSteps();

    @BeforeTest
    public void chooseCity() {
        top20BrandsScriptSteps.setCity("Приморский край");
    }

    @Test
     public void script() throws InterruptedException {
        top20BrandsScriptSteps.printTop20CarBrands();
    }
}
