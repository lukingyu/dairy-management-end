package com.haut.dm.domain.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeAndTime {
    private Double income;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
}
