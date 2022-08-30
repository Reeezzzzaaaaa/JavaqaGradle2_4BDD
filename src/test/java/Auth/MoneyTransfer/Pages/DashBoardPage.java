package Auth.MoneyTransfer.Pages;

import Auth.MoneyTransfer.DataHelper.DataHelper;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$x;

public class DashBoardPage {

    private final SelenideElement cardFirstPage = $x("//*[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']//button");
    private final SelenideElement cardSecondPage = $x("//*[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']//button");
    private final SelenideElement amount = $x("//*[@data-test-id='amount']//input");
    private final SelenideElement cardSender = $x("//*[@data-test-id='from']//input");
    private final SelenideElement transfer = $x("//*[@data-test-id='action-transfer']//span");
    private final SelenideElement cancel= $x("//*[@data-test-id='action-cancel']//span");
    private final SelenideElement balanceCardFirst = $x("//*[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private final SelenideElement balanceCardSecond = $x("//*[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashBoardPage() {
    }

    public DashBoardPage validTransferToFirstCard (DataHelper.numberCards info) {
        cardFirstPage.click();
        amount.setValue(info.getMoneyTransfer());
        cardSender.setValue(info.getCardSecond());
        return new DashBoardPage();
    }

    public DashBoardPage validTransferToSecondCard (DataHelper.numberCards info) {
        cardSecondPage.click();
        amount.setValue(info.getMoneyTransfer());
        cardSender.setValue(info.getCardFirst());
        return new DashBoardPage();
    }

    public DashBoardPage transfer () {
        transfer.click();
        return new DashBoardPage();
    }

    public DashBoardPage cancel () {
        cancel.click();
        return new DashBoardPage();
    }

    public String getCBalanceOfFirstCard() {
        val text = balanceCardFirst.text();
        String[] words = text.split(" ");
        for (String word : words);
        val value = words[5];
        return value;
    }

    public String getCBalanceOfSecondCard() {
        val text = balanceCardSecond.text();
        String[] words = text.split(" ");
        for (String word : words);
        val value = words[5];
        return value;
    }
}
