package day0918.bobing.service;

import day0908.MessageDTO;
import day0918.bobing.GiftGetter;
import day0918.bobing.Util;
import day0918.bobing.pojo.Gift;

import java.util.ArrayList;
import java.util.List;

public class BoBingServiceImpl implements BoBingService{
    private BoBingServiceImpl() {
    }

    private static final class BoBingServiceImplHolder {
        private static final BoBingServiceImpl BO_BING_SERVICE = new BoBingServiceImpl();
    }

    public static BoBingServiceImpl getInstance() {
        return BoBingServiceImplHolder.BO_BING_SERVICE;
    }


    @Override
    public int getNumberOfDraws(int money) {
        int count = money / 50;
        if(count > 5){
            count = 5;
        }
        return count;
    }

    @Override
    public MessageDTO getResult() {
        MessageDTO messageDTO = new MessageDTO();
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            numList.add(Util.num(6) + 1);
        }
        GiftGetter giftGetter = new GiftGetter();
        messageDTO.setObject(giftGetter.getGift(numList));
        messageDTO.setMessage(numList.toString());
        return messageDTO;
    }
}
