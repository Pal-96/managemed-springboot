package com.managemed.managemedapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.managemed.managemedapp.dto.DashboardStats;
import com.managemed.managemedapp.model.User;
import com.managemed.managemedapp.repository.ReportRepository;
import com.managemed.managemedapp.repository.UserRepository;

@Service
public class DashboardService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    public DashboardService(UserRepository userRepository,
                            ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    public DashboardStats getStats() {
        return new DashboardStats(
            reportRepository.countUsersByRole("Customer"),
            reportRepository.countOrdersByStatus("COMPLETED"),
            reportRepository.countAvailableProducts()
        );
    }

    public Optional<String> getFullName(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(u -> u.getFirstname() + " " + u.getLastname());
    }
    
}
