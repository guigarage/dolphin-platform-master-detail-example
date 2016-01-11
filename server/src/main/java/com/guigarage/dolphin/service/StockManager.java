package com.guigarage.dolphin.service;

import java.util.List;

/**
 * Created by hendrikebbers on 11.01.16.
 */
public interface StockManager {

    String SPECIAL_TYPE = "Special";

    String NORMAL_TYPE = "Normal";

    StockData find(String stockIdent);

    void save(StockData stockData);

    List<StockData> getAll();

    void lock(String stockIdent);

    void unlock(String stockIdent);

    boolean isLocked(String stockIdent);
}
