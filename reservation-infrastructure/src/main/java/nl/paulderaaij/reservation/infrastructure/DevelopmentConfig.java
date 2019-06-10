package nl.paulderaaij.reservation.infrastructure;

import nl.paulderaaij.reservation.infrastructure.events.entities.Event;
import nl.paulderaaij.reservation.infrastructure.events.repositories.EventRepository;
import nl.paulderaaij.reservation.infrastructure.events.entities.Reservation;
import nl.paulderaaij.reservation.infrastructure.events.repositories.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(EventRepository eventRepository, ReservationRepository reservationRepository) {
        return args -> {
            Event event = new Event(
                    UUID.fromString("f67270e4-00e5-4a37-b5d4-a2ee642e7e68"),
                    "Test event",
                    50,
                    LocalDate.now()
            );

            eventRepository.save(event);

            Reservation reservation = new Reservation(
                    event,
                    4
            );

            reservationRepository.save(reservation);

            Event event1 = new Event(
                    UUID.randomUUID(),
                    "Automated test event",
                    100,
                    LocalDate.now().plusDays(50)
            );

            eventRepository.save(event1);
        };
    }
}
