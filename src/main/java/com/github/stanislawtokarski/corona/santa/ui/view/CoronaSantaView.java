package com.github.stanislawtokarski.corona.santa.ui.view;

import com.github.stanislawtokarski.corona.santa.ui.component.ParticipantsComponent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.Map;

public class CoronaSantaView extends HorizontalLayout {


    private final ParticipantsComponent participantsComponent = new ParticipantsComponent();

    public CoronaSantaView() {
        add(participantsComponent);
        this.setAlignItems(Alignment.CENTER);
    }

    public void initListeners(ComponentEventListener<ClickEvent<Button>> drawButtonClickedListener) {
        participantsComponent.initListeners(drawButtonClickedListener);
    }

    public Map<String, String> getParticipants() {
        return participantsComponent.getParticipants();
    }

    public static void notification(String message) {
        Notification.show(message, 5_000, Notification.Position.MIDDLE);
    }
}
