package de.planerio.developertest.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class Transformation {

    private static final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public static Object of(Object object, Class source, Class destination){
        mapperFactory.classMap(source, destination);
        final MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper.map(object, destination);
    }








}
