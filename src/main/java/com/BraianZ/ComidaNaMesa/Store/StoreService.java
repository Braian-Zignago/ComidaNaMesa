package com.BraianZ.ComidaNaMesa.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreMapper storeMapper;
    private final StoreRepository storeRepository;

    public StoreResponseDTO register(StoreRequestDTO storeRequestDTO) {
        StoreModel storeModel = storeMapper.forStoreModel(storeRequestDTO);
        StoreResponseDTO storeResponseDTO = null;
        try {
            storeResponseDTO = storeMapper.forStoreResponseDTO(storeRepository.save(storeModel));
        } catch (Exception e) {
            throw new RuntimeException("Error saving store: " + e.getMessage());
        }
        return storeResponseDTO;
    }
}
