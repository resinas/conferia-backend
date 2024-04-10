package org.example.dto.requests;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String OldPassword;
    private String NewPassword;
}
