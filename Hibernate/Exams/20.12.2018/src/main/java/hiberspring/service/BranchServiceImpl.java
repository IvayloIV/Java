package hiberspring.service;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.json.BranchDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.base.BranchService;
import hiberspring.util.base.FileUtil;
import hiberspring.util.base.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final Gson gsonUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, Gson gsonUtil, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.gsonUtil = gsonUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile("branches.json");
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        String branchesStr = this.readBranchesJsonFile();
        BranchDto[] branchDtos = this.gsonUtil.fromJson(branchesStr, BranchDto[].class);

        for (BranchDto branchDto : branchDtos) {
            Branch branch = this.modelMapper.map(branchDto, Branch.class);
            Town town = this.townRepository.getByName(branchDto.getTown());
            branch.setTown(town);

            if (!this.validatorUtil.isValid(branch)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            this.branchRepository.saveAndFlush(branch);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                    "Branch",
                    branch.getName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
