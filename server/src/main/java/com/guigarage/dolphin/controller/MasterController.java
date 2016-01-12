package com.guigarage.dolphin.controller;

import com.canoo.dolphin.BeanManager;
import com.canoo.dolphin.server.DolphinController;
import com.canoo.dolphin.server.DolphinModel;
import com.canoo.dolphin.server.event.DolphinEventBus;
import com.guigarage.dolphin.Constants;
import com.guigarage.dolphin.ServerConstants;
import com.guigarage.dolphin.model.MasterModel;
import com.guigarage.dolphin.model.MasterModelStockItem;
import com.guigarage.dolphin.service.StockData;
import com.guigarage.dolphin.service.StockManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

@DolphinController(Constants.MASTER_CONTROLLER_NAME)
public class MasterController {

    @Inject
    private StockManager stockManager;

    @Inject
    private BeanManager beanManager;

    @Inject
    private DolphinEventBus eventBus;

    @DolphinModel
    private MasterModel model;

    @PostConstruct
    public void init() {
        model.setLockCount(0);
        model.setUnlockCount(0);
        for(StockData stockData : stockManager.getAll()) {
            MasterModelStockItem stockItem = beanManager.create(MasterModelStockItem.class);
            stockItem.stockIdentProperty().set(stockData.getIdent());
            stockItem.stockNameProperty().set(stockData.getName());
            stockItem.lockedProperty().set(stockManager.isLocked(stockData.getIdent()));
            model.getStockItems().add(stockItem);
        }

        model.selectedStockIdentProperty().onChanged(e -> {
            if(e.getOldValue() != null) {
                stockManager.unlock(e.getOldValue());
            }
            if(e.getNewValue() != null) {
                stockManager.lock(e.getNewValue());
            }
        });

        eventBus.subscribe(ServerConstants.STOCK_UPDATED_TOPIC, e -> {
            StockData stockData = stockManager.find(e.getData());

            for(MasterModelStockItem stockItem : model.getStockItems()) {
                if(stockItem.stockIdentProperty().get().equals(stockData.getIdent())) {
                    stockItem.stockNameProperty().set(stockData.getName());
                }
            }
        });

        eventBus.subscribe(ServerConstants.STOCK_LOCKED_TOPIC, e -> {
            for(MasterModelStockItem stockItem : model.getStockItems()) {
                if(stockItem.stockIdentProperty().get().equals(e.getData())) {
                    stockItem.lockedProperty().set(true);
                }
            }
            model.setLockCount(model.getLockCount() + 1);
        });

        eventBus.subscribe(ServerConstants.STOCK_UNLOCKED_TOPIC, e -> {
            for(MasterModelStockItem stockItem : model.getStockItems()) {
                if(stockItem.stockIdentProperty().get().equals(e.getData())) {
                    stockItem.lockedProperty().set(false);
                }
            }
            model.setUnlockCount(model.getUnlockCount() + 1);
        });
    }

    @PreDestroy
    public void onDestroy() {
        if(model.getSelectedStockIdent() != null) {
            stockManager.unlock(model.getSelectedStockIdent());
        }
    }

}
