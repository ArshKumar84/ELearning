package com.Project.ELearning.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageResponse<T> {

    private int pageNumber;

    private int pageSize;

    private List<T> List;

    private Boolean LastPage;

}
