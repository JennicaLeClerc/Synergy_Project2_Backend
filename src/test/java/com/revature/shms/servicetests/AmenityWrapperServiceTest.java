package com.revature.shms.servicetests;

import com.revature.shms.enums.Amenities;
import com.revature.shms.models.AmenityWrapper;
import com.revature.shms.repositories.AmenityWrapperRepository;
import com.revature.shms.services.AmenityWrapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AmenityWrapperServiceTest {
	@Mock AmenityWrapperRepository repo;
	@InjectMocks AmenityWrapperService service;

	@Test
	public void findAllAmenitiesTest(){
		List<AmenityWrapper> amenityWrappers = new ArrayList<>();
		amenityWrappers.add(new AmenityWrapper(Amenities.ADA,123));
		amenityWrappers.add(new AmenityWrapper(Amenities.KING_BED,12));
		Page<AmenityWrapper> pg = new PageImpl<>(amenityWrappers);
		when(repo.findAll(any(Pageable.class))).thenReturn(pg);
		assertEquals(service.findAllAmenities(PageRequest.of(0,1000000)).getContent(),amenityWrappers);
	}

	@Test
	public void setAmenityPriceTest(){
		AmenityWrapper wrapper = new AmenityWrapper(Amenities.SMALL_KITCHEN, 100);
		when(repo.save(any(AmenityWrapper.class))).thenReturn(wrapper);
		assertEquals(service.setAmenityPrice(Amenities.SMALL_KITCHEN,100),wrapper);
	}

	@Test
	public void getAmenityWrapperTest(){
		AmenityWrapper wrapper = new AmenityWrapper(Amenities.SMALL_KITCHEN, 100);
		when(repo.getById(any())).thenReturn(wrapper);
		assertEquals(service.getAmenityWrapper(Amenities.SMALL_KITCHEN),wrapper);
	}

	@Test
	public void getAmenityPriceTest(){
		AmenityWrapper wrapper = new AmenityWrapper(Amenities.SMALL_KITCHEN, 100);
		when(repo.getById(any())).thenReturn(wrapper);
		assertEquals(service.getAmenityPrice(Amenities.SMALL_KITCHEN),100);
	}

	@Test
	public void getTotalTest(){
		List<AmenityWrapper> amenityWrappers = new ArrayList<>();
		amenityWrappers.add(new AmenityWrapper(Amenities.ADA,123));
		amenityWrappers.add(new AmenityWrapper(Amenities.KING_BED,12));
		assertEquals(service.getTotal(amenityWrappers),135);
	}

	@Test
	public void GenerateAllAmenityWrappersTest(){
		service.GenerateAllAmenityWrappers();
		verify(repo,times(Amenities.values().length)).save(any());
	}

	@Test
	public void getterAndSetterTest(){
		AmenityWrapperRepository amenityWrapperRepository = null;
		AmenityWrapperService amenityWrapperService = new AmenityWrapperService();
		amenityWrapperService.setAmenityWrapperRepository(amenityWrapperRepository);
		Assertions.assertNull(amenityWrapperService.getAmenityWrapperRepository());

		amenityWrapperService.setAmenityWrapperRepository(null);
		Assertions.assertNull(amenityWrapperService.getAmenityWrapperRepository());
	}
}
