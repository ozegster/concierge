package ba.codecentric.master.schedule;

import ba.codecentric.base.domain.RoomCheckIn;
import ba.codecentric.base.repository.RoomCheckInRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PasswordReset {

    private final static Logger LOG = Logger.getLogger(PasswordReset.class);
    private final RoomCheckInRepository roomCheckInRepository;

    @Autowired
    public PasswordReset(RoomCheckInRepository roomCheckInRepository) {
        this.roomCheckInRepository = roomCheckInRepository;
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void resetPassword() {

        for (RoomCheckIn roomCheckIn : roomCheckInRepository.findByCheckOutDate(new Date())) {
            roomCheckIn.setPassword(0);
            roomCheckInRepository.save(roomCheckIn);
            LOG.info("Reset password for room number: " + roomCheckIn.getRoom().getNumber());
        }
    }
}
