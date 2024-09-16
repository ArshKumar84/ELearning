package com.Project.ELearning.Services;


import com.Project.ELearning.Entities.Video;
import com.Project.ELearning.Repositores.VideoRepo;
import com.Project.ELearning.dtos.CustomPageResponse;
import com.Project.ELearning.dtos.VideoDto;
import com.Project.ELearning.exceptionsHandler.InvalidParametersException;
import com.Project.ELearning.exceptionsHandler.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{
    private VideoRepo repo;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ModelMapper modelMapper;

    public VideoServiceImpl(VideoRepo repo) {
        this.repo = repo;
    }

    @Override
    public VideoDto create(VideoDto VideoDto) {
        Video Video=dtoToEntity(VideoDto);
        Video=repo.save(Video);
        return entityToDto(Video);
    }

    @Override
    public VideoDto read(String id) {
        Video Video=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Video Not found"));
        return entityToDto(Video);
    }
    @Override
    public VideoDto update(VideoDto dto, String id) {
        Video Video = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Video Not found"));
        Video.setTitle(dto.getTitle());
        Video.setDesc(dto.getDesc());
        Video.setFilePath(dto.getFilePath());
        Video.setContentType(dto.getContentType());
        Video = repo.save(Video);
        return entityToDto(Video);
    }

    @Override
    public void delete(String id) {
        Video Video = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Video Not found"));
        repo.deleteById(id);
    }

    @Override
    public CustomPageResponse<VideoDto> getAll(int pageNumber, int pageSize, String sortBy)
    {
        Sort sort=Sort.by(sortBy).ascending();
        PageRequest request = PageRequest.of(pageNumber, pageSize,sort);
        Page<Video> VideoPage;
        try {
            VideoPage = repo.findAll(request);
        }
        catch (Exception ex)
        {
            throw new InvalidParametersException();
        }

        List<Video> VideoList=VideoPage.stream().toList();
        List<VideoDto> VideoDtoList=VideoList.stream().map(this::entityToDto).toList();
        return new CustomPageResponse<>(
                pageNumber,
                pageSize,
                VideoDtoList,
                VideoPage.isLast()
        );


    }

    Video dtoToEntity(VideoDto dto)
    {
        Video Video = mapper.map(dto, Video.class);
        return Video;
    }

    VideoDto entityToDto(Video Video)
    {
        VideoDto dto = mapper.map(Video,VideoDto.class);
        return dto;
    }

}
