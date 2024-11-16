package com.example.sistem_prijave_za_poso.Services.Imp;

import com.example.sistem_prijave_za_poso.Dto.JobDto;
import com.example.sistem_prijave_za_poso.Exception.ResourceNotFoundException;
import com.example.sistem_prijave_za_poso.Mapper.JobMapper;
import com.example.sistem_prijave_za_poso.Models.Job;
import com.example.sistem_prijave_za_poso.Models.JobCategory;
import com.example.sistem_prijave_za_poso.Repositories.JobCategoryRepository;
import com.example.sistem_prijave_za_poso.Repositories.JobRepository;
import com.example.sistem_prijave_za_poso.Services.JobService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImp implements JobService {

    private final JobRepository jobRepository;
    private final JobCategoryRepository jobCategoryRepository;

    public JobServiceImp(JobRepository jobRepository, JobCategoryRepository jobCategoryRepository) {
        this.jobRepository = jobRepository;
        this.jobCategoryRepository = jobCategoryRepository;
    }

    @Override
    public JobDto createJob(JobDto jobDto) {
        JobCategory kategorija = jobCategoryRepository.findById(jobDto.getKategorijaId())
                .orElseThrow(() -> new ResourceNotFoundException("Kategorija sa ID-em " + jobDto.getKategorijaId() + " nije pronađena."));

        Job job = JobMapper.mapToJob(jobDto, kategorija);
        Job savedJob = jobRepository.save(job);

        return JobMapper.mapToJobDto(savedJob);
    }

    @Override
    public JobDto getJobById(int id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Posao sa ID-om " + id + " nije pronađen."));
        return JobMapper.mapToJobDto(job);
    }

    @Override
    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        if (jobs.isEmpty()) {
            throw new ResourceNotFoundException("Nijedan posao nije pronađen.");
        }
        return jobs.stream().map(JobMapper::mapToJobDto).collect(Collectors.toList());
    }

    @Override
    public JobDto updateJob(int id, JobDto jobDto) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Posao sa ID-om " + id + " nije pronađen."));

        existingJob.setNaziv(jobDto.getNaziv());

        Job updatedJob = jobRepository.save(existingJob);
        return JobMapper.mapToJobDto(updatedJob);
    }

    @Override
    public void deleteJob(int id) {
        if (!jobRepository.existsById(id)) {
            throw new ResourceNotFoundException("Posao sa ID-om " + id + " nije pronađen.");
        }
        jobRepository.deleteById(id);
    }
    
    @Override
    public List<JobDto> getJobsByCategory(int kategorijaId) {
        JobCategory kategorija = jobCategoryRepository.findById(kategorijaId)
                .orElseThrow(() -> new ResourceNotFoundException("Kategorija sa ID-em " + kategorijaId + " nije pronađena."));

        return jobRepository.findByKategorija(kategorija)
                .stream()
                .map(jobCategory -> JobMapper.mapToJobDto(jobCategory)) 
                .collect(Collectors.toList());
    }

    @Override
    public List<JobDto> filterJobsByCategory(String kategorija) {
        return jobRepository.findByKategorija2(kategorija);
}

}
