package com.poc.restfulpoc.data;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.poc.restfulpoc.entities.Address;
import com.poc.restfulpoc.entities.Customer;
import com.poc.restfulpoc.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This should run only in dev environment
 * 
 * @author rajakolli
 *
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataBuilder implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        log.debug("Loading test data...");
        final ZoneId defaultZoneId = ZoneId.of("UTC");
        final Customer customer1 = new Customer("Raja", "Kolli",
                Date.from(LocalDate.of(1982, Month.JANUARY, 10)
                        .atStartOfDay(defaultZoneId).toInstant()),
                new Address("High Street", "Belfast", "India", "BT893PY"));

        final Customer customer2 = new Customer("Paul", "Jones",
                Date.from(LocalDate.of(1973, Month.JANUARY, 03)
                        .atStartOfDay(defaultZoneId).toInstant()),
                new Address("Main Street", "Lurgan", "Armagh", "BT283FG"));

        final Customer customer3 = new Customer("Steve", "Toale",
                Date.from(LocalDate.of(1979, Month.MARCH, 8).atStartOfDay(defaultZoneId)
                        .toInstant()),
                new Address("Main Street", "Newry", "Down", "BT359JK"));
        customerRepository.saveAll(
                Stream.of(customer1, customer2, customer3).collect(Collectors.toList()));
        log.debug("Test data loaded...");
    }

}
