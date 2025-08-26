package com.BraianZ.ComidaNaMesa.Addresses;

import com.BraianZ.ComidaNaMesa.Customer.CustomerModel;
import com.BraianZ.ComidaNaMesa.Customer.CustomerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressesService {

    private final AddressesRepository addressesRepository;
    private final AddressesMapper addressesMapper;

    public AddressesResponseDTO register(AddressesRequestDTO addressesRequestDTO){
        AddressesModel addressesModel = addressesMapper.forAddressesModel(addressesRequestDTO);
        AddressesResponseDTO addressesResponseDTO = null;
        try {
            addressesResponseDTO = addressesMapper.forAddressesResponseDTO(addressesRepository.save(addressesModel));
        } catch (Exception e) {
            throw new RuntimeException("Error saving Address: " + e.getMessage());
        }
        return addressesResponseDTO;
    }

    public List<AddressesResponseDTO> getAll(){
        List<AddressesModel> addressesModelList = addressesRepository.findAll();
        List<AddressesResponseDTO> addressesResponseDTO = addressesMapper.forAddressesResponseDTOList(addressesModelList);
        return addressesResponseDTO;
    }

    public AddressesResponseDTO getById(Long id) {
        Optional<AddressesModel> addressesModel = addressesRepository.findById(id);
        return addressesModel.map(addressesMapper::forAddressesResponseDTO).orElse(null);
    }

    public AddressesResponseDTO deleteById(Long id) {
        AddressesResponseDTO addressesResponseDTO = getById(id);
        if (addressesResponseDTO == null) {
            return null;
        }
        addressesRepository.deleteById(id);
        return addressesResponseDTO;
    }
}
