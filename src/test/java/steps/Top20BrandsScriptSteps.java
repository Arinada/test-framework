package steps;

import pages.SearchCarSalesPage;
import tests.Brand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.min;

public class Top20BrandsScriptSteps {
    private SearchCarSalesPage searchCarSalesPage = new SearchCarSalesPage();

    public void setCity(String city) {
        searchCarSalesPage.chooseCity(city);
    }

    public void printTop20CarBrands() throws InterruptedException {
        HashMap<String, Integer> carAdHashmap = searchCarSalesPage.getBrandsList();

        ArrayList<Brand> brandList = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: carAdHashmap.entrySet()){
            brandList.add(new Brand(entry.getKey(), entry.getValue()));
        }

        Collections.sort(brandList);

        System.out.println("| Фирма | Количество объявлений |");
        for (int counter = 0; counter < min(20, brandList.size()); counter++) {
            Brand brand = brandList.get(counter);
            System.out.println("| " + brand.getName() + " | " + brand.getAmount() + " |");
        }
    }

}
