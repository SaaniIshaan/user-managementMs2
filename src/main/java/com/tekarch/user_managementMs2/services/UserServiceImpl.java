package com.tekarch.user_managementMs2.services;

import com.tekarch.user_managementMs2.models.AccountDTO;
import com.tekarch.user_managementMs2.models.User;
import com.tekarch.user_managementMs2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${accounts.ms.url}")
    String url;

 //   String ACCOUNT_SERVICE_URL = "http://localhost:8081/accounts";
 //   String FUND_TRANSFER_URL = "http://localhost:8082/transactions";

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }

    @Override
    public List<AccountDTO> getLinkedAccounts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String url1 = url +"?userId=" +userId;
        return List.of(restTemplate.getForObject(url1, AccountDTO[].class));
    }

    @Override
    public AccountDTO getAccountDetails(Long userId, Long accountId) {
        String url1 = url + accountId;
        return restTemplate.getForObject(url1, AccountDTO.class);
    }

    @Override
    public void addAccount(Long userId, AccountDTO accountDTO) {
        String url1 = url;
        accountDTO.setUserId(userId);
        restTemplate.postForObject(url1, accountDTO, AccountDTO.class);
    }

    @Override
    public void updateAccount(Long userId, Long accountId, AccountDTO accountDTO) {
        String url1 = url + accountId;
        restTemplate.put(url1, accountDTO);
    }

    @Override
    public void deleteAccount(Long userId, Long accountId) {
        String url1 = url + accountId;
        restTemplate.delete(url1);
    }
}
