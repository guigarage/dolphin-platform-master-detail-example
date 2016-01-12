package com.guigarage.dolphin.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean
public class DetailModel {

    private Property<String> stockName;

   // private Property<Double> stockValue;

   // private Property<Double> minValue;

   // private Property<Double> maxValue;

    private Property<String> stockType;

    private ObservableList<String> stockTypes;

    public Property<String> stockNameProperty() {
        return stockName;
    }

 //   public Property<Double> stockValueProperty() {
    //       return stockValue;
//    }

    public Property<String> stockTypeProperty() {
        return stockType;
    }

    public ObservableList<String> stockTypesProperty() {
        return stockTypes;
    }

    //  public Property<Double> minValueProperty() {
    //    return minValue;
    // }

    // public Property<Double> maxValueProperty() {
//        return maxValue;
    //  }

    public String getStockName() {
        return stockName.get();
    }

//    public Double getStockValue() {
    //      return stockValue.get();
    // }

    public String getStockType() {
        return stockType.get();
    }
}
