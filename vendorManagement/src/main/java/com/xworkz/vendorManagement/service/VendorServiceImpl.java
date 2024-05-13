package com.xworkz.vendorManagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.vendorManagement.dto.VendorDTO;
import com.xworkz.vendorManagement.entity.VendorEntity;
import com.xworkz.vendorManagement.repository.VendorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorRepository repo;

	@Autowired
	private MailSending mailSending;

	public VendorServiceImpl() {
		log.info("Invoking VendorServiceImpl...");
	}


	@Override
	public boolean validateAndSave(VendorDTO vendorDTO) {
	    log.info("Invoking validateAndSave....");
	    VendorEntity entity = new VendorEntity();
	    // Set default imagePath if not provided in DTO
	    if (vendorDTO.getImagePath() == null) {
	        vendorDTO.setImagePath("defaultImage.jpg");
	    }
	    
	    entity.setEmail(vendorDTO.getEmail());
	    BeanUtils.copyProperties(vendorDTO, entity);
	    this.repo.save(entity);
	    return true;
	}


	@Override
	public boolean findByEmail(String email) {
		System.out.println("findByEmail service=====================");
		return this.repo.findByEmail(email);
	}

	// Ajax

	@Override
	public String isExistByGstNoContactNoEmailWebsite(String gstNo, Long contactNo, String email, String website) {
		VendorEntity entity = repo.isExistGstNoContactNoEmailWebsite(gstNo, contactNo, email, website);
		if (entity != null) {
			if (entity.getGstNo().equals(gstNo)) {
				return "GstNo already exist";

			} else if (entity.getContactNo().equals(contactNo)) {
				return "Contact already exist";

			}

			else if (entity.getEmail().equals(email)) {
				return "Email already exist";

			} else if (entity.getWebsite().equals(website)) {
				return "Website already exist";

			}
			log.info("Deatils not found....Save the details");

		}
		return null;

	}

	@Override
	public String isExistByGstNoAjax(String gstNo) {
		List<VendorEntity> byGstNo = this.repo.findAll();
		System.err.println(byGstNo);
		for (VendorEntity dto : byGstNo) {
			System.out.println(dto + " checking for " + gstNo);

			if (dto.getGstNo().equalsIgnoreCase(gstNo)) {
				log.info("checking for " + gstNo);
				return "GstNo already exist.";
			}
		}

		return null;
	}

	@Override
	public String isExistByContactNoAjax(Long contactNo) {

		List<VendorEntity> byContactNo = this.repo.findAll();
		System.err.println(byContactNo);
		for (VendorEntity dto : byContactNo) {
			log.info(dto + " checking for " + contactNo);

			if (dto.getContactNo().equals(contactNo)) {
				log.info("checking for " + contactNo);
				return "ContactNo already exist.";
			}
		}

		return null;
	}

	@Override
	public String isExistByAlternativeNoAjax(Long alternativeNo) {
		List<VendorEntity> byAlternativeNo = this.repo.findAll();
		System.err.println(byAlternativeNo);
		for (VendorEntity dto : byAlternativeNo) {
			log.info(dto + " checking for " + alternativeNo);
			if (dto.getAlternativeNo() != null) {
				if (dto.getAlternativeNo().equals(alternativeNo)) {
					log.info("checking for " + alternativeNo);
					return "AlternativeNo already exist.";
				}
			}
		}

		return null;

	}

	@Override
	public String isExistByEmailAjax(String email) {
		List<VendorEntity> byEmail = this.repo.findAll();
		System.err.println(byEmail);
		for (VendorEntity dto : byEmail) {
			log.info(dto + " checking for " + email);

			if (dto.getEmail().equalsIgnoreCase(email)) {
				log.info("checking for " + email);
				return "Email already exist.";
			}
		}

		return null;

	}

	@Override
	public String isExistByWebsiteAjax(String website) {
		List<VendorEntity> byWebsite = this.repo.findAll();
		System.err.println(byWebsite);
		for (VendorEntity dto : byWebsite) {
			log.info(dto + " checking for " + website);

			if (dto.getWebsite().equalsIgnoreCase(website)) {
				log.info("checking for " + website);
				return "Website already exist.";
			}
		}

		return null;

	}

	@Override
	public boolean saveOtpByLoginEmailID(String email) {
		System.err.println("email============" + email);
		if (email != null) {
			Random random = new Random();
			int generatedOtp = random.nextInt(900000) + 100000;
			String otp = String.valueOf(generatedOtp);
			LocalDateTime generateOtpTime = LocalDateTime.now();
			boolean save = repo.saveLoginOtpByemaild(email, otp, generateOtpTime);
			if (save) {
				boolean sent = mailSending.vendorloginemailVerficationOtp(email, otp);
				if (sent) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public String validateLoginOTP(String email, String otp) {
		if (email != null && otp != null) {
			// Retrieve the latest OTP and its generated time
			Object[] latestOTPDetails = repo.getloginOTPAndgenratedTime(email);
			if (latestOTPDetails != null) {
				String latestOTP = (String) latestOTPDetails[0];
				LocalDateTime generatedTime = (LocalDateTime) latestOTPDetails[1];

				if (latestOTP.equals(otp)) {
					// Calculate the elapsed time since OTP generation
					long elapsedTimeMinutes = generatedTime.until(LocalDateTime.now(), ChronoUnit.MINUTES);
					if (elapsedTimeMinutes <= 2) { // 2 minutes
						return "OTP validated";
					} else {
						return "OTP expired";
					}
				} else {
					return "OTP not validate";
				}
			}
		}
		// If OTP validation fails or data is not available, return appropriate error
		// message
		return "OTP validation failed";
	}

	@Override
	public String findImagePathByEmail(String email) {
		System.out.println("image=======================");
		return repo.imagePathByEmail(email);
	}

	@Override
	public VendorDTO findAllVendorEntityByEmail(String email) {
		System.out.println("this is service");
		VendorDTO dto=new VendorDTO();
		VendorEntity entity=repo.findAllVendorEntityByEmail(email);
		dto.setOwnerName(entity.getOwnerName());
		dto.setEmail(entity.getEmail());
		dto.setContactNo(entity.getContactNo());
		dto.setAlternativeNo(entity.getAlternativeNo());
		dto.setVendorName(entity.getVendorName());
		dto.setGstNo(entity.getGstNo());
		dto.setCompanyStartDate(entity.getCompanyStartDate());
		dto.setWebsite(entity.getWebsite());
		dto.setAddress(entity.getAddress());
		dto.setPincode(entity.getPincode());
		dto.setImagePath(entity.getImagePath());
		return dto;
	}
	
	
	private void saveImage(VendorDTO dto) throws IOException {
		if (dto.getImageFile() != null && !dto.getImageFile().isEmpty()) {
			byte[] fileBytes = dto.getImageFile().getBytes();
			String filePath = "D:\\VendorImage\\" + dto.getImageFile().getOriginalFilename();
			File newFile = new File(filePath);
			Path path = Paths.get(newFile.getAbsolutePath());
			Files.write(path, fileBytes);
			dto.setImagePath(dto.getImageFile().getOriginalFilename().toString());
		} else {
			String existingUserImagePath = repo.imagePathByEmail(dto.getEmail());
			dto.setImagePath(existingUserImagePath);
		}
	}

	@Override
	public VendorDTO updateVendorEntityByEmail(VendorDTO dto, String email) {
		 if (dto == null) {
		        throw new IllegalArgumentException("VendorDto cannot be null");
		    }

		    VendorEntity entity = repo.findAllVendorEntityByEmail(email);
		    if (entity != null) {
		        if (entity.getEmail() == null) {
		            throw new IllegalArgumentException("Email cannot be null for entity: " + entity.getId());
		        }
		        // Update entity fields with DTO values
		        entity.setOwnerName(dto.getOwnerName());
		        entity.setContactNo(dto.getContactNo());
		        entity.setAlternativeNo(dto.getAlternativeNo());
		        entity.setVendorName(dto.getVendorName());
		        entity.setGstNo(dto.getGstNo());
		        entity.setCompanyStartDate(dto.getCompanyStartDate());
		        entity.setWebsite(dto.getWebsite());
		        entity.setAddress(dto.getAddress());
		        entity.setPincode(dto.getPincode());
		       
		        
		        // Save the image and set the image path in the DTO
	        try {
				saveImage(dto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        
		        // Set the image path in the entity
		        entity.setImagePath(dto.getImagePath());

		        // No need to update email and id fields as they remain the same

		        // Assuming repo.updateById() method handles both update and save
		        boolean update = repo.updateVendorDetailsById(entity, entity.getId());
		        if (update) {
		            System.out.println("Updated successfully");
		            // Optionally return the updated DTO
		        } else {
		            throw new RuntimeException("Update failed");
		        }
		    } else {
		        throw new EntityNotFoundException("VendorEntity not found for email: " + email);
		    }
		    return dto;
		}
	
	@Override
	public List<VendorDTO> getAllVendors() {
		List<VendorEntity> vendorDetails=repo.findAll();
		List<VendorDTO> readVendorDetails=new ArrayList<VendorDTO>(); 
		for (VendorEntity vendorEntity : vendorDetails) {
			VendorDTO vendorDto=new VendorDTO();
			
			vendorDto.setId(vendorEntity.getId());
			vendorDto.setOwnerName(vendorEntity.getOwnerName());
			vendorDto.setOwnerName(vendorEntity.getOwnerName());
			vendorDto.setEmail(vendorEntity.getEmail());
			vendorDto.setContactNo(vendorEntity.getContactNo());
			vendorDto.setAlternativeNo(vendorEntity.getAlternativeNo());
			vendorDto.setGstNo(vendorEntity.getGstNo());
			vendorDto.setVendorName(vendorEntity.getVendorName());
			vendorDto.setCompanyStartDate(vendorEntity.getCompanyStartDate());
			vendorDto.setWebsite(vendorEntity.getWebsite());
			vendorDto.setAddress(vendorEntity.getAddress());
			vendorDto.setPincode(vendorEntity.getPincode());
			vendorDto.setImagePath(vendorEntity.getImagePath());
			readVendorDetails.add(vendorDto);
		}
		return readVendorDetails;
	}
}
