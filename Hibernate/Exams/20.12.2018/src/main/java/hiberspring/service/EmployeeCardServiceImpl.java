package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.json.EmployeeCardDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.service.base.EmployeeCardService;
import hiberspring.util.base.FileUtil;
import hiberspring.util.base.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private final EmployeeCardRepository employeeCardRepository;
    private final Gson gsonUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, Gson gsonUtil, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.employeeCardRepository = employeeCardRepository;
        this.gsonUtil = gsonUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        return this.fileUtil.readFile("employee-cards.json");
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        String employeeCards = this.readEmployeeCardsJsonFile();
        EmployeeCardDto[] employeeCardDtos = this.gsonUtil.fromJson(employeeCards, EmployeeCardDto[].class);

        for (EmployeeCardDto employeeCardDto : employeeCardDtos) {
            EmployeeCard employeeCard = this.modelMapper.map(employeeCardDto, EmployeeCard.class);
            EmployeeCard employeeCardDb = this.employeeCardRepository.getByNumber(employeeCard.getNumber());

            if (!this.validatorUtil.isValid(employeeCard) || employeeCardDb != null) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            this.employeeCardRepository.saveAndFlush(employeeCard);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                        "EmployeeCard",
                        employeeCard.getNumber()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
