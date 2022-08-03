package com.infoshareacademy;

import com.infoshareacademy.entity.*;
import com.infoshareacademy.service.json.ClientJsonFileHandler;
import com.infoshareacademy.service.json.ServiceOrderJsonFileHandler;
import com.infoshareacademy.service.json.VehicleJsonFileHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class ServiceBoxWebApplication {
    @Value("${app.createSampleDatabase:false}")
    private String createDb;
    private final VehicleJsonFileHandler vehicleJsonFileHandler;

    private final ClientJsonFileHandler clientJsonFileHandler;
    private final ServiceOrderJsonFileHandler orderJsonFileHandler;

    public ServiceBoxWebApplication(
            VehicleJsonFileHandler vehicleJsonFileHandler,
            ClientJsonFileHandler clientJsonFileHandler,
            ServiceOrderJsonFileHandler orderJsonFileHandler) {
        this.vehicleJsonFileHandler = vehicleJsonFileHandler;
        this.clientJsonFileHandler = clientJsonFileHandler;
        this.orderJsonFileHandler = orderJsonFileHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceBoxWebApplication.class, args);
    }

    @PostConstruct
    public void initDatabase() throws IOException {
        if (createDb.equals("false")) return;

        // create cars
        Vehicle opel = new Vehicle("Opel", "Astra", "PK123456", 1.2, 2010);
        Vehicle audi = new Vehicle("Audi", "TT", "PO234566", 2.0, 2001);
        Vehicle fiat = new Vehicle("Fiat", "126p", "WD123987", 0.6, 1990);

        // create clients
        Client c1 = new Client("Dawid Górecki", "500600700", "mail@wp.pl");
        Client c2 = new Client("Jan Kowalski", "567567898", "mail.test@wp.pl");

        c2.setAddress(new Address("Warszawska", "10B", "", "60-685", "Poznań"));

        // add clients to JSON file
        clientJsonFileHandler.add(c1);
        clientJsonFileHandler.add(c2);

        // create relations - assign vehicles to clients
        c1.addVehicle(opel);
        c1.addVehicle(audi);
        c2.addVehicle(fiat);

        // add (save) vehicles
        vehicleJsonFileHandler.add(opel);
        vehicleJsonFileHandler.add(audi);
        vehicleJsonFileHandler.add(fiat);

        // create service order
        ServiceOrder order1 = new ServiceOrder(audi, "1/2022", true, 1000, "Wymiana klocków hamulcowych");

        // create repair card
        RepairCard repairCard1 = new RepairCard();
        repairCard1.addRepair(new Repair("Wymiana klocków", 250));
        repairCard1.addPart(new Part("Klocki hamulcowe", 150, 1));

        // add repair card to order
        order1.addRepairCard(repairCard1);

        // create another order
        ServiceOrder order2 = new ServiceOrder(fiat, "2/2022", false, 300, "Wymiana oleju");

        // save orders
        orderJsonFileHandler.add(order1);
        orderJsonFileHandler.add(order2);
    }
}
