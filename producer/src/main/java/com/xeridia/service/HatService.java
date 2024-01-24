package com.xeridia.service;

import com.xeridia.model.Hat;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class HatService {
    private final Map<Long, Hat> hatMap;

    public HatService() {
        hatMap = Map.of(
                1L, new Hat(1L, "Test Hat 1", 10, "striped"),
                2L, new Hat(2L, "Test Hat 2", 7, "green")
        );
    }
    public Hat findHatById(Long id) {
        return hatMap.get(id);
    }

    public Collection<Hat> findAll() {
        return hatMap.values();
    }
}


