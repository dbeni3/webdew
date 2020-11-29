package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.StaffDao;
import hu.unideb.webdev.model.Staff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffDao staffDao;

    @Override
    public Collection<Staff> getAllStaff() {
        return staffDao.readAll();
    }


    @Override
    public void recordStaff(Staff staff)  {
        staffDao.createStaff(staff);
    }

    @Override
    public void deleteStaff(Staff staff)  {
        staffDao.deleteStaff(staff);
    }
}
