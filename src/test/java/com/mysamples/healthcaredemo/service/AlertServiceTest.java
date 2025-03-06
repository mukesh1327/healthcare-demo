// package com.mysamples.healthcaredemo.service;

// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// @ExtendWith(MockitoExtension.class)
// public class AlertServiceTest {

//     @Mock
//     private AlertRepository alertRepository;

//     @InjectMocks
//     private AlertService alertService;

//     @Test
//     public void testCreateAlert() {
//         Alert alert = new Alert(1L, "High CPU Usage", "CRITICAL");
//         when(alertRepository.save(any(Alert.class))).thenReturn(alert);

//         Alert savedAlert = alertService.createAlert(alert);
//         assertNotNull(savedAlert);
//         assertEquals("High CPU Usage", savedAlert.getMessage());
//     }
// }
