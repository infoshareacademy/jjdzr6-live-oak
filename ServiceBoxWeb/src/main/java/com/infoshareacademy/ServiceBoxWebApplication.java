package com.infoshareacademy;

import com.infoshareacademy.entity.*;
import com.infoshareacademy.repository.ClientRepository;
import com.infoshareacademy.repository.ServiceOrderRepository;
import com.infoshareacademy.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class ServiceBoxWebApplication {
    @Value("${app.createSampleDatabase:false}")
    private String createDb;

    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final ServiceOrderRepository orderRepository;

    public ServiceBoxWebApplication(
            VehicleRepository vehicleRepository,
            ClientRepository clientRepository,
            ServiceOrderRepository orderRepository) {
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceBoxWebApplication.class, args);
    }

    @PostConstruct
    public void initDatabase() throws IOException {
        Client c = clientRepository.find(1);
        System.out.println(vehicleRepository.findClientVehicles(c));
        if (createDb.equals("false")) return;

        // create cars
        Vehicle opel = new Vehicle("Opel", "Astra", "PK123456", 1.2, 2010);
        Vehicle audi = new Vehicle("Audi", "TT", "PO234566", 2.0, 2001);
        Vehicle fiat = new Vehicle("Fiat", "126p", "WD123987", 0.6, 1990);

        // create clients
        Client c1 = new Client("Anna Nowak", "500600700", "anna.nowak@wp.pl");
        Client c2 = new Client("Jan Kowalski", "567567898", "jan.kowalski@wp.pl");

        c1.setAddress(new Address("Krakowska", "179A", "", "60-685", "Poznań"));
        c2.setAddress(new Address("Warszawska", "10B", "", "60-685", "Poznań"));

        // add clients to JSON file
        clientRepository.add(c1);
        clientRepository.add(c2);
        clientRepository.save();

        // create relations - assign vehicles to clients
        c1.addVehicle(opel);
        c1.addVehicle(audi);
        c2.addVehicle(fiat);

        // add (save) vehicles
        vehicleRepository.add(opel);
        vehicleRepository.add(audi);
        vehicleRepository.add(fiat);
        vehicleRepository.save();

        // create service order
        ServiceOrder order1 = new ServiceOrder(1, audi, "1", true, 1000, "Wymiana klocków hamulcowych");

        // create repair card
        RepairCard repairCard1 = new RepairCard();
        repairCard1.addRepair(new Repair("Wymiana klocków", 250));
        repairCard1.addPart(new Part("Klocki hamulcowe", 150, 1));

        // add repair card to order
        order1.addRepairCard(repairCard1);

        // create another order
        ServiceOrder order2 = new ServiceOrder(2, fiat, "2", false, 300, "Wymiana oleju");

        // save orders
        orderRepository.add(order1);
        orderRepository.add(order2);
        orderRepository.save();
    }
}
