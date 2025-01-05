package com.arihant.demo1.controller;

import com.arihant.demo1.model.MAC;
import com.arihant.demo1.repository.MacInterface;
import com.arihant.demo1.service.MacAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mac")
public class MacController {

    @Autowired
    private MacAddressService macAddressService;

    @Autowired
    private MacInterface macInterface;

    @PostMapping("/addValidmac")
    public ResponseEntity<MAC> addValidMac() {
        String toBeaddedMac = macAddressService.getMacAddress();

        if (macInterface.existsBymacadd(toBeaddedMac)) {
            return ResponseEntity.badRequest().body(new MAC("MAC Address Already exists in the DB"));
        }
        MAC mac = new MAC(toBeaddedMac);
        macInterface.save(mac);
        return ResponseEntity.ok(mac);
    }
}
