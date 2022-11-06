package com.infoshareacademy.controller.client;

import com.infoshareacademy.dto.serviceorder.ServiceOrderDto;
import com.infoshareacademy.entity.serviceorder.ServiceOrder;
import com.infoshareacademy.entity.user.User;
import com.infoshareacademy.service.ServiceOrderService;
import com.infoshareacademy.service.ThymeleafParser;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class PdfController {
    private final ThymeleafParser thymeleafParser;
    private final ServiceOrderService serviceOrderService;

    @GetMapping("/service-orders/{id}/pdf")
    public ResponseEntity<?> getPDF(@PathVariable Long id) {
        Optional<ServiceOrderDto> serviceOrderDto = serviceOrderService.findServiceOrder(id);

        if (serviceOrderDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // TODO: check if logged user is owner of requested service order

//        Map<String, ServiceOrderDto> vars = new HashMap<>();
//        vars.put("order", serviceOrderDto.get());
        String html = thymeleafParser.parseThymeleafTemplate("pdf/service-order", serviceOrderDto.get());
        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
//        ConverterProperties converterProperties = new ConverterProperties();
//        converterProperties.setBaseUri("http://localhost:8080");

        /* Call convert method */
        HtmlConverter.convertToPdf(html, target);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();

        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }
}
