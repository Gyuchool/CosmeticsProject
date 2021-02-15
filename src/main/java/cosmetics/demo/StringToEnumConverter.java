package cosmetics.demo;

import cosmetics.demo.Domain.Entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;

public class StringToEnumConverter implements Converter<String, Category> {

    @Override
    public Category convert(String source) {
        try{
            return Category.valueOf(source.toUpperCase());
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
