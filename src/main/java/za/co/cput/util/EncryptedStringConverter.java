package za.co.cput.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.cput.service.EncryptionService;

@Converter
@Component
public class EncryptedStringConverter implements AttributeConverter<String, String> {

    private static EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        EncryptedStringConverter.encryptionService = encryptionService;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (encryptionService == null) {
            encryptionService = new EncryptionService();
        }
        return encryptionService.encryptSensitiveField(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (encryptionService == null) {
            encryptionService = new EncryptionService();
        }
        return encryptionService.decryptSensitiveField(dbData);
    }
}