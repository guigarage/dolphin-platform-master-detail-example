package com.guigarage.dolphin.view;

import com.canoo.dolphin.client.ClientContext;
import com.canoo.dolphin.client.Param;
import com.canoo.dolphin.client.javafx.AbstractFXMLViewBinder;
import com.canoo.dolphin.client.javafx.FXBinder;
import com.guigarage.dolphin.Constants;
import com.guigarage.dolphin.model.DetailModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DetailView extends AbstractFXMLViewBinder<DetailModel> {

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

    public DetailView(ClientContext clientContext) throws IOException {
        super(clientContext, Constants.DETAIL_CONTROLLER_NAME, DetailView.class.getResource("detailView.fxml"));
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

}
