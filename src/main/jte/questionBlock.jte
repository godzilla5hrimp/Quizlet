@param org.godzilla5hrimp.quizlet.service.question.Question question

<script>
    webSocket = new WebSocket(url, protocols);
</script>

<div class="question-block bg-blue-500 justify-center align-center text-white font-bold py-4 px-4 rounded text-center h-full place-content-center">
    <p>
        ${question.getTextQuestion()}
    </p>
    @if(!question.getMediaQuestions().isEmpty())
        <img class="media-question w-4/5 h-3/6 rounded-lg h-80" src="${question.getMediaQuestions()}">
    @endif
</div>