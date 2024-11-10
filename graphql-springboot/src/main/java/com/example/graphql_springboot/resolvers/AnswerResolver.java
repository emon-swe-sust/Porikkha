package com.example.graphql_springboot.resolvers;

import com.example.graphql_springboot.model.Answer;
import com.example.graphql_springboot.model.MCQ;
import com.example.graphql_springboot.model.MCQAnswerInput;
import com.example.graphql_springboot.repository.AnswerRepository;
import com.example.graphql_springboot.repository.MCQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnswerResolver {
    @Autowired
    public AnswerRepository answerRepository;

    @Autowired
    public MCQRepository mcqRepository;

    @MutationMapping
    public Answer addAnswer(@Argument String examId, @Argument String examineeId, @Argument List<MCQAnswerInput> answers) {
        List<String> rightAnswers = new ArrayList<>();
        List<String> wrongAnswers = new ArrayList<>();

        List<MCQ> mcqs = mcqRepository.findByExamId(examId);

        for(MCQAnswerInput answerInput: answers) {
            mcqs.stream()
                    .filter(mcq -> mcq.getId().equals(answerInput.getMcqId()))
                    .findFirst()
                    .ifPresent(mcq -> {
                        if(mcq.getAnswer().equals(answerInput.getSelectedAnswer())) {
                            rightAnswers.add(mcq.getId());
                        } else {
                            wrongAnswers.add(mcq.getId());
                        }
                    });
        }

        Answer answer = new Answer();
        answer.setExamId(examId);
        answer.setExamineeId(examineeId);
        answer.setRightAnswers(rightAnswers);
        answer.setWrongAnswers(wrongAnswers);
        return answer;
    }

}