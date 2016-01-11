package com.guigarage.dolphin.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean
public class MasterModel {

    private ObservableList<MasterModelStockItem> stockItems;

    private Property<String> selectedStockIdent;

    public ObservableList<MasterModelStockItem> getStockItems() {
        return stockItems;
    }

    public Property<String> selectedStockIdentProperty() {
        return selectedStockIdent;
    }

    public String getSelectedStockIdent() {
        return selectedStockIdent.get();
    }

    public void setSelectedStockIdent(String selectedStockIdent) {
        this.selectedStockIdent.set(selectedStockIdent);
    }
}
