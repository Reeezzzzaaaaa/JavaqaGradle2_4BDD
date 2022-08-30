package Auth.MoneyTransfer.DataHelper;

import lombok.Value;

public class DataHelper {

    DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class numberCards {
        private String cardFirst;
        private String cardSecond;
        private String moneyTransfer;
    }

    public static numberCards getNumberCards(String moneyTransfer) {
        return new numberCards("5559 0000 0000 0001", "5559 0000 0000 0002",moneyTransfer);
    }
}
