package Auth.MoneyTransfer.Steps;

import Auth.MoneyTransfer.Pages.DashBoardPage;
import Auth.MoneyTransfer.Pages.LoginPage;
import Auth.MoneyTransfer.Pages.VerificationPage;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;

public class TempleSteps {

    private static LoginPage loginPage;
    private static VerificationPage verificationPage;
    private static DashBoardPage dashBoardPage;

    @Пусть("открыть страницу с формой авторизации {String}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LoginPage.class);
    }

    @И("пользователь пытается авторизоваться с именем {String} и паролем {String}")
    public void loginWithNameAndPassword(String login, String password) {
        verificationPage = loginPage.validLoginCucumber(login, password);
    }

    @И("пользователь вводит полученный код: {String}")
    public void setValidCode(String verificationCode) {
        dashBoardPage = verificationPage.validCodeCucumber(verificationCode);
    }

    @Когда("пользователь нажимает кнопку пополнить, вводит сумму перевода {String} и карту отправитель {String}, нажимает кнопку пополнить и переходит в личный кабинет")
    public void setTransfer(String amountCucumber, String numberSecondCard) {
        dashBoardPage.transferCucumber(amountCucumber, numberSecondCard);
    }

    @Тогда("пользователь получает информацию о балансе карт")
    public void getCurrencyBalanceCard() {
        dashBoardPage.getCBalanceOfFirstCardCucumber();
    }
}
