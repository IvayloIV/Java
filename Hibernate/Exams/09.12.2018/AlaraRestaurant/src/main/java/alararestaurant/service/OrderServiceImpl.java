package alararestaurant.service;

import alararestaurant.domain.dtos.imports.xml.OrderDto;
import alararestaurant.domain.dtos.imports.xml.OrderRootDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.base.FileUtil;
import alararestaurant.util.base.ValidatorUtil;
import alararestaurant.util.base.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, ModelMapper modelMapper, FileUtil fileUtil, XmlParser xmlParser) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile("orders.xml");
    }

    @Override
    public String importOrders() throws JAXBException, IOException {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        OrderRootDto orderRootDto = this.xmlParser.importXML("orders", OrderRootDto.class);

        for (OrderDto orderDto : orderRootDto.getOrders()) {
            Order order = this.modelMapper.map(orderDto, Order.class);
            Employee employee = this.employeeRepository.getByName(order.getEmployee().getName());

            if (employee == null) {
                continue;
            }
            order.setEmployee(employee);

            boolean isProductsValid = true;
            for (OrderItem orderItem : order.getOrderItems()) {
                Item item = this.itemRepository.getByName(orderItem.getItem().getName());

                if (item == null) {
                    isProductsValid = false;
                    break;
                }

                orderItem.setItem(item);
                orderItem.setOrder(order);
            }

            if (!isProductsValid) {
                continue;
            }

            this.orderRepository.saveAndFlush(order);
            sb.append(String.format("Order for %s on %s added%n",
                    order.getCustomer(),
                    dtf.format(order.getDateTime())));
        }

        return sb.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder sb = new StringBuilder();
        List<Order> orders = this.orderRepository.getByPositionName("Burger Flipper");

        for (Order order : orders) {
            sb.append("Name: ").append(order.getEmployee().getName())
                    .append(System.lineSeparator());
            sb.append("Orders:").append(System.lineSeparator());
            sb.append(" Customer: ").append(order.getCustomer()).append(System.lineSeparator());
            sb.append(" Items: ").append(System.lineSeparator());

            for (OrderItem orderItem : order.getOrderItems()) {
                Item item = orderItem.getItem();
                sb.append("     Name: ").append(item.getName()).append(System.lineSeparator());
                sb.append("     Price: ").append(item.getPrice()).append(System.lineSeparator());
                sb.append("     Quantity: ").append(orderItem.getQuantity())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
