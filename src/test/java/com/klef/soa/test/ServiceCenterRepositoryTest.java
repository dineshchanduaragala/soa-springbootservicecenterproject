package com.klef.soa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.entity.ServiceCenter;
import com.klef.soa.repository.ServiceCenterRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceCenterRepositoryTest 
{
    @Autowired
    private ServiceCenterRepository repository;

    @Test
    void testSaveServiceCenter() 
    {
        ServiceCenter sc = new ServiceCenter();
        sc.setName("Apex Auto Service");
        sc.setType("Automobile");
        sc.setStatus("Active");
        sc.setLocation("Vijayawada");

        ServiceCenter saved = repository.save(sc);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Apex Auto Service", saved.getName());
        assertEquals("Automobile", saved.getType());
        System.out.println("Saved ServiceCenter ID: " + saved.getId());
    }

    @Test
    void testFindAllServiceCenters() 
    {
        List<ServiceCenter> list = repository.findAll();
        assertNotNull(list);
        System.out.println("Total Service Centers in DB: " + list.size());
    }

    @Test
    void testFindByName() 
    {
        ServiceCenter sc = new ServiceCenter();
        sc.setName("Speedy Motors");
        sc.setType("Two Wheeler");
        sc.setStatus("Active");
        sc.setLocation("Guntur");
        repository.save(sc);

        List<ServiceCenter> found = repository.findByName("Speedy Motors");
        assertNotNull(found);
        assertFalse(found.isEmpty());
        assertEquals("Speedy Motors", found.get(0).getName());
        System.out.println("Found by Name: " + found.get(0).getName());
    }

    @Test
    void testFindByType() 
    {
        ServiceCenter sc = new ServiceCenter();
        sc.setName("City Car Care");
        sc.setType("Automobile");
        sc.setStatus("Active");
        sc.setLocation("Hyderabad");
        repository.save(sc);

        List<ServiceCenter> found = repository.findByType("Automobile");
        assertNotNull(found);
        assertFalse(found.isEmpty());
        System.out.println("Found by Type 'Automobile': " + found.size());
    }

    @Test
    void testFindServiceCenterById() 
    {
        ServiceCenter sc = new ServiceCenter();
        sc.setName("Express Wheels");
        sc.setType("Four Wheeler");
        sc.setStatus("Active");
        sc.setLocation("Bangalore");
        ServiceCenter saved = repository.save(sc);

        Optional<ServiceCenter> optional = repository.findById(saved.getId());
        assertTrue(optional.isPresent());
        assertEquals(saved.getId(), optional.get().getId());
        System.out.println("Retrieved by ID: " + optional.get().getId());
    }

    @Test
    void testDatabaseConnection() 
    {
        long count = repository.count();
        assertTrue(count >= 0);
        System.out.println("Total Service Centers in Database: " + count);
    }

    @Test
    void testDeleteServiceCenter() 
    {
        ServiceCenter sc = new ServiceCenter();
        sc.setName("Temporary Hub");
        sc.setType("General");
        sc.setStatus("Inactive");
        sc.setLocation("Amaravati");
        ServiceCenter saved = repository.save(sc);

        Long savedId = saved.getId();
        repository.deleteById(savedId);

        Optional<ServiceCenter> optional = repository.findById(savedId);
        assertFalse(optional.isPresent());
        System.out.println("Deleted Service Center ID: " + savedId);
    }
}

