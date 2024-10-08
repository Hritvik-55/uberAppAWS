package com.application.uberApp.configs;

import com.application.uberApp.dto.PointDTO;
import com.application.uberApp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper= new ModelMapper();
        mapper.typeMap(PointDTO.class, Point.class).setConverter(converter->{
            PointDTO pointDTO=converter.getSource();
            return GeometryUtil.createPoint(pointDTO);

                    });
        mapper.typeMap(Point.class,PointDTO.class).setConverter(mappingContext -> {
            Point point=mappingContext.getSource();
            double[] coordinates ={
                    point.getX(),point.getY()
            };
            return new PointDTO(coordinates);
        });
        return mapper;
    }



}
