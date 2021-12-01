package com.revature.shms.servicetests;

import com.google.inject.matcher.Matchers;
import com.revature.shms.enums.Amenities;
import com.revature.shms.models.AmenityWrapper;
import com.revature.shms.repositories.AmenityWrapperRepository;
import com.revature.shms.services.AmenityWrapperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AmenityWapperServiceTest {
	@Mock AmenityWrapperRepository repo;
	@InjectMocks AmenityWrapperService service;

	@Test
	public void getAllAmenitiesTest(){
		List<AmenityWrapper> amenityWrappers = new ArrayList<>();
		amenityWrappers.add(new AmenityWrapper(Amenities.ADA,123));
		amenityWrappers.add(new AmenityWrapper(Amenities.KING_BED,12));
		when(repo.findAll()).thenReturn(amenityWrappers);
		assertEquals(service.getAllAmenities(),amenityWrappers);
	}

	@Test
	public void setAmenityPrice(){
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
	public void GenerateAllAmenityWrappers(){
		service.GenerateAllAmenityWrappers();
		verify(repo,times(Amenities.values().length)).save(any());
	}
}
