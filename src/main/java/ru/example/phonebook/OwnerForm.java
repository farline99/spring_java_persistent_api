package ru.example.phonebook;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class OwnerForm {
    @NotBlank(message = "Фамилия: заполните поле")
    @Size(max = 60, message = "Не более 60 символов")
    @Pattern(regexp = "[\\p{L}\\p{M}]+(?:[ '’-]+[\\p{L}\\p{M}]+)*",
            message = "Используйте буквы, пробел, дефис или апостроф")
    private String lastName = "";

    @NotBlank(message = "Имя: заполните поле")
    @Size(max = 60, message = "Не более 60 символов")
    @Pattern(regexp = "[\\p{L}\\p{M}]+(?:[ '’-]+[\\p{L}\\p{M}]+)*",
            message = "Используйте буквы, пробел, дефис или апостроф")
    private String firstName = "";

    @Size(max = 60, message = "Не более 60 символов")
    @Pattern(regexp = "(?:[\\p{L}\\p{M}]+(?:[ '’-]+[\\p{L}\\p{M}]+)*)?",
            message = "Используйте буквы, пробел, дефис или апостроф")
    private String patronymic = "";

    @NotBlank(message = "Укажите почтовый индекс")
    @Pattern(regexp = "(?=.*[\\p{L}\\p{M}0-9])[\\p{L}\\p{M}0-9 -]{3,12}",
            message = "Индекс: от 3 до 12 букв, цифр, пробелов или дефисов")
    private String postalCode = "";

    @NotBlank(message = "Заполните поле")
    @Size(max = 100, message = "Не более 100 символов")
    private String country = "";

    @NotBlank(message = "Заполните поле")
    @Size(max = 100, message = "Не более 100 символов")
    private String region = "";

    @NotBlank(message = "Заполните поле")
    @Size(max = 100, message = "Не более 100 символов")
    private String district = "";

    @NotBlank(message = "Заполните поле")
    @Size(max = 100, message = "Не более 100 символов")
    private String city = "";

    @NotBlank(message = "Заполните поле")
    @Size(max = 100, message = "Не более 100 символов")
    private String street = "";

    @NotBlank(message = "Укажите дом")
    @Size(max = 20, message = "Не более 20 символов")
    private String house = "";

    @Size(max = 20, message = "Не более 20 символов")
    private String apartment = "";

    @NotBlank(message = "Укажите номер телефона")
    @Pattern(regexp = "\\+?[0-9]{7,15}", message = "Введите от 7 до 15 цифр, можно начать с +")
    private String number = "";

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
