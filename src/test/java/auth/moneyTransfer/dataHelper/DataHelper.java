package auth.moneyTransfer.dataHelper;

import auth.moneyTransfer.pages.DashBoardPage;
import lombok.Value;

public class DataHelper {

    DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithSymbol() {
        return new AuthInfo("vasya&$*(!$%", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithRUSLetter() {
        return new AuthInfo("Вася", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithEmptyLogin() {
        return new AuthInfo("", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithInvalidPassword() {
        return new AuthInfo("vasya", "123qwerty");
    }

    public static AuthInfo getAuthInfoWithEmptyPassword() {
        return new AuthInfo("vasya", "");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static VerificationCode getInvalidVerificationCode() {
        return new VerificationCode("11111");
    }

    public static VerificationCode getEmptyVerificationCode() {
        return new VerificationCode("");
    }

    public static CardInfo getInfoOfFirstCard() {
        return new CardInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardInfo getInfoOfSecondCard() {
        return new CardInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static CardInfo getInfoOfThirdCard() {
        return new CardInfo("5559 0000 0000 0003", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static CardInfo getInfoWrongNumberCard() {
        return new CardInfo("**** **** **** 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static CardInfo getInfoEmptyCard() {
        return new CardInfo("", "");
    }

    public static TransferInfo getTransfer(String transfer) {
        return new TransferInfo(transfer);
    }

    public static TransferInfo getTransferAmount() {
        return new TransferInfo("500");
    }

    public static TransferInfo getTransferEmpty() {
        return new TransferInfo("");
    }

    public static TransferInfo getCriticalTransferFromFirstCard() {
        DashBoardPage balance = new DashBoardPage();
        return new TransferInfo(String.valueOf(balance.getBalanceOfFirstCard()+1));
    }

    public static TransferInfo getCriticalTransferFromSecondCard() {
        DashBoardPage balance = new DashBoardPage();
        return new TransferInfo(String.valueOf(balance.getBalanceOfSecondCard()+1));
    }

    @Value
    public static class TransferInfo {
        private String transfer;
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String cardId;
    }
}
