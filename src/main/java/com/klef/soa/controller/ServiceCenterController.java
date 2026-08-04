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
    public ResponseEntity<?> displayById(@RequestParam Long id)
    {
        ServiceCenter sc = service.displayServiceCenterById(id);

        if(sc!=null)
            return ResponseEntity.ok(sc);

        return ResponseEntity.status(404).body("Service Center ID Not Found");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ServiceCenter sc)
    {
        ServiceCenter center = service.updateServiceCenter(sc);

        if(center!=null)
            return ResponseEntity.ok(center);

        return ResponseEntity.status(404).body("Service Center ID Not Found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
    	String msg = service.deleteServiceCenter(id);

    	if(msg.equals("Service Center Deleted Successfully"))
    	    return ResponseEntity.ok(msg);

    	return ResponseEntity.status(404).body(msg);
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