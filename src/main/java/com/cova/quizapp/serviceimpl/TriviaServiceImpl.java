package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.TriviaHistoryRepo;
import com.cova.quizapp.data.TriviaRepository;
import com.cova.quizapp.model.entity.Trivia;
import com.cova.quizapp.model.response.GetNewTriviaResponse;
import com.cova.quizapp.model.response.GetTriviaHistoryResponse;
import com.cova.quizapp.model.response.GetTriviaResultResponse;
import com.cova.quizapp.model.response.TriviaResponse;
import com.cova.quizapp.service.ITriviaService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        if(totalAvailableQuestions > 0) totalAvailableQuestions = 0;
        totalAvailableQuestions = triviaRepository.countAllByDifficultyLevel(level);
    }

    @Override
    public TriviaResponse getTrivia(Trivia.DifficultyLevel level) {
        Trivia trivia = new Trivia();
        GetNewTriviaResponse newTrivia = new GetNewTriviaResponse();
        if(totalAvailableQuestions == 0)
            totalAvailableQuestions = triviaRepository.countAllByDifficultyLevel(level);
        if(qosAnswered.size() <= level.getNumQos()) {
            int nextQos = qosAnswered.size();
            trivia = triviaRepository.findById((long)nextQos++).orElse(null);
            qosAnswered.add(nextQos);
        } else endTrivia(level);
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
    public GetTriviaResultResponse endTrivia(Trivia.DifficultyLevel level) {
        triviaResult.setNum_of_answered_questions(qosAnswered.size());
        triviaResult.setTotal_passed(0);
        triviaResult.setTotal_failed(0);
        triviaResult.setDifficulty_level(level.name());
        double percentScore = triviaResult.getTotal_passed() /100.0;
        triviaResult.setPerformance(GetTriviaResultResponse.Performance.getPerformance(percentScore).name());
        return triviaResult;
    }

    @Override
    public GetTriviaHistoryResponse getTriviaHistory() {
        return null;
    }

}
