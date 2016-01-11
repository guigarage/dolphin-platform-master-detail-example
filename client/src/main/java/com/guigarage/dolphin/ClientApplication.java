package com.guigarage.dolphin;

import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.javafx.DolphinPlatformApplication;
import com.guigarage.dolphin.view.DetailView;
import com.guigarage.dolphin.view.MasterView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class ClientApplication extends DolphinPlatformApplication {

    @Override
    protected String getServerEndpoint() {
        return "http://localhost:8080/dolphin";
    }

    @Override
    protected void start(Stage primaryStage, ClientContext clientContext) throws Exception {
        MasterView masterView = new MasterView(clientContext);
        DetailView detailView = new DetailView(clientContext);
        masterView.selectedStockIdentProperty().addListener((obs, oldVal, newVal) -> detailView.showStock(newVal));

        HBox mainPane = new HBox(masterView.getBaseNode(), detailView.getBaseNode());
        mainPane.setSpacing(12);
        mainPane.setPadding(new Insets(12));
        HBox.setHgrow(masterView.getBaseNode(), Priority.NEVER);
        HBox.setHgrow(detailView.getBaseNode(), Priority.ALWAYS);

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
