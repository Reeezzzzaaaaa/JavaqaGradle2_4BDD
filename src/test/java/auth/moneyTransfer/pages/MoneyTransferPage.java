package auth.moneyTransfer.pages;

import auth.moneyTransfer.dataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class MoneyTransferPage {

    private final SelenideElement amount = $x("//*[@data-test-id='amount']//input");
    private final SelenideElement cardSender = $x("//*[@data-test-id='from']//input");
    private final SelenideElement transfer = $x("//*[@data-test-id='action-transfer']//span");
    private final SelenideElement cancel = $x("//*[@data-test-id='action-cancel']//span");

    public MoneyTransferPage() {
    }

    public DashBoardPage validTransfer(DataHelper.NumberCards info) {
        amount.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        amount.setValue(info.getMoneyTransfer());
        cardSender.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        cardSender.setValue(info.getCardNumber());
        return new DashBoardPage();
    }

//    public DashBoardPage validTransferToSecondCard (DataHelper.NumberCards info) {
//        amount.setValue(info.getMoneyTransfer());
//        cardSender.setValue(info.getCardNumber());
//        return new DashBoardPage();
//    }

    public DashBoardPage transfer() {
        transfer.click();
        return new DashBoardPage();
    }

    public DashBoardPage cancel() {
        cancel.click();
        return new DashBoardPage();
    }

    public DashBoardPage alignmentTransfer(String cardNumberFoAlignment, String alignmentTransfer) {
        amount.setValue(String.valueOf(alignmentTransfer));
        cardSender.setValue(cardNumberFoAlignment);
        return new DashBoardPage();
    }
}
