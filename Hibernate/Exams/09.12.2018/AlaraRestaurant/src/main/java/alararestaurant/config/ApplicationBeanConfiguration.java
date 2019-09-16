package alararestaurant.config;

import alararestaurant.util.FileUtilImpl;
import alararestaurant.util.GsonUtilImpl;
import alararestaurant.util.ValidatorUtilImpl;
import alararestaurant.util.XmlParserImpl;
import alararestaurant.util.base.FileUtil;
import alararestaurant.util.base.GsonUtil;
import alararestaurant.util.base.ValidatorUtil;
import alararestaurant.util.base.XmlParser;
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
    public GsonUtil gsonUtil() {
        return new GsonUtilImpl();
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
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }
}
