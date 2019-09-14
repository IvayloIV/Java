package softuni.workshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.importDto.ProjectDto;
import softuni.workshop.domain.dtos.importDto.ProjectRootDto;
import softuni.workshop.domain.dtos.jsonDtos.exportDto.ProjectJsonDto;
import softuni.workshop.domain.entities.Project;
import softuni.workshop.repository.CompanyRepository;
import softuni.workshop.repository.ProjectRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.GsonUtil;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final GsonUtil gsonUtil;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, CompanyRepository companyRepository, FileUtil fileUtil, ValidatorUtil validatorUtil, XmlParser xmlParser, ModelMapper modelMapper, GsonUtil gsonUtil) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.gsonUtil = gsonUtil;
    }

    @Override
    public void importProjects() throws JAXBException, IOException {
        ProjectRootDto projects = this.xmlParser.importXML("projects", ProjectRootDto.class);

        for (ProjectDto projectDto : projects.getProjects()) {
            Project project = this.modelMapper.map(projectDto, Project.class);
            project.setCompany(this.companyRepository.findByName(project.getCompany().getName()));
            List<String> violationsMessage = this.validatorUtil.getViolationsMessage(project);

            if (violationsMessage.size() > 0) {
                violationsMessage.forEach(System.out::println);
            } else {
                this.projectRepository.save(project);
            }
        }
    }


    @Override
    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    @Override
    public String readProjectsXmlFile() throws IOException {
        return this.fileUtil.readFile("/xmls/projects.xml");
    }

    @Override
    public String exportFinishedProjects() {
        StringBuilder sb = new StringBuilder();
        List<Project> finishedProjects = this.projectRepository.findAllByIsFinishedTrue();

        finishedProjects.forEach(p -> sb.append(String.format("Project Name: %s%n    Description: %s%n   %.2f%n",
                p.getName(),
                p.getDescription(),
                p.getPayment())));

        return sb.toString().trim();
    }


    @Override
    public void exportProjectToJson() throws IOException {
        List<Project> projects = this.projectRepository.findAll();
        ProjectJsonDto[] projectJsonDtos = projects
                .stream()
                .map(p -> this.modelMapper.map(p, ProjectJsonDto.class))
                .toArray(ProjectJsonDto[]::new);

        this.gsonUtil.saveJSONtoFile("projects", projectJsonDtos);
    }

    @Override
    public String readProjectJsonFile() throws IOException {
        return this.fileUtil.readFile("jsons/projects.json");
    }

    @Override
    public boolean areExported() throws IOException {
        return this.readProjectJsonFile().length() > 0;
    }

    @Override
    public String exportProjectsWithNameEnding() {
        StringBuilder sb = new StringBuilder();
        List<Project> projects = this.projectRepository.findAllByNameEndingWith("e");

        projects.forEach(p -> sb.append(String.format("Project name: %s%n Payment: %.2f%n",
                p.getName(),
                p.getPayment())));

        return sb.toString().trim();
    }
}
