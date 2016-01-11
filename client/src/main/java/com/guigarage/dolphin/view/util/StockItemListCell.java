package com.guigarage.dolphin.view.util;

import com.canoo.dolphin.event.Subscription;
import com.guigarage.dolphin.model.MasterModelStockItem;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;

public class StockItemListCell extends ListCell<MasterModelStockItem> {

    private Subscription nameBinding;

    private Subscription identBinding;

    private Subscription lockedBinding;

    public StockItemListCell() {
        addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (getItem() != null && getItem().isLocked()) {
                e.consume();
            }
        });
    }

    @Override
    protected void updateItem(MasterModelStockItem item, boolean empty) {
        super.updateItem(item, empty);

        if (nameBinding != null) {
            nameBinding.unsubscribe();
        }
        if (identBinding != null) {
            identBinding.unsubscribe();
        }
        if (lockedBinding != null) {
            lockedBinding.unsubscribe();
        }
        if (item != null) {
            nameBinding = item.stockNameProperty().onChanged(e -> updateText());
            identBinding = item.stockIdentProperty().onChanged(e -> updateText());
            lockedBinding = item.lockedProperty().onChanged(e -> updateText());
        }
        updateText();
    }

    public void updateText() {
        if (getItem() == null) {
            setText(null);
        } else {
            String text = getItem().getStockName() + " (" + getItem().getStockIdent() + ")";
            if (getItem().isLocked()) {
                text = text + " LOCKED!";
            }
            setText(text);
        }
    }
}
