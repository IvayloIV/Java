package app.ccb.services;

import app.ccb.domain.dtos.json.ClientDto;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.base.FileUtil;
import app.ccb.util.base.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;
    private final FileUtil fileUtil;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper, Gson gson, ValidatorUtil validatorUtil, FileUtil fileUtil) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return this.fileUtil.readFile("json/clients.json");
    }

    @Override
    public String importClients(String clients) throws IOException {
        StringBuilder sb = new StringBuilder();
        String clientsStr = this.readClientsJsonFile();
        ClientDto[] clientDtos = this.gson.fromJson(clientsStr, ClientDto[].class);

        for (ClientDto clientDto : clientDtos) {
            Client client = this.modelMapper.map(clientDto, Client.class);
            client.setFullName(clientDto.getFirstName() + " " + clientDto.getLastName());

            if (client.getEmployees() == null) {
                client.setEmployees(new HashSet<>());
            }

            client.getEmployees().add(this.employeeRepository.findByFullName(clientDto.getAppointedEmployee()));

            if (!this.validatorUtil.isValid(client)) {
                sb.append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
            }

            this.clientRepository.saveAndFlush(client);
            sb.append(String.format("Successfully imported %s - %s.%n",
                    "Client",
                    client.getFullName()));
        }

        return sb.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {
        StringBuilder sb = new StringBuilder();
        Client client = this.clientRepository.getClientWithTheMostCards().get(0);

        sb.append(String.format("Name: %s, Age: %d%n Account number: %s Balance: %.2f%n",
                client.getFullName(),
                client.getAge(),
                client.getBankAccount().getAccountNumber(),
                client.getBankAccount().getBalance()));

        client.getBankAccount().getCards().forEach(c ->
                sb.append(String.format("\tCard Number: %s, Card Status: %s%n",
                    c.getCardNumber(),
                    c.getCardStatus())));

        return sb.toString().trim();
    }
}
