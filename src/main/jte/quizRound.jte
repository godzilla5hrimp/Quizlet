@param org.godzilla5hrimp.quizlet.service.question.Question question
@param java.util.List<org.godzilla5hrimp.quizlet.service.answer.Answer> answerList
@param org.godzilla5hrimp.quizlet.service.quiz.Quiz quiz
@param org.godzilla5hrimp.quizlet.service.quizSession.QuizSession quizSession
@param String currentDomain

<html lang="en">
    <head>
        <title></title>
        <link rel="stylesheet" href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css">
        <script src="https://cdn.jsdelivr.net/npm/@json-editor/json-editor@latest/dist/jsoneditor.min.js"></script>
        <script>
            let usersConnected = 0;
            let currentQuestion = 0;
            let id = id => document.getElementById(id);
            //Establish the WebSocket connection and set up event handlers
            let domain = "${currentDomain}";
            let ws = new WebSocket('ws://' + domain +'/quiz/${quiz.getId()}');
            ws.onopen = () => console.log("ws connected");
            ws.onmessage = msg => {
                console.log("received from server: " + msg.data);
                if (msg.data.includes('usersConnected')) {
                    usersConnected = JSON.parse(msg.data).usersConnected;
                    document.getElementById("userCounter").innerHTML = usersConnected;
                    console.log("user added");
                }
            }
        </script>
    </head>
    <button id="userCounter" class="userCounter font-semibold bg-blue-500 hover:bg-blue-700 rounded w-10 h-10 text-center inline-block align-middle">
        0
    </button>
    <div class="quiz-step flex flex-col gap-3 h-screen justify-stretch">
        <div class="question-block">
            @template.questionBlock(question = question)
        </div>
        <div class="answer-block flex flex-row gap-3 place-content-center grid grid-cols-2">
            @for(int i = 0; i < answerList.size(); i++)
               @template.answerButton(answer = answerList.get(i), questionNumber = i)
            @endfor
        </div>
    </div>>

</html>
