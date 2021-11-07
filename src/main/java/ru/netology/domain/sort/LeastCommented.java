package ru.netology.domain.sort;

import ru.netology.domain.Issue;

import java.util.Comparator;

public class LeastCommented implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        return o1.getCountComment() - o2.getCountComment();
    }
}
