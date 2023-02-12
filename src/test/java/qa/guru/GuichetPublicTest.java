package qa.guru;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;


public class GuichetPublicTest {


    public enum Locale {
        en, de
    }

    static Stream<Arguments> guichetLocaleDataProvider() {
        return Stream.of(
                Arguments.of(Locale.en, List.of("Starting up & Development",
                        "Urban planning & Environment",
                        "Financing & Support measures",
                        "Health & Safety / Social Security",
                        "Human Resources",
                        "Taxation",
                        "Retail trade",
                        "Accounting & Legal obligations",
                        "International Trade",
                        "Preservation & Termination of a business",
                        "Procedures by sector of activity",
                        "Brexit")),
                Arguments.of(Locale.de, List.of("Gründung & Ausbau von Unternehmen",
                        "Stadtplanung & Umwelt",
                        "Finanzierung & Beihilfen",
                        "Gesundheit & Sicherheit / Sozialversicherung",
                        "Personalwesen",
                        "Steuern",
                        "Handel",
                        "Geschäftsführung & Rechnungswesen",
                        "Internationaler Handel",
                        "Sanierung & Geschäftsauflösung",
                        "Vorgänge nach Tätigkeitsbereich",
                        "Brexit"))
        );
    }

    @MethodSource("guichetLocaleDataProvider")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @Tag("BLOCKER")
    void selenideSiteShouldContainAllOfButtonsForGivenLocale(
            Locale locale,
            List<String> buttons
    ) {
        open("https://guichet.public.lu/");
        $("a[lang='" + locale.name() + "']").click();
        $("div[class='splash-item splash-item--entreprises']")
                .$$("li[class='category']")
                .shouldHave(CollectionCondition.texts(buttons));

    }

}