@param org.godzilla5hrimp.quizlet.service.question.Question question
@param java.util.List<org.godzilla5hrimp.quizlet.service.answer.Answer> answerList
@param org.godzilla5hrimp.quizlet.service.quiz.Quiz quiz
@param org.godzilla5hrimp.quizlet.service.quizSession.QuizSession quizSession

<html lang="en">
    <head>
        <title></title>
        <link rel="stylesheet" href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css">
        <script src="https://cdn.jsdelivr.net/npm/@json-editor/json-editor@latest/dist/jsoneditor.min.js"></script>
       
    </head>
    <div class="quiz-step flex flex-col gap-3 h-screen justify-stretch">
        <div class="quiz-connected flex flex-row h-screen">
            ${quizSession.getUsersConnected().size()}
        </div> 
        @template.questionBlock(question = question)
        <div class="answer-block flex flex-row gap-3 place-content-center grid grid-cols-2">
            @for(int i = 0; i < answerList.size(); i++)
               @template.answerButton(answer = answerList.get(i), questionNumber = i)
            @endfor
        </div>
    </div>>

</html>
