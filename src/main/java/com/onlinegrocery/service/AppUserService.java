package com.onlinegrocery.service;

import com.onlinegrocery.dto.LoginResponseDto;
import com.onlinegrocery.entity.AppUser;
import com.onlinegrocery.exceptions.AppUserException;

public interface AppUserService{
	LoginResponseDto login( String userName,String password);
	AppUser register(AppUser appUser);
	String resetPassword(String userName, String password,  String newPassword) throws AppUserException;
	String findUserName(String userName);
	
	
	String sendEmail(String toEmail) throws AppUserException;
	String verifyEmail(String email);
	
	String VerifyOtp(String email, String otp) throws AppUserException;
	String verifyOtp(String email, String otp);
}

