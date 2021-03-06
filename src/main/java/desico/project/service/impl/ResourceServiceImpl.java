package desico.project.service.impl;

import desico.project.model.entity.VideoEntity;
import desico.project.repository.ResourceRepository;
import desico.project.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;


    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;

    }

    @Override
    public List<String> findAllNames() {
        return resourceRepository.findAllNames();
    }

    @Override
    public VideoEntity findByVideoName(String name) {
        return resourceRepository.findByVideoName(name).orElse(null);
    }
}
