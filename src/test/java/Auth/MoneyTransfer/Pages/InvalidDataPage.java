package Auth.MoneyTransfer.Pages;

import Auth.MoneyTransfer.DataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class InvalidDataPage {

    private final SelenideElement loginField = $x("//*[@data-test-id='login']//*[@name='login']");
    private final SelenideElement passwordField = $x("//*[@data-test-id='password']//*[@name='password']");
    private final SelenideElement actionLogin = $x("//*[@data-test-id='action-login']");
    private final SelenideElement codeField = $x("//*[@data-test-id='code']//input");
    private final SelenideElement actionVerify = $x("//*[@data-test-id='action-verify']");
    private final SelenideElement fieldVerifyCodeEmpty = $x("//*[@data-test-id='code']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement cardPage = $x("//*[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']//button");
    private final SelenideElement amount = $x("//*[@data-test-id='amount']//input");
    private final SelenideElement transfer = $x("//*[@data-test-id='action-transfer']//span");
    private final SelenideElement fieldAmountEmpty = $x("//*[@data-test-id='amount']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement cardSender = $x("//*[@data-test-id='from']//input");
    private final SelenideElement notificationError = $x("//*[@data-test-id='error-notification']//*[contains(text(), 'Ошибка')]");


    public InvalidDataPage() {
    }

    public InvalidDataPage invalidLoginWithRUSLetter (DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        loginField.setValue("Вася");
        passwordField.setValue(info.getPassword());
        actionLogin.click();
        notificationError.shouldHave(visible);
        return new InvalidDataPage();
    }

    public InvalidDataPage invalidLoginWithOtherSymbol (DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        loginField.setValue("vasya&$*(!$%");
        passwordField.setValue(info.getPassword());
        actionLogin.click();
        notificationError.shouldHave(visible);
        return new InvalidDataPage();
    }

    public InvalidDataPage invalidPassword (DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        passwordField.setValue("123qwerty");
        actionLogin.click();
        notificationError.shouldHave(visible);
        return new InvalidDataPage();
    }


    public InvalidDataPage invalidCode (DataHelper.VerificationCode info) {
        codeField.setValue(info.getCode());
        codeField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        codeField.setValue("11111");
        actionVerify.click();
        notificationError.shouldHave(visible);
        return new InvalidDataPage();
    }

    public InvalidDataPage InvalidCardSender0001() {
        cardPage.click();
        amount.setValue("500");
        cardSender.setValue("5559 0000 0000 0001");
        transfer.click();
        notificationError.shouldBe(visible);
        return new InvalidDataPage();
    }

    public InvalidDataPage InvalidCardSender0003() {
        cardPage.click();
        amount.setValue("500");
        cardSender.setValue("5559 0000 0000 0003");
        transfer.click();
        notificationError.shouldBe(visible);
        return new InvalidDataPage();
    }

    public InvalidDataPage InvalidCardSender0002() {
        cardPage.click();
        amount.setValue("500");
        cardSender.setValue("**** **** **** 0002");
        transfer.click();
        notificationError.shouldBe(visible);
        return new InvalidDataPage();
    }

}
