package com.tanasobbartar.individual.client;

public interface ClientService {

    ClientDto findById(Long id);

    ClientDto saveClient(NewClientDto clientDto);

}
