package day0918.bobing;

import day0918.bobing.pojo.Gift;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiftGetter {

    Gift gift1 = new Gift(1, "5元", "一秀");
    Gift gift2 = new Gift(2, "10元", "二举");
    Gift gift3 = new Gift(3, "20元", "四进");
    Gift gift4 = new Gift(4, "25元", "三红");
    Gift gift5 = new Gift(5, "50元", "对堂");
    Gift gift6 = new Gift(6, "100元", "四红");
    Gift gift7 = new Gift(7, "200元", "五子");
    Gift gift8 = new Gift(8, "400元", "五红");
    Gift gift9 = new Gift(9, "500元", "六勃黑");
    Gift gift10 = new Gift(10, "800元", "遍地锦");
    Gift gift11 = new Gift(11, "1000元", "六勃红");
    Gift gift12 = new Gift(12, "1500元", "状元插金花");

    public Gift getGift(List<Integer> numList) {
        Map<Integer, Integer> numMap = new HashMap<>();
        numMap.put(1, 0);
        numMap.put(2, 0);
        numMap.put(3, 0);
        numMap.put(4, 0);
        numMap.put(5, 0);
        numMap.put(6, 0);
        numList.forEach(i -> numMap.put(i, numMap.get(i) + 1));
        if (numMap.get(4) == 4 && numMap.get(1) == 2) {
            return gift12;
        } else if (numMap.get(4) == 6) {
            return gift11;
        } else if (numMap.get(1) == 6) {
            return gift10;
        } else if (numMap.entrySet().stream().filter(e -> e.getValue() == 6).count() == 1) {
            return gift9;
        } else if (numMap.get(4) == 5) {
            return gift8;
        } else if (numMap.entrySet().stream().filter(e -> e.getValue() == 5).count() == 1) {
            return gift7;
        } else if (numMap.get(4) == 4) {
            return gift6;
        } else if (numMap.entrySet().stream().allMatch(e -> e.getValue() == 1)) {
            return gift5;
        } else if (numMap.get(4) == 3) {
            return gift4;
        } else if (numMap.get(2) == 4) {
            return gift3;
        } else if (numMap.get(4) == 2){
            return gift2;
        } else if (numMap.get(4) == 1){
            return gift1;
        }
        return null;
    }
}
