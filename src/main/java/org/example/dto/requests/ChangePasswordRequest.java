package org.example.dto.requests;

import lombok.Data;

@Data
public class ChangePassword {
    private String OldPassword;
    private String NewPassword;
}
