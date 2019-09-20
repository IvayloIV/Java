package app.ccb.services;

import app.ccb.domain.dtos.xml.CardRootXmlDto;
import app.ccb.domain.dtos.xml.CardXmlDto;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
import app.ccb.util.base.FileUtil;
import app.ccb.util.base.ValidatorUtil;
import app.ccb.util.base.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil, FileUtil fileUtil, XmlParser xmlParser) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() throws IOException {
        return this.fileUtil.readFile("xml/cards.xml");
    }

    @Override
    public String importCards() throws JAXBException, IOException {
        StringBuilder sb = new StringBuilder();
        CardRootXmlDto cardRootXmlDtos = this.xmlParser.importXML("cards", CardRootXmlDto.class);

        for (CardXmlDto cardXmlDto : cardRootXmlDtos.getCardXmlDtos()) {
            Card card = this.modelMapper.map(cardXmlDto, Card.class);
            card.setBankAccount(this.bankAccountRepository.findByAccountNumber(cardXmlDto.getAccountNumber()));

            if (!this.validatorUtil.isValid(card)) {
                sb.append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
            }

            this.cardRepository.saveAndFlush(card);
            sb.append(String.format("Successfully imported %s - %s.%n",
                    "Card",
                    card.getCardNumber()));
        }

        return sb.toString().trim();
    }
}
