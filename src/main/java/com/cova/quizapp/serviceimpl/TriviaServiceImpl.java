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
    static int BONUS_QUESTION_NO = 0;
    static List<Long> qosIDs = new ArrayList<>();

    @Autowired
    public TriviaServiceImpl(TriviaRepository triviaRepository, TriviaHistoryRepo repo){
        this.triviaRepository = triviaRepository;
        triviaHistoryRepo = repo;
    }

    @Override
    public void startTrivia(Trivia.DifficultyLevel level) {
        if(totalAvailableQuestions > 0) totalAvailableQuestions = 0;
        qosIDs = triviaRepository.getTriviaIds(level.name()).orElse(new ArrayList<>());
        totalAvailableQuestions = qosIDs.size();
        log.info("total available questions for level {} = {}", level, totalAvailableQuestions);
    }

    @Override
    public TriviaResponse getTrivia(Trivia.DifficultyLevel level) {
        Trivia trivia = new Trivia();
        GetNewTriviaResponse newTrivia = new GetNewTriviaResponse();
        if(totalAvailableQuestions == 0) {
            log.info("getting total question in DB from getTrivia");
            totalAvailableQuestions = triviaRepository.countAllByDifficultyLevel(level);
        }
        if(qosAnswered.size() < level.getNumOfAllowedQosForLevel() && qosAnswered.size() < totalAvailableQuestions) {
            int nextQos = qosAnswered.size();
            log.info("next qos number {}", nextQos);
            trivia = triviaRepository.findByDifficultyLevelAndId(level, qosIDs.get(nextQos)).orElse(null);
            qosAnswered.add(nextQos);
            log.info("got trivia from DB {}", trivia);
        } else return endTrivia(level);
        newTrivia.setLevel(level.name());
        if(trivia != null) {
            newTrivia.setQuestion(trivia.getQuestion());
        }
        else {
            newTrivia.setQuestion("Bonus Question: what is your name?");
            qosAnswered.add(BONUS_QUESTION_NO);
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
       log.info("end trivia. Trivia Result {}", triviaResult);
        return triviaResult;
    }

    @Override
    public GetTriviaHistoryResponse getTriviaHistory() {
        return null;
    }

}
