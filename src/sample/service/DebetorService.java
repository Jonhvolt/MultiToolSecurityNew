package sample.service;

import sample.beans.Debetors;

import java.util.List;

public interface DebetorService {

    List<Debetors> getDebetor();

    void saveDebetor(Debetors debetor);

    void deleteDebetor(Debetors debetor);
}
