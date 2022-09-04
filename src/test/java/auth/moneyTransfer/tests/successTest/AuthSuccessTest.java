package auth.moneyTransfer.tests.successTest;

import auth.moneyTransfer.dataHelper.DataHelper;
import auth.moneyTransfer.pages.*;
import com.codeborne.selenide.Configuration;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthSuccessTest {

    @BeforeEach
    void terminalStart() throws IOException {
        Runtime.getRuntime().exec("java -jar ./artifacts/app-ibank-build-for-testers.jar");
    }

    @BeforeEach
    void openPage() {
        Configuration.holdBrowserOpen=true;
        login.openPage();
    }

    LoginPage login = new LoginPage();
    VerificationPage verify = new VerificationPage();
    DashBoardPage balance = new DashBoardPage();
    MoneyTransferPage transfer = new MoneyTransferPage();
    FieldEmptyPage exception = new FieldEmptyPage();
    InvalidDataPage error = new InvalidDataPage();

    @Test
    void shouldSuccessTransferToFirstCardTest() {

        String moneyTransfer = String.valueOf(500);
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getStartBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getStartBalanceOfSecondCard();
        balance.choiceFirstCard();
        transfer.validTransfer(DataHelper.getNumberCards("5559 0000 0000 0002",moneyTransfer));
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(Integer.parseInt(currencyBalanceOfFirstCard), Integer.parseInt(startBalanceOfFirstCard) + Integer.parseInt(moneyTransfer));
        assertEquals(Integer.parseInt(currencyBalanceOfSecondCard), Integer.parseInt(startBalanceOfSecondCard) - Integer.parseInt(moneyTransfer));
    }

    @Test
    void shouldAmountSuccessTransferToSecondCardTest() {

        String moneyTransfer = String.valueOf(500);
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getStartBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getStartBalanceOfSecondCard();
        balance.choiceSecondCard();
        transfer.validTransfer(DataHelper.getNumberCards("5559 0000 0000 0001",moneyTransfer));
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(Integer.parseInt(currencyBalanceOfFirstCard), Integer.parseInt(startBalanceOfFirstCard) - Integer.parseInt(moneyTransfer));
        assertEquals(Integer.parseInt(currencyBalanceOfSecondCard), Integer.parseInt(startBalanceOfSecondCard) + Integer.parseInt(moneyTransfer));
    }

    @Test
    void shouldAmountSuccessCancelToFirstCardTest() {

        String moneyTransfer = String.valueOf(500);
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getStartBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getStartBalanceOfSecondCard();
        balance.choiceFirstCard();
        transfer.validTransfer(DataHelper.getNumberCards("5559 0000 0000 0002",moneyTransfer));
        transfer.cancel();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(Integer.parseInt(currencyBalanceOfFirstCard), Integer.parseInt(startBalanceOfFirstCard));
        assertEquals(Integer.parseInt(currencyBalanceOfSecondCard), Integer.parseInt(startBalanceOfSecondCard));
    }

    @Test
    void shouldAmountSuccessCancelToSecondCardTest() {

        String moneyTransfer = String.valueOf(500);
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getStartBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getStartBalanceOfSecondCard();
        balance.choiceSecondCard();
        transfer.validTransfer(DataHelper.getNumberCards("5559 0000 0000 0001", moneyTransfer));
        transfer.cancel();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(Integer.parseInt(currencyBalanceOfFirstCard), Integer.parseInt(startBalanceOfFirstCard));
        assertEquals(Integer.parseInt(currencyBalanceOfSecondCard), Integer.parseInt(startBalanceOfSecondCard));
    }

    @Test
    void shouldNotEmptyLoginFieldTest() {
        exception.loginEmpty(DataHelper.getAuthInfo());
    }

    @Test
    void shouldNotEmptyPasswordFieldTest() {
        exception.passwordEmpty(DataHelper.getAuthInfo());
    }

    @Test
    void shouldNotEmptyVerifyCodeFieldTest() {
        login.validLogin(DataHelper.getAuthInfo());
        exception.codeEmpty();
    }

    @Test
    void shouldNotTransferWithCardSenderEmptyTest() {
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        exception.cardSenderEmpty();
    }

    @Test
    void shouldHaveErrorLoginWithRUSLetters() {
        error.invalidLoginWithRUSLetter(DataHelper.getAuthInfo());
    }

    @Test
    void shouldHaveErrorLoginWithOtherSymbols() {
        error.invalidLoginWithOtherSymbol(DataHelper.getAuthInfo());
    }

    @Test
    void shouldHaveErrorPassword() {
        error.invalidPassword(DataHelper.getAuthInfo());
    }

    @Test
    void shouldHaveErrorCode() {
        login.validLogin(DataHelper.getAuthInfo());
        error.invalidCode(DataHelper.getVerificationCode());
    }

    @Test
    void shouldHaveErrorCardSender0003() {
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        error.InvalidCardSender0003();
    }

    @Test
    void shouldHaveErrorCardSender0002() {
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        error.InvalidCardSender0002();
    }
}
