package Auth.MoneyTransfer.Tests;

import Auth.MoneyTransfer.DataHelper.DataHelper;
import Auth.MoneyTransfer.Pages.DashBoardPage;
import Auth.MoneyTransfer.Pages.LoginPage;
import Auth.MoneyTransfer.Pages.VerificationPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthSuccessTest {

    LoginPage login = new LoginPage();
    VerificationPage verify = new VerificationPage();
    DashBoardPage balance = new DashBoardPage();

    @BeforeEach
    void openPage() {
        Configuration.holdBrowserOpen=true;
        login.openPage();
    }

    @Test
    void successCancelToFirstCardTest() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.validTransferToFirstCard(DataHelper.getNumberCards("5 000"));
        balance.cancel();
        assertEquals("-989999", balance.getCBalanceOfFirstCard());
        assertEquals("1009999", balance.getCBalanceOfSecondCard());
    }

    @Test
    void successCancelToSecondCardTest() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.validTransferToSecondCard(DataHelper.getNumberCards("5 000"));
        balance.cancel();
        assertEquals("10000", balance.getCBalanceOfFirstCard());
        assertEquals("10000", balance.getCBalanceOfSecondCard());
    }

    @Test
    void successTransferToFirstCardTest() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.validTransferToFirstCard(DataHelper.getNumberCards("5 000"));
        balance.transfer();
        assertEquals("10000", balance.getCBalanceOfFirstCard());
        assertEquals("10000", balance.getCBalanceOfSecondCard());
    }

    @Test
    void successTransferToSecondCardTest() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.validTransferToSecondCard(DataHelper.getNumberCards("5 000"));
        balance.transfer();
        assertEquals("5000", balance.getCBalanceOfFirstCard());
        assertEquals("15000", balance.getCBalanceOfSecondCard());
    }

    @Test
    void successTransferToFirstCard999_999Test() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.validTransferToFirstCard(DataHelper.getNumberCards("999 999"));
        balance.transfer();
        assertEquals("10000", balance.getCBalanceOfFirstCard());
        assertEquals("10000", balance.getCBalanceOfSecondCard());
    }

    @Test
    void successTransferToSecondCard999_999Test() {

        login.validLogin(DataHelper.getAuthInfo());
        verify.validCode(DataHelper.getVerificationCode());
        balance.validTransferToSecondCard(DataHelper.getNumberCards("999 999"));
        balance.transfer();
        assertEquals("-989999", balance.getCBalanceOfFirstCard());
        assertEquals("1009999", balance.getCBalanceOfSecondCard());
    }
}
