package com.magdy.challenge.tenpo.adapter.shared;
import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.infrastructure.repository.entity.HistoryEntity;
import org.modelmapper.ModelMapper;

public class Mapper {

    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public HistoryEntity toEntity(History history){
       return this.modelMapper.map(history, HistoryEntity.class);
    }

    public History toDTO(HistoryEntity history){
        return this.modelMapper.map(history, History.class);
    }

}
