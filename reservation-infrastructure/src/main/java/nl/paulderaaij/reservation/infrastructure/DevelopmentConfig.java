package nl.paulderaaij.reservation.infrastructure;

import nl.paulderaaij.reservation.infrastructure.events.Event;
import nl.paulderaaij.reservation.infrastructure.events.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(EventRepository eventRepository) {
        return args -> {
            Event event = new Event(
                    UUID.randomUUID(),
                    "Test event",
                    50,
                    LocalDate.now()
            );

            eventRepository.save(event);
        };
    }
}
