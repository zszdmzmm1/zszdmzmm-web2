package day0918.bobing;


import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        int[] num = new int[]{1, 3, 2, 1, 5, 1};
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            numList.add(Util.num(6) + 1);
        }
        System.out.println(numList);
        GiftGetter giftGetter = GiftGetter.getInstance();
        System.out.println(giftGetter.getGift(numList));
    }
}
