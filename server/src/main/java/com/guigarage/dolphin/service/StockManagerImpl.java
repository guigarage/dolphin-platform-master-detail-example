package com.guigarage.dolphin.service;

import com.canoo.dolphin.server.event.DolphinEventBus;
import com.guigarage.dolphin.ServerConstants;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class StockManagerImpl implements StockManager {

    @Inject
    private DolphinEventBus eventBus;

    private List<StockData> store;

    private List<String> locked;

    @PostConstruct
    public void init() {
        locked = new CopyOnWriteArrayList<>();
        store = new CopyOnWriteArrayList<>();

        StockData stock1 = new StockData();
        stock1.setIdent("APP");
        stock1.setName("Apple");
        stock1.setValue(127);
        stock1.setType(StockManager.NORMAL_TYPE);

        StockData stock2 = new StockData();
        stock2.setIdent("SAM");
        stock2.setName("Samsung");
        stock2.setValue(92.7);
        stock2.setType(StockManager.NORMAL_TYPE);

        StockData stock3 = new StockData();
        stock3.setIdent("GOO");
        stock3.setName("Google");
        stock3.setValue(83.4);
        stock3.setType(StockManager.NORMAL_TYPE);

        StockData stock4 = new StockData();
        stock4.setIdent("MIC");
        stock4.setName("Microsoft");
        stock4.setValue(46.74);
        stock4.setType(StockManager.NORMAL_TYPE);

        StockData stock5 = new StockData();
        stock5.setIdent("IBM");
        stock5.setName("IBM");
        stock5.setValue(12.4);
        stock5.setType(StockManager.NORMAL_TYPE);

        StockData stock6 = new StockData();
        stock6.setIdent("ORA");
        stock6.setName("Oracle");
        stock6.setValue(24.5);
        stock6.setType(StockManager.NORMAL_TYPE);

        store.addAll(Arrays.asList(stock1, stock2, stock3, stock4, stock5, stock6));
    }

    @Override
    public synchronized StockData find(String stockIdent) {
        for(StockData stockData : store) {
            if(stockData.getIdent().equals(stockIdent)) {
                return stockData;
            }
        }
        return null;
    }

    @Override
    public synchronized void save(StockData stockData) {
        for(StockData persisted : store) {
            if(persisted.getIdent().equals(stockData.getIdent())) {
                persisted.setName(stockData.getName());
                persisted.setValue(stockData.getValue());
                persisted.setType(stockData.getType());
                eventBus.publish(ServerConstants.STOCK_UPDATED_TOPIC, persisted.getIdent());
            }
        }
    }

    @Override
    public synchronized List<StockData> getAll() {
        return store;
    }

    @Override
    public synchronized void lock(String stockIdent) {
        if(locked.contains(stockIdent)) {
            throw new RuntimeException("Stock " + stockIdent + " is already locked");
        }
        locked.add(stockIdent);
        eventBus.publish(ServerConstants.STOCK_LOCKED_TOPIC, stockIdent);
    }

    @Override
    public synchronized void unlock(String stockIdent) {
        locked.remove(stockIdent);
        eventBus.publish(ServerConstants.STOCK_UNLOCKED_TOPIC, stockIdent);
    }

    @Override
    public boolean isLocked(String stockIdent) {
        return locked.contains(stockIdent);
    }
}
