@param org.godzilla5hrimp.quizlet.service.answer.Answer answer
@param int questionNumber

<button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded place-content-center justify-center mb-4 flex flex-col items-center" id="${questionNumber}-item">
    <svg version="1.0" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
         width="50px" height="50px" viewBox="0 0 64 64" enable-background="new 0 0 64 64" xml:space="preserve">
        <path fill="#FFFF" d="${org.godzilla5hrimp.quizlet.service.answer.Answer.Shape.drawShapeSvg(answer.getAnswerShape())}" />
    </svg>
    ${answer.getTextAnswer()}
</button>
@if(answer.isRightAnswer())
    <script>
        const rightButton = document.getElementById("${questionNumber}-item");
        rightButton.addEventListener('click', function(){alert("You were right!");}, false);
    </script>
@endif

