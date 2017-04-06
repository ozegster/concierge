package ba.codecentric.master.controller;

import ba.codecentric.base.domain.Bed;
import ba.codecentric.base.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BedController {

    private final BedService bedService;

    @Autowired
    public BedController(BedService bedService) {
        this.bedService = bedService;
    }

    @RequestMapping(value = "/beds", method = RequestMethod.GET)
    public List<Bed> getAllBeds(){
        return bedService.getAllBeds();
    }
}
