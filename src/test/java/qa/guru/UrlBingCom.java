package qa.guru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class UrlBingCom {


    @BeforeEach
    void beforeEach() {
        open("https://bing.com");
        Configuration.browserSize = "1920x1080";
       // Configuration.holdBrowserOpen = true;
    }

    @CsvSource({
            "Philips company, www.philips.com",
            "Samsung, www.samsung.com",
            "Sony, www.sony.com"
    })
    //  @Disabled
    @DisplayName("Test Bing.com")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче bing.com по запросу {0}")
    void containsUrlCompanyOnTheSiteBingCom(String company, String url
    ) {

        $("#sb_form_q").setValue(company).pressEnter();
        // System.out.println($$("ol#b_results").first());
        $$("ol#b_results").first().shouldHave(text(url));
        //прошу уточнить возможно и  корректно ли использование кода ниже?
        if ($$("div#bnp_cookie_banner").size() > 0)
            $("#bnp_btn_accept").click();
    }


}
