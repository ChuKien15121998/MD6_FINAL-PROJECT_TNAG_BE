package com.codegym.service;

import com.codegym.model.Food;
import com.codegym.model.Order;
import com.codegym.model.OrderDetails;

public interface IOrderDetailService extends IGeneralService<OrderDetails> {
    Iterable<OrderDetails> findAllByOrder(Order order);

    Iterable<OrderDetails> findAllByFoodId(Long id);

    Iterable<OrderDetails> findAllByFood(Food food);

}
