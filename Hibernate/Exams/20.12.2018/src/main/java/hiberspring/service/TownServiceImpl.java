package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.json.TownDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.base.TownService;
import hiberspring.util.base.FileUtil;
import hiberspring.util.base.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final Gson gsonUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, Gson gsonUtil, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.townRepository = townRepository;
        this.gsonUtil = gsonUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile("towns.json");
    }

    @Override
    public String importTowns(String townsFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        String townsStr = this.readTownsJsonFile();
        TownDto[] townDtos = this.gsonUtil.fromJson(townsStr, TownDto[].class);

        for (TownDto townDto : townDtos) {
            Town town = this.modelMapper.map(townDto, Town.class);

            if (!this.validatorUtil.isValid(town)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            this.townRepository.saveAndFlush(town);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                        "Town",
                        town.getName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
