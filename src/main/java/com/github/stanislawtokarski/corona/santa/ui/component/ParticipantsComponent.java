package com.github.stanislawtokarski.corona.santa.ui.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class ParticipantsComponent extends VerticalLayout {

    private final VerticalLayout rowsLayout = new VerticalLayout();
    private final List<Row> rows = new ArrayList<>();
    private final Button drawButton = new Button("Draw and send emails");

    public ParticipantsComponent() {
        var addRowButton = new Button("Add another Santa");
        addRowButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        addRowButton.addClickListener(__ -> addRow());
        drawButton.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        addRow();
        add(rowsLayout, new HorizontalLayout(addRowButton, drawButton));
    }

    public void initListeners(ComponentEventListener<ClickEvent<Button>> drawButtonClickedListener) {
        drawButton.addClickListener(drawButtonClickedListener);
    }

    public Map<String, String> getParticipants() {
        return rows.stream()
                .collect(toMap(Row::getUsername, Row::getEmail));
    }

    private void addRow() {
        var row = new Row();
        rows.add(row);
        rowsLayout.add(row);
    }

    private void removeRow(Row row) {
        rows.remove(row);
        rowsLayout.remove(row);
    }

    private class Row extends HorizontalLayout {

        private final TextField usernameField = new TextField("Santa's name");
        private final EmailField emailField = new EmailField("Santa's email");

        private Row() {
            var removeButton = new Button("Remove Santa");
            removeButton.addClickListener(e -> {
                if (rows.size() > 1) {
                    removeRow(this);
                } else {
                    usernameField.clear();
                    emailField.clear();
                }
            });
            removeButton.addThemeVariants(ButtonVariant.MATERIAL_OUTLINED);
            var layout = new HorizontalLayout(usernameField, emailField, removeButton);
            this.add(layout);
        }

        private String getUsername() {
            return usernameField.getValue();
        }

        private String getEmail() {
            return emailField.getValue();
        }
    }
}
