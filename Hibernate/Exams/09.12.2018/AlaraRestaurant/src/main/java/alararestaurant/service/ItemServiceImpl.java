package alararestaurant.service;

import alararestaurant.domain.dtos.imports.json.ItemDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.base.FileUtil;
import alararestaurant.util.base.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, ModelMapper modelMapper, FileUtil fileUtil, Gson gson, ValidatorUtil validatorUtil) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return this.fileUtil.readFile("items.json");
    }

    @Override
    public String importItems(String items) throws IOException {
        StringBuilder sb = new StringBuilder();
        String itemsStr = this.readItemsJsonFile();
        ItemDto[] itemsDto = this.gson.fromJson(itemsStr, ItemDto[].class);

        for (ItemDto itemDto : itemsDto) {
            Item item = this.modelMapper.map(itemDto, Item.class);
            Boolean isValidItem = this.validatorUtil.isValid(item) && this.itemRepository.getByName(item.getName()) == null;

            Category category = item.getCategory();
            Boolean isValidCategory = this.validatorUtil.isValid(category);
            Category categoryDb = this.categoryRepository.getByName(category.getName());

            if ((categoryDb == null && !isValidCategory) || !isValidItem) {
                sb.append("Invalid data format.")
                        .append(System.lineSeparator());
                continue;
            }

            if (categoryDb == null) {
                this.categoryRepository.saveAndFlush(category);
            } else {
                item.setCategory(categoryDb);
            }

            this.itemRepository.saveAndFlush(item);
            sb.append(String.format("Record %s successfully imported.%n", item.getName()));
        }

        return sb.toString();
    }
}
