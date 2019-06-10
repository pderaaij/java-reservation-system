package nl.paulderaaij.reservation.infrastructure;

import nl.paulderaaij.reservation.application.ReservationService;
import nl.paulderaaij.reservation.infrastructure.events.repositories.FacadeEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {
    private FacadeEventRepository eventRepository;

    @Autowired
    public DomainConfiguration(FacadeEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Bean
    public ReservationService provideReservationService() {
        return new ReservationService(this.eventRepository);
    }

}
