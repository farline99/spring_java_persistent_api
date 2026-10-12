package ru.example.phonebook;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class OwnerService {
    private final OwnerRepository owners;

    public OwnerService(OwnerRepository owners) {
        this.owners = owners;
    }

    @Transactional(readOnly = true)
    public List<Owner> search(SearchForm form) {
        return owners.search(form.getLastName(), form.getCity(), form.getNumber());
    }

    @Transactional(readOnly = true)
    public Owner find(Long id) {
        return owners.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Owner create(OwnerForm form) {
        Address address = new Address();
        address.setPostalCode(form.getPostalCode());
        address.setCountry(form.getCountry());
        address.setRegion(form.getRegion());
        address.setDistrict(form.getDistrict());
        address.setCity(form.getCity());
        address.setStreet(form.getStreet());
        address.setHouse(form.getHouse());
        address.setApartment(form.getApartment());
        Owner owner = new Owner();
        owner.setLastName(form.getLastName());
        owner.setFirstName(form.getFirstName());
        owner.setPatronymic(form.getPatronymic());
        owner.setAddress(address);
        Phone phone = new Phone();
        phone.setNumber(form.getNumber());
        owner.addPhone(phone);
        return owners.save(owner);
    }

    public void addPhone(Long id, PhoneForm form) {
        Owner owner = find(id);
        Phone phone = new Phone();
        phone.setNumber(form.getNumber());
        owner.addPhone(phone);
        owners.save(owner);
    }
}
