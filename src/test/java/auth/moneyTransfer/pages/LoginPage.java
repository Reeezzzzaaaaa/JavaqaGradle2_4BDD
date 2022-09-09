package auth.moneyTransfer.pages;

import auth.moneyTransfer.dataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement loginField = $x("//*[@data-test-id='login']//*[@name='login']");
    private SelenideElement passwordField = $x("//*[@data-test-id='password']//*[@name='password']");
    private SelenideElement actionLogin = $x("//*[@data-test-id='action-login']");
    private SelenideElement nextPage = $x("//*[@id='root']//*[contains(text(), 'Необходимо подтверждение')]");
    private final SelenideElement fieldLoginEmpty = $x("//*[@data-test-id='login']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement fieldPasswordEmpty = $x("//*[@data-test-id='password']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement notificationError = $x("//*[@data-test-id='error-notification']//*[contains(text(), 'Ошибка')]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        loginField.setValue(info.getLogin());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        passwordField.setValue(info.getPassword());
        actionLogin.click();
        nextPage.shouldBe(visible);
        return new VerificationPage();
    }

    public LoginPage loginEmpty(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        passwordField.setValue(info.getPassword());
        actionLogin.click();
        fieldLoginEmpty.shouldHave(visible);
        return new LoginPage();
    }

    public LoginPage passwordEmpty(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        actionLogin.click();
        fieldPasswordEmpty.shouldHave(visible);
        return new LoginPage();
    }

    public LoginPage invalidLogin(DataHelper.AuthInfo info, String login) {
        loginField.setValue(info.getLogin());
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        loginField.setValue(login);
        passwordField.setValue(info.getPassword());
        actionLogin.click();
        notificationError.shouldHave(visible);
        return new LoginPage();
    }

    public LoginPage invalidPassword(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        passwordField.setValue("123qwerty");
        actionLogin.click();
        notificationError.shouldHave(visible);
        return new LoginPage();
    }
}
