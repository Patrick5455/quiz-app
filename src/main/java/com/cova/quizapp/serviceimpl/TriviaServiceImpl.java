package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.TriviaRepository;
import com.cova.quizapp.service.ITriviaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class TriviaServiceImpl implements ITriviaService {

    private TriviaRepository triviaRepository;;

    @Autowired
    public TriviaServiceImpl(TriviaRepository triviaRepository){
        this.triviaRepository = triviaRepository; }
}
