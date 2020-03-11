package com.producthop.softuni.web.controllers;

import com.producthop.softuni.domain.models.bindings.order.OrderCreateBindingModel;
import com.producthop.softuni.domain.models.services.OrderServiceModel;
import com.producthop.softuni.domain.models.services.ProductServiceModel;
import com.producthop.softuni.domain.models.services.UserServiceModel;
import com.producthop.softuni.domain.models.views.order.OrderCreateViewModel;
import com.producthop.softuni.domain.models.views.order.OrderDetailsViewModel;
import com.producthop.softuni.domain.models.views.order.OrderListViewModel;
import com.producthop.softuni.service.OrderService;
import com.producthop.softuni.service.ProductService;
import com.producthop.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public OrderController(ModelMapper modelMapper, OrderService orderService, ProductService productService, UserService userService) {
        this.modelMapper = modelMapper;
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/create/product/{id}")
    public ModelAndView create(@PathVariable String id,
                               ModelAndView modelAndView,
                               Principal principal) {
        ProductServiceModel productServiceModel = this.productService.getById(id);
        OrderCreateViewModel orderViewModel = this.modelMapper
                .map(productServiceModel, OrderCreateViewModel.class);
        orderViewModel.setUsername(principal.getName());

        modelAndView.addObject("order", orderViewModel);
        return super.view("order/create", modelAndView);
    }

    @PostMapping("/create/product/{id}")
    public ModelAndView createConfirm(@PathVariable String id,
                                      @ModelAttribute OrderCreateBindingModel orderCreateBindingModel) {
        OrderServiceModel orderServiceModel = this.modelMapper
                .map(orderCreateBindingModel, OrderServiceModel.class);

        UserServiceModel userServiceModel = this.userService.getByUsername(orderCreateBindingModel.getCustomer());
        orderServiceModel.setUser(userServiceModel);

        ProductServiceModel productServiceModel = this.productService.getById(id);
        orderServiceModel.setProduct(productServiceModel);
        orderServiceModel.setTotalPrice(orderServiceModel.getProduct().getPrice()
                .multiply(new BigDecimal(orderCreateBindingModel.getQuantity())));

        OrderServiceModel savedOrder = this.orderService.save(orderServiceModel);

        if (savedOrder == null) {
            return super.redirect("/order/create/product/" + id);
        }
        return super.redirect("/order/my");
    }

    @GetMapping("/{listType}")
    public ModelAndView orderList(@PathVariable String listType,
                                  ModelAndView modelAndView,
                                  Principal principal) {
        List<OrderServiceModel> orderList;
        if (listType.equals("all")) {
            orderList = this.orderService.getAll();
        } else {
            orderList = this.userService
                    .getByUsername(principal.getName())
                    .getOrders();
        }

        List<OrderListViewModel> orderViewList = orderList
                .stream()
                .map(o -> this.modelMapper.map(o, OrderListViewModel.class))
                .collect(Collectors.toList());

        String title = String.format("%s Orders",
                listType.substring(0, 1).toUpperCase() + listType.substring(1));
        modelAndView.addObject("orders", orderViewList);
        modelAndView.addObject("title", title);
        return view("order/list", modelAndView);
    }

    @GetMapping("/details/{orderId}")
    public ModelAndView details(@PathVariable String orderId,
                                ModelAndView modelAndView) {
        OrderServiceModel orderService = this.orderService.getById(orderId);
        OrderDetailsViewModel orderView = this.modelMapper
                .map(orderService, OrderDetailsViewModel.class);

        modelAndView.addObject("order", orderView);
        return super.view("order/details", modelAndView);
    }
}
