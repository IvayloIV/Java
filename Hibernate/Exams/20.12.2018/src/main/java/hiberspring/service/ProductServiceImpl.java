package hiberspring.service;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.xml.ProductRootXmlDto;
import hiberspring.domain.dtos.xml.ProductXmlDto;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.base.ProductService;
import hiberspring.util.base.FileUtil;
import hiberspring.util.base.ValidatorUtil;
import hiberspring.util.base.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, XmlParser xmlParser, ModelMapper modelMapper, FileUtil fileUtil, ValidatorUtil validatorUtil) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return this.fileUtil.readFile("products.xml");
    }

    @Override
    public String importProducts() throws JAXBException, IOException {
        StringBuilder sb = new StringBuilder();
        ProductRootXmlDto productsXmlDto = this.xmlParser.importXML("products", ProductRootXmlDto.class);

        for (ProductXmlDto productXmlDto : productsXmlDto.getProductsDto()) {
            Product product = this.modelMapper.map(productXmlDto, Product.class);
            product.setBranch(this.branchRepository.getByName(productXmlDto.getBranch()));

            if (!this.validatorUtil.isValid(product)) {
                sb.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            this.productRepository.saveAndFlush(product);
            sb.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                        "Product",
                        product.getName()))
                    .append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
