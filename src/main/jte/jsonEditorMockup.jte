@param org.godzilla5hrimp.quizlet.service.question.Question question
@param java.util.List<org.godzilla5hrimp.quizlet.service.answer.Answer> answerList
@param String jsonEditorSchema

<html lang="en">
    <head>
        <script src="https://cdn.jsdelivr.net/npm/@json-editor/json-editor@latest/dist/jsoneditor.min.js"></script>
        <script>
            const element = document.getElementById('editor-holder');
            //TODO: add data from  configured classes above
            const editor = new JSONEditor(element, {
                schema: ${jsonEditorSchema},
                theme:'tailwind'
            });
            editor.enable();
        </script>
    </head>
    <body>
        <div id="editor-holder">

        </div>
    </body>
</html>