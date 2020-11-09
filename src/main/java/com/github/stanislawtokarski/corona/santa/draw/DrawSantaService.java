package com.github.stanislawtokarski.corona.santa.draw;

import com.github.stanislawtokarski.corona.santa.model.Participant;

import javax.mail.MessagingException;
import java.util.List;

public interface DrawSantaService {

    void drawAndNotify(List<Participant> participants) throws MessagingException;
}
