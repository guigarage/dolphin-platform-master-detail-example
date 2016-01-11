package com.guigarage.dolphin;

import com.canoo.dolphin.client.ClientConfiguration;
import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.ClientContextFactory;
import com.canoo.dolphin.client.javafx.JavaFXConfiguration;
import com.guigarage.dolphin.view.DetailView;
import com.guigarage.dolphin.view.MasterView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class ClientApplication extends Application {

    private ClientContext clientContext;

    private MasterView masterView;

    private DetailView detailView;

    @Override
    public void init() throws Exception {
        ClientConfiguration config = new JavaFXConfiguration("http://localhost:8080/dolphin");
        clientContext = ClientContextFactory.connect(config).get();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        masterView = new MasterView(clientContext);
        detailView = new DetailView(clientContext);
        masterView.selectedStockIdentProperty().addListener((obs, oldVal, newVal) -> detailView.showStock(newVal));

        HBox mainPane = new HBox(masterView.getBaseNode(), detailView.getBaseNode());
        mainPane.setSpacing(12);
        mainPane.setPadding(new Insets(12));
        HBox.setHgrow(masterView.getBaseNode(), Priority.NEVER);
        HBox.setHgrow(detailView.getBaseNode(), Priority.ALWAYS);

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        masterView.destroy().get();
        detailView.destroy().get();
        clientContext.disconnect().get();
        System.exit(0);
    }

    public static void main(String... args) {
        launch(args);
    }
}
