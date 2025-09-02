package com.BraianZ.ComidaNaMesa.Delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveyMapper deliveryMapper;
    private final DeliveryRespository deliveryRepository;

    public DeliveyResponseDTO register(DeliveyRequestDTO deliveyRequestDTO) {
        DeliveryModel deliveryModel = deliveryMapper.forDeliveryModel(deliveyRequestDTO);
        DeliveyResponseDTO deliveyResponseDTO = null;
        try {
            deliveyResponseDTO = deliveryMapper.forDeliveryResponseDTO(deliveryRepository.save(deliveryModel));
        } catch (Exception e) {
            throw new RuntimeException("Error saving delivery: " + e.getMessage());
        }
        return deliveyResponseDTO;
    }

    public List<DeliveyResponseDTO> getAll() {
        List<DeliveryModel> deliveryModel = deliveryRepository.findAll();
        return deliveryMapper.forDeliveryResponseDTOList(deliveryModel);
    }

    public DeliveyResponseDTO getById(Long id) {
        Optional<DeliveryModel> deliveryModel = deliveryRepository.findById(id);
        return deliveryModel.map(deliveryMapper::forDeliveryResponseDTO).orElse(null);
    }

    public DeliveyResponseDTO deleteById(Long id) {
        DeliveyResponseDTO deliveyResponseDTO = getById(id);
        if (deliveyResponseDTO == null) {
            return null;
        }
        deliveryRepository.deleteById(id);
        return deliveyResponseDTO;
    }
}
