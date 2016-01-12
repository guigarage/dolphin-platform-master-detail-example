package com.guigarage.dolphin.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean
public class MasterModel {

    private ObservableList<MasterModelStockItem> stockItems;

    private Property<String> selectedStockIdent;

    private Property<Integer> lockCount;

    private Property<Integer> unlockCount;

    public ObservableList<MasterModelStockItem> getStockItems() {
        return stockItems;
    }

    public Property<String> selectedStockIdentProperty() {
        return selectedStockIdent;
    }

    public Property<Integer> lockCountProperty() {
        return lockCount;
    }

    public Property<Integer> unlockCountProperty() {
        return unlockCount;
    }

    public void setLockCount(int lockCount) {
        this.lockCount.set(lockCount);
    }

    public void setUnlockCount(int unlockCount) {
        this.unlockCount.set(unlockCount);
    }

    public Integer getLockCount() {
        return lockCount.get();
    }

    public Integer getUnlockCount() {
        return unlockCount.get();
    }

    public String getSelectedStockIdent() {
        return selectedStockIdent.get();
    }

    public void setSelectedStockIdent(String selectedStockIdent) {
        this.selectedStockIdent.set(selectedStockIdent);
    }
}
