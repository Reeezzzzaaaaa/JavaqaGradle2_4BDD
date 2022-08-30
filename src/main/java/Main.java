import lombok.val;

class Main {

    public static void main(String[] args) {

        int balanceStart = Integer.parseInt("баланс: ");
        int balanceFinish = Integer.parseInt(" р.");

        String string = "**** **** **** 0001, баланс: 25000 р.";

// You can also use unicode for characters
        String[] words = string.split(" ");
        for (String currencyBalance : words) {
            System.out.println(currencyBalance);
            val start = currencyBalance.indexOf("баланс: ");
            val finish = currencyBalance.indexOf(" р.");
            String value = currencyBalance.substring(balanceStart + "баланс: ".length(), balanceFinish);
            System.out.println(value);
        }


    }

}