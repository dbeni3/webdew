package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.StoreDao;
import hu.unideb.webdev.model.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
    private final StoreDao storeDao;

    @Override
    public Collection<Store> getAllStore() {
        return storeDao.readAll();
    }


    @Override
    public void recordStore(Store store)  {
        storeDao.createStore(store);
    }

    @Override
    public void deleteStore(Store store)  {
        storeDao.deleteStore(store);
    }
}
