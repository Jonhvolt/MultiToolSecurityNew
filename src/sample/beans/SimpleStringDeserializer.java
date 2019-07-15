package sample.beans;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import javafx.beans.property.SimpleStringProperty;

import java.lang.reflect.Type;

public class SimpleStringDeserializer implements JsonDeserializer<SimpleStringProperty> {

    @Override
    public SimpleStringProperty deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
        simpleStringProperty.set(jsonElement.getAsString());
        return simpleStringProperty;
    }
}
