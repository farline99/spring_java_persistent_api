package ru.example.phonebook;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SearchForm {
    @Size(max = 60, message = "Фамилия: не более 60 символов")
    private String lastName = "";

    @Size(max = 100, message = "Город: не более 100 символов")
    private String city = "";

    @Pattern(regexp = "(?:\\+?[0-9]{1,15})?",
            message = "Поиск телефона: от 1 до 15 цифр и необязательный + в начале")
    private String number = "";

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
