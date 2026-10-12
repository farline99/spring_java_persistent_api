package ru.example.phonebook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PhoneForm {
    @NotBlank(message = "Укажите номер телефона")
    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Введите от 7 до 15 цифр, можно начать с +")
    private String number = "";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
