package com.guigarage.dolphin.model;

import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean
public class MasterModelStockItem {

    private Property<String> stockIdent;

    private Property<String> stockName;

    private Property<Boolean> locked;

    public Property<String> stockIdentProperty() {
        return stockIdent;
    }

    public Property<String> stockNameProperty() {
        return stockName;
    }

    public String getStockIdent() {
        return stockIdent.get();
    }

    public String getStockName() {
        return stockName.get();
    }

    public Property<Boolean> lockedProperty() {
        return locked;
    }

    public boolean isLocked() {
        if(locked.get() == null) {
            return false;
        }
        return locked.get();
    }
}
