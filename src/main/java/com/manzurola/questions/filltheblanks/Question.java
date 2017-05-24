package com.manzurola.questions.filltheblanks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guym on 16/05/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question {

    private final String id;
    private final String body;
    private final String blankToken; // so client may parse body
    private final List<String> answerKey; // list index matches blank index, each blank may have more than one answer
    private final String notes;
    private final String subject;
    private final String solution;
    private final String originalSentence;
    private final String version; // to reference the parser version

    public Question(String sentence) {
        this(new Parser().parseSentence(sentence));
    }

    public Question(String sentence, String subject, String notes) {
        this(new Parser().parseSentence(sentence).setSubject(subject).setNotes(notes));
    }

    protected Question(String body,
                    List<String> answerKey,
                    String blankToken,
                    String solution,
                    String originalSentence,
                    String version) {
        this(UUID.randomUUID().toString(), body, answerKey, blankToken, "", "", solution, originalSentence, version);
    }

    protected Question(String body,
                    List<String> answerKey,
                    String blankToken,
                    String subject,
                    String notes,
                    String solution,
                    String originalSentence,
                    String version) {
        this(UUID.randomUUID().toString(), body, answerKey, blankToken, subject, notes, solution, originalSentence, version);
    }

    protected Question(String id,
                    String body,
                    List<String> answerKey,
                    String blankToken,
                    String subject,
                    String notes,
                    String solution,
                    String originalSentence,
                    String version) {
        this.id = id;
        this.body = body;
        this.answerKey = Collections.unmodifiableList(answerKey);
        this.blankToken = blankToken;
        this.subject = subject;
        this.notes = notes;
        this.solution = solution;
        this.originalSentence = originalSentence;
        this.version = version;
    }

    public Question(Question copy) {
        this(copy.id, copy.body, copy.answerKey, copy.blankToken, copy.subject, copy.notes, copy.solution, copy.originalSentence, copy.version);
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonIgnore
    public Question setSubject(String subject) {
        return new Question(this.id, this.body, this.answerKey, this.blankToken, subject, this.notes, this.solution, this.originalSentence, this.version);
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("answerKey")
    public List<String> getAnswerKey() {
        return new ArrayList<>(answerKey);
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonIgnore
    public Question setNotes(String notes) {
        return new Question(this.id, this.body, this.answerKey, this.blankToken, this.subject, notes, this.solution, this.originalSentence, this.version);
    }

    @JsonProperty("blankToken")
    public String getBlankToken() {
        return blankToken;
    }

    @JsonProperty("solution")
    public String getSolution() {
        return solution;
    }

    @JsonProperty("originalSentence")
    public String getOriginalSentence() {
        return originalSentence;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", blankToken='" + blankToken + '\'' +
                ", answerKey=" + answerKey +
                ", notes='" + notes + '\'' +
                ", subject='" + subject + '\'' +
                ", solution='" + solution + '\'' +
                ", originalSentence='" + originalSentence + '\'' +
                ", version='" + version + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(body, question.body) &&
                Objects.equals(blankToken, question.blankToken) &&
                Objects.equals(answerKey, question.answerKey) &&
                Objects.equals(notes, question.notes) &&
                Objects.equals(subject, question.subject) &&
                Objects.equals(solution, question.solution) &&
                Objects.equals(originalSentence, question.originalSentence) &&
                Objects.equals(version, question.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, blankToken, answerKey, notes, subject, solution, originalSentence, version);
    }

    private static class Parser {
        private static final String VERSION = "v1";
        private static final String BLANK_BODY_PLACEHOLDER = "<?>";
        private static final Pattern BODY_PATTERN = Pattern.compile("(\\$\\((.*?)\\))");

        public Question parseSentence(String sentence) {
            StringBuffer bodyBuffer = new StringBuffer();
            List<String> actualAnswer = new ArrayList<>();

            Matcher matcher = BODY_PATTERN.matcher(sentence);
            while (matcher.find()) {
                matcher.appendReplacement(bodyBuffer, matcher.group(0).replaceFirst(Pattern.quote(matcher.group(1)), Matcher.quoteReplacement(BLANK_BODY_PLACEHOLDER)));
                String answer = matcher.group(2);
                actualAnswer.add(answer);
            }
            matcher.appendTail(bodyBuffer);

            String actualBody = bodyBuffer.toString();
            return new Question(actualBody, actualAnswer, BLANK_BODY_PLACEHOLDER, createSolution(actualBody, actualAnswer), sentence, VERSION);
        }

        private String createSolution(String body, List<String> answerKey) {
            Map<String, String> patternReplacements = new HashMap<>();
            patternReplacements.put(BLANK_BODY_PLACEHOLDER, "");
            patternReplacements.put(" " + BLANK_BODY_PLACEHOLDER + " ", " ");
            final Pattern pattern = Pattern.compile(" " + Pattern.quote(BLANK_BODY_PLACEHOLDER) + " |" + Pattern.quote(BLANK_BODY_PLACEHOLDER));
            String solution = body;
            for (String answer : answerKey) {
                Matcher matcher = pattern.matcher(solution);
                if (matcher.find()) {
                    String replacement = answer.isEmpty() ? patternReplacements.get(matcher.group(0)) : answer;
                    solution = matcher.replaceFirst(replacement);
                }
            }
            return solution;
        }
    }
}
