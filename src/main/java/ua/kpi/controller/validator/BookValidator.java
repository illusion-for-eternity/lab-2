package ua.kpi.controller.validator;

import ua.kpi.model.entity.Book;
import ua.kpi.utils.AttributesHolder;
import ua.kpi.utils.ErrorsMessages;

import java.util.regex.Pattern;

public class BookValidator implements Validator<Book> {

    private final Pattern textPattern;

    public BookValidator() {
        textPattern = Pattern.compile(RegExp.NAME);
    }

    @Override
    public boolean validate(Book book, Errors errors) {
        if (book != null) {

            if (book.getTitle() == null ||
                    !textPattern.matcher(book.getTitle()).matches()) {
                reject(errors, AttributesHolder.TITLE,
                        ErrorsMessages.TITLE_INVALID);
            }

            if (book.getAuthor() == null ||
                    !textPattern.matcher(book.getAuthor()).matches()) {
                reject(errors, AttributesHolder.AUTHOR,
                        ErrorsMessages.AUTHOR_INVALID);
            }

            if (book.getGenre() == null ||
                    !textPattern.matcher(book.getGenre()).matches()) {
                reject(errors, AttributesHolder.GENRE,
                        ErrorsMessages.GENRE_INVALID);
            }

        } else {
            reject(errors, AttributesHolder.BOOK,
                    ErrorsMessages.NOT_EXCEPTED_ERROR);
        }

        return !errors.hasError();
    }

    private void reject(Errors errors, String attribute, String message){
        errors.addMessage(attribute, message);
        errors.setResult(false);
    }
}