package auth.moneyTransfer.pages;

import auth.moneyTransfer.dataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MoneyTransferPage {

    private final SelenideElement amount = $x("//*[@data-test-id='amount']//input");
    private final SelenideElement cardSender = $x("//*[@data-test-id='from']//input");
    private final SelenideElement transfer = $x("//*[@data-test-id='action-transfer']//span");
    private final SelenideElement cancel = $x("//*[@data-test-id='action-cancel']//span");
    private final SelenideElement fieldAmountEmpty = $x("//*[@data-test-id='amount']//*[contains(text(), 'Поле обязательно')]");
    private final SelenideElement notificationError = $x("//*[@data-test-id='error-notification']//*[contains(text(), 'Ошибка')]");

    public MoneyTransferPage() {
    }

    public MoneyTransferPage validTransfer(DataHelper.CardInfo info, DataHelper.TransferInfo infoTransfer) {
        amount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        amount.setValue(String.valueOf(infoTransfer.getTransfer()));
        cardSender.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        cardSender.setValue(info.getCardNumber());
        return new MoneyTransferPage();
    }

    public DashBoardPage transfer() {
        transfer.click();
        return new DashBoardPage();
    }

    public DashBoardPage cancel() {
        cancel.click();
        return new DashBoardPage();
    }

    public MoneyTransferPage amountEmpty() {
        amount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        transfer.click();
        fieldAmountEmpty.shouldBe(visible);
        return new MoneyTransferPage();
    }

    public MoneyTransferPage cardSenderEmpty() {
        cardSender.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        transfer.click();
        notificationError.shouldBe(visible);
        return new MoneyTransferPage();
    }

    public MoneyTransferPage InvalidCardSender(String cardNumber, DataHelper.TransferInfo infoTransfer) {
        amount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        amount.setValue(String.valueOf(infoTransfer.getTransfer()));
        cardSender.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        cardSender.setValue(cardNumber);
        transfer.click();
        notificationError.shouldBe(visible);
        return new MoneyTransferPage();
    }
}
