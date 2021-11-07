package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Author;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IssueManagerTest {
    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);
    private Author author1 = new Author(1, "Michael1993");
    private Author author2 = new Author(2, "marcphilipp");
    private Author author3 = new Author(3, "Michaeli71");
    private Author author4 = new Author(4, "jlink");
    private Author author5 = new Author(5, "kriegfrj");
    private Label label1 = new Label(1, "Add new AnnotationBasedArgumentsProvider");
    private Label label2 = new Label(2, "Add JBang catalog");
    private Label label3 = new Label(3, "Make @Timeout more valuable");
    private Label label4 = new Label(4, "Add capability to get all configuration parameters with given prefix");
    private Label label5 = new Label(5, "[osgi] API Guardian should be (optionally) imported");
    private Set<Label> labels = new HashSet<>();
    private Set<Author> assignees = new HashSet<>();
    private Issue issue1 = new Issue(1, true, 3, author1, labels, "None yet", "General Backlog", assignees, "23.05.21", "08.06.21");
    private Issue issue2 = new Issue(2, false, 7, author2, labels, "None yet", "General Backlog", assignees, "15.05.21", "08,06.21");
    private Issue issue3 = new Issue(3, true, 1, author3, labels, "None yet", "General Backlog", assignees, "07.05.21", "08,06.21");
    private Issue issue4 = new Issue(4, false, 6, author4, labels, "None yet", "General Backlog", assignees, "9.01.21", "08,06.21");
    private Issue issue5 = new Issue(5, true, 3, author5, labels, "None yet", "General Backlog", assignees, "08.02.21", "08,06.21");

    @Nested
    class MultipleIssue {
        @BeforeEach
        public void setting() {
            labels.add(label1);
            labels.add(label2);
            labels.add(label4);
            labels.add(label5);
            assignees.add(author2);
            repository.add(issue1);
            repository.add(issue2);
            repository.add(issue3);
            repository.add(issue4);
            repository.add(issue5);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelYes() {
            List<Issue> actual = manager.filterLabel(label2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            expected.add(issue4);
            expected.add(issue5);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author1);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeYes() {
            List<Issue> actual = manager.filterAssignee(author2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            expected.add(issue4);
            expected.add(issue5);
            assertEquals(expected, actual);
        }
    }


    @Nested
    class Empty {
        @Test
        void shouldFilterAuthorNo() {
            List<Issue> actual = manager.filterAuthor(author5);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author1);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

    }

    @Nested
    class SingleIssue {
        @BeforeEach
        public void setting() {
            labels.add(label1);
            assignees.add(author5);
            manager.add(issue1);

        }

        @Test
        void shouldFilterAuthorNo() {
            List<Issue> actual = manager.filterAuthor(author4);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelYes() {
            List<Issue> actual = manager.filterLabel(label1);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author2);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeYes() {
            List<Issue> actual = manager.filterAssignee(author5);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

    }
}