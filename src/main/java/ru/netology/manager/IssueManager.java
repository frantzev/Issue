package ru.netology.manager;

import ru.netology.domain.Author;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager() {
    }

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue Issue) {
        repository.add(Issue);
    }

    public List<Issue> getAll() {
        return repository.getAll();
    }


    // TODO Фильтраци по имени автора
    public List<Issue> filterAuthor(Author author) {
        List<Issue> Issue = repository.getAll();
        Predicate<Issue> predicate = obj -> obj.getAuthor().equals(author);
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (predicate.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    // TODO Фильтраци по маркировки
    public List<Issue> filterLabel(Label label) {
        List<Issue> Issue = repository.getAll();
        Predicate<Issue> predicate = obj -> (obj.getLabel()).contains(label);
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (predicate.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }

    // TODO Фильтраци по назначению
    public List<Issue> filterAssignee(Author author) {
        List<Issue> Issue = repository.getAll();
        Predicate<Issue> predicate = obj -> (obj.getAssignees()).contains(author);
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.getAll()) {
            if (predicate.test(issue)) {
                result.add(issue);
            }
        }
        return result;
    }
}
