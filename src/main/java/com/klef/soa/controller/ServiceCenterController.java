package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.entity.ServiceCenter;
import com.klef.soa.service.ServiceCenterService;

@RestController
@RequestMapping("/servicecenter")
public class ServiceCenterController
{

    @Autowired
    private ServiceCenterService service;

    @GetMapping("/")
    public String test()
    {
        return "SOA Service Center API";
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceCenter> add(@RequestBody ServiceCenter sc)
    {
        return ResponseEntity.status(201).body(service.addServiceCenter(sc));
    }

    @GetMapping("/displayall")
    public ResponseEntity<List<ServiceCenter>> displayAll()
    {
        return ResponseEntity.ok(service.displayAllServiceCenters());
    }

    @GetMapping("/display")
    public ResponseEntity<ServiceCenter> displayById(@RequestParam Long id)
    {
        return ResponseEntity.ok(service.displayServiceCenterById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<ServiceCenter> update(@RequestBody ServiceCenter sc)
    {
        return ResponseEntity.ok(service.updateServiceCenter(sc));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.deleteServiceCenter(id));
    }

    @GetMapping("/displaybyname/{name}")
    public ResponseEntity<List<ServiceCenter>> displayByName(@PathVariable String name)
    {
        return ResponseEntity.ok(service.displayByName(name));
    }

    @GetMapping("/displaybytype/{type}")
    public ResponseEntity<List<ServiceCenter>> displayByType(@PathVariable String type)
    {
        return ResponseEntity.ok(service.displayByType(type));
    }

}