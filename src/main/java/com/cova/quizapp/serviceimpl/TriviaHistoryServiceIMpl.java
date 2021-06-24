package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.TriviaHistoryRepository;
import com.cova.quizapp.service.ITriviaHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TriviaHistoryServiceIMpl implements ITriviaHistoryService {

    private TriviaHistoryRepository triviaHistoryRepository;

    @Autowired
    public TriviaHistoryServiceIMpl(TriviaHistoryRepository triviaHistoryRepository){
        this.triviaHistoryRepository = triviaHistoryRepository;
    }
}
