package com.BraianZ.ComidaNaMesa.Store;

import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<StoreResponseDTO> getAll(){
        List<StoreModel> storeModel = storeRepository.findAll();
        return storeMapper.forStoreResponseDTOList(storeModel);
    }

    public StoreResponseDTO getById(Long id){
        Optional<StoreModel> storeModel = storeRepository.findById(id);
        return storeModel.map(storeMapper::forStoreResponseDTO).orElse(null);
    }

    public StoreResponseDTO deleteById(Long id){
        StoreResponseDTO storeResponseDTO = getById(id);
        if(storeResponseDTO == null){
            return null;
        }
        storeRepository.deleteById(id);
        return storeResponseDTO;
    }
}
