package br.com.digitalhouse.digital.pimarvel.model.faq;

import android.os.Parcel;
import android.os.Parcelable;

public class Faq implements Parcelable {

    private String question;
    private String answer;

    public Faq() {
    }

    public Faq(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    protected Faq(Parcel in) {
        question = in.readString();
        answer = in.readString();
    }

    public static final Creator<Faq> CREATOR = new Creator<Faq>() {
        @Override
        public Faq createFromParcel(Parcel in) {
            return new Faq(in);
        }

        @Override
        public Faq[] newArray(int size) {
            return new Faq[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(answer);
    }
}
