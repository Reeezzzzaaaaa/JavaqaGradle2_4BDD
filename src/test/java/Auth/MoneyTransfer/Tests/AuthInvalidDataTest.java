package Auth.MoneyTransfer.Tests;

import Auth.MoneyTransfer.DataHelper.DataHelper;
import Auth.MoneyTransfer.Pages.DashBoardPage;
import Auth.MoneyTransfer.Pages.InvalidDataPage;
import Auth.MoneyTransfer.Pages.LoginPage;
import Auth.MoneyTransfer.Pages.VerificationPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthInvalidDataTest {

    LoginPage login = new LoginPage();
    VerificationPage verify = new VerificationPage();
    DashBoardPage balance = new DashBoardPage();
    InvalidDataPage error = new InvalidDataPage();

    @BeforeEach
    void openPage() {
        Configuration.holdBrowserOpen=true;
        login.openPage();
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
