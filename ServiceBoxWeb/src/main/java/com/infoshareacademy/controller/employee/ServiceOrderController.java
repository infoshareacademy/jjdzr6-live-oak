package com.infoshareacademy.controller.employee;


import com.infoshareacademy.dto.serviceorder.ServiceOrderDetailsDto;
import com.infoshareacademy.dto.serviceorder.ServiceOrderDto;
import com.infoshareacademy.dto.vehicle.VehicleDto;
import com.infoshareacademy.entity.serviceorder.ServiceOrderState;
import com.infoshareacademy.service.ClientService;
import com.infoshareacademy.service.ServiceOrderService;
import com.infoshareacademy.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employee/service-orders")
@RequiredArgsConstructor
public class ServiceOrderController {
    private final ServiceOrderService serviceOrderService;
    private final VehicleService vehicleService;
    private final ClientService clientService;

    @GetMapping
    public String getOrders(Model model, @RequestParam(name = "s", required = false, defaultValue = "") String searchQuery, @RequestParam(name = "f", required = false, defaultValue = "") String filter) {
        if (searchQuery.isBlank() && filter.isBlank()) {
            model.addAttribute("serviceOrders", serviceOrderService.findAll());
        }

        if (filter.isBlank() && !searchQuery.isBlank()) {
            model.addAttribute("serviceOrders", serviceOrderService.findByQuery(searchQuery));
            model.addAttribute("searchQuery", searchQuery);
        }

        if (searchQuery.isBlank() && !filter.isBlank()) {
            ServiceOrderState state = ServiceOrderState.CREATED;
            if (filter.equals("inprogress")) {
                state = ServiceOrderState.IN_PROGRESS;
            } else if (filter.equals("finished")) {
                state = ServiceOrderState.FINISHED;
            }
            model.addAttribute("serviceOrders", serviceOrderService.filterByState(state));
        }

        return "employee/service-order-list";
    }

    @GetMapping("/{id}/details")
    public String showServiceOrderDetails(@PathVariable("id") Long serviceOrderId, Model model) {
        ServiceOrderDetailsDto serviceOrderDetails = serviceOrderService.getServiceOrderDetails(serviceOrderId);
        VehicleDto vehicleDto = vehicleService.findById(serviceOrderDetails.getVehicleId());

        model.addAttribute("serviceOrderDetails", serviceOrderDetails);
        model.addAttribute("vehicle", vehicleDto);

        return "employee/service-order-details";
    }

    @PostMapping("add-note")
    public String addNote(@RequestParam("id") Long serviceOrderId, @RequestParam("note") String note, RedirectAttributes redirectAttributes) {

        serviceOrderService.addNote(serviceOrderId, note);
        redirectAttributes.addFlashAttribute("success", "Notatka została zapisana.");
        return "redirect:/employee/service-orders";
    }

    @GetMapping("/{id}/change-state")
    public String changeServiceOrderState(@PathVariable("id") Long serviceOrderId) {
        serviceOrderService.updateStatus(serviceOrderId);
        return "redirect:/employee/service-orders";
    }
}
