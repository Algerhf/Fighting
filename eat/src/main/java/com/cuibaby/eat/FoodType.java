package com.cuibaby.eat;

import androidx.annotation.StringDef;

@StringDef({FoodType.VEGETABLE, FoodType.MEAT})
public @interface FoodType {
    String VEGETABLE = "vegetable";
    String MEAT = "meat";
}
