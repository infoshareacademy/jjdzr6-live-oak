package com.infoshareacademy.controller.employee;

import com.infoshareacademy.dto.serviceorder.ServiceOrderDetailsDto;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.entity.vehicle.Vehicle;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.ServiceOrderService;
import com.infoshareacademy.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor
public class ServiceOrderController {
    private final ServiceOrderService serviceOrderService;
    private final VehicleService vehicleService;
    private final ClientService clientService;

    @GetMapping("/service-orders")
    public String getOrders(
            Model model,
            @RequestParam(name = "q", required = false, defaultValue = "") String searchQuery
    ) {
        if (!searchQuery.isBlank()) {
            model.addAttribute("serviceOrders", serviceOrderService.findByQuery(searchQuery));
            model.addAttribute("searchQuery", searchQuery);
        } else {
            model.addAttribute("created", serviceOrderService.findByState(ServiceOrderState.CREATED));
            model.addAttribute("inProgress", serviceOrderService.findByState(ServiceOrderState.IN_PROGRESS));
            model.addAttribute("finished", serviceOrderService.findByState(ServiceOrderState.FINISHED));
        }

        return "employee/service-order-list";
    }

    @GetMapping("/service-orders/{id}/details")
    public String showServiceOrderDetails(@PathVariable("id") Long serviceOrderId, Model model) {
        ServiceOrderDetailsDto serviceOrderDetails = serviceOrderService.getServiceOrderDetails(serviceOrderId);
        VehicleDto vehicleDto = vehicleService.findById(serviceOrderDetails.getVehicleId());

        model.addAttribute("serviceOrderDetails", serviceOrderDetails);
        model.addAttribute("vehicle", vehicleDto);

        return "employee/service-order-details";
    }

    @PostMapping("/service-orders/add-note")
    public String addNote(@RequestParam("id") Long serviceOrderId, @RequestParam("note") String note, RedirectAttributes redirectAttributes) {

        serviceOrderService.addNote(serviceOrderId, note);
        redirectAttributes.addFlashAttribute("success", "Notatka została zapisana.");
        return "redirect:/employee/service-orders";
    }

    @GetMapping("/service-orders/{id}/change-state")
    public String changeServiceOrderState(@PathVariable("id") Long serviceOrderId) {
        serviceOrderService.updateStatus(serviceOrderId);
        return "redirect:/employee/service-orders";
    }

    @GetMapping("/service-order")
    public String newServiceOrder() {
        return "employee/create-service-order";
    }

    @PostMapping("/service-order")
    public String createServiceOrder(
            @RequestParam("plateNumber") String plateNumber,
            RedirectAttributes redirectAttributes
    ) {
        Optional<Vehicle> vehicle = vehicleService.findByPlateNumber(plateNumber);

        if (vehicle.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Nie znaleziono pojazdu o numerze rejestracyjnym <strong>" + plateNumber + "</strong>");
            return "redirect:/employee/service-order";
        }

        return "redirect:/employee/vehicles/" + vehicle.get().getId() + "/create-service-order";
    }
}
