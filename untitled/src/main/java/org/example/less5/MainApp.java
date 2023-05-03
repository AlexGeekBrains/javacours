package org.example.less5;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = SessionFactoryUtils.getSessionFactoryUtils();

        try {
            StudentDao studentDao = new StudentDaoImpl(sessionFactoryUtils);
            List<Student> studentsList = studentDao.findAll();
            System.out.println(studentsList);
            Student ivan = new Student("Ivan", 3);
            Student masha = new Student("Masha", 4);
            System.out.println(studentDao.saveOrUpdate(ivan));
            System.out.println(studentDao.saveOrUpdate(masha));

            for (int i = 0; i < 1000; i++) {
                studentDao.saveOrUpdate(new Student("Student" + i, i));
            }
            Student student = studentDao.findById(2l);
            student.setMark(2);
            studentDao.saveOrUpdate(student);
            List<Student> students = studentDao.findAll();
            System.out.println(students);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}

