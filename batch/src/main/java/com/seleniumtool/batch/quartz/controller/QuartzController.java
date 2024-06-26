package com.seleniumtool.batch.quartz.controller;

import com.example.core.model.JobModelBean;
import com.seleniumtool.batch.quartz.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scheduler")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;


    @PostMapping("create-job")
    public ResponseEntity<String> createJob(@RequestBody JobModelBean jobModelBean) throws Exception {
        return quartzService.createJob(jobModelBean);
    }

}
