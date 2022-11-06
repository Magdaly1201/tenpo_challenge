package com.magdy.challenge.tenpo.adapter.repository;

import com.magdy.challenge.tenpo.core.history.model.History;
import com.magdy.challenge.tenpo.core.history.model.Status;
import com.magdy.challenge.tenpo.core.history.model.TypeTransaction;
import com.magdy.challenge.tenpo.core.history.port.HistoryRepository;
import com.magdy.challenge.tenpo.infrastructure.repository.dao.HistoryDao;
import com.magdy.challenge.tenpo.infrastructure.repository.entity.HistoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class HistoryAdapterRepository implements HistoryRepository {

    private final HistoryDao historyDao;
    private final ModelMapper modelMapper;

    public HistoryAdapterRepository(HistoryDao historyDao, ModelMapper modelMapper) {
        this.historyDao = historyDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createTransaction(History history) {
        historyDao.save(modelMapper.map(history,HistoryEntity.class));
    }

    @Override
    public float getLastPercentage() {
        String percentage = historyDao.findFirstByStatusAndTypeOrderByDateTimeDesc(Status.OK, TypeTransaction.PERCENTAGE).orElseThrow(() -> new RuntimeException("error obteniendo percentage")).getPayload();
        return Float.parseFloat(percentage);
    }

    @Override
    public Page<History> getAllByPage(int page, int size) {
        Page<HistoryEntity> historyEntityPage = this.historyDao.findAll(PageRequest.of(page, size));
        return historyEntityPage.map(history -> modelMapper.map(history, History.class));
    }
}
