package com.infy.license.controller;

import com.infy.license.model.ErrorValidation;
import com.infy.license.model.License;
import com.infy.license.model.LicenseValidation;
import com.infy.license.service.LicenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/getlicense/{licenseNumber}")
    public Object getLicenseByLicenseNumber(@PathVariable("licenseNumber") String licenseNumber) throws Exception {
        //String licenseNumber = "ABC001";
        License license = licenseService.getLicenseByLicenseNumber(licenseNumber);
        if (license == null){
            return "License not Found";
        }
        return license;
    }

}