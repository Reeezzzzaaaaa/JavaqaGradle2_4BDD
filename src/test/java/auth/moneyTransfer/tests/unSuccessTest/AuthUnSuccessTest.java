package auth.moneyTransfer.tests.unSuccessTest;

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

public class AuthUnSuccessTest {

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
    void shouldHaveWrongTransferOrExceptionWithSendAboveLimitToFirstCardTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();
        AlignmentClass alignment = new AlignmentClass();

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        alignment.alignmentTransfer();
        var moneyTransfer = balance.getBalanceOfSecondCard()+1;
        var startBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        balance.choiceFirstCard();
        transfer.validTransfer(DataHelper.getInfoOfSecondCard(), DataHelper.getTransfer(moneyTransfer));
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard, currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard, currencyBalanceOfSecondCard);
    }

    @Test
    void shouldHaveWrongTransferOrExceptionWithSendAboveLimitToSecondCardTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();
        AlignmentClass alignment = new AlignmentClass();

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        alignment.alignmentTransfer();
        var moneyTransfer = balance.getBalanceOfFirstCard()+1;
        var startBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        balance.choiceSecondCard();
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransfer(moneyTransfer));
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard, currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard, currencyBalanceOfSecondCard);
    }

    @Test
    void shouldNotTransferWithAmountEmptyTest() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.choiceFirstCard();
        transfer.amountEmpty();
    }

    @Test
    void shouldHaveErrorCardSender0001() {

        LoginPage login = new LoginPage();
        VerificationPage verify = new VerificationPage();
        DashBoardPage balance = new DashBoardPage();
        MoneyTransferPage transfer = new MoneyTransferPage();

        var moneyTransfer = 500;
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.choiceFirstCard();
        transfer.InvalidCardSender("5559 0000 0000 0001", DataHelper.getTransfer(moneyTransfer));
    }
}
