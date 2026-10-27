package com.placement.service;

import com.placement.entity.Student;
import com.placement.exception.BadRequestException;
import com.placement.exception.ResourceNotFoundException;
import com.placement.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
    }

    public Student createStudent(Student student) {
        validateStudent(student);

        if (studentRepository.existsByRollNumber(student.getRollNumber().trim())) {
            throw new BadRequestException("Student with Roll Number '" + student.getRollNumber() + "' already exists");
        }
        if (studentRepository.existsByEmail(student.getEmail().trim())) {
            throw new BadRequestException("Student with Email '" + student.getEmail() + "' already exists");
        }

        student.setRollNumber(student.getRollNumber().trim());
        student.setName(student.getName().trim());
        student.setEmail(student.getEmail().trim().toLowerCase());
        student.setDepartment(student.getDepartment().trim());

        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedData) {
        validateStudent(updatedData);
        Student existing = getStudentById(id);

        if (!existing.getRollNumber().equalsIgnoreCase(updatedData.getRollNumber().trim())
                && studentRepository.existsByRollNumber(updatedData.getRollNumber().trim())) {
            throw new BadRequestException("Roll Number '" + updatedData.getRollNumber() + "' is already taken by another student");
        }

        if (!existing.getEmail().equalsIgnoreCase(updatedData.getEmail().trim())
                && studentRepository.existsByEmail(updatedData.getEmail().trim())) {
            throw new BadRequestException("Email '" + updatedData.getEmail() + "' is already taken by another student");
        }

        existing.setRollNumber(updatedData.getRollNumber().trim());
        existing.setName(updatedData.getName().trim());
        existing.setEmail(updatedData.getEmail().trim().toLowerCase());
        existing.setDepartment(updatedData.getDepartment().trim());
        existing.setCgpa(updatedData.getCgpa());
        existing.setGraduationYear(updatedData.getGraduationYear());
        existing.setPhone(updatedData.getPhone());
        existing.setSkills(updatedData.getSkills());

        return studentRepository.save(existing);
    }

    public void deleteStudent(Long id) {
        Student existing = getStudentById(id);
        studentRepository.delete(existing);
    }

    private void validateStudent(Student s) {
        if (s == null) {
            throw new BadRequestException("Student payload cannot be empty");
        }
        if (s.getRollNumber() == null || s.getRollNumber().trim().isEmpty()) {
            throw new BadRequestException("Roll number is required");
        }
        if (s.getName() == null || s.getName().trim().isEmpty()) {
            throw new BadRequestException("Full name is required");
        }
        if (s.getEmail() == null || !s.getEmail().contains("@")) {
            throw new BadRequestException("A valid email address is required");
        }
        if (s.getDepartment() == null || s.getDepartment().trim().isEmpty()) {
            throw new BadRequestException("Department is required");
        }
        if (s.getCgpa() == null || s.getCgpa() < 0.0 || s.getCgpa() > 10.0) {
            throw new BadRequestException("CGPA must be between 0.0 and 10.0");
        }
        if (s.getGraduationYear() == null || s.getGraduationYear() < 2000) {
            throw new BadRequestException("Valid graduation year is required");
        }
    }
}
