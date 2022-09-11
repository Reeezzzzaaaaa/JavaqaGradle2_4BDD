package auth.moneyTransfer.dataHelper;

import auth.moneyTransfer.pages.DashBoardPage;
import auth.moneyTransfer.pages.MoneyTransferPage;

public class AlignmentClass {

    public AlignmentClass alignmentTransfer() {

        DashBoardPage currentBalance = new DashBoardPage();
        MoneyTransferPage money = new MoneyTransferPage();
        int alignmentTransfer = 10000-currentBalance.getBalanceOfFirstCard();

        if ( alignmentTransfer < 0 ) {
            currentBalance.choiceSecondCard();
            money.validTransfer(DataHelper.getInfoOfFirstCard(), DataHelper.getTransfer(String.valueOf(alignmentTransfer)));
            money.transfer();
        } else {
            currentBalance.choiceFirstCard();
            money.validTransfer(DataHelper.getInfoOfSecondCard(), DataHelper.getTransfer(String.valueOf(alignmentTransfer)));
            money.transfer();
        }
        return new AlignmentClass();
    }
}
