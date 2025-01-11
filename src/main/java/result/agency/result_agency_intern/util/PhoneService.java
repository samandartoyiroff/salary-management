package result.agency.result_agency_intern.util;

import org.springframework.stereotype.Service;

@Service
public class PhoneService {

    public String concatPhoneNumber(String phoneNumber) {
        if (phoneNumber.length()<9) throw new IllegalArgumentException("Phone number must have at least 9 numbers");
        return phoneNumber.substring(phoneNumber.length()-9);
    }

}
