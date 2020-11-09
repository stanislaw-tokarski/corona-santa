package com.github.stanislawtokarski.corona.santa.draw;

import com.github.stanislawtokarski.corona.santa.mail.MailSender;
import com.github.stanislawtokarski.corona.santa.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class DrawSantaServiceImpl implements DrawSantaService {

    private final MailSender mailSender;

    @Autowired
    public DrawSantaServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void drawAndNotify(List<Participant> participants) throws MessagingException {
        Map<Participant, Participant> fromToParticipantsMap = new HashMap<>(participants.size());
        Collections.shuffle(participants);
        IntStream.range(0, participants.size() - 1)
                .boxed()
                .forEach(index -> fromToParticipantsMap.put(participants.get(index), participants.get(index + 1)));
        fromToParticipantsMap.put(participants.get(participants.size() - 1), participants.get(0));
        for (Map.Entry<Participant, Participant> fromToParticipantEntry : fromToParticipantsMap.entrySet()) {
            mailSender.push(fromToParticipantEntry.getKey().getEmail(), "Your Corona Santa drew you...",
                    message(fromToParticipantEntry.getKey().getName(), fromToParticipantEntry.getValue().getName()));
        }
    }

    private String message(String giverName, String recipientName) {
        return String.format("...%s! Prepare a gift so that it is absolutely unique and makes him/her a lot of joy! " +
                "See you under the Christmas tree, %s!\nYours,\nCorona Santa", recipientName, giverName);
    }
}
