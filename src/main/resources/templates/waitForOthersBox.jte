@param int rightAnswer
@param int userAnswer

<div data-te-animation-init data-te-animation-start="onLoad" data-te-animation="[fade-in-up]">
    @if(userAnswer == rightAnswer)
        <p class="right-answer">
            <%-- TODO: make all string localized --%>
            You were right, waiting for others
        </p>
    @else
        <p class="wrong-answer">
            You were not right, waiting for others
        </p>
    @endif
    <button class="others-results">

    </button>
</div>
