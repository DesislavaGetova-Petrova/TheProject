package desico.project.service.impl;

import desico.project.model.entity.UnitNameEntity;
import desico.project.model.service.UnitNameServiceModel;
import desico.project.repository.UnitNameRepository;
import desico.project.service.UnitNameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitNameServiceImpl implements UnitNameService {
    private final UnitNameRepository unitNameRepository;
    private  final ModelMapper modelMapper;

    public UnitNameServiceImpl(UnitNameRepository unitNameRepository, ModelMapper modelMapper) {
        this.unitNameRepository = unitNameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addUnit(UnitNameServiceModel unitNameServiceModel) {
        unitNameRepository.save(modelMapper.map(unitNameServiceModel, UnitNameEntity.class));
    }

    @Override
    public UnitNameEntity findByUnitName(String unitName) {
        return unitNameRepository.findByUnitName(unitName);
    }

    @Override
    public List<UnitNameEntity> findAll() {
        return unitNameRepository.findAll();
    }


    @Override
    public List<String> findAllUnitNames() {
        return unitNameRepository.findAllUnitNames();
    }
}
