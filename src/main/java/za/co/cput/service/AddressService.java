package za.co.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.cput.domain.Address;
import za.co.cput.repository.AddressRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
