package com.github.stanislawtokarski.corona.santa.ui.presenter;

import com.github.stanislawtokarski.corona.santa.draw.DrawSantaService;
import com.github.stanislawtokarski.corona.santa.model.Participant;
import com.github.stanislawtokarski.corona.santa.ui.view.CoronaSantaView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static com.github.stanislawtokarski.corona.santa.ui.view.CoronaSantaView.notification;
import static java.util.stream.Collectors.toList;

@Route("")
@PageTitle("Corona Santa")
@Theme(value = Material.class)
public class CoronaSantaPresenter extends CanonicalPresenter<CoronaSantaView> {

    private static final Logger log = LoggerFactory.getLogger(CoronaSantaPresenter.class);

    private final DrawSantaService drawSantaService;

    public CoronaSantaPresenter(DrawSantaService drawSantaService) {
        this.drawSantaService = drawSantaService;
    }

    @Override
    protected void refresh() {
    }

    @PostConstruct
    private void initListeners() {
        view.initListeners(e -> onDrawButtonClicked());
        refresh();
    }

    private void onDrawButtonClicked() {
        try {
            var participants = this.view.getParticipants()
                    .entrySet()
                    .stream()
                    .map(Participant::fromMapEntry)
                    .collect(toList());
            drawSantaService.drawAndNotify(participants);
            notification("Participants successfully notified for whom they should prepare wonderful Christmas gifts!");
        } catch (Exception e) {
            log.error("Draw failed, showing error notification", e);
            notification("Oops, unexpected error occurred :(");
        }
    }
}
