package ba.codecentric.base.service;

import ba.codecentric.base.domain.Bed;
import ba.codecentric.base.repository.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedServiceImpl implements BedService {

    private final BedRepository bedRepository;

    @Autowired
    public BedServiceImpl(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    @Override
    public List<Bed> getAllBeds() {
        return bedRepository.findAll();
    }
}
