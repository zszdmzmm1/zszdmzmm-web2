package day0918.bobing.service;

import day0908.MessageDTO;

public interface BoBingService {
    int getNumberOfDraws(int money);
    MessageDTO getResult();
}
