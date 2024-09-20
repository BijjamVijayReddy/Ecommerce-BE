package com.greetlabs.swiftcart.service;

import com.greetlabs.swiftcart.dto.UpdatePasswordDto;


public interface UpdatePasswordService {
    public String userEmailVerificationForUpdatePassword(String email) ;
    public String updatePassword(String token,UpdatePasswordDto updatePasswordDto);
    public void  UpdateTemporaryToken(String email , String token);
    boolean validateTemporaryToken(String token);

}
