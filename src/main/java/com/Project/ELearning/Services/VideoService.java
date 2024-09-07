package com.Project.ELearning.Services;

import com.Project.ELearning.dtos.VideoDto;
import com.Project.ELearning.dtos.CustomPageResponse;

public interface VideoService
{
    //create Video
    VideoDto create(VideoDto VideoDto);

    //read Video
    VideoDto read(String id);

    //update Video
    VideoDto update(VideoDto dto, String id);

    //delete Video
    void delete(String id);

    //get all
    CustomPageResponse<VideoDto> getAll(int pageNumber, int pageSize, String sortBy);
}
