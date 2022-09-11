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
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getVerificationCode());
        verify.nextPage();
        DashBoardPage balance = new DashBoardPage();
        AlignmentClass alignment = new AlignmentClass();
        alignment.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        balance.choiceFirstCard();
        MoneyTransferPage transfer = new MoneyTransferPage();
        transfer.validTransfer(DataHelper.getInfoOfSecondCard(), DataHelper.getCriticalTransferFromSecondCard());
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard, currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard, currencyBalanceOfSecondCard);
    }

    @Test
    void shouldHaveWrongTransferOrExceptionWithSendAboveLimitToSecondCardTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getVerificationCode());
        verify.nextPage();
        DashBoardPage balance = new DashBoardPage();
        AlignmentClass alignment = new AlignmentClass();
        alignment.alignmentTransfer();
        var startBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var startBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        balance.choiceSecondCard();
        MoneyTransferPage transfer = new MoneyTransferPage();
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getCriticalTransferFromFirstCard());
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard, currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard, currencyBalanceOfSecondCard);
    }

    @Test
    void shouldNotTransferWithAmountEmptyTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getVerificationCode());
        verify.nextPage();
        DashBoardPage balance = new DashBoardPage();
        balance.choiceFirstCard();
        MoneyTransferPage transfer = new MoneyTransferPage();
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransferEmpty());
        transfer.amountEmpty();
    }

    @Test
    void shouldHaveErrorCardSender0001() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getVerificationCode());
        verify.nextPage();
        DashBoardPage balance = new DashBoardPage();
        balance.choiceFirstCard();
        MoneyTransferPage transfer = new MoneyTransferPage();
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransferAmount());
        transfer.cardSenderError();
    }
}
