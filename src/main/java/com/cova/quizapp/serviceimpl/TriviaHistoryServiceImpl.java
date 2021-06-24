package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.TriviaHistoryRepo;
import com.cova.quizapp.service.ITriviaHistoryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class TriviaHistoryServiceImpl implements ITriviaHistoryService {

    private TriviaHistoryRepo triviaHistoryRepo;

    @Autowired
    public TriviaHistoryServiceImpl(TriviaHistoryRepo triviaHistoryRepo){
        this.triviaHistoryRepo = triviaHistoryRepo;
    }
}
