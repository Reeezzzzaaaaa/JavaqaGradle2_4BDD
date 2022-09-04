package auth.moneyTransfer.pages;

import auth.moneyTransfer.dataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class FieldEmptyPage {

    private final SelenideElement loginField = $x("//*[@data-test-id='login']//*[@name='login']");
    private final SelenideElement passwordField = $x("//*[@data-test-id='password']//*[@name='password']");
    private final SelenideElement actionLogin = $x("//*[@data-test-id='action-login']");
    private final SelenideElement fieldLoginEmpty = $x("//*[@data-test-id='login']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement fieldPasswordEmpty = $x("//*[@data-test-id='password']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement actionVerify = $x("//*[@data-test-id='action-verify']");
    private final SelenideElement fieldVerifyCodeEmpty = $x("//*[@data-test-id='code']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement cardPage = $x("//*[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']//button");
    private final SelenideElement amount = $x("//*[@data-test-id='amount']//input");
    private final SelenideElement transfer = $x("//*[@data-test-id='action-transfer']//span");
    private final SelenideElement fieldAmountEmpty = $x("//*[@data-test-id='amount']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement cardSender = $x("//*[@data-test-id='from']//input");
    private final SelenideElement fieldCardSender = $x("//*[@data-test-id='error-notification']//*[contains(text(), 'Ошибка')]");


    public FieldEmptyPage() {
    }

    public FieldEmptyPage loginEmpty (DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        passwordField.setValue(info.getPassword());
        actionLogin.click();
        fieldLoginEmpty.shouldHave(visible);
        return new FieldEmptyPage();
    }

    public FieldEmptyPage passwordEmpty (DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        actionLogin.click();
        fieldPasswordEmpty.shouldHave(visible);
        return new FieldEmptyPage();
    }

    public FieldEmptyPage codeEmpty () {
        actionVerify.click();
        fieldVerifyCodeEmpty.shouldBe(visible);
        return new FieldEmptyPage();
    }

    public FieldEmptyPage amountEmpty() {
        cardPage.click();
        amount.setValue("");
        cardSender.setValue("5559 0000 0000 0002");
        transfer.click();
        fieldAmountEmpty.shouldBe(visible);
        return new FieldEmptyPage();
    }

    public FieldEmptyPage cardSenderEmpty() {
        cardPage.click();
        amount.setValue("500");
        cardSender.setValue("");
        transfer.click();
        fieldCardSender.shouldBe(visible);
        return new FieldEmptyPage();
    }

}
