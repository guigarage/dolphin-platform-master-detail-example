package com.guigarage.dolphin.controller;

import com.canoo.dolphin.server.DolphinAction;
import com.canoo.dolphin.server.DolphinController;
import com.canoo.dolphin.server.DolphinModel;
import com.canoo.dolphin.server.Param;
import com.guigarage.dolphin.Constants;
import com.guigarage.dolphin.model.DetailModel;
import com.guigarage.dolphin.service.StockData;
import com.guigarage.dolphin.service.StockManager;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@DolphinController(Constants.DETAIL_CONTROLLER_NAME)
public class DetailController {

    @Inject
    private StockManager stockManager;

    @DolphinModel
    private DetailModel model;

    private StockData currentData;

    @PostConstruct
    public void init() {
        model.maxValueProperty().set(100.0);
        model.minValueProperty().set(0.0);
        model.stockTypesProperty().addAll(StockManager.NORMAL_TYPE, StockManager.SPECIAL_TYPE);
    }

    @DolphinAction(Constants.DETAIL_CONTROLLER_UPDATE_ACTION)
    public void onShowStock(@Param(Constants.STOCK_IDENT_PARAM) String stockIdent) {
        update(stockIdent);
    }

    @DolphinAction(Constants.DETAIL_CONTROLLER_RESET_ACTION)
    public void reset() {
        if(currentData != null) {
            update(currentData.getIdent());
        }
    }

    @DolphinAction(Constants.DETAIL_CONTROLLER_SAVE_ACTION)
    public void save() {
        if(currentData != null) {
            currentData.setName(model.getStockName());
            currentData.setValue(model.getStockValue());
            currentData.setType(model.getStockType());
           stockManager.save(currentData);
        }
    }

    private void update(String stockIdent) {
        currentData = stockManager.find(stockIdent);
        if(currentData == null) {
            model.stockNameProperty().set(null);
            model.stockTypeProperty().set(null);
            model.stockValueProperty().set(null);
        } else {
            model.stockNameProperty().set(currentData.getName());
            model.stockTypeProperty().set(currentData.getType());
            model.stockValueProperty().set(currentData.getValue());
        }
    }
}
