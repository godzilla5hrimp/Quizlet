package org.godzilla5hrimp.quizlet.service.answer;

import com.fasterxml.jackson.annotation.JsonValue;
import gg.jte.TemplateEngine;
import lombok.Getter;
import lombok.Setter;

import org.godzilla5hrimp.quizlet.utils.ColourScheme;

@Getter
@Setter
public class Answer {
    @JsonValue
    private String textAnswer;
    @JsonValue
    private ColourScheme.Colour answerColour;
    @JsonValue
    private Shape answerShape;
    @JsonValue
    private Boolean isRightAnswer;

    //TODO: add jackson annotations to be able to use json values for the question editing
    public Answer(final String textAnswer, final Boolean isRightAnswer) {
        this.textAnswer = textAnswer;
        this.answerShape = Shape.STAR;
        this.isRightAnswer = isRightAnswer;
    }

    public void setRightAnswer(boolean isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
    }

    public boolean isRightAnswer() {
        return isRightAnswer;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public ColourScheme.Colour getAnswerColour() {
        return answerColour;
    }

    public void setAnswerColour(ColourScheme.Colour answerColour) {
        this.answerColour = answerColour;
    }

    public Shape getAnswerShape() {
        return answerShape;
    }

    public void setAnswerShape(Shape answerShape) {
        this.answerShape = answerShape;
    }

    public void drawAnswerBlock(final TemplateEngine templateEngine, final int answerId) {

    }

    public enum Shape {
        STAR,
        SQUARE,
        CIRCLE,
        TRIANGLE,
        CUSTOM;

        public static String drawShapeSvg(final Shape shape) {
            String resultSvgString;
            switch (shape) {
                case STAR:
                    resultSvgString = "M62.799,23.737c-0.47-1.399-1.681-2.419-3.139-2.642l-16.969-2.593L35.069,2.265 C34.419,0.881,33.03,0,31.504,0c-1.527,0-2.915,0.881-3.565,2.265l-7.623,16.238L3.347,21.096c-1.458,0.223-2.669,1.242-3.138,2.642 c-0.469,1.4-0.115,2.942,0.916,4l12.392,12.707l-2.935,17.977c-0.242,1.488,0.389,2.984,1.62,3.854 c1.23,0.87,2.854,0.958,4.177,0.228l15.126-8.365l15.126,8.365c0.597,0.33,1.254,0.492,1.908,0.492c0.796,0,1.592-0.242,2.269-0.72 c1.231-0.869,1.861-2.365,1.619-3.854l-2.935-17.977l12.393-12.707C62.914,26.68,63.268,25.138,62.799,23.737z";
                    break;
                default:
                    resultSvgString = "";
                    break;
            }
            return resultSvgString;
        }
    }
}
