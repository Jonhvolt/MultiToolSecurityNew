package sample.service;

import sample.beans.SimCard;

import java.util.List;

public interface SimCardService {

    List<SimCard> getSimCard();

    void saveSimCard(SimCard simCard);

    void deleteSimCard(SimCard simCard);
}
