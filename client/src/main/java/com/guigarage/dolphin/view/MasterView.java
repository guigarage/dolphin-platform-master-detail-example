package com.guigarage.dolphin.view;

import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.javafx.AbstractViewBinder;
import com.canoo.dolphin.client.javafx.FXBinder;
import com.guigarage.dolphin.Constants;
import com.guigarage.dolphin.model.MasterModel;
import com.guigarage.dolphin.model.MasterModelStockItem;
import com.guigarage.dolphin.view.util.StockItemListCell;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

public class MasterView extends AbstractViewBinder<MasterModel> {

    private ListView<MasterModelStockItem> listView;

    private StackPane rootNode;

    private ReadOnlyStringWrapper selectedStockIdent;

    public MasterView(ClientContext clientContext) {
        super(clientContext, Constants.MASTER_CONTROLLER_NAME);
        listView = new ListView<>();
        listView.setCellFactory(c -> new StockItemListCell());
        rootNode = new StackPane(listView);

        selectedStockIdent = new ReadOnlyStringWrapper();
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                selectedStockIdent.set(null);
                getModel().setSelectedStockIdent(null);
            } else {
                selectedStockIdent.set(newVal.getStockIdent());
                getModel().setSelectedStockIdent(newVal.getStockIdent());
            }
        });
    }

    @Override
    protected void init() {
        FXBinder.bind(listView.getItems()).to(getModel().getStockItems());
    }

    public StackPane getRootNode() {
        return rootNode;
    }

    public String getSelectedStockIdent() {
        return selectedStockIdent.get();
    }

    public ReadOnlyStringProperty selectedStockIdentProperty() {
        return selectedStockIdent.getReadOnlyProperty();
    }
}
