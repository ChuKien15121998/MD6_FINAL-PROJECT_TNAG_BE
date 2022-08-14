package com.codegym.service.impl;

import com.codegym.model.Customer;
import com.codegym.model.Merchant;
import com.codegym.model.Order;
import com.codegym.model.OrderStatus;
import com.codegym.repository.IOrderRepository;
import com.codegym.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    IOrderRepository orderRepository;

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void remove(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<Order> getListOrderbyCustomerSearch(String search, Long id) {
        return orderRepository.getListOrderbyCustomerSearch(search, id);
    }

    @Override
    public Iterable<Order> findAllByMerchant(Merchant merchant) {
        return orderRepository.findAllByMerchantOrderByCreateAt(merchant);
    }

    @Override
    public Iterable<Order> findAllByCustomer(Customer customer) {
        return orderRepository.findAllByCustomerOrderByCreateAt(customer);
    }

    public Iterable<Order> findAllByOrderStatusAndMerchant(OrderStatus orderStatus, Merchant merchant) {
        return orderRepository.findAllByOrderStatusAndMerchant(orderStatus, merchant);
    }

    public Iterable<Order> merchantSearch(String search, Long id) {
        return orderRepository.merchantSearch(search,id);
    }

    @Override
    public Iterable<Order> merchantSearchAccepted(String search, Long id) {
        return orderRepository.merchantSearchAccepted(search,id);
    }

    @Override
    public Iterable<Order> merchantSearchDenied(String search, Long id) {
        return orderRepository.merchantSearchDenied(search,id);
    }

    @Override
    public Iterable<Order> merchantSearchWait(String search, Long id) {
        return orderRepository.merchantSearchWait(search,id);
    }

    @Override
    public Iterable<Double> getReveneuOfMerchant(Long merchant_id, String from, String to) {
        return orderRepository.getReveneuOfMerchant(merchant_id, from, to);
    }
}