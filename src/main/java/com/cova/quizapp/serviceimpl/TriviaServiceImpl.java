package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.TriviaHistoryRepo;
import com.cova.quizapp.data.TriviaRepository;
import com.cova.quizapp.model.entity.Trivia;
import com.cova.quizapp.model.response.GetTriviaHistoryResponse;
import com.cova.quizapp.model.response.GetTriviaResponse;
import com.cova.quizapp.model.response.GetTriviaResultResponse;
import com.cova.quizapp.service.ITriviaService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Data
@Slf4j
public class TriviaServiceImpl implements ITriviaService {

    private TriviaRepository triviaRepository;;
    private TriviaHistoryRepo triviaHistoryRepo;

    static long totalAvailableQuestions =0;
    static List<Integer> qosAnswered = new ArrayList<>();
    static GetTriviaResultResponse triviaResult = new GetTriviaResultResponse();

    @Autowired
    public TriviaServiceImpl(TriviaRepository triviaRepository, TriviaHistoryRepo repo){
        this.triviaRepository = triviaRepository;
        triviaHistoryRepo = repo;
    }

    @Override
    public void startTrivia(Trivia.DifficultyLevel level) {
        totalAvailableQuestions = triviaRepository.countAllByDifficultyLevel(level);
    }

    @Override
    public GetTriviaResponse getTrivia(Trivia.DifficultyLevel level) {
        Trivia trivia = new Trivia();
        GetTriviaResponse newTrivia = new GetTriviaResponse();

        if(totalAvailableQuestions == 0)
            totalAvailableQuestions = triviaRepository.countAllByDifficultyLevel(level);
        if(qosAnswered.size() != level.getNumQos()) {
            int qosNo = qosAnswered.size();
            trivia = triviaRepository.findById((long)qosNo++).orElse(null);
            qosAnswered.add(qosNo);
        } else endTrivia();

        newTrivia.setLevel(level.name());
        if(trivia != null) {
            newTrivia.setQuestion(trivia.getQuestion());
        }
        else {
            newTrivia.setQuestion("Bonus Question: what is your name?");
        }
        return newTrivia;
    }

    @Override
    public void endTrivia() {

    }

    @Override
    public GetTriviaResultResponse getTriviaResult() {
        return null;
    }

    @Override
    public GetTriviaHistoryResponse getTriviaHistory() {
        return null;
    }




}
