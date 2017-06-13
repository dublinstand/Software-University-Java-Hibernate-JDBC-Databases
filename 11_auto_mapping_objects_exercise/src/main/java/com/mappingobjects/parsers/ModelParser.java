package com.mappingobjects.parsers;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelParser {

    //this is our ModelMapper for converting the DTO to Entity and visa versa
    private ModelMapper modelMapper;

    //in our constructor we initialize ModelMapper
    public ModelParser() {
        this.modelMapper = new ModelMapper();
    }

    //this is a generic method - using Source and Destination and returning the converted object (the Destination object)
    public <S,D> D convert(S source, Class<D> destination){
        //we create a new object, that comes from the model mapper
        D convertedObject = this.modelMapper.map(source, destination);
        return convertedObject;
    }

    //we do an overload of the method, so that we can do a mapping of the properties, by saying explicitly how to convert
    //here we pass the source and the destination, together with the PropertyMap
    public <S,D> D convert(S source, Class<D> destination, PropertyMap<S,D> propertyMap){
        //we pass the mapping to the model mapper
        this.modelMapper.addMappings(propertyMap);
        D convertedObject = this.modelMapper.map(source, destination);
        return convertedObject;
    }

    //this is a generic method - using Source and Destination - getting a List of the Source and returning a list of Destination
    public <S,D> List<D> convert(List<S> source, Class<D> destination){
        List<D> convertedList = new ArrayList<D>();
        //for each object in the source list, convert it with the mapper and add it to the convertedList
        for(S s : source){
            D convertedType = this.modelMapper.map(s, destination);
            convertedList.add(convertedType);
        }
        return convertedList;
    }
}
