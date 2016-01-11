package com.guigarage.dolphin.view;

import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.Param;
import com.canoo.dolphin.client.javafx.AbstractViewBinder;
import com.canoo.dolphin.client.javafx.FXBinder;
import com.guigarage.dolphin.Constants;
import com.guigarage.dolphin.model.DetailModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class DetailView extends AbstractViewBinder<DetailModel> {

    @FXML
    private TextField nameField;

    @FXML
    private Slider valueSlider;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button resetButton;

    private Parent baseNode;

    public DetailView(ClientContext clientContext) {
        super(clientContext, Constants.DETAIL_CONTROLLER_NAME);

        try {
            FXMLLoader loader = new FXMLLoader(DetailView.class.getResource("detailView.fxml"));
            loader.setController(this);
            baseNode = loader.load();
        } catch (Exception e) {
            throw new RuntimeException("Can't load FXML for view " + DetailView.class.getSimpleName(), e);
        }
    }

    @Override
    protected void init() {
        FXBinder.bind(nameField.textProperty()).bidirectionalTo(getModel().stockNameProperty());
        FXBinder.bind(valueSlider.valueProperty()).bidirectionalToNumeric(getModel().stockValueProperty());
        FXBinder.bind(valueSlider.minProperty()).bidirectionalToNumeric(getModel().minValueProperty());
        FXBinder.bind(valueSlider.maxProperty()).bidirectionalToNumeric(getModel().maxValueProperty());
        FXBinder.bind(typeChoiceBox.valueProperty()).bidirectionalTo(getModel().stockTypeProperty());
        FXBinder.bind(typeChoiceBox.getItems()).to(getModel().stockTypesProperty());

        saveButton.setOnAction(e -> invoke(Constants.DETAIL_CONTROLLER_SAVE_ACTION));
        resetButton.setOnAction(e -> invoke(Constants.DETAIL_CONTROLLER_RESET_ACTION));
    }

    public void showStock(String stockIdent) {
        Param param = new Param(Constants.STOCK_IDENT_PARAM, stockIdent);
        invoke(Constants.DETAIL_CONTROLLER_UPDATE_ACTION, param);
    }

    public Parent getBaseNode() {
        return baseNode;
    }
}
