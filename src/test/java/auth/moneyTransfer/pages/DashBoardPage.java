package auth.moneyTransfer.pages;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$x;

public class DashBoardPage {

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

    public int getBalanceOfFirstCard() {
        val text = balanceCardFirst.text();
        String[] words = text.split(" ");
        for (String word : words) ;
        val value = words[5];
        return Integer.parseInt(value);
    }

    public int getBalanceOfSecondCard() {
        val text = balanceCardSecond.text();
        String[] words = text.split(" ");
        for (String word : words) ;
        val value = words[5];
        return Integer.parseInt(value);
    }
}
