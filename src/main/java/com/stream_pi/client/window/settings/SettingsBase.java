package com.stream_pi.client.window.settings;

import com.stream_pi.client.controller.ClientListener;
import com.stream_pi.client.window.ExceptionAndAlertHandler;
import com.stream_pi.client.window.settings.About.AboutTab;

import javafx.application.HostServices;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.control.*;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SettingsBase extends VBox
{
    private TabPane tabPane;

    private GeneralTab generalTab;

    private Button closeButton;

    private HostServices hostServices;
    private ExceptionAndAlertHandler exceptionAndAlertHandler;

    public SettingsBase(HostServices hostServices, ExceptionAndAlertHandler exceptionAndAlertHandler,
                        ClientListener clientListener)
    {
        this.exceptionAndAlertHandler = exceptionAndAlertHandler;
        this.hostServices = hostServices;

        tabPane = new TabPane();
        tabPane.addEventFilter(SwipeEvent.ANY, Event::consume);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        Tab generalSettingsTab = new Tab("Settings");
        generalTab = new GeneralTab(exceptionAndAlertHandler, clientListener, hostServices);
        generalSettingsTab.setContent(generalTab);

        Tab aboutTab = new Tab("About");
        aboutTab.setContent(new AboutTab(clientListener));

        tabPane.getTabs().addAll(generalSettingsTab, aboutTab);

        setAlignment(Pos.TOP_RIGHT);

        closeButton = new Button("Close");
        VBox.setMargin(closeButton, new Insets(5.0));

        getChildren().addAll(tabPane, closeButton);

        setCache(true);
        setCacheHint(CacheHint.SPEED);
    }

    public void setDefaultTabToGeneral()
    {
        tabPane.getSelectionModel().selectFirst();
    }

    public Button getCloseButton()
    {
        return closeButton;
    }

    public GeneralTab getGeneralTab()
    {
        return generalTab;
    }
}
