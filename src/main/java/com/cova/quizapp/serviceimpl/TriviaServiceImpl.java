package com.cova.quizapp.serviceimpl;

import com.cova.quizapp.data.TriviaHistoryRepo;
import com.cova.quizapp.data.TriviaRepository;
import com.cova.quizapp.model.entity.Trivia;
import com.cova.quizapp.model.entity.TriviaHistory;
import com.cova.quizapp.model.response.GetNewTriviaResponse;
import com.cova.quizapp.model.response.GetTriviaHistoryResponse;
import com.cova.quizapp.model.response.GetTriviaResultResponse;
import com.cova.quizapp.model.response.TriviaResponse;
import com.cova.quizapp.service.ITriviaService;
import com.cova.quizapp.service.IUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class TriviaServiceImpl implements ITriviaService {

    private TriviaRepository triviaRepository;;
    private TriviaHistoryRepo triviaHistoryRepo;
    private IUserService userService;

    static long totalAvailableTrivia;
    static List<Integer> totalTriviaAnswered ;
    static GetTriviaResultResponse triviaResult;
    static int BONUS_TRIVIA;
    static List<Long> triviaIds;
    static Map<Long, String> triviaCorrectAnswers;
    static Map<Long, String> userTriviaAnswers;
    static int correctAnswersCount;
    static int wrongAnswersCount;
    static boolean resultComputed;

    @Autowired
    public TriviaServiceImpl(TriviaRepository triviaRepository, TriviaHistoryRepo repo,
                             UserServiceImpl userService){
        this.triviaRepository = triviaRepository;
        triviaHistoryRepo = repo;
        this.userService = userService;
    }

    @Override
    public void startTrivia(Trivia.DifficultyLevel level) {
        resetTriviaSessionData();
        List<Trivia> triviaList = triviaRepository.findByDifficultyLevel(level).orElse(new ArrayList<>());
        triviaIds = triviaList.stream().map(Trivia::getId).collect(Collectors.toList());
        totalAvailableTrivia = triviaList.size();
        triviaList.forEach(T -> triviaCorrectAnswers.put(T.getId(), T.getAnswer()));
    }

    @Override
    public TriviaResponse getNextTrivia(Trivia.DifficultyLevel level, String previousAnswers, int previousTriviaId) {
        Trivia trivia;
        int nextTriviaId;
        if(totalAvailableTrivia == 0) startTrivia(level);
        if(totalTriviaAnswered.size() < level.getNumOfAllowedTriviaForLevel() && totalTriviaAnswered.size() < totalAvailableTrivia) {
            nextTriviaId = totalTriviaAnswered.size();
            trivia = triviaRepository.findByDifficultyLevelAndId(level, triviaIds.get(nextTriviaId)).orElse(null);
            userTriviaAnswers.put((long)previousTriviaId, previousAnswers);
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
        if (!resultComputed) {
            triviaResult.setDifficulty_level(level.name());
            calculateResult(level);
            double scorePerQos = 100.0 / level.getNumOfAllowedTriviaForLevel();
            double totalScore = scorePerQos * correctAnswersCount;
            double percentScore = totalScore / 100.0;

            triviaResult.updateTriviaSession(totalTriviaAnswered.size(), correctAnswersCount,
                    wrongAnswersCount, totalScore);
            triviaResult.setPerformance(triviaResult.calculatePerformance(percentScore).name());
            resultComputed = true;
            saveTriviaAsHistory(triviaResult);
        }
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
    }

    @Override
    public long saveTriviaAsHistory(GetTriviaResultResponse completedTrivia) {

        TriviaHistory triviaHistory = new TriviaHistory();
        triviaHistory.setTriviaDate(Timestamp.valueOf(LocalDateTime.now()));
        triviaHistory.setPassedTrivia(completedTrivia.getNum_passed_trivia());
        triviaHistory.setFailedTrivia(completedTrivia.getNum_failed_trivia());
        triviaHistory.setTotalScore(completedTrivia.getTotal_score());
        triviaHistory.setDifficultyLevel(Trivia.DifficultyLevel.valueOf(completedTrivia.getDifficulty_level().trim()));
        triviaHistory.setNumOfAnsweredTrivia(completedTrivia.getNum_of_answered_trivia());
        triviaHistory.setPerformance(GetTriviaResultResponse.Performance.valueOf(completedTrivia.getPerformance().trim()));
        triviaHistory.setAppUser(userService.getLoggedInUser());
        triviaHistory.setTotalTriviaGiven((int)totalAvailableTrivia);

        long lastInsertedId = triviaHistoryRepo.save(triviaHistory).getId();
        log.info("trivia history with id {} successfully saved", lastInsertedId);
        return lastInsertedId;
    }

    private void resetTriviaSessionData(){
        totalAvailableTrivia = 0;
        totalTriviaAnswered = new ArrayList<>();
        resultComputed = false;
        triviaResult = new GetTriviaResultResponse();
        triviaIds = new ArrayList<>();
        triviaCorrectAnswers = new HashMap<>();
        userTriviaAnswers = new HashMap<>();
        correctAnswersCount = 0;
        wrongAnswersCount = 0;
        resultComputed = false;
    }



    @Override
    public GetTriviaHistoryResponse getTriviaHistory() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();

        return null;
    }

}
