package com.sugadev.diaryservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private UserDTO user;
    private DiaryDTO diary;
    private CulinaryDTO culinary;

}
