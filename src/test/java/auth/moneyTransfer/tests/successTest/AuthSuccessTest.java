package auth.moneyTransfer.tests.successTest;

import auth.moneyTransfer.dataHelper.AlignmentClass;
import auth.moneyTransfer.dataHelper.DataHelper;
import auth.moneyTransfer.pages.*;
import com.codeborne.selenide.Configuration;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthSuccessTest {

    @BeforeEach
    void terminalStart() throws IOException {
        Runtime.getRuntime().exec("java -jar ./artifacts/app-ibank-build-for-testers.jar");
    }

    @BeforeEach
    void openPage() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldSuccessTransferToFirstCardTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();
        AlignmentClass alignment = new AlignmentClass();

        var moneyTransfer = 500;
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        alignment.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        balance.choiceFirstCard();
        transfer.validTransfer(DataHelper.getInfoOfSecondCard(), DataHelper.getTransfer(moneyTransfer));
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard+DataHelper.getTransfer(moneyTransfer).getTransfer(), currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard-DataHelper.getTransfer(moneyTransfer).getTransfer(), currencyBalanceOfSecondCard);
    }

    @Test
    void shouldAmountSuccessTransferToSecondCardTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();
        AlignmentClass alignment = new AlignmentClass();

        var moneyTransfer = 500;
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        alignment.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        balance.choiceSecondCard();
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransfer(moneyTransfer));
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard-DataHelper.getTransfer(moneyTransfer).getTransfer(), currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard+DataHelper.getTransfer(moneyTransfer).getTransfer(), currencyBalanceOfSecondCard);
    }

    @Test
    void shouldAmountSuccessCancelToFirstCardTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();
        AlignmentClass alignment = new AlignmentClass();

        var moneyTransfer = 500;
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        alignment.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        balance.choiceFirstCard();
        transfer.validTransfer(DataHelper.getInfoOfSecondCard(), DataHelper.getTransfer(moneyTransfer));
        transfer.cancel();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard, currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard, currencyBalanceOfSecondCard);
    }

    @Test
    void shouldAmountSuccessCancelToSecondCardTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();
        AlignmentClass alignment = new AlignmentClass();

        var moneyTransfer = 500;
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        alignment.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        balance.choiceSecondCard();
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransfer(moneyTransfer));
        transfer.cancel();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard, currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard, currencyBalanceOfSecondCard);
    }

    @Test
    void shouldNotEmptyLoginFieldTest() {

        LoginPage login = new LoginPage();

        login.loginEmpty(DataHelper.getAuthInfo());
    }

    @Test
    void shouldNotEmptyPasswordFieldTest() {

        LoginPage login = new LoginPage();

        login.passwordEmpty(DataHelper.getAuthInfo());
    }

    @Test
    void shouldNotEmptyVerifyCodeFieldTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();

        login.validLogin(DataHelper.getAuthInfo());
        verify.invalidCode("");
        verify.fieldVerifyCodeEmpty();
    }

    @Test
    void shouldNotTransferWithCardSenderEmptyTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();

        var moneyTransfer = 500;
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.choiceFirstCard();
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransfer(moneyTransfer));
        transfer.cardSenderEmpty();
    }

    @Test
    void shouldHaveErrorLoginWithRUSLetters() {

        LoginPage login = new LoginPage();

        login.invalidLogin(DataHelper.getAuthInfo(), "Вася");
    }

    @Test
    void shouldHaveErrorLoginWithOtherSymbols() {

        LoginPage login = new LoginPage();

        login.invalidLogin(DataHelper.getAuthInfo(), "vasya&$*(!$%");
    }

    @Test
    void shouldHaveErrorPassword() {

        LoginPage login = new LoginPage();

        login.invalidPassword(DataHelper.getAuthInfo());
    }

    @Test
    void shouldHaveErrorCode() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();

        login.validLogin(DataHelper.getAuthInfo());
        verify.invalidCode("11111");
        verify.notification();
    }

    @Test
    void shouldHaveErrorCardSender0003() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();

        var moneyTransfer = 500;
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.choiceFirstCard();
        transfer.InvalidCardSender("5559 0000 0000 0003", DataHelper.getTransfer(moneyTransfer));
    }

    @Test
    void shouldHaveErrorCardSender0002() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();

        var moneyTransfer = 500;
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.choiceFirstCard();
        transfer.InvalidCardSender("**** **** **** 0002", DataHelper.getTransfer(moneyTransfer));
    }
}
