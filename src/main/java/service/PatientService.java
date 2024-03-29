package service;

import model.Employee;
import model.EmployeeStatus;
import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PatientRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findByPatientId(int patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    public List<Patient> findAllByDateOfBirthBetween(Date minDate, Date maxDate) {
        return patientRepository.findAllByDateOfBirthBetween(minDate, maxDate);
    }

    public List<Patient> findAllByDoctorDepartment(String department) {
        return patientRepository.findAllByDoctorDepartment(department);
    }

    public List<Patient> findAllByDoctorStatus(EmployeeStatus status) {
        return patientRepository.findAllByDoctorStatus(status);
    }

    public List<Patient> findAllByDoctorStatusIsOff() {
        return patientRepository.findAllByDoctorStatusIsOff();
    }

    public void addNewPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void updateInformation (int patientId, Patient patientUpdated) {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patientOK = optionalPatient.get();
            patientOK.setName(patientUpdated.getName());
            patientOK.setDateOfBirth(patientUpdated.getDateOfBirth());
            patientOK.setAdmittedBy(patientUpdated.getAdmittedBy());
            patientRepository.save(patientUpdated);
        }
    }
}