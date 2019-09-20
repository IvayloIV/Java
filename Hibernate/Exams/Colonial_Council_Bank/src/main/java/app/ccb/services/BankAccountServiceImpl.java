package app.ccb.services;

import app.ccb.domain.dtos.xml.BankAccountRootXmlDto;
import app.ccb.domain.dtos.xml.BankAccountXmlDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.base.FileUtil;
import app.ccb.util.base.ValidatorUtil;
import app.ccb.util.base.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, FileUtil fileUtil, XmlParser xmlParser) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        return this.fileUtil.readFile("xml/bank-accounts.xml");
    }

    @Override
    public String importBankAccounts() throws JAXBException, IOException {
        StringBuilder sb = new StringBuilder();
        BankAccountRootXmlDto bankAccountRootXmlDto = this.xmlParser.importXML("bank-accounts", BankAccountRootXmlDto.class);

        for (BankAccountXmlDto bankAccountXmlDto : bankAccountRootXmlDto.getBankAccountDtos()) {
            BankAccount bankAccount = this.modelMapper.map(bankAccountXmlDto, BankAccount.class);
            List<Client> clients = this.clientRepository.findByFullName(bankAccountXmlDto.getClientName());
            Client client = null;
            if (clients.size() > 0) {
                client = clients.get(0);
            }
            bankAccount.setClient(client);

            if (!this.validatorUtil.isValid(bankAccount)) {
                sb.append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
            }

            this.bankAccountRepository.saveAndFlush(bankAccount);
            sb.append(String.format("Successfully imported %s - %s.%n",
                    "Bank Account",
                    bankAccount.getAccountNumber()));
        }

        return sb.toString().trim();
    }
}
