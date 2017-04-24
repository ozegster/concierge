package ba.codecentric.base.service;

import ba.codecentric.base.domain.BedType;
import ba.codecentric.base.repository.BedTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BedTypeServiceImpl implements BedTypeService {

    private final BedTypeRepository bedTypeRepository;

    @Autowired
    public BedTypeServiceImpl(BedTypeRepository bedTypeRepository) {
        this.bedTypeRepository = bedTypeRepository;
    }

    @Override
    @Transactional
    public List<BedType> getAllBeds() {
        return bedTypeRepository.findAll();
    }
}
