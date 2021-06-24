package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.TriviaHistoryRepository;
import com.cova.quizapp.service.ITriviaHistoryService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class TriviaHistoryServiceIMpl implements ITriviaHistoryService {

    private  TriviaHistoryRepository triviaHistoryRepository;

    @Autowired
    public TriviaHistoryServiceIMpl(TriviaHistoryRepository triviaHistoryRepository){
        this.triviaHistoryRepository = triviaHistoryRepository;
    }
}
