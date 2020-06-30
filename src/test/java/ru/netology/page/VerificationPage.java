package ru.netology.page;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import ru.netology.data.User;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    public void EmptyCodeVerify()  {
         verifyButton.click();
        SelenideElement code = $("[data-test-id=code]");
        code.$("[class='input__sub']").shouldHave(exactText("Поле обязательно для заполнения"));
         }

    public void invalidCodeVerify()  {
        codeField.setValue(DataHelper.getNotVerificationCode());
        verifyButton.click();
        $(withText("Неверно указан код")).waitUntil(visible, 15000);
    }

    public DashboardPage validCodeVerify(User user) throws SQLException {
        codeField.setValue(DataHelper.getVerificationCode(user));
        verifyButton.click();
        return new DashboardPage();
    }
}
