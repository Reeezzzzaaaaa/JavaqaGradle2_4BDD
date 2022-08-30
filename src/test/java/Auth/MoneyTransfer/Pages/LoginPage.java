package Auth.MoneyTransfer.Pages;

import Auth.MoneyTransfer.DataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    public void openPage() {open("http://localhost:9999/");}
    private SelenideElement loginField = $x("//*[@data-test-id='login']//*[@name='login']");
    private SelenideElement passwordField = $x("//*[@data-test-id='password']//*[@name='password']");
    private SelenideElement actionLogin = $x("//*[@data-test-id='action-login']");
    private SelenideElement nextPage = $x("//*[@id='root']//*[contains(text(), 'Необходимо подтверждение')]");

    public VerificationPage validLogin (DataHelper.AuthInfo info) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        loginField.setValue(info.getLogin());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        passwordField.setValue(info.getPassword());
        actionLogin.click();
        nextPage.shouldBe(visible);
        return new VerificationPage();
    }

    public VerificationPage validLoginCucumber (String login, String password) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        loginField.setValue(login);
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        passwordField.setValue(password);
        actionLogin.click();
        nextPage.shouldBe(visible);
        return new VerificationPage();
    }
}
