package org.example.less5;

import java.util.List;

public interface StudentDao {
    Student findById(Long id);

    List<Student> findAll();

    void deleteById(Long id);

    Student saveOrUpdate(Student student);
}
