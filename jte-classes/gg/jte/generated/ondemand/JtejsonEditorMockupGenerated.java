package gg.jte.generated.ondemand;
public final class JtejsonEditorMockupGenerated {
	public static final String JTE_NAME = "jsonEditorMockup.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,9,9,11,11,11,22,22,22,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, org.godzilla5hrimp.quizlet.service.question.Question question, java.util.List<org.godzilla5hrimp.quizlet.service.answer.Answer> answerList, String jsonEditorSchema) {
		jteOutput.writeContent("\n<html lang=\"en\">\n    <head>\n        <script src=\"https://cdn.jsdelivr.net/npm/@json-editor/json-editor@latest/dist/jsoneditor.min.js\"></script>\n        <script>    \n            const element = document.getElementById('editor-holder');\n            ");
		jteOutput.writeContent("\n            const editor = new JSONEditor(element, {\n                schema: ");
		jteOutput.setContext("script", null);
		jteOutput.writeUserContent(jsonEditorSchema);
		jteOutput.writeContent(",\n                theme:'tailwind'\n            });\n            editor.enable();\n        </script>\n    </head>\n    <body>\n        <div id=\"editor-holder\">\n\n        </div>\n    </body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		org.godzilla5hrimp.quizlet.service.question.Question question = (org.godzilla5hrimp.quizlet.service.question.Question)params.get("question");
		java.util.List<org.godzilla5hrimp.quizlet.service.answer.Answer> answerList = (java.util.List<org.godzilla5hrimp.quizlet.service.answer.Answer>)params.get("answerList");
		String jsonEditorSchema = (String)params.get("jsonEditorSchema");
		render(jteOutput, jteHtmlInterceptor, question, answerList, jsonEditorSchema);
	}
}
