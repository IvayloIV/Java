package spring.demo.shampoos.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import spring.demo.shampoos.dao.IngredientRepository;
import spring.demo.shampoos.dao.LabelRepository;
import spring.demo.shampoos.dao.ShampooRepository;
import spring.demo.shampoos.models.Ingredient;

@Component
public class ShampooRunner {

    private final Logger logger = LoggerFactory.getLogger(ShampooRunner.class);

    private final IngredientRepository ingredientRepository;
    private final LabelRepository labelRepository;
    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooRunner(IngredientRepository ingredientRepository, LabelRepository labelRepository, ShampooRepository shampooRepository) {
        this.ingredientRepository = ingredientRepository;
        this.labelRepository = labelRepository;
        this.shampooRepository = shampooRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() throws Exception {
        // 1. Select Shampoos by Size
//        shampooRepository.findAllBySizeOrderById(Size.MEDIUM)
//                .forEach(s -> logger.info(String.format("%s %s %.2flv.", s.getBrand(), s.getSize().name(), s.getPrice())));

        // 2. Select Shampoos by Size or Label
//        shampooRepository.findAllBySizeOrLabel_IdOrderByPrice(Size.MEDIUM, 10L)
//                .forEach(s -> logger.info(String.format("%s %s %.2flv.", s.getBrand(), s.getSize().name(), s.getPrice())));

        // 3. Select Shampoos by Price
//        shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(5)
//                .forEach(s -> logger.info(String.format("%s %s %.2flv.", s.getBrand(), s.getSize().name(), s.getPrice())));

        // 4. Select Ingredients by Name
//        ingredientRepository.findAllByNameStartsWith("M")
//                .forEach(i -> logger.info(i.getName()));

        // 5. Select Ingredients by Name
//        ingredientRepository.findAllByNameInOrderByPrice(Arrays.asList("Lavender", "Herbs", "Apple"))
//            .forEach(i -> logger.info(i.getName()));

        // 6. Count Shampoos by Price
//        logger.info(String.valueOf(shampooRepository.countAllByPriceLessThan(8.5)));

        // 7. Select Shampoos by Ingredients
//        shampooRepository.findAllByIngredientsIn(Arrays.asList("Berry", "Mineral-Collagen"))
//                .forEach(s -> logger.info("{} - {}", s.getBrand(), s.getIngredients()));

        // 8. Select Shampoos by Ingredients Count
//        shampooRepository.findAllByIngredientCountLessThan(2)
//                .forEach(s -> logger.info("{} - {}", s.getBrand(), s.getIngredients().size()));

        // 9. Select Ingredient Name and Shampoo Brand By Name
//        logger.info(String.format("%.2f", shampooRepository.findIngredientsPriceByBrand("Fresh it up!")));

        // 10. Delete Ingredients by name
//        ingredientRepository.deleteByName("Active-Caffeine");

        // 11. Update Ingredients by price
//        ingredientRepository.increasePrice(10.0);

        // 12. Update Ingredients by Names
//        ingredientRepository.increasePriceWith100ByNameIn(Arrays.asList("Apple", "Nettle", "Cherry"));
    }
}
