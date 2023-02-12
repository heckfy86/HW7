package qa.guru;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.*;

public class UrlOnlineTradeRu {

    @BeforeEach
    void beforeEach() {
        open("https://bing.com");
        Configuration.browserSize = "1920x1080";
        // Configuration.holdBrowserOpen = true;
    }

    @CsvFileSource(resources = "/testData.csv")
    @ParameterizedTest(name = "Количество товаров фирмы {0} должно быть не меньше {1}")
    void containsProductsOnSite(String product, int count) {
        open("https://www.onlinetrade.ru/");
        $("input[type=text]").setValue(product).pressEnter();
        $$("div[class='indexGoods__item']")
                .shouldHave(CollectionCondition.sizeGreaterThan(count));
    }
}
