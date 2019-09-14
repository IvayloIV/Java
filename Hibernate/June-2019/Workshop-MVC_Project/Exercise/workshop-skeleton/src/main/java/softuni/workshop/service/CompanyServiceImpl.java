package softuni.workshop.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.importDto.CompanyDto;
import softuni.workshop.domain.dtos.importDto.CompanyRootDto;
import softuni.workshop.domain.dtos.jsonDtos.exportDto.CompanyJsonDto;
import softuni.workshop.domain.entities.Company;
import softuni.workshop.repository.CompanyRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.GsonUtil;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final GsonUtil gsonUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, GsonUtil gsonUtil, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil, XmlParser xmlParser) {
        this.companyRepository = companyRepository;
        this.gsonUtil = gsonUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public void importCompanies() throws JAXBException, IOException {
        CompanyRootDto companiesRootDto = this.xmlParser.importXML("companies", CompanyRootDto.class);

        for (CompanyDto companyDto : companiesRootDto.getCompanies()) {
            Company company = this.modelMapper.map(companyDto, Company.class);
            List<String> violationsMessage = this.validatorUtil.getViolationsMessage(company);

            if (violationsMessage.size() > 0) {
                violationsMessage.forEach(System.out::println);
            } else {
                this.companyRepository.save(company);
            }
        }
    }

    @Override
    public boolean areImported() {
       return this.companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {
        return this.fileUtil.readFile("/xmls/companies.xml");
    }


    @Override
    public void exportJsonCompanies() throws IOException {
        List<Company> companies = this.companyRepository.findAll();
        List<CompanyJsonDto> companyJsonDtos = new ArrayList<>();
        companies.forEach(c -> companyJsonDtos.add(this.modelMapper.map(c, CompanyJsonDto.class)));
        this.gsonUtil.saveJSONtoFile("companies", companyJsonDtos.toArray(new CompanyJsonDto[0]));
    }

    @Override
    public String readCompaniesJsonFile() throws IOException {
        return this.fileUtil.readFile("jsons/companies.json");
    }

    @Override
    public boolean areExported() throws IOException {
       return this.readCompaniesJsonFile().length() > 0;
    }
}
