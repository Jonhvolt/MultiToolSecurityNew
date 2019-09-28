package sample.service;

import sample.beans.Client;

import java.util.List;

public interface ClientService {
    List<Client> getClient();

    void saveClient(Client client);

    void deleteClient(Client client);
}
