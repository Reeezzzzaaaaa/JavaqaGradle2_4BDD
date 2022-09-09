package auth.moneyTransfer.pages;

import auth.moneyTransfer.dataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {

    private SelenideElement codeField = $x("//*[@data-test-id='code']//input");
    private SelenideElement actionVerify = $x("//*[@data-test-id='action-verify']");
    private SelenideElement nextPage = $x("//*[@data-test-id='dashboard']");
    private final SelenideElement fieldVerifyCodeEmpty = $x("//*[@data-test-id='code']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement notificationError = $x("//*[@data-test-id='error-notification']//*[contains(text(), 'Ошибка')]");

    public DashBoardPage validCode(DataHelper.VerificationCode info) {
        codeField.setValue(info.getCode());
        actionVerify.click();
        nextPage.shouldBe(visible);
        return new DashBoardPage();
    }

    public VerificationPage invalidCode(String code) {
        codeField.setValue(code);
        actionVerify.click();
        return new VerificationPage();
    }

    public VerificationPage fieldVerifyCodeEmpty() {
        fieldVerifyCodeEmpty.shouldBe(visible);
        return new VerificationPage();
    }

    public VerificationPage notification() {
        notificationError.shouldBe(visible);
        return new VerificationPage();
    }
}
