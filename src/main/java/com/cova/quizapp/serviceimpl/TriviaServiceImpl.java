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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class TriviaServiceImpl implements ITriviaService {

    private TriviaRepository triviaRepository;;
    private TriviaHistoryRepo triviaHistoryRepo;

    static long totalAvailableTrivia =0;
    static List<Integer> totalTriviaAnswered = new ArrayList<>();
    static GetTriviaResultResponse triviaResult = new GetTriviaResultResponse();
    static int BONUS_TRIVIA = 0;
    static List<Long> triviaIds = new ArrayList<>();
    static Map<Long, String> triviaCorrectAnswers = new HashMap<>();
    static Map<Long, String> userTriviaAnswers = new HashMap<>();
    static int correctAnswersCount =0 ;
    static int wrongAnswersCount =0;



    @Autowired
    public TriviaServiceImpl(TriviaRepository triviaRepository, TriviaHistoryRepo repo){
        this.triviaRepository = triviaRepository;
        triviaHistoryRepo = repo;
    }

    @Override
    public void startTrivia(Trivia.DifficultyLevel level) {
        if(totalAvailableTrivia > 0) totalAvailableTrivia = 0;
        // get list of trivia to be asked from DB
        List<Trivia> triviaList = triviaRepository.findByDifficultyLevel(level).orElse(new ArrayList<>());
        // get ids of fetched trivia
        triviaIds = triviaList.stream().map(Trivia::getId).collect(Collectors.toList());
        totalAvailableTrivia = triviaList.size();
        // get answers of fetched list of trivia
        triviaList.forEach(T -> triviaCorrectAnswers.put(T.getId(), T.getAnswer()));
    }

    @Override
    public TriviaResponse getNextTrivia(Trivia.DifficultyLevel level, String previousAnswers, int previousTriviaId) {
        Trivia trivia;
        int nextTriviaId;
        if(totalAvailableTrivia == 0) startTrivia(level);
        if(totalTriviaAnswered.size() < level.getNumOfAllowedTriviaForLevel() && totalTriviaAnswered.size() < totalAvailableTrivia) {
            log.info("before before putting into user answers");
            nextTriviaId = totalTriviaAnswered.size();
            trivia = triviaRepository.findByDifficultyLevelAndId(level, triviaIds.get(nextTriviaId)).orElse(null);
           log.info("before putting into user answers");
            userTriviaAnswers.put((long)previousTriviaId, previousAnswers);
            log.info("user trivia answers {}", userTriviaAnswers);
        } else return endTrivia(level);
        return getTriviaResponse(level, trivia, nextTriviaId);
    }

    @Override
    public TriviaResponse getNextTrivia(Trivia.DifficultyLevel level) {
        Trivia triviaFromDb;
        int nextTriviaId;
        if(totalAvailableTrivia == 0) startTrivia(level);
        if(totalTriviaAnswered.size() < level.getNumOfAllowedTriviaForLevel() && totalTriviaAnswered.size() < totalAvailableTrivia) {
            nextTriviaId = totalTriviaAnswered.size();
            triviaFromDb = triviaRepository.findByDifficultyLevelAndId(level, triviaIds.get(nextTriviaId)).orElse(null);
        } else return endTrivia(level);
        return getTriviaResponse(level, triviaFromDb, nextTriviaId);
    }

    private TriviaResponse getTriviaResponse(Trivia.DifficultyLevel level, Trivia trivia, int nextTriviaId) {
        GetNewTriviaResponse newTrivia = new GetNewTriviaResponse();
        newTrivia.setLevel(level.name());
        if(trivia != null) {
            newTrivia.setQuestion(trivia.getQuestion());
            newTrivia.setTriviaId(triviaIds.get(nextTriviaId));
        } else {
            newTrivia.setQuestion("Bonus Question: what is your name?");
            newTrivia.setTriviaId(BONUS_TRIVIA);
            nextTriviaId = BONUS_TRIVIA;
        }
        totalTriviaAnswered.add(nextTriviaId);
        return newTrivia;
    }

    @Override
    public GetTriviaResultResponse endTrivia(Trivia.DifficultyLevel level) {
        triviaResult.setDifficulty_level(level.name());
        calculateResult(level);
        double scorePerQos =  100.0/level.getNumOfAllowedTriviaForLevel();
        double totalScore  = scorePerQos * correctAnswersCount;
        double percentScore = totalScore /100.0;

        triviaResult.updateTriviaSession(totalTriviaAnswered.size(), correctAnswersCount,
                wrongAnswersCount, totalScore);
      triviaResult.setPerformance(triviaResult.calculatePerformance(percentScore).name());
        return triviaResult;
    }

    @Override
    public void calculateResult(Trivia.DifficultyLevel level){
        userTriviaAnswers.forEach(
                (k, v) -> {
                    // pass for bonus mark
                    if(k == 0)
                        correctAnswersCount++;
                    if (triviaCorrectAnswers.get(k).trim().equalsIgnoreCase(v.trim()))
                        correctAnswersCount++;
                     else
                        wrongAnswersCount++;

                });
        log.info("total correct answers {}", correctAnswersCount);
        log.info("total wrong answers {}", wrongAnswersCount);
    }



    @Override
    public GetTriviaHistoryResponse getTriviaHistory() {
        return null;
    }

}
