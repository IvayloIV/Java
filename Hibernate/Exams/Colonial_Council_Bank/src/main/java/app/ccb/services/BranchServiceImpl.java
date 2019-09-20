package app.ccb.services;

import app.ccb.domain.dtos.json.BranchDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.base.FileUtil;
import app.ccb.util.base.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;
    private final FileUtil fileUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, ModelMapper modelMapper, Gson gson, ValidatorUtil validatorUtil, FileUtil fileUtil) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile("json/branches.json");
    }

    @Override
    public String importBranches(String branchesJson) throws IOException {
        StringBuilder sb = new StringBuilder();
        String branchesStr = this.readBranchesJsonFile();
        BranchDto[] branchDtos = this.gson.fromJson(branchesStr, BranchDto[].class);

        for (BranchDto branchDto : branchDtos) {
            Branch branch = this.modelMapper.map(branchDto, Branch.class);

            if (!this.validatorUtil.isValid(branch)) {
                sb.append("Error: Incorrect Data!")
                    .append(System.lineSeparator());
            }

            this.branchRepository.saveAndFlush(branch);
            sb.append(String.format("Successfully imported %s - %s.%n",
                    "Branch",
                    branch.getName()));
        }

        return sb.toString().trim();
    }
}
