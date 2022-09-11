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
        transfer.validTransfer(DataHelper.getInfoOfSecondCard(), DataHelper.getTransferAmount());
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard+Integer.parseInt(DataHelper.getTransferAmount().getTransfer()), currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard-Integer.parseInt(DataHelper.getTransferAmount().getTransfer()), currencyBalanceOfSecondCard);
    }

    @Test
    void shouldAmountSuccessTransferToSecondCardTest() {

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
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransferAmount());
        transfer.transfer();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard-Integer.parseInt(DataHelper.getTransferAmount().getTransfer()), currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard+Integer.parseInt(DataHelper.getTransferAmount().getTransfer()), currencyBalanceOfSecondCard);
    }

    @Test
    void shouldAmountSuccessCancelToFirstCardTest() {

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
        transfer.validTransfer(DataHelper.getInfoOfSecondCard(), DataHelper.getTransferAmount());
        transfer.cancel();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard, currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard, currencyBalanceOfSecondCard);
    }

    @Test
    void shouldAmountSuccessCancelToSecondCardTest() {

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
        transfer.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransferAmount());
        transfer.cancel();
        var currencyBalanceOfFirstCard = balance.getBalanceOfFirstCard();
        var currencyBalanceOfSecondCard = balance.getBalanceOfSecondCard();
        assertEquals(startBalanceOfFirstCard, currencyBalanceOfFirstCard);
        assertEquals(startBalanceOfSecondCard, currencyBalanceOfSecondCard);
    }

    @Test
    void shouldNotEmptyLoginFieldTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfoWithEmptyLogin());
        login.loginEmpty();
    }

    @Test
    void shouldNotEmptyPasswordFieldTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfoWithEmptyPassword());
        login.passwordEmpty();
    }

    @Test
    void shouldNotEmptyVerifyCodeFieldTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getEmptyVerificationCode());
        verify.fieldVerifyCodeEmpty();
    }

    @Test
    void shouldNotTransferWithCardSenderEmptyTest() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getVerificationCode());
        verify.nextPage();
        DashBoardPage balance = new DashBoardPage();
        balance.choiceFirstCard();
        MoneyTransferPage transfer = new MoneyTransferPage();
        transfer.validTransfer(DataHelper.getInfoEmptyCard(), DataHelper.getTransferAmount());
        transfer.cardSenderError();
    }

    @Test
    void shouldHaveErrorLoginWithRUSLetters() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfoWithRUSLetter());
        login.invalidLogin();
    }

    @Test
    void shouldHaveErrorLoginWithOtherSymbols() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfoWithSymbol());
        login.invalidLogin();
    }

    @Test
    void shouldHaveErrorPassword() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfoWithInvalidPassword());
        login.invalidPassword();
    }

    @Test
    void shouldHaveErrorCode() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getInvalidVerificationCode());
        verify.notification();
    }

    @Test
    void shouldHaveErrorCardSender0003() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getVerificationCode());
        verify.nextPage();
        DashBoardPage balance = new DashBoardPage();
        balance.choiceFirstCard();
        MoneyTransferPage transfer = new MoneyTransferPage();
        transfer.validTransfer(DataHelper.getInfoOfThirdCard(), DataHelper.getTransferAmount());
        transfer.cardSenderError();
    }

    @Test
    void shouldHaveErrorCardSender0002() {

        LoginPage login = new LoginPage();
        login.validLogin(DataHelper.getAuthInfo());
        login.nextPage();
        VerificationPage verify = new VerificationPage();
        verify.validCode(DataHelper.getVerificationCode());
        verify.nextPage();
        DashBoardPage balance = new DashBoardPage();
        balance.choiceFirstCard();
        MoneyTransferPage transfer = new MoneyTransferPage();
        transfer.validTransfer(DataHelper.getInfoWrongNumberCard(), DataHelper.getTransferAmount());
        transfer.cardSenderError();
    }
}
