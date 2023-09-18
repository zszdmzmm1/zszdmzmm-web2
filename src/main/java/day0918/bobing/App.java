package day0918.bobing;


import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        int[] nums = new int[6];
        for(int i = 0; i < 6; i++){
            nums[i] = Util.num(6);
        }
        System.out.println(Arrays.toString(nums));
    }
}
