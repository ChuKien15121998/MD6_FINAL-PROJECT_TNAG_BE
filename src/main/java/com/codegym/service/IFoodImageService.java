package com.codegym.service;

import com.codegym.model.Food;
import com.codegym.model.FoodImage;

public interface IFoodImageService extends IGeneralService<FoodImage> {
    Iterable<FoodImage> findFoodImageByFood (Food food);

}
