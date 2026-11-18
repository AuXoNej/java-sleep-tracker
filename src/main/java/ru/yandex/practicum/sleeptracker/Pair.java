package ru.yandex.practicum.sleeptracker;

public record Pair<L, R>(L key, R value) {

    public L getLeft() {
        return key;
    }

    public R getRight() {
        return value;
    }

    @Override
    public String toString() {
        if (key == null && value == null) {
            return "()";
        }
        return "(" + key + " - " + value + ")";
    }

}