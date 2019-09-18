package hiberspring.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hiberspring.util.FileUtilImpl;
import hiberspring.util.ValidatorUtilImpl;
import hiberspring.util.XmlParserImpl;
import hiberspring.util.base.FileUtil;
import hiberspring.util.base.ValidatorUtil;
import hiberspring.util.base.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtil fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }

    @Bean
    public ValidatorUtil validationUtil() {
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
}
