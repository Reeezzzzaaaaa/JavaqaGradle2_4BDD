package Auth.MoneyTransfer.Tests;

import Auth.MoneyTransfer.DataHelper.DataHelper;
import Auth.MoneyTransfer.Pages.*;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthExceptionTest {

    LoginPage login = new LoginPage();
    VerificationPage verify = new VerificationPage();
    DashBoardPage balance = new DashBoardPage();
    FieldEmptyPage exception = new FieldEmptyPage();
    InvalidDataPage error = new InvalidDataPage();

    @BeforeEach
    void openPage() {
        Configuration.holdBrowserOpen=true;
        login.openPage();
    }

    @Test
    void shouldHaveTransferOrExceptionWithSend1_000_000ToFirstCardTest() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.validTransferToFirstCard(DataHelper.getNumberCards("1 000 000"));
        balance.cancel();
        assertEquals("1010000", balance.getCBalanceOfFirstCard());
        assertEquals("-990000", balance.getCBalanceOfSecondCard());
    }

    @Test
    void shouldHaveTransferOrExceptionWithSend1_000_000ToSecondCardTest() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.validTransferToSecondCard(DataHelper.getNumberCards("1 000 000"));
        balance.cancel();
        assertEquals("-990000", balance.getCBalanceOfFirstCard());
        assertEquals("1010000", balance.getCBalanceOfSecondCard());
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
    void shouldNotTransferWithAmountEmptyTest() {
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        exception.amountEmpty();
    }

    @Test
    void shouldNotTransferWithCardSenderEmptyTest() {
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        exception.cardSenderEmpty();
    }

    @Test
    void shouldHaveErrorCardSender0001() {
        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        error.InvalidCardSender0001();
    }
}
