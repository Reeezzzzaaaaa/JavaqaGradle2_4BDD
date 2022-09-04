package auth.moneyTransfer.tests.unSuccessTest;

import auth.moneyTransfer.dataHelper.DataHelper;
import auth.moneyTransfer.pages.*;
import com.codeborne.selenide.Configuration;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthUnSuccessTest {

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
    void shouldHaveWrongTransferOrExceptionWithSendAboveLimitToFirstCardTest() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.alignmentTransfer();
        String moneyTransfer = balance.getStartBalanceOfFirstCard() + 1;
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
    void shouldHaveWrongTransferOrExceptionWithSendAboveLimitToSecondCardTest() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.alignmentTransfer();
        String moneyTransfer = String.valueOf((Integer.parseInt(balance.getBalanceOfFirstCard()) + 1));
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
    void shouldNotTransferWithAmountEmptyTest() {
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        exception.amountEmpty();
    }

    @Test
    void shouldHaveErrorCardSender0001() {
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        error.InvalidCardSender0001();
    }
}
