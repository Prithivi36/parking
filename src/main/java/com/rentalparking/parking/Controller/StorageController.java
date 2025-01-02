package com.rentalparking.parking.Controller;

import com.rentalparking.parking.Entity.Storage;
import com.rentalparking.parking.Service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
@CrossOrigin("*")
@AllArgsConstructor
public class StorageController {
    StorageService storageService;

    //fine
    @PostMapping("/")
    public String addStorage(@RequestBody Storage storage){
        return storageService.addStorage(storage);
    }

    //fine
    @GetMapping("/{id}")
    public Storage getStorage(@PathVariable String id){
        return storageService.getStorage(id);
    }
    //fine
    @PatchMapping("/{id}/{avail}")
    public String toggleAvail(@PathVariable String id, @PathVariable char avail){
        return  storageService.toggleAvailability(id,avail=='1');
    }
    //fine
    @DeleteMapping("/{id}")
    public String deleteStorage(@PathVariable String id){
        return storageService.deleteParking(id);
    }
    //fine
    @GetMapping("/{lt}/{ld}/{rad}")
    public List<Storage> findNear(@PathVariable double lt,@PathVariable double ld , @PathVariable int rad){
        return storageService.findNearBy(lt,ld,rad);
    }
    //fine
    @GetMapping("/my/{id}")
    public List<Storage> getMine(@PathVariable String id){
        return storageService.findMine(id);
    }

}
