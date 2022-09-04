package auth.moneyTransfer.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$x;

public class DashBoardPage {

    MoneyTransferPage transfer = new MoneyTransferPage();

    private final SelenideElement cardFirstPage = $x("//*[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']//button");
    private final SelenideElement cardSecondPage = $x("//*[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']//button");
    private final SelenideElement balanceCardFirst = $x("//*[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private final SelenideElement balanceCardSecond = $x("//*[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashBoardPage() {
    }

    public DashBoardPage choiceFirstCard() {
        cardFirstPage.click();
        return new DashBoardPage();
    }

    public DashBoardPage choiceSecondCard() {
        cardSecondPage.click();
        return new DashBoardPage();
    }

    public String getBalanceOfFirstCard() {
        val text = balanceCardFirst.text();
        String[] words = text.split(" ");
        for (String word : words) ;
        val value = words[5];
        if ( Integer.parseInt(value) < 0 ) {
            return null;
        }
        return value;
    }

    public String getBalanceOfSecondCard() {
        val text = balanceCardSecond.text();
        String[] words = text.split(" ");
        for (String word : words) ;
        val value = words[5];
        if ( Integer.parseInt(value) < 0 ) {
            return null;
        }
        return value;
    }

    public String getStartBalanceOfFirstCard() {
        val text = balanceCardFirst.text();
        String[] words = text.split(" ");
        for (String word : words) ;
        val value = words[5];
        return value;
    }

    public String getStartBalanceOfSecondCard() {
        val text = balanceCardSecond.text();
        String[] words = text.split(" ");
        for (String word : words) ;
        val value = words[5];
        return value;
    }

    public DashBoardPage alignmentTransfer() {
        int alignmentTransfer = 10000-Integer.parseInt(getStartBalanceOfFirstCard());
        if ( alignmentTransfer < 0 ) {
            choiceSecondCard();
            transfer.alignmentTransfer("5559 0000 0000 0001", String.valueOf(alignmentTransfer));
            transfer.transfer();
        } else {
            choiceFirstCard();
            transfer.alignmentTransfer("5559 0000 0000 0002", String.valueOf(alignmentTransfer));
            transfer.transfer();
        }
        return new DashBoardPage();
    }
}
