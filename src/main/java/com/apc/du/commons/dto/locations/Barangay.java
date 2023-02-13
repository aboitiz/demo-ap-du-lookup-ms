package com.apc.du.commons.dto.locations;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barangay {
    private Long id;
    private String code;
    private String description;
}
