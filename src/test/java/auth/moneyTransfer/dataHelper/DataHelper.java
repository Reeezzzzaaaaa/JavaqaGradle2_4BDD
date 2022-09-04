package auth.moneyTransfer.dataHelper;

import lombok.Value;

public class DataHelper {

    DataHelper() {
    }

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
    public static class NumberCards {
        private String cardNumber;
        private String moneyTransfer;
    }

    public static NumberCards getNumberCards(String cardNumber, String moneyTransfer) {
        return new NumberCards(cardNumber, moneyTransfer);
    }

    @Value
    public static class AlignmentTransfer {
        private String cardNumberFoAlignment;
    }

    public static AlignmentTransfer getAlignmentTransfer(String cardNumberFoAlignment) {
        return new AlignmentTransfer(cardNumberFoAlignment);
    }
}
