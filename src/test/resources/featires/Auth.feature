#language:ru

  Функциональность: Перевод средств

    Сценарий: Перевод средств со второй карты на первую (позитивный)
      Пусть открыть страницу с формой авторизации "http://localhost:9999/"
      Пусть пользователь пытается авторизоваться с именем <login> и паролем <password>
      Пусть пользователь вводит полученный код: <verificationCode>
      Когда пользователь нажимает кнопку пополнить, вводит сумму перевода <amountCucumber> и карту отправитель <numberSecondCard>, нажимает кнопку пополнить и переходит в личный кабинет
      Тогда пользователь получает информацию о балансе карт
      | url | login | password | verificationCode | amountCucumber | numberSecondCard |
      | "http://localhost:9999/" || "vasya" | "qwerty123" | "12345" | "5 000" | "5559 0000 0000 0002" |