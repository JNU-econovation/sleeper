package econo.app.sleeper.util;

import io.swagger.models.auth.In;

public class MoneyManager {

    public static Integer judgeMoney(String content){
        int moneyIncrease = 0;
        int length = content.length();

        if(length<1){
            return moneyIncrease;
        }else if(length < 10){
            moneyIncrease = 5;
            return moneyIncrease;
        }else{
            moneyIncrease = 10;
            return moneyIncrease;
        }
    }

    public static Integer earnMoney(Integer currentMoney, Integer earnedMoney){
        return currentMoney + earnedMoney;
    }

    public static Integer spendMoney(Integer currentMoney, Integer price){
        return currentMoney - price;
    }




}
