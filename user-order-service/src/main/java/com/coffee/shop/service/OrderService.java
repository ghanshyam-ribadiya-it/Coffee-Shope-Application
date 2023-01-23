package com.coffee.shop.service;

import com.coffee.shop.constants.ErrorAppMessage;
import com.coffee.shop.constants.OrderStatus;
import com.coffee.shop.constants.PaymentStatus;
import com.coffee.shop.constants.SuccessAppMessage;
import com.coffee.shop.domain.Orders;
import com.coffee.shop.exception.ShopException;
import com.coffee.shop.model.*;
import com.coffee.shop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private CoffeeShopService coffeeShopService;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public OrderResponse saveRequestedOrder(OrderRequest orderRequest) {
        log.info("Request is received for the new Order : {} ", orderRequest);

        UserDetailResponse customer = usersService.getUserDetailById(orderRequest.getCustomerId());
        CoffeeShopDetailResponse coffeeShop = coffeeShopService.getCoffeeShopDetailById(orderRequest.getCoffeeShopeId());
        MenuResponse menuResponse = coffeeShopService.getMenuDetailsByIds(orderRequest.getCoffeeShopeId(), orderRequest.getMenuId());

        Orders orderHistory = new Orders();
        orderHistory.setCustomerId(customer.getUserDetail().getId());
        orderHistory.setCoffeeShopId(coffeeShop.getCoffeeShopDetail().getId());
        orderHistory.setMenuId(menuResponse.getMenuDetail().getId());
        orderHistory.setPrice(menuResponse.getMenuDetail().getPrice());
        orderHistory.setQuantity(orderRequest.getQuantity());
        orderHistory.setTotalPayment(calculateCost(orderRequest.getQuantity(), menuResponse.getMenuDetail().getPrice()));
        orderHistory.setOrderStatus(OrderStatus.REQUESTED);
        orderHistory.setPaymentStatus(PaymentStatus.PENDING);

        orderRepository.save(orderHistory);

        OrderResponse orderResponse = new OrderResponse(SuccessAppMessage.OK_ORDER_PLACED);
        orderResponse.setOrderId(orderHistory.getOrderId());
        return orderResponse;
    }

    private BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
        BigDecimal itemCost = new BigDecimal(BigInteger.ZERO, 2);
        BigDecimal totalCost = new BigDecimal(BigInteger.ZERO, 2);

        itemCost = itemPrice.multiply(new BigDecimal(itemQuantity));
        return totalCost.add(itemCost);
    }

    @Transactional
    public OrderDetailResponse getOrderDetails(String orderId) {
        log.info("Get Order Details for orderId : {}", orderId);

        Optional<Orders> orderHistory = orderRepository.findById(orderId);
        if (orderHistory.isPresent() == false) {
            throw new ShopException(ErrorAppMessage.INVALID_ORDER_ID);
        }

        OrderDetailResponse orderDetailResponse = new OrderDetailResponse(SuccessAppMessage.OK_ORDER_DETAILS);
        orderDetailResponse.setOrderDetail(mapDomainToModelObject(orderHistory.get()));
        return orderDetailResponse;
    }

    private OrderDetail mapDomainToModelObject(Orders orderHistoryDetail) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderHistoryDetail.getOrderId());

        UserDetailResponse customer = usersService.getUserDetailById(orderHistoryDetail.getCustomerId());
        CoffeeShopDetailResponse coffeeShop = coffeeShopService.getCoffeeShopDetailById(orderHistoryDetail.getCoffeeShopId());
        MenuResponse menuResponse = coffeeShopService.getMenuDetailsByIds(orderHistoryDetail.getCoffeeShopId(), orderHistoryDetail.getMenuId());

        orderDetail.setCustomerDetail(customer.getUserDetail());
        orderDetail.setCoffeeShopDetail(coffeeShop.getCoffeeShopDetail());
        orderDetail.setMenuDetail(menuResponse.getMenuDetail());
        orderDetail.setPrice(orderHistoryDetail.getPrice());
        orderDetail.setQuantity(orderHistoryDetail.getQuantity());
        orderDetail.setTotalPayment(orderHistoryDetail.getTotalPayment());
        orderDetail.setOrderStatus(orderHistoryDetail.getOrderStatus());
        orderDetail.setPaymentStatus(orderHistoryDetail.getPaymentStatus());

        return orderDetail;
    }

    @Transactional
    public OrderDetailListResponse retrieveQueueOrders(Long coffeeShopId) {
        List<Orders> orderHistoryList = orderRepository.findPendingOrdersByCoffeeShopId(coffeeShopId);

        List<OrderDetail> orderDetails = new ArrayList<>(orderHistoryList.size());
        for (Orders orderHistory : orderHistoryList) {
            orderDetails.add(mapDomainToModelObject(orderHistory));
        }

        OrderDetailListResponse orderDetailListResponse = new OrderDetailListResponse(SuccessAppMessage.OK_ORDER_DETAILS);
        orderDetailListResponse.setOrderDetails(orderDetails);
        return orderDetailListResponse;
    }
}