package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue {
    public int id;
    private boolean status;
    private int countComment; // Кол-во комментариев
    private Author author; // Автор
    private Set<Label> label; // Маркировка
    private String projects; // Проект
    private String milestones; // Вехи
    private Set<Author> assignees; // Правопреемник
    private String creationDate; // Дата создания
    private String updateDate; // Дата обновления
}
