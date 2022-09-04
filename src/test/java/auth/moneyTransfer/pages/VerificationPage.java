package auth.moneyTransfer.pages;

import auth.moneyTransfer.dataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class VerificationPage {

    private SelenideElement codeField = $x("//*[@data-test-id='code']//input");
    private SelenideElement actionVerify = $x("//*[@data-test-id='action-verify']");
    private SelenideElement nextPage = $x("//*[@data-test-id='dashboard']");

    public DashBoardPage validCode (DataHelper.VerificationCode info) {
        codeField.setValue(info.getCode());
        actionVerify.click();
        nextPage.shouldBe(visible);
        return new DashBoardPage();
    }
}
