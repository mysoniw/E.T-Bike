package com.etbike.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etbike.server.domain.model.MyCourseOverlayItem;
import com.etbike.server.persistence.MyCourseRepository;
import com.etbike.server.persistence.MyCourseSpecifications;
import com.etbike.server.service.MyCourseService;

@Service
public abstract class MyCourseServiceImpl implements MyCourseService{

	@Autowired private MyCourseRepository myCourseRepository;
	
	@Override
	public MyCourseOverlayItem saveMyCourse(MyCourseOverlayItem myCourseOverlayItem) {
		// TODO Auto-generated method stub
		return myCourseRepository.save(myCourseOverlayItem);
	}

	public MyCourseOverlayItem findByCoordinates(double sLatitude,double sLongitude) {
		// TODO Auto-generated method stub
		return myCourseRepository.findOne(MyCourseSpecifications.isCoordinates(sLatitude, sLongitude));
		
	}
}
