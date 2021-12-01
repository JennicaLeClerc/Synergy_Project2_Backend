package com.revature.shms.servicetests;

import com.revature.shms.enums.CleaningStatus;
import com.revature.shms.enums.EmployeeType;
import com.revature.shms.models.Cleaning;
import com.revature.shms.models.Employee;
import com.revature.shms.models.Room;
import com.revature.shms.repositories.CleaningRepository;
import com.revature.shms.repositories.EmployeeRepository;
import com.revature.shms.services.CleaningService;
import com.revature.shms.services.EmployeeService;
import com.revature.shms.services.RoomService;
import com.revature.shms.services.UserService;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CleaningServiceTests {

    @Mock CleaningRepository cleaningRepository;
    @Mock EmployeeService employeeService;
    @Mock UserService userService;
    @Mock RoomService roomService;
    @InjectMocks CleaningService cleaningService;

    @Test
    public void getAllCleaningsTest(){
        Cleaning cleaning = new Cleaning();
        List<Cleaning> cleaningList = new ArrayList<>();
        cleaningList.add(cleaning);
        when(cleaningRepository.findAllByOrderByPriorityDescDateAddedAsc()).thenReturn(cleaningList);
        Assertions.assertEquals(cleaningList, cleaningService.GetAllCleanings());
    }

    @Test
    public void getAllCleaningsByEmployeeTest(){
        Employee employee = new Employee();
        Cleaning cleaning = new Cleaning();
        List<Cleaning> cleaningList = new ArrayList<>();
        cleaningList.add(cleaning);
        when(cleaningRepository.findAllByEmployeeOrderByPriorityDescDateAddedAsc(any())).thenReturn(cleaningList);
        Assertions.assertEquals(cleaningList, cleaningService.GetAllCleaningsByEmployee(employee));
    }

    @Test
    public void getByRoomTest(){
        Room room = new Room();
        Cleaning cleaning = new Cleaning();
        when(cleaningRepository.findByRoom(any())).thenReturn(cleaning);
        Assertions.assertEquals(cleaning, cleaningService.getByRoom(room));
    }

    @Test
    public void scheduleTest(){
        Cleaning cleaning = new Cleaning();
        when(cleaningRepository.save(any())).thenReturn(cleaning);
        Assertions.assertEquals(cleaning, cleaningService.schedule(cleaning));
    }

    @Test
    public void removeTest(){
        Cleaning cleaning = new Cleaning();
        cleaningService.remove(cleaning);
        verify(cleaningRepository, times(1)).delete(any());
    }
}
