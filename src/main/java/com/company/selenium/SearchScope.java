package com.company.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by anjalhussan on 10/22/16.
 */
public interface SearchScope extends SearchContext{

    default Element findElement(Supplier<By> by) {
        return new Element(findElement(by.get()));
    }

    default Stream<Element> findElements(Supplier<By> by) {
        return findElements(by.get()).stream().map(Element::new);
    }

    default List<WebElement> findElementsList(Supplier<By> by) {
        return findElements(by.get());
    }


    default List<Element> elementList(Supplier<By> by) {
        return findElements(by.get()).stream().map(Element::new).collect(Collectors.toList());
    }


    default Optional<Element> optionalElement(Supplier<By> by) {
        try {
            return Optional.of(findElement(by));
        } catch (NoSuchElementException ignored) {
            return Optional.empty();
        }
    }

    default boolean isPresent(Supplier<By> by) {
        return optionalElement(by).isPresent();
    }
}
