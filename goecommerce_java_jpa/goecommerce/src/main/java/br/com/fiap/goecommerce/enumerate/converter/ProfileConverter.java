package br.com.fiap.goecommerce.enumerate.converter;

import br.com.fiap.goecommerce.enumerate.Profile;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProfileConverter implements AttributeConverter<Profile,Integer> {

    @Override
    public Integer convertToDatabaseColumn(Profile profile) {
        if(profile == null){
            return null;
        }
        return profile.getCod();
    }

    @Override
    public Profile convertToEntityAttribute(Integer code) {
        if (code == null){
            return null;
        }

        return Stream.of(Profile.values())
                .filter(p -> p.getCod().equals(code))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
