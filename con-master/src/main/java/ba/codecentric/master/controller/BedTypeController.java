package ba.codecentric.master.controller;

import ba.codecentric.base.domain.BedType;
import ba.codecentric.base.service.BedTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BedTypeController {

    private final BedTypeService bedTypeService;

    @Autowired
    public BedTypeController(BedTypeService bedTypeService) {
        this.bedTypeService = bedTypeService;
    }

    @RequestMapping(value = "/beds", method = RequestMethod.GET)
    public List<BedType> getAllBeds(){
        return bedTypeService.getAllBeds();
    }
}
